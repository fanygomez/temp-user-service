package com.bci.user_service.service.impl;

import com.bci.user_service.components.exceptions.DuplicateDataException;
import com.bci.user_service.components.exceptions.GeneralException;
import com.bci.user_service.components.jwt.JwtTokenService;
import com.bci.user_service.domain.models.Phone;
import com.bci.user_service.domain.models.User;
import com.bci.user_service.domain.repositories.IUserRepository;
import com.bci.user_service.dto.user.PhoneDto;
import com.bci.user_service.dto.user.UserGetRespDto;
import com.bci.user_service.dto.user.UserReqDto;
import com.bci.user_service.dto.user.UserRespDto;
import com.bci.user_service.service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(PasswordEncoder passwordEncoder, IUserRepository userRepository, JwtTokenService jwtTokenService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRespDto saveUser(UserReqDto reqDto) {
        log.info("UserServiceImpl.saveUser INIT");
        userValidation(reqDto);

        User user = new User();
        user.setName(reqDto.getName());
        user.setEmail(reqDto.getEmail());
        user.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        user.setPhones(mapAndGetPhones(reqDto, user));
        user.setLastLogin(LocalDateTime.now());

        var userSaved = userRepository.save(user);
        userSaved.setToken(jwtTokenService.generateToken(userSaved.getId()));

        userRepository.updateToken(userSaved.getToken(), userSaved.getId());

        log.info("UserServiceImpl.saveUser END");
        return mapUserEntityToUserRespDto(userSaved);
    }

    @Override
    public UserGetRespDto getUserById(UUID id) {
        log.info("UserServiceImpl.getUserById {}", id);
        User userFound =  userRepository.findById(id).orElseThrow(
                () ->  new GeneralException("No encontro data con ese identificador", HttpStatus.NOT_FOUND));

        return modelMapper.map(userFound, UserGetRespDto.class);
    }

    @Override
    public User findByEmail(String email) {
        log.info("UserServiceImpl.findByEmail INIT {}", email);
        var userOpt =  userRepository.findByEmailAndIsActiveIsTrue(email);

        if (userOpt.isEmpty())
            throw new GeneralException("Credenciales inválidas",HttpStatus.UNAUTHORIZED);

        return userOpt.get();
    }
    @Override
    public void updateUserTokenByUserId(UUID userId, String newToken) {
        log.info("UserServiceImpl.updateUserTokenByUserId {}",userId);
        userRepository.updateTokenAndLastLogin(newToken,LocalDateTime.now(), userId );
    }

    private static UserRespDto mapUserEntityToUserRespDto(User userAfterToken) {
        UserRespDto response = new UserRespDto();
        response.setId(userAfterToken.getId());
        response.setCreated(userAfterToken.getCreated());
        response.setModified(userAfterToken.getModified());
        response.setLastLogin(userAfterToken.getLastLogin());
        response.setToken(userAfterToken.getToken());
        response.setIsActive(userAfterToken.getActive());
        return response;
    }

    private static List<Phone> mapAndGetPhones(UserReqDto reqDto, User user) {
        return reqDto.getPhones().stream().map(phoneDto -> {
            Phone phone = new Phone();
            phone.setNumber(phoneDto.getNumber());
            phone.setCityCode(phoneDto.getCityCode());
            phone.setCountryCode(phoneDto.getCountryCode());
            phone.setUser(user);
            return phone;
        }).toList();
    }

    private void userValidation(UserReqDto reqDto){
        if(userRepository.findByEmailAndIsActiveIsTrue(reqDto.getEmail()).isPresent()){
            throw new DuplicateDataException("El usuario ya existe.");
        }
        validateUniquePhones(reqDto.getPhones());
    }
    private void validateUniquePhones(List<PhoneDto> phones) {
        Set<String> uniquePhones = new HashSet<>();
        for (PhoneDto phone : phones) {
            if (!uniquePhones.add(phone.getNumber())) {
                throw new DuplicateDataException("No, no es posible registrar teléfonos duplicados. Verifique e intente de nuevo.");
            }
        }
    }
}

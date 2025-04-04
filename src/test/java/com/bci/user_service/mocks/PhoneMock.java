package com.bci.user_service.mocks;

import com.bci.user_service.domain.models.Phone;
import com.bci.user_service.dto.user.PhoneDto;

public class PhoneMock {
    public static Phone mockPhoneEntity(){
        var phone = new Phone();
        phone.setNumber("72908945");
        phone.setCityCode("1614");
        phone.setCountryCode("503");
        return phone;
    }
    public static PhoneDto mockPhoneDto(){
        var phone = new PhoneDto();
        phone.setNumber("72908945");
        phone.setCityCode("1614");
        phone.setCountryCode("503");
        return phone;
    }
}

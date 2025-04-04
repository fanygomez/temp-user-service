package com.bci.user_service.mocks;

import com.bci.user_service.domain.models.Phone;
public class PhoneMock {
    public static Phone mockPhoneEntity(){
        var phone = new Phone();
        phone.setNumber("72908945");
        phone.setCityCode("1614");
        phone.setCountryCode("503");
        return phone;
    }
}

package com.nisumexercise.apiUsers.utils;

import com.nisumexercise.apiUsers.dto.PhoneDto;
import com.nisumexercise.apiUsers.entity.Phone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MethodService {

    public List<Phone> mapToPhoneEntity(List<PhoneDto> phones) {
        return phones.stream().map(phoneDto -> {
            Phone phone = new Phone();
            phone.setNumber(phoneDto.getNumber());
            phone.setCitycode(phoneDto.getCitycode());
            phone.setContrycode(phoneDto.getContrycode());
            return phone;
        }).collect(Collectors.toList());
    }
}

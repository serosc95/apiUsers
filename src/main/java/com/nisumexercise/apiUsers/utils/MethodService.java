package com.nisumexercise.apiUsers.utils;

import com.nisumexercise.apiUsers.dto.PhoneDto;
import com.nisumexercise.apiUsers.entity.Phone;
import com.nisumexercise.apiUsers.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MethodService {

    public List<Phone> mapToPhoneEntity(List<PhoneDto> phones, User user) {
        return phones.stream().map(phoneDto -> {
            return Phone.builder()
                    .number(phoneDto.getNumber())
                    .citycode(phoneDto.getCitycode())
                    .contrycode(phoneDto.getContrycode())
                    .user(user)
                    .build();
        }).collect(Collectors.toList());
    }

    public Map<String, Object> mapToResponse(User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("created", user.getCreated());
        response.put("modified", user.getModified());
        response.put("last_login", user.getLastLogin());
        response.put("token", user.getToken());
        response.put("isactive", user.isActive());
        response.put("phones", user.getPhones());

        return response;
    }
}

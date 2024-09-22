package com.nisumexercise.apiUsers.dto.response;

import com.nisumexercise.apiUsers.entity.Phone;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private List<Phone> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isactive;
}

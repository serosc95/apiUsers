package com.nisumexercise.apiUsers.repository;

import com.nisumexercise.apiUsers.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByUserId(UUID userId);
}

package com.h2.dogrooming.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByAdminId(String adminId);

    //@Query("SELECT A FROM Admin A WHERE adminId = :adminId AND password = :password")
    Admin findAdminByAdminIdAndPassword(String adminId, String password);
}

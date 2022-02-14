package com.h2.dogrooming.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    boolean existsByAdminId(String adminId);

    Optional<Admin> findByAdminId(String adminId);

    Admin getAdminByAdminId(String adminId);
}

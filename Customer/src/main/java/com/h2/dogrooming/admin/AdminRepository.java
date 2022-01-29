package com.h2.dogrooming.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    boolean existsByAdminID(String adminID);

    Optional<Admin> findByAdminID(String adminID);

    Admin getAdminByAdminID(String adminID);

    Admin findAdminByNameAndEmail(String name, String email);
}

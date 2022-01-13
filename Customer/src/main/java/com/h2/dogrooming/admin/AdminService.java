package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void signUpAdmin(Admin admin) {
        admin.setType(1);
        admin.setUseState(1);
        adminRepository.save(admin);
    }
}
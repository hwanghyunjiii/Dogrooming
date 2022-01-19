package com.h2.dogrooming.admin;

import com.h2.dogrooming.config.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin adminInfo(String adminID) {
        return adminRepository.getAdminByAdminID(adminID);
    }

    public void signUpAdmin(Admin admin) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin.setType(1);
        admin.setUseState(1);

        adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String adminID) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByAdminID(adminID)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user" + adminID));

        return User.builder()
                .username(admin.getAdminID())
                .password(admin.getPassword())
                .roles("CUSTOMER")
                .build();
    }


    // 아이디 중복 체크
    public boolean CheckAdminID(String AdminID) {
        return adminRepository.existsByAdminID(AdminID);
    }
}
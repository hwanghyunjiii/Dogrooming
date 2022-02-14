package com.h2.dogrooming.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin getAdmin(String adminId) {
        return adminRepository.getAdminByAdminId(adminId);
    }

    public void registerAdmin(Admin admin) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin.setType(1);
        admin.setUseState(1);

        adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user" + adminId));

        return User.builder()
                .username(admin.getAdminId())
                .password(admin.getPassword())
                .roles("CUSTOMER")
                .build();
    }


    // 아이디 중복 체크
    public boolean checkAdminID(String AdminID) {
        return adminRepository.existsByAdminId(AdminID);
    }

    // 회원정보 수정
    public void modifyAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
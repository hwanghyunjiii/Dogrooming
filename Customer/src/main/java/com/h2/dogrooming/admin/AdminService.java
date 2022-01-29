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

    public Admin getAdmin(String adminID) {
        return adminRepository.getAdminByAdminID(adminID);
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
    public boolean checkAdminID(String adminID) {
        return adminRepository.existsByAdminID(adminID);
    }

    // 회원정보 수정
    public void modifyAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // 아이디 찾기
    public String findAdminID(String name, String email){
        Admin admin = adminRepository.findAdminByNameAndEmail(name, email);
        String AdminID = "";
        if(admin != null){
            AdminID = admin.getAdminID();
        }

        return AdminID;
    }
}
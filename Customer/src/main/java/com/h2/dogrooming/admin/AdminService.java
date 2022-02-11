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
    public boolean checkAdminId(String adminId) {
        return adminRepository.existsByAdminId(adminId);
    }

    // 회원정보 수정
    public void modifyAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // 아이디 찾기
    public String findAdminId(AdminDTO adminDTO){
        Admin admin = adminRepository.findAdminByNameAndEmail(adminDTO.getName(), adminDTO.getEmail());
        String adminId = "";
        if(admin != null){
            adminId = admin.getAdminId();
        }

        return adminId;
    }
}
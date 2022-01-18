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

        Optional<Admin> adminOptional = adminRepository.findByAdminID(adminID);
        Admin admin = adminOptional.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.CUSTOMER.getValue()));

        return new User(admin.getAdminID(), admin.getPassword(), authorities);
    }


    // 아이디 중복 체크
    public boolean CheckAdminID(String AdminID) {
        return adminRepository.existsByAdminID(AdminID);
    }
}
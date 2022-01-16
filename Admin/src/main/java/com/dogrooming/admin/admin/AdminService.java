package com.h2.dogrooming.admin;

import com.h2.dogrooming.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    /***
     * 어드민 계정 등록
     * @param admin Admin
     */
    void registerAdmin(Admin admin){
        adminRepository.save(admin);
    }

    /***
     * 어드민 계정 정보 조회
     * @param adminId  아이디
     * @param password 비밀번호
     * @return Admin
     */
    Admin getAdmin(String adminId, String password){
        return adminRepository.findAdminByAdminIdAndPassword(adminId, password);
    }

}

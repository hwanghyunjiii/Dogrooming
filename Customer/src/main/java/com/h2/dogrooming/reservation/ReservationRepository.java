package com.h2.dogrooming.reservation;

import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.designer.Designer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ReservationRepository extends JpaRepository<Admin, String> {

    // 구매자 예약 내역 조회
    @Query("select a from Reservation a where a.buyer_Admin.adminID = :buyerId")
    Page<Reservation> findAllByBuyerID(@Param("buyerId") String buyerId, Pageable pageable);

}

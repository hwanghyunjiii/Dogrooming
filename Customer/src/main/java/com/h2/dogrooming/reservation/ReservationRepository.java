package com.h2.dogrooming.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    // 구매자 예약 내역 조회
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select a from Reservation a where a.buyer_Admin.adminId = :buyerId and a.reservationYmd between :dateFrom and :dateTo")
    Page<Reservation> findAllByBuyerId(@Param("buyerId") String buyerId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo, Pageable pageable);

    // 구매자 예약 내역 Summary
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select sum(a.product.amount) as amount from Reservation a where a.buyer_Admin.adminId = :buyerId and a.reservationYmd between :dateFrom and :dateTo")
    Integer findAllByBuyerIdSummary(@Param("buyerId") String buyerId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);

    // 구매자 예약 상세 내역
    Reservation findReservationByReservationId(Integer reservationId);
}

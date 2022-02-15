package com.h2.dogrooming.designer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
    /*
    @Query("Select a from Designer a where a.name like :name")
    List<Designer> findAllByNameContaining(@Param("name")String name);

    @Query("select a from Designer a where a.addresses like :address")
    Slice<Designer> findAllByAddressesContaining(@Param("address")String address, Pageable pageable);
     */

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Slice<Designer> findAllBy(Pageable pageable);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select a from Designer a where a.name like :name")
    Slice<Designer> findAllByNameContaining(@Param("name")String name, Pageable pageable);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query(value = "select a from Designer a where a.name like :keyword or a.region like :keyword", nativeQuery = true)
    Slice<Designer> findAllByNameContainingOrRegionContaining(@Param("keyword") String keyword, Pageable pageable);

    Designer findDesignerByDesignerId(long designerId);
}

package com.travel_agency.repository;

import com.travel_agency.domain.Tour;
import com.travel_agency.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TourRepository extends CrudRepository<Tour, Long> {

    Page<Tour> findAll(Pageable pageable);

    Page<Tour> findByTag(String tag, Pageable pageable);

    @Query("from Tour tour where tour.author = :author")
    Page<Tour> findByUser(Pageable pageable, @Param("author") User author);
}
package com.travel_agency.repository;

import com.travel_agency.domain.Message;
import com.travel_agency.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TourRepository extends CrudRepository<Message, Long> {

    Page<Message> findAll(Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);
    @Query("from Message message where message.author = :author")
    Page<Message> findByUser(Pageable pageable,@Param("author") User author);
}
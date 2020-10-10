package com.travel_agency.service;

import com.travel_agency.domain.Tour;
import com.travel_agency.domain.User;
import com.travel_agency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    @Autowired
    TourRepository tourRepository;

    public Page<Tour> tourList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return tourRepository.findByTag(filter, pageable);
        } else {
            return tourRepository.findAll(pageable);
        }
    }

    public Page<Tour> tourListForUser(Pageable pageable, User currentUser, User author) {
        return tourRepository.findByUser(pageable, author);
    }
}

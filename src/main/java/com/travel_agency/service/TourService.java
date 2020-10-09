package com.travel_agency.service;

import com.travel_agency.domain.Message;
import com.travel_agency.domain.User;
import com.travel_agency.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    @Autowired
    MessageRepository messageRepository;

    public Page<Message> messageList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepository.findByTag(filter, pageable);
        } else {
            return messageRepository.findAll(pageable);
        }
    }

    public Page<Message> tourListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepository.findByUser(pageable, author);
    }
}

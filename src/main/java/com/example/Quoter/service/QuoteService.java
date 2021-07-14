package com.example.Quoter.service;

import com.example.Quoter.domain.User;
import com.example.Quoter.domain.dto.QuoteDto;
import com.example.Quoter.repos.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepo quoteRepo;

    public Page<QuoteDto> quoteList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            // get the quotes that have the particular tag.
            return this.quoteRepo.findByTag(filter, pageable, user);
        }
        else {
            return this.quoteRepo.findAll(pageable, user);
        }
    }

    public Page<QuoteDto> quoteListForUser(Pageable pageable, User author, User currentUser) {
        return this.quoteRepo.findByUser(pageable, author, currentUser);
    }
}

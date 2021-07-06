package com.example.Quoter.repos;

import com.example.Quoter.domain.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface QuoteRepo extends CrudRepository<Quote, Long> {

    Page<Quote> findAll(Pageable pageable);
    Page<Quote> findByTag(String tag, Pageable pageable);
    
}

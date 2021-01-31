package com.example.Quoter.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Quoter.domain.Quote;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface QuoteRepo extends CrudRepository<Quote, Long> {
    
    List<Quote> findByTag(String tag);
    
}

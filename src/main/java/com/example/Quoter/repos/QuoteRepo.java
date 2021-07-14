package com.example.Quoter.repos;

import com.example.Quoter.domain.Quote;
import com.example.Quoter.domain.User;
import com.example.Quoter.domain.dto.QuoteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers to Create, Read, Update, Delete

public interface QuoteRepo extends CrudRepository<Quote, Long> {

    @Query("select new com.example.Quoter.domain.dto.QuoteDto(" +
            "   q, " +
            "   count(ql), " +
            "   sum(case when ql = :user then 1 else 0 end) > 0 " +
            ") " +
            "from Quote as q left join q.likes as ql " +
            "group by q")
    Page<QuoteDto> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new com.example.Quoter.domain.dto.QuoteDto(" +
            "   q, " +
            "   count(ql), " +
            "   sum(case when ql = :user then 1 else 0 end) > 0 " +
            ") " +
            "from Quote as q left join q.likes as ql " +
            "where q.tag = :tag " +
            "group by q")
    Page<QuoteDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query("select new com.example.Quoter.domain.dto.QuoteDto(" +
            "   q, " +
            "   count(ql), " +
            "   sum(case when ql = :user then 1 else 0 end) > 0 " +
            ") " +
            "from Quote as q left join q.likes as ql " +
            "where q.author = :author " +
            "group by q")
    Page<QuoteDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}

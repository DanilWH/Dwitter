package com.example.Quoter.domain.dto;

import com.example.Quoter.domain.Quote;
import com.example.Quoter.domain.User;
import com.example.Quoter.domain.util.QuoteHelper;

public class QuoteDto {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public QuoteDto(Quote quote, Long likes, Boolean meLiked) {
        this.id = quote.getId();
        this.text = quote.getText();
        this.tag = quote.getTag();
        this.author = quote.getAuthor();
        this.filename = quote.getFilename();

        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return QuoteHelper.getAuthorName(this.author);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    @Override
    public String toString() {
        return "QuoteDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}

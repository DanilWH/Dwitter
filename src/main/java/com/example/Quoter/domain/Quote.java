package com.example.Quoter.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Please fill the field")
    @Length(max = 2048, message = "Message is too long (more than 2kB)")
    private String text;
    @Length(max = 255, message = "Tag is too long")
    private String tag;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    
    private String filename;
    
    public Quote() {
        // create an empty constructor so that
        // Spring is able to create an instance of the class.
        // (polymorphism).
    }
    
    public Quote(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }
    
    public String getAuthorName() {
        return (this.author != null)? this.author.getUsername() : "<anonymus>";
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String getTag() {
        return tag;
    }
    
    public User getAuthor() {
        return this.author;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}

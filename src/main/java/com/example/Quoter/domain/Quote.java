package com.example.Quoter.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String text;
    private String tag;
    
    public Quote() {
        // create an empty constructor so that
        // Spring is able to create an instance of the class.
        // (polymorphism).
    }
    
    public Quote(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String getTag() {
        return tag;
    }
}

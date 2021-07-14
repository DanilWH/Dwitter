package com.example.Quoter.domain.util;

import com.example.Quoter.domain.User;

public abstract class QuoteHelper {
    public static String getAuthorName(User author) {
        return (author != null)? author.getUsername() : "<anonymous>";
    }
}

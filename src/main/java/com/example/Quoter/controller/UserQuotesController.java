package com.example.Quoter.controller;

import com.example.Quoter.domain.Quote;
import com.example.Quoter.domain.User;
import com.example.Quoter.domain.dto.QuoteDto;
import com.example.Quoter.repos.QuoteRepo;
import com.example.Quoter.repos.UserRepo;
import com.example.Quoter.service.QuoteService;
import com.example.Quoter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.NoResultException;
import java.io.IOException;

@Controller
class UserQuotesController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private QuoteRepo quoteRepo;

    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/user-quotes/{userId}")
    public String userQuotes(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId,
            @RequestParam(name = "quote", required = false) Long quoteId,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        User author = this.userRepo.findById(userId).orElseThrow(NoResultException::new);
        Quote quote = (quoteId == null)? null : this.quoteRepo.findById(quoteId).orElseThrow(NoResultException::new);

        Page<QuoteDto> page= this.quoteService.quoteListForUser(pageable, author, currentUser);

        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscribtions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("quote", quote);
        model.addAttribute("isCurrentUser",  currentUser.equals(author));
        model.addAttribute("url", "/user-quotes/" + userId);
        model.addAttribute("pagerSequence", ControllerUtils.getPagerSequence(page));

        return "userQuotes";
    }

    @PostMapping("/user-quotes/{userId}")
    public String updateQuote(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId,
            @RequestParam("id") Long quoteId,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam MultipartFile file
    ) throws IOException {
        Quote quote = this.quoteRepo.findById(quoteId).orElseThrow(NoResultException::new);

        if (quote.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                quote.setText(text);
            }

            if(!StringUtils.isEmpty(tag)) {
                quote.setTag(tag);
            }
            ControllerUtils.saveFile(file, uploadPath, quote);
            this.quoteRepo.save(quote);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have the right to change someone's quote!");
        }

        return "redirect:/user-quotes/" + userId;
    }

}

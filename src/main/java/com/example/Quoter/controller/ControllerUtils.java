package com.example.Quoter.controller;

import com.example.Quoter.domain.Quote;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ControllerUtils {

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
            fieldError -> fieldError.getField() + "Error",
            FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    static void saveFile (
            MultipartFile file,
            String uploadPath,
            Quote quote
    ) throws IOException {
        if (file != null && !file.isEmpty()) {
            // create the directory if it doesn't exist.
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists())
                uploadDir.mkdirs();

            // create a new unique filename to avoid collisions.
            String uuidFilename = UUID.randomUUID().toString();
            String resultFilename = uuidFilename + "." + file.getOriginalFilename();

            // upload the file to the directory we pointed.
            file.transferTo(new File(uploadPath + resultFilename));

            // save the name of the file to a certain quote in the database.
            quote.setFilename(resultFilename);
        }
    }

    static List<Integer> getPagerSequence(Page<Quote> page) {
        /*
        * Pay attention: we always subtract one from page.getTotlaPages() when working with indexes
        * because page.getTotalPages() returns the total amount of pages, not the last page index!
        */

        List<Integer> pagerSequence;

        if (page.getTotalPages() > 7) {

            if (page.getNumber() < 3) {
                pagerSequence = sequenceOf(0, 4);
                // and the three dots.
                pagerSequence.add(-1);
                // add the maximum page at the end.
                pagerSequence.add(page.getTotalPages() - 1);
            } else if (page.getNumber() > page.getTotalPages() - 4) {
                pagerSequence = sequenceOf(page.getTotalPages() - 5, page.getTotalPages() - 1);
                // add the very first page at the beginning.
                pagerSequence.add(0, 0);
                // add the three dots.
                pagerSequence.add(1, -1);
            } else {
                pagerSequence = sequenceOf(page.getNumber() - 2, page.getNumber() + 2);
                // set the minimum and the maximum page at the ends of the pager sequence.
                pagerSequence.add(0, 0);
                pagerSequence.add(page.getTotalPages() - 1);

                // dots adding.
                if (page.getNumber() >= 4) {
                    // add the three dots to the left if the current page is 5 pages far from the beginning.
                    pagerSequence.add(1, -1);
                }
                if (page.getNumber() <= page.getTotalPages() - 5) {
                    // add the three dots to the right if the current page is 5 pages far from the ending.
                    pagerSequence.add(pagerSequence.size() - 1, -1);
                }
            }
        }
        else {
            pagerSequence = sequenceOf(0, page.getTotalPages() - 1);
        }

        return pagerSequence;
    }

    private static List<Integer> sequenceOf(int start, int end) {
        List<Integer> sequence = new ArrayList<>();

        if (end > start) {
            for (int i = start; i <= end; i++) {
                sequence.add(i);
            }
        }
        else {
            for (int i = start; i >= end; i--) {
                sequence.add(i);
            }
        }

        return sequence;
    }
}

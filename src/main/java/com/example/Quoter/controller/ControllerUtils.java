package com.example.Quoter.controller;

import com.example.Quoter.domain.Quote;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

}

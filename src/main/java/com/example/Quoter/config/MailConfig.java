package com.example.Quoter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        mailSender.setPort(this.port);
        mailSender.setProtocol(this.protocol);

        Properties properties = mailSender.getJavaMailProperties();

        /*
        * In the cases when something goes wrong, we can see what exactly went wrong with the mail service in the log console.
        * For example: the username is wrong or the password doesn't match.
        */
        properties.setProperty("mail.transport.protocol", this.protocol);
        // you must turn the "debug" variable off on production.
        properties.setProperty("mail.debug", this.debug);


        return mailSender;
    }

}

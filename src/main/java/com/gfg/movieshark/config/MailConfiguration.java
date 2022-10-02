package com.gfg.movieshark.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl(){
         JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
//            javaMailSender.setUsername("<EMAIL>");
//          javaMailSender.setPassword("<PASSWORD>");
////        javaMailSender.setHost("smtp-mail.outlook.com");
////        javaMailSender.setPort(25);
//
//        Properties props=javaMailSender.getJavaMailProperties();
//        props.put("mail.smtp.user","<EMAIL>");
//        props.put("mail.smtp.host", "smtp-mail.outlook.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable","true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "true");
//
//        //javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable",true);


        return javaMailSender;
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}

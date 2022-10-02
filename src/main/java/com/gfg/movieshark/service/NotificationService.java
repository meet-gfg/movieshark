package com.gfg.movieshark.service;

import com.gfg.movieshark.resource.TicketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {


    @Autowired
    JavaMailSenderImpl javaMailSender;


    public void sendNotification(TicketMessage message){
        sendEmailToUser(message);
        sendSMSToUser(message);
    }

    private void sendSMSToUser(TicketMessage message) {
        log.info("calling sms service for showDetails: {}  seatDetails : {}to number {}",message.getShow(),message.getSeats(),message.getMobile());
    }

    private void sendEmailToUser(TicketMessage message) {
        log.info("calling sms service for showDetails: {}  seatDetails : {}to number {}",message.getShow(),message.getSeats(),message.getMobile());
        /**
         *  JSONObject jsonObject=mapper.readValue(message,JSONObject.class);
         *         SimpleMailMessage mailMessage=new SimpleMailMessage();
         *         if(jsonObject.containsKey("to"))
         *             mailMessage.setTo((String) jsonObject.get("to"));
         *         if(jsonObject.containsKey("cc"))
         *             mailMessage.setCc((String) jsonObject.get("cc"));
         *         if(jsonObject.containsKey("subject"))
         *             mailMessage.setSubject((String) jsonObject.get("subject"));
         *         if(jsonObject.containsKey("body"))
         *             mailMessage.setText((String) jsonObject.get("body"));
         *
         *         logger.info(mapper.writeValueAsString(mailMessage));
         *
         *         javaMailSender.send(mailMessage);
         * */


    }

}

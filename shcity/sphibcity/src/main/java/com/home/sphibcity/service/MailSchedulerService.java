package com.home.sphibcity.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.model.enumeration.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class MailSchedulerService {
    public static void main(String[] args) {

    }


    @Autowired
    CountryServiceImpl countryService;
    private JavaMailSender javaMailSender;
    private CountryEntity countryEntity = new CountryEntity();
    private CityEntity cityEntity = new CityEntity();

    @Scheduled(cron = "0/10 * * * * * ")
    public void sendEmail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.addAttachment("simple attachment", new File("D:\\JAVA\\project\\shcity\\sphibcity\\src\\main\\resources\\templates\\index.html"));
        helper.setText("Test mail");
        helper.setTo("nikolaev.english@gmail.com");
        helper.setSubject("Test mail subject");
        javaMailSender.send(mimeMessage);
    }
}


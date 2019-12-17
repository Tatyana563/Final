package com.home.sphibcity.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.model.enumeration.Type;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.lang.reflect.Array;
import java.time.Instant;
//Задача, при создании новой страны рассылать пользователям, которые сконфигурированы
// в новой специальной таблице, письмо, в теле письма  - вся информация про созданную
// страну.  Задача должна запускаться каждые 3 минуты
//если ничего нового не было создано в промежуток между запусками, то никаких письме не
// должно быть, если в промежуток между запусками планировщика было создано больше одной
// страны, то информация обо всех странах должна быть в теле одного письма
@Service
public class MailSchedulerService {
    public static void main(String[] args) throws MessagingException {
        MailSchedulerService ms = new MailSchedulerService();
       ms.saveCountryUpdates();
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
        helper.setSubject("Testc mail subject");
        javaMailSender.send(mimeMessage);
    }

    public CountryEntity init() {
        countryEntity.setName("Poland");
        countryEntity.setPopulation(100);
        countryEntity.setDescription("a neighbour");
        countryEntity.setLanguage(Language.Polish);
        countryEntity.setSquare(66.5);
        cityEntity.setName("Warsaw");
        cityEntity.setType(Type.EMERGING_GATEWAY);
        cityEntity.setCountryId(countryEntity.getId());
        countryService.createOrUpdate(countryEntity);

        return countryEntity;
    }

    public  void saveCountryUpdates() throws MessagingException {
        init();
        ObjectMapper objectMapper = new ObjectMapper();
        try(FileOutputStream out = new FileOutputStream("D:\\JAVA\\General\\mail.json")){
            out.write(objectMapper.writeValueAsBytes(countryEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileInputStream in = new FileInputStream("D:\\JAVA\\General\\mail.json")){
            CountryEntity desCountry=objectMapper.readValue(in,CountryEntity.class);
            System.out.println(desCountry);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendCountryUpdatesEmail();
    }

    @Scheduled(cron = "* 0/3 * * * * ")
    public void sendCountryUpdatesEmail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.addAttachment("country update attachment", new File("D:\\JAVA\\General\\mail.json"));
        helper.setText("The country was updated");
      //  helper.setTo(to); pom-file
        String [] emails = {"nikolaev.english@gmail.com"};
        helper.setTo(emails);
        helper.setSubject("Testing country info updates");
        javaMailSender.send(mimeMessage);
    }
}


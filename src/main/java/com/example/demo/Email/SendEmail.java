package com.example.demo.Email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
	 
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String content,String attchment) throws MessagingException {

        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        
        mimeMessageHelper.setFrom("angelrodirguez16@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(content);
        mimeMessageHelper.setSubject(subject);
        
        FileSystemResource fileSystemResource = new FileSystemResource(new File(attchment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        mailSender.send(mimeMessage);
        
    }
}

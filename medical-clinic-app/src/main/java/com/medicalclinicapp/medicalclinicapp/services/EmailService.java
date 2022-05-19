package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.dto.MailResponse;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration config;

    public MailResponse sendmail(MailRequest request, Map<String, Object> model) {
        MailResponse response = new MailResponse();
        request.setFrom("vijulans2000@gmail.com");
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

          //  helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            mailSender.send(message);

            response.setMessange("Mail send: " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setStatus(Boolean.FALSE);
            response.setMessange("Email failed: " + e.getMessage());

        }
        return response;
    }
}

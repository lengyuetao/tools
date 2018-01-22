package com.tao.tools.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by zhangantao on 2017/4/12.
 */
public class EmailUtils {
    private String displayName;

    private String from;

    private String smtpServer;

    private String username;

    private String password;

    private String charset = "utf-8";

    public EmailUtils(String smtpServer, String from, String displayName,
                      String username, String password) {
        this.smtpServer = smtpServer;
        this.from = from;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }



    public String getDisplayName() {
        return displayName;
    }



    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }



    public String getFrom() {
        return from;
    }



    public void setFrom(String from) {
        this.from = from;
    }



    public String getSmtpServer() {
        return smtpServer;
    }



    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getCharset() {
        return charset;
    }



    public void setCharset(String charset) {
        this.charset = charset;
    }


    public void send(String[] tos, boolean isAuth, String subject, String content, boolean isHtml, File[] files) throws MessagingException, UnsupportedEncodingException {
        Session session = null;
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", smtpServer);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        if (isAuth) {
            props.put("mail.smtp.auth", "true");

            Authenticator authenticator = new Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            };
            session = Session.getDefaultInstance(props, authenticator);
        } else {
            props.put("mail.smtp.auth", "false");
            session = Session.getDefaultInstance(props, null);
        }
        session.setDebug(false);
        Transport trans = session.getTransport("smtp");

        InternetAddress[] address = new InternetAddress[tos.length];
        for (int i = 0; i < address.length; i++) {
            address[i] = new InternetAddress(tos[i]);
        }

        trans.connect(smtpServer, username, password);

        Message msg = new MimeMessage(session);

        Address from_address = new InternetAddress(from, displayName);

        msg.setFrom(from_address);

        msg.setRecipients(Message.RecipientType.TO, address);

        msg.setSubject(subject);

        Multipart mp = new MimeMultipart();

        MimeBodyPart mbp = new MimeBodyPart();


        if (isHtml) {
            mbp.setContent(content, "text/html;charset=" + charset);
        } else{
            mbp.setText(content);
        }
        mp.addBodyPart(mbp);

        if (files != null && files.length > 0) {
            for (File file : files) {
                mbp = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(file);
                mbp.setDataHandler(new DataHandler(fds));
                mbp.setFileName(fds.getName());
                mp.addBodyPart(mbp);
            }
        }
        msg.setContent(mp);

        msg.setSentDate(new Date());

        msg.saveChanges();

        trans.sendMessage(msg, msg.getAllRecipients());

        trans.close();

    }


    public static void main(String[] args) throws UnsupportedEncodingException,MessagingException {

        EmailUtils send=new EmailUtils("smtp.exmail.qq.com", "lengyuetao@163.com", "测试", "lengyuetao@163.com", "yb1991");
        String[] toEmail=new String[]{"dyadan@163.com"};
        File[] file=new File[]{new File("/Users/zhangantao/Desktop/queryData.jsp")};

        send.send(toEmail, true, "测试邮件", "aa", false, file);
    }
}

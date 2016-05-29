/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.util;

import br.com.elearning.praticas.model.Usuario;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Felipe
 */
public class EmailUtils {

    Session session = null;
    private String username = "emailpraticas@ffm.com.br";
    private String senha = "Praticas123";

    public EmailUtils() {
        ajustaParametros();
    }

    public void enviarEmail(Usuario user, String assunto, String conteudo) {

        try {
            Message message = new MimeMessage(session);

            //Configura o Remetente da mensagem
            message.setFrom(new InternetAddress("emailpraticas@ffm.com.br"));

            //Configura o Destinatário da mensagem
            Address[] toUser = InternetAddress.parse("elearningpraticas@gmail.com");

            //Configura o Assunto da mensagem
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);

            //Configura o Conteudo da mensagem
            message.setText(conteudo + "\n\n\n\nUSERNAME: " + user.getNick()+ "\nEMAIL: " + user.getEmail());

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private void ajustaParametros() {

        Properties props = new Properties();

        /**
         * Conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        /**
         * Associa autenticação a sessao de correio
         */
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, senha);
            }
        });

    }

}

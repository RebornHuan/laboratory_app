package my.skypiea.punygod.module.emailLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/8.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

/**
 * Email action executor. It takes to, cc addresses along with a subject and body and sends
 * out an email.
 */
public class EmailActionExecutor {

    public static final String CONF_PREFIX = "oozie.email.";
    public static final String EMAIL_SMTP_HOST = CONF_PREFIX + "smtp.host";
    public static final String EMAIL_SMTP_PORT = CONF_PREFIX + "smtp.port";
    public static final String EMAIL_SMTP_AUTH = CONF_PREFIX + "smtp.auth";
    public static final String EMAIL_SMTP_USER = CONF_PREFIX + "smtp.username";
    public static final String EMAIL_SMTP_PASS = CONF_PREFIX + "smtp.password";
    public static final String EMAIL_SMTP_FROM = CONF_PREFIX + "from.address";
    public static final String EMAIL_ATTACHMENT_ENABLED = CONF_PREFIX + "attachment.enabled";

    private final static String TO = "to";
    private final static String CC = "cc";
    private final static String SUB = "subject";
    private final static String BOD = "body";
    private final static String ATTACHMENT = "attachment";
    private final static String COMMA = ",";
    private final static String CONTENT_TYPE = "content_type";
    private final static String DEFAULT_CONTENT_TYPE = "text/plain";


    public static final String EMAIL_ATTACHMENT_ERROR_MSG =
            "\n Note: This email is missing configured email attachments "
                    + "as sending attachments in email action is disabled in the Oozie server. "
                    + "It could be for security compliance with data protection or other reasons";


    public void email(String[] to, String[] cc, String subject, String body, String[] attachments, String contentType,
                      String user) throws ActionExecutorException {
        // Get mailing server details.

        Configuration conf = new Configuration();
        String smtpHost = conf.get(EMAIL_SMTP_HOST, "localhost");
        String smtpPort = conf.get(EMAIL_SMTP_PORT, "25");
        Boolean smtpAuth = conf.getBoolean(EMAIL_SMTP_AUTH, false);
        String smtpUser = conf.get(EMAIL_SMTP_USER, "");
        String smtpPassword = conf.get(EMAIL_SMTP_PASS, "");
        String fromAddr = conf.get(EMAIL_SMTP_FROM, "reborn@localhost");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.auth", smtpAuth.toString());

        Session session;
        // Do not use default instance (i.e. Session.getDefaultInstance)
        // (cause it may lead to issues when used second time).
        if (!smtpAuth) {
            session = Session.getInstance(properties);
        } else {
            session = Session.getInstance(properties, new JavaMailAuthenticator(smtpUser, smtpPassword));
        }

        Message message = new MimeMessage(session);
        InternetAddress from;
        List<InternetAddress> toAddrs = new ArrayList<InternetAddress>(to.length);
        List<InternetAddress> ccAddrs = new ArrayList<InternetAddress>(cc.length);

        try {
            from = new InternetAddress(fromAddr);
            message.setFrom(from);
        } catch (AddressException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM002", "Bad from address specified in ${oozie.email.from.address}.", e);
        } catch (MessagingException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM003", "Error setting a from address in the message.", e);
        }

        try {
            // Add all <to>
            for (String toStr : to) {
                toAddrs.add(new InternetAddress(toStr.trim()));
            }
            message.addRecipients(RecipientType.TO, toAddrs.toArray(new InternetAddress[0]));

            // Add all <cc>
            for (String ccStr : cc) {
                ccAddrs.add(new InternetAddress(ccStr.trim()));
            }
            message.addRecipients(RecipientType.CC, ccAddrs.toArray(new InternetAddress[0]));

            // Set subject
            message.setSubject(subject);

            // when there is attachment
            if (attachments != null && attachments.length > 0 && conf.getBoolean(EMAIL_ATTACHMENT_ENABLED, true)) {
                Multipart multipart = new MimeMultipart();
                // Set body text
                MimeBodyPart bodyTextPart = new MimeBodyPart();
                bodyTextPart.setText(body);
                multipart.addBodyPart(bodyTextPart);

                for (String attachment : attachments) {
                    URI attachUri = new URI(attachment);
                    if (attachUri.getScheme() != null && attachUri.getScheme().equals("file")) {
                        throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM008",
                                "Encountered an error when attaching a file. A local file cannot be attached:"
                                        + attachment);
                    }
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    DataSource source = new URIDataSource(attachUri, user);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(new File(attachment).getName());
                    multipart.addBodyPart(messageBodyPart);
                }
                message.setContent(multipart);
            } else {
                if (attachments != null && attachments.length > 0 && !conf.getBoolean(EMAIL_ATTACHMENT_ENABLED, true)) {
                    body = body + EMAIL_ATTACHMENT_ERROR_MSG;
                }
                message.setContent(body, contentType);
            }
        } catch (AddressException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM004", "Bad address format in <to> or <cc>.", e);
        } catch (MessagingException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM005", "An error occured while adding recipients.", e);
        } catch (URISyntaxException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM008", "Encountered an error when attaching a file", e);
        } catch (Exception e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM008", "Encountered an error when attaching a file", e);
        }

        try {
            // Send over SMTP Transport
            // (Session+Message has adequate details.)
            Transport.send(message);
        } catch (NoSuchProviderException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM006", "Could not find an SMTP transport provider to email.", e);
        } catch (MessagingException e) {
            throw new ActionExecutorException(ActionExecutorException.ErrorType.ERROR, "EM007", "Encountered an error while sending the email message over SMTP.", e);
        }
    }


    public static class JavaMailAuthenticator extends Authenticator {

        String user;
        String password;

        public JavaMailAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }
    }

    class URIDataSource implements DataSource {

        //        HadoopAccessorService has = Services.get().get(HadoopAccessorService.class);
        FileSystem fs;
        URI uri;

        public URIDataSource(URI uri, String user) throws Exception {
            this.uri = uri;

            final URI urlFinal = uri;
            UserGroupInformation ugi = UserGroupInformation.createRemoteUser(user);
            fs = ugi.doAs(new PrivilegedExceptionAction<FileSystem>() {
                public FileSystem run() throws Exception {
                    return FileSystem.get(urlFinal, new Configuration());
                }
            });
        }

        public InputStream getInputStream() throws IOException {
            return fs.open(new Path(uri));
        }

        public OutputStream getOutputStream() throws IOException {
            return fs.create(new Path(uri));
        }

        public String getContentType() {
            return "application/octet-stream";
        }

        public String getName() {
            return uri.getPath();
        }
    }
}

import RestClientPackage.userGroupClient;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
import java.io.Serializable;
import java.util.Properties;

@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable {

    private userGroupClient ugc;
    private String forgEmail;
    private String forgpass;
    private String forgconf;
    private String errorMessage;
    private String companyName = "Electronic Store Management";

    public RegisterBean() {
        ugc = new userGroupClient();
    }

    public String getForgEmail() {
        return forgEmail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setForgEmail(String forgEmail) {
        this.forgEmail = forgEmail;
    }

    public String getForgpass() {
        return forgpass;
    }

    public void setForgpass(String forgpass) {
        this.forgpass = forgpass;
    }

    public String getForgconf() {
        return forgconf;
    }

    public void setForgconf(String forgconf) {
        this.forgconf = forgconf;
    }

    public void forgetPassword() {
        final String username = "pnaitik504@gmail.com"; // Your email
        final String password = "qnpqwcohvtazvehb"; // Your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(forgEmail));
            message.setSubject("Password Reset Request for " + companyName);

            String htmlContent = "<h1>Password Reset Request</h1>"
                + "<p>Click the link below to reset your password:</p>"
                + "<a href='http://localhost:8080/electronic_store_management/forgetPassword.jsf?email=" + forgEmail + "'>Reset Password</a>";

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password reset instructions have been sent to your email.", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("email", forgEmail);
        } catch (MessagingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Failed to send reset instructions. Please try again later."));
            throw new RuntimeException(e);
        }
    }

    public String setPassword() {
        System.out.println("Setting Password for email: " + forgEmail);
        if (forgconf.equals(forgpass)) {
            System.out.println("*-*-*-*-*-*" + forgEmail + forgpass + forgconf + "*--*-*-*-*-*--**--*-*");
            ugc.changePassword(forgEmail, forgpass);
            return "login.jsf";
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords do not match.");
            FacesContext.getCurrentInstance().addMessage("confirmPassword", message);
            errorMessage = "Passwords do not match.";
            return null;
        }
    }
}

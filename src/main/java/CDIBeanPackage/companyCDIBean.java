/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import EntityPackage.CompanyDetails;
import RestClientPackage.companyClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "companyCDIBean")
@RequestScoped
public class companyCDIBean {
//    String captchaCode; // Change to String
    CompanyDetails cd = new CompanyDetails();
    RestClientPackage.companyClient cc;
    private String email, password;
    UploadedFile file;
    
    public companyCDIBean()
    {
        cc = new companyClient();
//        generateCaptcha();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
//    public String getCaptchaCode() {
//        return captchaCode;
//    }
//
//    public void setCaptchaCode(String captchaCode) {
//        this.captchaCode = captchaCode;
//    }
//
//    private void generateCaptcha() {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        StringBuilder captchaCodeBuilder = new StringBuilder();
//        SecureRandom random = new SecureRandom();
//
//        for (int i = 0; i < 6; i++) {
//            int index = random.nextInt(characters.length());
//            captchaCodeBuilder.append(characters.charAt(index));
//        }
//        captchaCode = captchaCodeBuilder.toString();
//    }
    
    public CompanyDetails getCd() {
        return cd;
    }

    public void setCd(CompanyDetails cd) {
        this.cd = cd;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void addCompany()
    {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("electronic_store_management/src/main/webapp/public/uploads/" + fileName);
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                } finally {
                    output.close();
                }   
            } catch (IOException e) {
                // Handle the error
                e.printStackTrace();
            }
        }
        
        cc.addCompany(cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()),cd.getPassword(), fileName, cd.getCountry());
        
    }
    
    public String checkLogin() {
        try {
            boolean chk = cc.checkCompanyLogin(Boolean.class,email, password);
            if (chk) {
                return "companyRegister?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid email or password"));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred during login"));
            e.printStackTrace();
            return null;
        }
    }
}

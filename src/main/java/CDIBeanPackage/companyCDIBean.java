/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import RestClientPackage.companyClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
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
    CategoryDetails cdetail = new CategoryDetails();
    RestClientPackage.companyClient cc;
    private String email, password;
    UploadedFile file;
    String errorStatus;

    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

    HttpSession session = request.getSession();
    Response rs;
    Collection<CategoryDetails> catdt;
    GenericType<Collection<CategoryDetails>> gcatdt;

    public companyCDIBean() {
        cc = new companyClient();
        this.errorStatus = "";
//        generateCaptcha();
        catdt = new ArrayList<>();
        gcatdt = new GenericType<Collection<CategoryDetails>>() {
        };
        session.removeAttribute("successmessage");
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

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    // Register Company
    public String addCompany() {
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

        cc.addCompany(cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), cd.getPassword(), fileName, cd.getCountry());
        return "companyLogin?faces-redirect=true";
    }

    // Company Login
    public String checkLogin() {
        try {
            boolean chk = cc.checkCompanyLogin(Boolean.class, email, password);
            if (chk) {
                Collection<CompanyDetails> comdetil = new ArrayList<>();
                GenericType<Collection<CompanyDetails>> gcomdetil = new GenericType<Collection<CompanyDetails>>(){};
                Integer comId = 0;
                rs = cc.getUserId(Response.class, email);
                comdetil = rs.readEntity(gcomdetil);
                for (CompanyDetails cd : comdetil) {
                    comId = cd.getCompanyId();
                }
                session.setAttribute("comId", comId);
                return "displayCategory?faces-redirect=true";
            } else {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid email or password"));
                this.errorStatus = "Invalid email or password";
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred during login"));
            e.printStackTrace();
            return null;
        }
    }

    // display Category
    public Collection<CategoryDetails> getCatdt() {
        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getAllCategory(Response.class, companyId.toString());
        catdt = rs.readEntity(gcatdt);
        return catdt;
    }

    public void setCatdt(Collection<CategoryDetails> catdt) {
        this.catdt = catdt;
    }

    public CategoryDetails getCdetail() {
        return cdetail;
    }

    public void setCdetail(CategoryDetails cdetail) {
        this.cdetail = cdetail;
    }

    // Add Category
    public String addCategory() {
        session.setAttribute("successmessage", "Category Successfully Inserted");
        Integer companyId = (Integer) session.getAttribute("comId");
        cc.addCategory(cdetail.getCategoryName(), companyId.toString());
        return "displayCategory";
    }
    
    // edit form
    public String editForm(CategoryDetails catd)
    {
        this.cdetail = catd;
        return "editCategory";
    }
    
    // Update or Edit Category
    public String editCategory()
    {
        session.setAttribute("successmessage", "Category Successfully Edited");
        Integer companyId = (Integer) session.getAttribute("comId");
        cc.updateCategory(String.valueOf(cdetail.getCategoryId()), cdetail.getCategoryName(), companyId.toString());
        return "displayCategory";
    }
}

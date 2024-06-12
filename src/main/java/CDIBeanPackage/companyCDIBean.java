/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import EntityPackage.CompanyProductStock;
import EntityPackage.ProductDetails;
import RestClientPackage.companyClient;
import RestClientPackage.userGroupClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.Collection;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.activation.DataSource;
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

    userGroupClient ugc;
    @Inject
    LoginBean lb;

    CompanyDetails cd = new CompanyDetails();
    ProductDetails prodetail = new ProductDetails();
    CategoryDetails cdetail = new CategoryDetails();
    CompanyProductStock cpsdetails = new CompanyProductStock();
    String productStocks;
    Integer productId;

    RestClientPackage.companyClient cc;
    private String email, password;
    UploadedFile file;
    String errorStatus;

    private String addCategoryToProduct;

    Collection<ProductDetails> prodt;
    GenericType<Collection<ProductDetails>> gprodt;

    Collection<CompanyProductStock> cpsdt;
    GenericType<Collection<CompanyProductStock>> gcpsdt;

    Response rs;
    Collection<CategoryDetails> catdt;
    GenericType<Collection<CategoryDetails>> gcatdt;

    private String proflogo;

    // Variables for counting category,product,stocks
    Integer catCount;
    Integer prodCount;
    Integer stockCount;
    Integer storeOrderCount;

    // for Error Message
    String succesMessage, errorMessage;

    public companyCDIBean() {
        cc = new companyClient();
        ugc = new userGroupClient();

        this.errorStatus = "";
//        generateCaptcha();
        catdt = new ArrayList<>();
        gcatdt = new GenericType<Collection<CategoryDetails>>() {
        };

        prodt = new ArrayList<>();
        gprodt = new GenericType<Collection<ProductDetails>>() {
        };

        cpsdt = new ArrayList<>();
        gcpsdt = new GenericType<Collection<CompanyProductStock>>() {
        };

        succesMessage = null;
        errorMessage = null;
    }

    public String getProflogo() {
        this.cd = cc.getCompanyById(CompanyDetails.class, lb.getComId().toString());
        proflogo = cd.getCompanyLogo();
        return proflogo;
    }

    public void setProflogo(String proflogo) {
        this.proflogo = proflogo;
    }

    public ProductDetails getProdetail() {
        return prodetail;
    }

    public void setProdetail(ProductDetails prodetail) {
        this.prodetail = prodetail;
    }

    public String getAddCategoryToProduct() {
        return addCategoryToProduct;
    }

    public void setAddCategoryToProduct(String addCategoryToProduct) {
        this.addCategoryToProduct = addCategoryToProduct;
    }

    public Collection<CompanyProductStock> getCpsdt() {
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.displayCompanyProductStock(Response.class, String.valueOf(lb.getComId()));
        cpsdt = rs.readEntity(gcpsdt);
        return cpsdt;
    }

    public void setCpsdt(Collection<CompanyProductStock> cpsdt) {
        this.cpsdt = cpsdt;
    }

    //display Product
    public Collection<ProductDetails> getProdt() {
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getAllProduct(Response.class, String.valueOf(lb.getComId()));
        prodt = rs.readEntity(gprodt);
        return prodt;
    }

    public void setProdt(Collection<ProductDetails> prodt) {
        this.prodt = prodt;
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

    public CompanyProductStock getCpsdetails() {
        return cpsdetails;
    }

    public void setCpsdetails(CompanyProductStock cpsdetails) {
        this.cpsdetails = cpsdetails;
    }

    public String getSuccesMessage() {
        return succesMessage;
    }

    public void setSuccesMessage(String succesMessage) {
        this.succesMessage = succesMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // file uploading function
    private String uploadImage() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("C:/Users/Kartik Patel/Desktop/sem8_Project/electronic_store_management/src/main/webapp/public/uploads/" + fileName);
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
        return fileName;
    }

    public void sendConfirmationEmail(String companyName, String recipientEmail, String cno, String companyLogoPath, String pass, String country) {
        final String username = "pnaitik504@gmail.com"; // Your email
        final String password = "qnpqwcohvtazvehb"; // Your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Assuming you're using Gmail
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Welcome to " + companyName);

            // HTML content for the email
            String htmlContent = "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<style>"
                    + "body {font-family: Arial, sans-serif;}"
                    + ".header {background-color: #f2f2f2; padding: 20px; text-align: center;}"
                    + ".content {padding: 20px;}"
                    + ".footer {background-color: #f2f2f2; padding: 10px; text-align: center;}"
                    + "img {max-width: 200px; height: auto;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='header'>"
                    + "<img src='cid:companyLogo' alt='Company Logo'/>"
                    + "</div>"
                    + "<div class='content'>"
                    + "<h2>Dear " + recipientEmail + ",</h2>"
                    + "<p>Welcome to Electronic Store Management!</p>"
                    + "<p>We are thrilled to have you with us. Below are your registration details:</p>"
                    + "<ul>"
                    + "<li>Company Name: " + companyName + "</li>"
                    + "<li>Contact Number: " + cno + "</li>"
                    + "<li>Password: " + pass + "</li>"
                    + "<li>Country: " + country + "</li>"
                    + "</ul>"
                    + "<p>We look forward to a great journey together.</p>"
                    + "</div>"
                    + "<div class='footer'>"
                    + "<p>Best Regards,</p>"
                    + "<p>" + companyName + "</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            // Create the message body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html");

            // Create the image attachment part
            MimeBodyPart imageAttachment = new MimeBodyPart();
            DataSource source = new FileDataSource(companyLogoPath);
            imageAttachment.setDataHandler(new DataHandler(source));
            imageAttachment.setHeader("Content-ID", "<companyLogo>");

            // Create a multipart message to include both HTML and image
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(imageAttachment);

            // Set the multipart content to the message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent to " + recipientEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Register Company
    public String addCompany() {
        String logoFileName = uploadImage();
        cc.addCompany(cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), cd.getPassword(), logoFileName, cd.getCountry());
        ugc.addUser(cd.getEmail(), cd.getPassword());
        ugc.addGroup("Company", cd.getEmail());

        sendConfirmationEmail(cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), "C:/Users/Admin/Desktop/sem8_Project/electronic_store_management/src/main/webapp/public/uploads/" + logoFileName, cd.getPassword(), cd.getCountry());

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/login.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Company Login
    public String checkLogin() {
        try {
            boolean chk = cc.checkCompanyLogin(Boolean.class, email, password);
            if (chk) {
                Collection<CompanyDetails> comdetil = new ArrayList<>();
                GenericType<Collection<CompanyDetails>> gcomdetil = new GenericType<Collection<CompanyDetails>>() {
                };
                Integer comId = 0;
//                rs = cc.getUserId(Response.class, email);
                comdetil = rs.readEntity(gcomdetil);
                for (CompanyDetails cd : comdetil) {
                    comId = cd.getCompanyId();
                }
//                session.setAttribute("comId", comId);
                return "companyDashboard?faces-redirect=true";
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
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getAllCategory(Response.class, String.valueOf(lb.getComId()));
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

    //Add Product  
    public String addProduct() {
        String fileName = uploadImage();
        ProductDetails pd1 = new ProductDetails();
        GenericType<ProductDetails> gpd1 = new GenericType<ProductDetails>() {
        };

        succesMessage = "Product Successfully Inserted";
//        Integer companyId = (Integer) session.getAttribute("comId");

        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(prodetail.getMfgDate());

        cc.addProduct(prodetail.getProductName(), String.valueOf(prodetail.getDiscount()), String.valueOf(prodetail.getPrice()), fileName, formattedDate, String.valueOf(prodetail.getWarranty()), addCategoryToProduct, String.valueOf(lb.getComId()));
        rs = cc.getProdIdByNameAndComId(Response.class, prodetail.getProductName(), String.valueOf(lb.getComId()));
        pd1 = rs.readEntity(gpd1);
        cc.addCompanyProductStock(String.valueOf(cpsdetails.getQuantity()), pd1.getProductId().toString());
        return "displayProduct";
    }

    public String editCompanyForm() {
        this.cd = cc.getCompanyById(CompanyDetails.class, lb.getComId().toString());
        return "editCompany";
    }

    public String editCompany() {
        String fileName = uploadImage();
        if (fileName.isEmpty()) {
            cc.updateCompany(cd.getCompanyId().toString(), cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), cd.getPassword(), cd.getCompanyLogo(), cd.getCountry());
        } else {
            cc.updateCompany(cd.getCompanyId().toString(), cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), cd.getPassword(), fileName, cd.getCountry());
        }
        ugc.changePassword(cd.getEmail(), cd.getPassword());
        succesMessage = "Profile Successfully Edited";
        return "companyDashboard?faces-redirect=true";
    }

    // Add Category
    public String addCategory() {
        succesMessage = "Category Successfully Inserted";
        cc.addCategory1(cdetail.getCategoryName(), String.valueOf(lb.getComId()));
        return "displayCategory";
    }

    // edit product
    public String editProductForm(ProductDetails prod) {
        this.prodetail = prod;
        this.addCategoryToProduct = prod.getCategoryId().getCategoryId().toString();
        return "editProduct";
    }

    // Update or Edit Product
    public String editProduct() {
        String fileName = uploadImage();

        succesMessage = "Product Successfully Edited";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(prodetail.getMfgDate());

        if (fileName.isEmpty()) {
            cc.updateProduct(prodetail.getProductId().toString(), prodetail.getProductName(), String.valueOf(prodetail.getDiscount()), String.valueOf(prodetail.getPrice()), prodetail.getProductImage(), formattedDate, String.valueOf(prodetail.getWarranty()), addCategoryToProduct, String.valueOf(lb.getComId()));
        } else {
            cc.updateProduct(prodetail.getProductId().toString(), prodetail.getProductName(), String.valueOf(prodetail.getDiscount()), String.valueOf(prodetail.getPrice()), fileName, formattedDate, String.valueOf(prodetail.getWarranty()), addCategoryToProduct, String.valueOf(lb.getComId()));
        }
        return "displayProduct";
    }

    // edit category
    public String editCategoryForm(CategoryDetails catd) {
        this.cdetail = catd;
        return "editCategory";
    }

    // Update or Edit Category
    public String editCategory() {
        succesMessage = "Category Successfully Edited";
//        Integer companyId = (Integer) session.getAttribute("comId");
        cc.updateCategory(String.valueOf(cdetail.getCategoryId()), cdetail.getCategoryName(), String.valueOf(lb.getComId()));
        return "displayCategory";
    }

    // get category count
    public Integer getCatCount() {
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getCategoryCount(Response.class, String.valueOf(lb.getComId()));
        catCount = rs.readEntity(Integer.class);
        return catCount;
    }

    public void setCatCount(Integer catCount) {
        this.catCount = catCount;
    }

    // get Product Count
    public Integer getProdCount() {
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getProductCount(Response.class, String.valueOf(lb.getComId()));
        prodCount = rs.readEntity(Integer.class);
        return prodCount;
    }

    public void setProdCount(Integer prodCount) {
        this.prodCount = prodCount;
    }

    // get company product stock count
    public Integer getStockCount() {
//        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getCompanyProductStockCount(Response.class, String.valueOf(lb.getComId()));
        stockCount = rs.readEntity(Integer.class);
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStoreOrderCount() {
        rs = cc.getStoreOrderCount(Response.class, String.valueOf(lb.getComId()));
        storeOrderCount = rs.readEntity(Integer.class);
        return storeOrderCount;
    }

    public void setStoreOrderCount(Integer storeOrderCount) {
        this.storeOrderCount = storeOrderCount;
    }

    public String getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(String productStocks) {
        this.productStocks = productStocks;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    // edit Product stock form
    public String editProductStockForm(CompanyProductStock prodStock) {
        this.cpsdetails = prodStock;
        this.productId = prodStock.getProductId().getProductId();
        return "editProductStock";
    }

    // edit company product stock
    public String editCompanyProductStock() {
        if (productStocks == null) {
            cc.editComapnyProductStock(String.valueOf(productId), String.valueOf(0));
        }else{
            cc.editComapnyProductStock(String.valueOf(productId), productStocks);
        }
        succesMessage = "Product Stock Successfully Added";
        return "displayProductStock";
    }
}

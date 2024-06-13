/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanUserPackage;

import CDIBeanPackage.LoginBean;
import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import RestClientPackage.userClient;
import RestClientPackage.userGroupClient;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.file.UploadedFile;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;
import javax.mail.Multipart;
import javax.mail.Session;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "homeCDI")
@RequestScoped
public class homeCDI {

    RestClientPackage.userClient uc;
    Response rs;
    Collection<ElectronicStoreSellingProduct> sellingProducts;
    GenericType<Collection<ElectronicStoreSellingProduct>> gsellingProducts;
    Collection<UserCartDetails> ucart;
    GenericType<Collection<UserCartDetails>> gucart;
    Collection<ElectronicStoreFestival> storeFestivals;
    GenericType<Collection<ElectronicStoreFestival>> gstoreFestivals;

    Collection<ElectronicStoreSellingProduct> productfordropdown;
    GenericType<Collection<ElectronicStoreSellingProduct>> gproductfordropdown;
    String prodId;
    Integer sellingProdId;
    String confirmPassword;

    UserDetails ud = new UserDetails();

    userGroupClient ugc;
    UploadedFile file;
    @Inject
    LoginBean lb;

    private String proflogo;

    Integer totalPrice;
    String errormessage;
    Integer cartCount;
    String succesMessage;
    String succesProfilemsg;

    public homeCDI() {
        uc = new userClient();
        ugc = new userGroupClient();
        sellingProducts = new ArrayList<>();
        gsellingProducts = new GenericType<Collection<ElectronicStoreSellingProduct>>() {
        };
        ucart = new ArrayList<>();
        gucart = new GenericType<Collection<UserCartDetails>>() {
        };

        storeFestivals = new ArrayList<>();
        gstoreFestivals = new GenericType<Collection<ElectronicStoreFestival>>() {
        };

        productfordropdown = new ArrayList<>();
        gproductfordropdown = new GenericType<Collection<ElectronicStoreSellingProduct>>() {
        };

        errormessage = null;
        succesMessage = null;
        succesProfilemsg = null;
    }

    public String getProflogo() {
        this.ud = uc.getUserById(UserDetails.class, lb.getComId().toString());
        proflogo = ud.getProfilePic();
        return proflogo;
    }

    public void setProflogo(String proflogo) {
        this.proflogo = proflogo;
    }

    public UserDetails getUd() {
        return ud;
    }

    public void setUd(UserDetails ud) {
        this.ud = ud;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getSuccesProfilemsg() {
        return succesProfilemsg;
    }

    public void setSuccesProfilemsg(String succesProfilemsg) {
        this.succesProfilemsg = succesProfilemsg;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getSellingProdId() {
        return sellingProdId;
    }

    public void setSellingProdId(Integer sellingProdId) {
        this.sellingProdId = sellingProdId;
    }

    // load selling product into the dropdown
    public Collection<ElectronicStoreSellingProduct> getProductfordropdown() {
        rs = uc.getAllSellingProducts(Response.class);
        productfordropdown = rs.readEntity(gproductfordropdown);
        return productfordropdown;
    }

    public void setProductfordropdown(Collection<ElectronicStoreSellingProduct> productfordropdown) {
        this.productfordropdown = productfordropdown;
    }

    public Collection<ElectronicStoreSellingProduct> getSellingProducts() {
        if (prodId != null) {
            rs = uc.getSearchSellingProducts(Response.class, prodId);
            sellingProducts = rs.readEntity(gsellingProducts);
            for (ElectronicStoreSellingProduct selProd : sellingProducts) {
                sellingProdId = selProd.getSellingProductId();
            }
            return sellingProducts;
        } else {
            rs = uc.getAllSellingProducts(Response.class);
            sellingProducts = rs.readEntity(gsellingProducts);
            return sellingProducts;
        }
    }

    public void setSellingProducts(Collection<ElectronicStoreSellingProduct> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    // file uploading function
    private String uploadImage() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("C:/Users/Admin/Desktop/sem8_Project/electronic_store_management/src/main/webapp/public/uploads/" + fileName);
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

    public void sendConfirmationEmail(String name, String recipientEmail, String cno, String pass, String dat, String gender, String logoPath, String address, String country) {
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
            message.setSubject("Welcome to " + name);

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
                    + "<img src='cid:companyLogo' alt='Logo'/>"
                    + "</div>"
                    + "<div class='content'>"
                    + "<h2>Dear " + recipientEmail + ",</h2>"
                    + "<p>Welcome to Electronic Store Management!</p>"
                    + "<p>We are thrilled to have you with us. Below are your registration details:</p>"
                    + "<ul>"
                    + "<li>Name: " + name + "</li>"
                    + "<li>Contact Number: " + cno + "</li>"
                    + "<li>Password: " + pass + "</li>"
                    + "<li>Date: " + dat + "</li>"
                    + "<li>Gender: " + gender + "</li>"
                    + "<li>Address: " + address + "</li>"
                    + "<li>Country: " + country + "</li>"
                    + "</ul>"
                    + "<p>We look forward to a great journey together.</p>"
                    + "</div>"
                    + "<div class='footer'>"
                    + "<p>Best Regards,</p>"
                    + "<p>" + name + "</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            // Create the message body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html");

            // Create the image attachment part
            MimeBodyPart imageAttachment = new MimeBodyPart();
            DataSource source = new FileDataSource(logoPath);
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String userRegister() {
        if (ud.getPassword().equals(confirmPassword)) {
            String fileName = uploadImage();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(ud.getDob());
            uc.addUserDetails(ud.getUsername(), ud.getEmail(), String.valueOf(ud.getContactNo()), ud.getPassword(), formattedDate, ud.getGender(), fileName, ud.getAddress(), ud.getCountry());
            ugc.addUser(ud.getEmail(), ud.getPassword());
            ugc.addGroup("User", ud.getEmail());

            // Call sendConfirmationEmail to send the email after user registration
            sendConfirmationEmail(ud.getUsername(), ud.getEmail(), String.valueOf(ud.getContactNo()), ud.getPassword(), formattedDate, ud.getGender(), "C:/Users/Admin/Desktop/sem8_Project/electronic_store_management/src/main/webapp/public/uploads/" + fileName, ud.getAddress(), ud.getCountry());

            FacesContext context = FacesContext.getCurrentInstance();
            try {
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/login.jsf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            succesMessage = "Password and Confirm Password Does Not Match";
            return "userRegister";
        }
        return null;
    }

    public String editUserForm() {
        this.ud = uc.getUserById(UserDetails.class, lb.getComId().toString());
        return "editUser";
    }

    public String editUser() {
        String fileName = uploadImage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(ud.getDob());
        if (fileName.isEmpty()) {
            uc.updateUserDetails(ud.getUserId().toString(), ud.getUsername(), ud.getEmail(), String.valueOf(ud.getContactNo()), ud.getPassword(), formattedDate, ud.getGender(), ud.getProfilePic(), ud.getAddress(), ud.getCountry());
        } else {
            uc.updateUserDetails(ud.getUserId().toString(), ud.getUsername(), ud.getEmail(), String.valueOf(ud.getContactNo()), ud.getPassword(), formattedDate, ud.getGender(), fileName, ud.getAddress(), ud.getCountry());
        }
        ugc.changePassword(ud.getEmail(), ud.getPassword());
        succesProfilemsg = "Profile Successfully Edited";
        return "userDashboard";
    }

    // add data in the cart table
    public void addCartDetails(Integer selprodid) {
        if (sellingProdId != null) {
            uc.addCartDetails("1", String.valueOf(sellingProdId), String.valueOf(lb.getComId()));
            sellingProdId = null;
        } else {
            uc.addCartDetails("1", String.valueOf(selprodid), String.valueOf(lb.getComId()));
        }
    }

    // display festival offers
    public Collection<ElectronicStoreFestival> getStoreFestivals() {
        rs = uc.getAllFestivalOffers(Response.class);
        storeFestivals = rs.readEntity(gstoreFestivals);
        return storeFestivals;
    }

    public void setStoreFestivals(Collection<ElectronicStoreFestival> storeFestivals) {
        this.storeFestivals = storeFestivals;
    }

    public Collection<UserCartDetails> getUcart() {
        rs = uc.getAllCartDetails(Response.class, String.valueOf(lb.getComId()));
        ucart = rs.readEntity(gucart);
        Collection<UserCartDetails> ucd = ucart;
        Collection<ElectronicStoreFestival> festivals = storeFestivals;
        totalPrice = 0;
        for (UserCartDetails u : ucd) {
            boolean festivalDiscountApplied = false;
            for (ElectronicStoreFestival f : festivals) {
                // Format the festival date to match the current date format
                String festivalDate = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy").format(f.getFestivalDate());

                if (festivalDate.equals(getCurrentDate())) {
                    totalPrice += u.getQuantity() * (u.getSellingProductId().getPrice() - (u.getSellingProductId().getPrice() * f.getFestivalDiscount() + u.getSellingProductId().getProductId().getDiscount()) / 100);
                    festivalDiscountApplied = true;
                    break;
                }
            }
            if (!festivalDiscountApplied) {
                totalPrice += u.getQuantity() * (u.getSellingProductId().getPrice() - (u.getSellingProductId().getPrice() * u.getSellingProductId().getProductId().getDiscount()) / 100);
            }
        }
        return ucart;
    }

    public void setUcart(Collection<UserCartDetails> ucart) {
        this.ucart = ucart;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getSuccesMessage() {
        return succesMessage;
    }

    public void setSuccesMessage(String succesMessage) {
        this.succesMessage = succesMessage;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy");
        return sdf.format(new Date());
    }

    public Integer getCartCount() {
        rs = uc.getCartCount(Response.class, String.valueOf(lb.getComId()));
        cartCount = rs.readEntity(Integer.class);
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

//    public void decreaseQty(Integer cid, Integer qty, Integer pid) {
//        if (qty > 1) {
//            uc.decreaseQuantity(String.valueOf(cid));
//        }
//    }
//    public void increseQty(Integer cid, Integer qty, Integer pid) {
//        rs = uc.getStoreStock(Response.class, String.valueOf(pid));
//        Integer stock = rs.readEntity(Integer.class);
//        if (qty >= stock) {
//            errormessage = "Above this quantity is not available";
//        } else {
//            uc.increaseQuantity(String.valueOf(cid));
//        }
//    }
    // remove cart item
//    public String removeCartItem(Integer cartId) {
//        uc.removeCartItem(String.valueOf(cartId));
//        return "userCart";
//    }
    // add user order
    public String addUserOrder() throws IOException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatter);

        Collection<UserCartDetails> ucd = ucart;
        Collection<ElectronicStoreFestival> festivals = storeFestivals;

        for (UserCartDetails cd : ucd) {
            boolean festivalDiscountApplied = false;
            for (ElectronicStoreFestival f : festivals) {
                String festivalDate = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy").format(f.getFestivalDate());

                if (festivalDate.equals(getCurrentDate())) {
                    uc.addUserOrder(String.valueOf(cd.getQuantity()),
                            String.valueOf(cd.getQuantity() * (cd.getSellingProductId().getPrice() -
                                    (cd.getSellingProductId().getPrice() * f.getFestivalDiscount() + cd.getSellingProductId().getProductId().getDiscount()) / 100)),
                            dateString, String.valueOf(cd.getSellingProductId().getSellingProductId()), String.valueOf(lb.getComId()));
                    festivalDiscountApplied = true;
                    break;
                }
            }
            if (!festivalDiscountApplied) {
                uc.addUserOrder(String.valueOf(cd.getQuantity()),
                        String.valueOf(cd.getQuantity() * (cd.getSellingProductId().getPrice() -
                                (cd.getSellingProductId().getPrice() * cd.getSellingProductId().getProductId().getDiscount()) / 100)),
                        dateString, String.valueOf(cd.getSellingProductId().getSellingProductId()), String.valueOf(lb.getComId()));
            }
        }

        uc.removeAllCartItems(String.valueOf(lb.getComId()));

        String home = System.getProperty("user.home");
        Path downloadsDir = Paths.get(home, "Downloads");
        if (!Files.exists(downloadsDir)) {
            Files.createDirectories(downloadsDir);
        }
        Path pdfPath = downloadsDir.resolve("order_details.pdf");
        PdfWriter writer = new PdfWriter(pdfPath.toString());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        double totalAmt = 0.0;

        String imagePath = "C:/Users/Admin/Desktop/sem8_Project/electronic_store_management/src/main/webapp/public/uploads/logo.jpeg";
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image logo = new Image(imageData);
        logo.setWidth(UnitValue.createPercentValue(20))
                .setTextAlignment(TextAlignment.LEFT);
        

        // Logo and company info side by side with space
        Paragraph companyInfo = new Paragraph()
                .add("Gada Electronic Store\n")
                .add("5V88 V3C, Aarey Colony, Goregaon\n")
                .add("Mumbai, Maharashtra 400063\n")
                .add("Phone: (123) 456-7890\n")
                .add("Email: gada@electronicstore.com")
                .setFontColor(ColorConstants.DARK_GRAY)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginLeft(250);

        Paragraph logoAndCompany = new Paragraph()
                .add(logo)
                .add(companyInfo);

        // Add space between logo and company info
        logoAndCompany.setMarginTop(20); // Adjust margin between logo and company info

        document.add(logoAndCompany);

        Paragraph title = new Paragraph("Order Details")
                .setFontSize(24)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.BLUE)
                .setMarginTop(30)
                .setMarginBottom(20);
        document.add(title);

        Paragraph date = new Paragraph("Date: " + LocalDate.now().toString())
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20);
        document.add(date);

        float[] columnWidths = {1, 4, 2, 2, 2};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell().add(new Paragraph("S.No")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Product Name")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Quantity")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Price")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Price")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));

        int serialNumber = 1;

        for (UserCartDetails cd : ucd) {
            boolean festivalDiscountApplied = false;
            double price = cd.getSellingProductId().getPrice();
            double discount = cd.getSellingProductId().getProductId().getDiscount();
            for (ElectronicStoreFestival f : festivals) {
                String festivalDate = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy").format(f.getFestivalDate());

                if (festivalDate.equals(getCurrentDate())) {
                    discount += f.getFestivalDiscount();
                    festivalDiscountApplied = true;
                    break;
                }
            }
            double finalPrice = price - (price * discount / 100);
            double totalPrice = cd.getQuantity() * finalPrice;
            totalAmt += totalPrice;

            table.addCell(new Cell().add(new Paragraph(String.valueOf(serialNumber++))));
            table.addCell(new Cell().add(new Paragraph(cd.getSellingProductId().getProductId().getProductName())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(cd.getQuantity()))));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f", finalPrice))));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f", totalPrice))));
        }

        document.add(table);

        Paragraph total = new Paragraph("Total Amount: " + String.format("%.2f", totalAmt))
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(20);
        document.add(total);

        Paragraph status1 = new Paragraph("Order Verified: Pending")
                .setFontSize(10)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontColor(ColorConstants.ORANGE)
                .setMarginTop(5);
        document.add(status1);

        Paragraph thankYouNote = new Paragraph("Thank you for shopping with us!\n\nPlease visit us again.")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(20);
        document.add(thankYouNote);

        document.close();

        succesMessage = "Your order has been successfully placed and the bill has been downloaded";
        return "userDashboard.jsf";
    }
}

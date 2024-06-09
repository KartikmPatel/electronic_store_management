/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanUserPackage;

import CDIBeanPackage.LoginBean;
import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import RestClientPackage.userClient;
import RestClientPackage.userGroupClient;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

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

    UserDetails ud = new UserDetails();

    userGroupClient ugc;
    UploadedFile file;
    @Inject
    LoginBean lb;

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

        errormessage = null;
        succesMessage = null;
        succesProfilemsg = null;
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

    public Collection<ElectronicStoreSellingProduct> getSellingProducts() {
        rs = uc.getAllSellingProducts(Response.class);
        sellingProducts = rs.readEntity(gsellingProducts);
        return sellingProducts;
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

    public String userRegister() {
        String fileName = uploadImage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(ud.getDob());
        uc.addUserDetails(ud.getUsername(), ud.getEmail(), String.valueOf(ud.getContactNo()), ud.getPassword(), formattedDate, ud.getGender(), fileName, ud.getAddress(), ud.getCountry());
        ugc.addUser(ud.getEmail(), ud.getPassword());
        ugc.addGroup("User", ud.getEmail());

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/login.jsf");
        } catch (IOException e) {
            e.printStackTrace();
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
        succesProfilemsg = "Profile Successfully Edited";
        return "userDashboard";
    }

    // add data in the cart table
    public void addCartDetails(Integer selprodid) {
        uc.addCartDetails("1", String.valueOf(selprodid), String.valueOf(lb.getComId()));
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
        String dateString = currentDate.format(formatter); // Convert LocalDate to String

        Collection<UserCartDetails> ucd = ucart;
        Collection<ElectronicStoreFestival> festivals = storeFestivals;

        for (UserCartDetails cd : ucd) {
            boolean festivalDiscountApplied = false;
            for (ElectronicStoreFestival f : festivals) {
                // Format the festival date to match the current date format
                String festivalDate = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy").format(f.getFestivalDate());

                if (festivalDate.equals(getCurrentDate())) {
                    uc.addUserOrder(String.valueOf(cd.getQuantity()), String.valueOf(cd.getQuantity() * (cd.getSellingProductId().getPrice() - (cd.getSellingProductId().getPrice() * f.getFestivalDiscount() + cd.getSellingProductId().getProductId().getDiscount()) / 100)), dateString, String.valueOf(cd.getSellingProductId().getSellingProductId()), String.valueOf(lb.getComId()));
                    festivalDiscountApplied = true;
                    break;
                }
            }
            if (!festivalDiscountApplied) {
                uc.addUserOrder(String.valueOf(cd.getQuantity()), String.valueOf(cd.getQuantity() * (cd.getSellingProductId().getPrice() - (cd.getSellingProductId().getPrice() * cd.getSellingProductId().getProductId().getDiscount()) / 100)), dateString, String.valueOf(cd.getSellingProductId().getSellingProductId()), String.valueOf(lb.getComId()));
            }
        }

        uc.removeAllCartItems(String.valueOf(lb.getComId()));

        // pdf generate and download code
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"order_details.pdf\"");
        // Generate PDF and save to Downloads folder
        String home = System.getProperty("user.home");
        Path downloadsDir = Paths.get(home, "Downloads");
        if (!Files.exists(downloadsDir)) {
            Files.createDirectories(downloadsDir);
        }
        Path pdfPath = downloadsDir.resolve("order_details.pdf");
//        OutputStream out = response.getOutputStream();
        PdfWriter writer = new PdfWriter(pdfPath.toString());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Integer totalAmt = 0;

        // Add title
        Paragraph title = new Paragraph("Order Details")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.GREEN)
                .setMarginBottom(20);
        document.add(title);

        // Add date
        Paragraph date = new Paragraph("Date: " + LocalDate.now().toString())
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(10);
        document.add(date);

        // Add content to the PDF
        Collection<UserCartDetails> ucd1 = ucart;

        for (UserCartDetails cd : ucd1) {
            document.add(new Paragraph("Product Name: " + cd.getSellingProductId().getProductId().getProductName())
                    .setMarginBottom(5));
            document.add(new Paragraph("Quantity: " + cd.getQuantity())
                    .setMarginBottom(5));
            boolean festivalDiscountApplied = false;
            for (ElectronicStoreFestival f : festivals) {
                // Format the festival date to match the current date format
                String festivalDate = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy").format(f.getFestivalDate());

                if (festivalDate.equals(getCurrentDate())) {
                    document.add(new Paragraph("Price: " + (cd.getSellingProductId().getPrice()
                            - (cd.getSellingProductId().getPrice() * f.getFestivalDiscount() + cd.getSellingProductId().getProductId().getDiscount()) / 100))
                            .setMarginBottom(5));
                    document.add(new Paragraph("Total Price: " + cd.getQuantity() * (cd.getSellingProductId().getPrice()
                            - (cd.getSellingProductId().getPrice() * f.getFestivalDiscount() + cd.getSellingProductId().getProductId().getDiscount()) / 100))
                            .setMarginBottom(10));

                    totalAmt += cd.getQuantity() * (cd.getSellingProductId().getPrice()
                            - (cd.getSellingProductId().getPrice() * f.getFestivalDiscount() + cd.getSellingProductId().getProductId().getDiscount()) / 100);
                    festivalDiscountApplied = true;
                    break;
                }
            }
            if (!festivalDiscountApplied) {
                document.add(new Paragraph("Price: " + (cd.getSellingProductId().getPrice()
                        - (cd.getSellingProductId().getPrice() * cd.getSellingProductId().getProductId().getDiscount()) / 100))
                        .setMarginBottom(5));
                document.add(new Paragraph("Total Price: " + cd.getQuantity() * (cd.getSellingProductId().getPrice()
                        - (cd.getSellingProductId().getPrice() * cd.getSellingProductId().getProductId().getDiscount()) / 100))
                        .setMarginBottom(10));

                totalAmt += cd.getQuantity() * (cd.getSellingProductId().getPrice()
                        - (cd.getSellingProductId().getPrice() * cd.getSellingProductId().getProductId().getDiscount()) / 100);

            }
        }

        // Add total amount
        Paragraph total = new Paragraph("Total Amount: " + totalAmt)
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(20);
        document.add(total);

        Paragraph status1 = new Paragraph("Order Verified: " + "Pending")
                .setFontSize(10)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontColor(ColorConstants.ORANGE)
                .setMarginTop(5);
        document.add(status1);

        document.close();

        succesMessage = "Your order has been successfully placed, and the bill has been downloaded";
        return "userDashboard.jsf";
    }
}

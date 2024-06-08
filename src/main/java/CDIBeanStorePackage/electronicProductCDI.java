/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanStorePackage;

import EntityPackage.ElectronicStoreDetails;
import EntityPackage.ProductDetails;
import RestClientPackage.storeClient;
import RestClientPackage.userGroupClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
@Named(value = "electronicProductCDI")
@RequestScoped
public class electronicProductCDI {

    RestClientPackage.storeClient sc;
    userGroupClient ugc;
    Response rs;
    Collection<ProductDetails> pdetails;
    GenericType<Collection<ProductDetails>> gpdetails;
    UploadedFile file;
    
    ElectronicStoreDetails esd = new ElectronicStoreDetails();

    ProductDetails prod = new ProductDetails();
    Integer quantity;
    Integer totalPrice;
    Integer comId;
    Integer stock;

    // for Error Message
    String succesMessage, errorMessage;

    public electronicProductCDI() {
        sc = new storeClient();
        ugc = new userGroupClient();
        
        pdetails = new ArrayList<>();
        gpdetails = new GenericType<Collection<ProductDetails>>() {
        };
        succesMessage = null;
        errorMessage = null;
    }

    public ElectronicStoreDetails getEsd() {
        return esd;
    }

    public void setEsd(ElectronicStoreDetails esd) {
        this.esd = esd;
    }

    public Collection<ProductDetails> getPdetails() {
        rs = sc.getAllProducts(Response.class);
        pdetails = rs.readEntity(gpdetails);
        return pdetails;
    }

    public void setPdetails(Collection<ProductDetails> pdetails) {
        this.pdetails = pdetails;
    }

    public ProductDetails getProd() {
        return prod;
    }

    public void setProd(ProductDetails prod) {
        this.prod = prod;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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
    
    public String purchaseElecProduct(ProductDetails prod) {
        this.prod = prod;
        this.comId = prod.getCompanyId().getCompanyId();
        rs = sc.getQuantity(Response.class, String.valueOf(prod.getProductId()));
        this.stock = rs.readEntity(Integer.class);
        return "purchaseElecProduct.jsf";
    }

//    public void updateTotalPrice(AjaxBehaviorEvent event,Integer price,Integer discount) {
//        if (quantity != null) {
//            totalPrice = price * quantity;
//        } else {
//            totalPrice = 0;
//        }
//    }
    
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
    
    //add Store Orders
    public String addOrder(Integer prod1) {
        rs = sc.getQuantity(Response.class, String.valueOf(prod.getProductId()));
        Integer checkqty = rs.readEntity(Integer.class);
        if (quantity != null && quantity > 0) {
            if (quantity > checkqty) {
                errorMessage = "That much Quantity is not available";
                rs = sc.getProductById(Response.class, String.valueOf(prod1));
                this.prod = rs.readEntity(ProductDetails.class);
                this.comId = prod.getCompanyId().getCompanyId();
                this.stock = checkqty;
                return "purchaseElecProduct";
            } else {
                succesMessage = "Order Successfully placed";
                Integer billamt = quantity * (prod.getPrice() - (prod.getPrice() * prod.getDiscount()) / 100);
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateString = currentDate.format(formatter); // Convert LocalDate to String

                sc.addOrder(String.valueOf(quantity), String.valueOf(billamt), dateString, "0", String.valueOf(prod.getProductId()), String.valueOf(comId));
                return "displayAllProducts";
            }
        } else {
            errorMessage = "Quantity is Required or required 1 or more than 1 quantity";
            rs = sc.getProductById(Response.class, String.valueOf(prod1));
            this.prod = rs.readEntity(ProductDetails.class);
            this.comId = prod.getCompanyId().getCompanyId();
            this.stock = checkqty;
            return "purchaseElecProduct";
        }
    }
    
    public String editStoreForm(){
        this.esd = sc.getStoreById(ElectronicStoreDetails.class);
        return "editStore";
    }
    
    public String editStore() {
        String fileName = uploadImage();
        if (fileName.isEmpty()) {
            sc.updateElectronicStore(esd.getStoreName(), esd.getEmail(), String.valueOf(esd.getContactNo()), esd.getPassword(), esd.getStoreLogo(), esd.getAddress(), esd.getCountry());
        } else {
            sc.updateElectronicStore(esd.getStoreName(), esd.getEmail(), String.valueOf(esd.getContactNo()), esd.getPassword(), fileName, esd.getAddress(), esd.getCountry());
        }
        ugc.changePassword(esd.getEmail(), esd.getPassword());
        succesMessage = "Profile Successfully Edited";
        return "storeDashboard";
    }
}

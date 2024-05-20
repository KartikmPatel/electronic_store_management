/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanUserPackage;

import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.UserDetails;
import RestClientPackage.userClient;
import RestClientPackage.userGroupClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
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
    UserDetails ud = new UserDetails();
    
    userGroupClient ugc;
    UploadedFile file;
    
    public homeCDI() {
        uc = new userClient();
        ugc = new userGroupClient();
        sellingProducts = new ArrayList<>();
        gsellingProducts = new GenericType<Collection<ElectronicStoreSellingProduct>>(){};
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
    
    public String userRegister(){
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
}

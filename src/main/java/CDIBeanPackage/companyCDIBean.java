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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
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
    ProductDetails prodetail = new ProductDetails();
    CategoryDetails cdetail = new CategoryDetails();
    CompanyProductStock cpsdetails = new CompanyProductStock();
    
    RestClientPackage.companyClient cc;
    private String email, password;
    UploadedFile file;
    String errorStatus;

    private String addCategoryToProduct;

    Collection<ProductDetails> prodt;
    GenericType<Collection<ProductDetails>> gprodt;

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

        prodt = new ArrayList<>();
        gprodt = new GenericType<Collection<ProductDetails>>() {
        };
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

    //display Product
    public Collection<ProductDetails> getProdt() {
        Integer companyId = (Integer) session.getAttribute("comId");
        rs = cc.getAllProduct(Response.class, companyId.toString());
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

    // Register Company
    public String addCompany() {
        String fileName = uploadImage();

        cc.addCompany(cd.getCompanyName(), cd.getEmail(), String.valueOf(cd.getContactNo()), cd.getPassword(), fileName, cd.getCountry());
        return "companyLogin?faces-redirect=true";
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

    //Add Product  
    public String addProduct() {
        String fileName = uploadImage();
        ProductDetails pd1 = new ProductDetails();
        GenericType<ProductDetails> gpd1 = new GenericType<ProductDetails>(){};
        
        session.setAttribute("successmessage", "Product Successfully Inserted");
        Integer companyId = (Integer) session.getAttribute("comId");

        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(prodetail.getMfgDate());

        cc.addProduct(prodetail.getProductName(), String.valueOf(prodetail.getDiscount()), String.valueOf(prodetail.getPrice()), fileName, formattedDate, String.valueOf(prodetail.getWarranty()), addCategoryToProduct, companyId.toString());
        rs = cc.getProdIdByNameAndComId(Response.class,prodetail.getProductName(), companyId.toString());
        pd1 = rs.readEntity(gpd1);
        cc.addCompanyProductStock(String.valueOf(cpsdetails.getQuantity()),pd1.getProductId().toString());
        return "displayProduct";
    }

    // Add Category
    public String addCategory() {
        session.setAttribute("successmessage", "Product Successfully Inserted");
        Integer companyId = (Integer) session.getAttribute("comId");
        cc.addCategory(cdetail.getCategoryName(), companyId.toString());
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
                
        session.setAttribute("successmessage", "Product Successfully Edited");
        Integer companyId = (Integer) session.getAttribute("comId");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(prodetail.getMfgDate());

        cc.updateProduct(prodetail.getProductId().toString(), prodetail.getProductName(), String.valueOf(prodetail.getDiscount()), String.valueOf(prodetail.getPrice()), fileName, formattedDate, String.valueOf(prodetail.getWarranty()), addCategoryToProduct, companyId.toString());
        return "displayProduct";
    }

    // edit category
    public String editCategoryForm(CategoryDetails catd) {
        this.cdetail = catd;
        return "editCategory";
    }

    // Update or Edit Category
    public String editCategory() {
        session.setAttribute("successmessage", "Category Successfully Edited");
        Integer companyId = (Integer) session.getAttribute("comId");
        cc.updateCategory(String.valueOf(cdetail.getCategoryId()), cdetail.getCategoryName(), companyId.toString());
        return "displayCategory";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import EntityPackage.CompanyDetails;
import JWTAuth.KeepRecord;
import RestClientPackage.companyClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    SecurityContext sc;

    boolean checkrem;

    public boolean getCheckrem() {
        return checkrem;
    }

    public void setCheckrem(boolean checkrem) {
        this.checkrem = checkrem;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public companyClient getRc() {
        return rc;
    }

    public void setRc(companyClient rc) {
        this.rc = rc;
    }

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }

    /**
     * Creates a new instance of LoginBean
     */
    companyClient rc;
    boolean rememberme;
    String username, password;
    String errorstatus;
    Integer comId;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String pth1() {
        return rc.path();
    }

    public String pth2() {
        return rc.path2();
    }

    public LoginBean() {
        rc = new companyClient();
        errorstatus = null;
    }

    public void login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("login.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        username = "";
        password = "";
        comId = null;

//        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        Cookie[] cookies1 = req.getCookies();
//        if (cookies1 != null) {
//            for (Cookie cookie : cookies1) {
//                if ("username".equals(cookie.getName())) {
//                    username = cookie.getValue();
//                }
//                if ("password".equals(cookie.getName())) {
//                    password = cookie.getValue();
//                }
//                if ("rememberme".equals(cookie.getName())) {
//                    checkrem = Boolean.parseBoolean(cookie.getValue());
//                }
//            }
//        }

        KeepRecord.reset();
        KeepRecord.setLogout(true);
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("/electronic_store_management/login.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

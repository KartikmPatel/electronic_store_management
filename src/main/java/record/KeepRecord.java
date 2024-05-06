/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package record;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.security.enterprise.CallerPrincipal;

/**
 *
 * @author leap
 */
@Named(value = "keepRecord")
@SessionScoped
public class KeepRecord implements Serializable {

    public static boolean isLogout() {
        return logout;
    }

    public static void setLogout(boolean logout) {
        KeepRecord.logout = logout;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        KeepRecord.token = token;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        KeepRecord.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        KeepRecord.password = password;
    }

    public static CallerPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(CallerPrincipal principal) {
        KeepRecord.principal = principal;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        KeepRecord.roles = roles;
    }
    
    public static void reset(){
        principal = null;
        roles = new HashSet<>();
        username="";
        password="";
        token="";
        logout=false;
    }
    /**
     * Creates a new instance of KeepRecord
     */
    static String username="";
    static String password="";
    static CallerPrincipal principal;
    static Set<String> roles;
    static String token="";
    static boolean logout=false;    
    public KeepRecord() {
       
    }
}

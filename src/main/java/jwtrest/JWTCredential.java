/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jwtrest;

import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;

/**
 *
 * @author leap
 */
public class JWTCredential implements Credential,Serializable{

    public JWTCredential(String principal, Set<String> roles) {
        this.principal = principal;
        this.roles = roles;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getRoles() {
        return roles;
    }
    public final String principal;
    public final Set<String> roles;
    
}

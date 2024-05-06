/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package security;

import CDIBeanPackage.LoginBean;
import RestClientPackage.companyClient;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jwtrest.Constants.AUTHORIZATION_HEADER;
import static jwtrest.Constants.BEARER;
import jwtrest.JWTCredential;
import jwtrest.TokenProvider;
import record.KeepRecord;

/**
 *
 * @author leap
 */
@Named(value = "secureAuthentiacation")
@SessionScoped
public class SecureAuthentiacation implements HttpAuthenticationMechanism,Serializable {

    /**
     * Creates a new instance of SecureAuthentiacation
     */
    public SecureAuthentiacation() {
    }
    @Inject IdentityStoreHandler sh;
    companyClient cc = new companyClient();
    CredentialValidationResult cres;
    AuthenticationStatus status;
    @Inject TokenProvider tp;
    @Inject LoginBean lb;
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest hsr, HttpServletResponse hsr1, HttpMessageContext hmc) throws AuthenticationException {
        String token = extractToken(hsr);
        if(token!=null){
        token = token.substring(7);
            if(tp.validateToken(token)){
                JWTCredential cred = tp.getCredential(token);
//                if(cred.getRoles().contains("Company")){
//                    lb.setComId(cc.getCompanyId(Integer.class, cred.getPrincipal()));
//                    System.out.println(cc.getCompanyId(Integer.class, cred.getPrincipal()));
//                }
                KeepRecord.setToken(token);
                KeepRecord.setPrincipal(new CallerPrincipal(cred.getPrincipal()));
                KeepRecord.setRoles(cred.getRoles());
                status = hmc.notifyContainerAboutLogin(KeepRecord.getPrincipal(),KeepRecord.getRoles());
                return status;
            }
        }
        try{
            if(lb.getUsername()!=null && lb.getPassword()!=null){
                String user = lb.getUsername();
                String pass = lb.getPassword();
                Credential cred = new UsernamePasswordCredential(user, new Password(pass));
                cres = sh.validate(cred);
                if(cres.getStatus()==VALID){
                    status = hmc.notifyContainerAboutLogin(cres);
                    status = createToken(cres, hmc);
//                    if(cres.getCallerGroups().contains("Company")){
//                        lb.setComId(cc.getCompanyId(Integer.class, user));
//                        System.out.println("---------------------------------------------------");
//                        System.out.println(cc.getCompanyId(Integer.class,user));
//                        System.out.println(lb.getComId());
//                        System.out.println("---------------------------------------------------");
//                    }
                    KeepRecord.setRoles(cres.getCallerGroups());
                    KeepRecord.setPrincipal(cres.getCallerPrincipal());
                    return status;
                }
                else{
                    lb.setErrorstatus("Username or Password is Incorrect...");
                }
            }
            else if(KeepRecord.getRoles()!=null && KeepRecord.getPrincipal()!=null){
                status = hmc.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
                return status;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return hmc.doNothing();
    }
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tp.validateToken(token)) {
                JWTCredential credential = tp.getCredential(token);
                System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getRoles());
            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result the result from validation of UsernamePasswordCredential
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
            // if (true) {
             String jwt = tp.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            //context.getRequest().getSession().setAttribute("token", jwt);
            KeepRecord.setToken(jwt);
            //context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
            System.out.println("Token Value"+ jwt);
          
        System.out.println("JWTAuthenticationMechanism - Token Created");
        
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    public String extractToken(HttpServletRequest request) {
       
       /*Cookie cookie = (Cookie) request.getServletContext().getAttribute(AUTHORIZATION_HEADER);
       if(cookie!=null){
           return cookie.getValue();
       } 
       */
       Cookie[] cookies = request.getCookies();
       if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
          //  System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }
}

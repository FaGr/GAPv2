
package enterprise.jsf_jpa_war;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * A simple managed bean to mediate between the user and the persistence Layer
 * @author anon
 */
public class userManagerBean {
    /**
     * The key for the session scoped attribute
 holding the appropriate Wuser instance.
     */
    public static final String USER_SESSION_KEY = "user";
    /**
     * The PersistenceContext.
     * Entities are managed by javax.persistence.EntityManager instance using 
     * persistence context.
     * Each EntityManager instance is associated with a persistence context.
     * Within the persistence context, the entity instances and their lifecycle 
     * are managed.
     * Persistence context defines a scope under which particular entity 
     * instances are created, persisted, and removed.
     * A persistence context is like a cache which contains a set of persistent 
     * entities ,so once the transaction is finished, all persistent objects 
     * are detached from the EntityManager's persistence context and are 
     * no longer managed.
     */
    @PersistenceContext 
    private EntityManager em;
    /**
     * The transaction resource.
     */
    @Resource 
    private UserTransaction utx;
    /**
     * User properties.
     */
    private String mail;
    private String password;
    //private String passwordv;
    private String fname;
    private String lname;  
    private String status;
    // -------------------------------------------------------------- Properties
    public String getMail() {
        return mail;
    }    
    public void setMail(String mail) {
        this.mail = mail;
    }    
    public String getStatus(){
        return status;
    }    
    public void setStatus(String status){
        this.status = status;
    }    
    //return true se e' Professore
    public boolean checkStatus(){
        return (this.status.equals("Professore"));
    }//quindi disabled=true, studente non puo' prenotare
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*public String getPasswordv() {
        return passwordv;
    }
    public void setPasswordv(String passwordv) {
        this.passwordv = passwordv;
    }
    */
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    // ---------------------------------------------------------- Public Methods
    /**
     * Validates the user.  If the user doesn't exist or the password
     * is incorrect, the appropriate message is added to the current
     * FacesContext. If the user successfully authenticates,
     * navigate them to the page referenced by the outcome app-main.
     *
     * @return app-main if the user authenticates, otherwise
     *  returns null
     */
    public String validateUser() {   
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser user = getUser(); //da dove esce la getUser ???
        if (user != null) {
            if (!user.getPassword().equals(password)) {
            FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Login Failed!",
                                    "The password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }
            /*else if(!user.getStatus().equals(status)){
            FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Login Failed!",
                                    "The status is wrong.");
                context.addMessage(null, message);
                return null;
            }
            */
        context.getExternalContext().getSessionMap().put(USER_SESSION_KEY,user);
        return "app-main";
        }else{           
            FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                             "Login Failed!",
                                             "Mail '"+mail+"' does not exist.");
            context.addMessage(null, message);
            return null;
        }
    } 
    /**
     * Creates a new Wuser.If the specified user name exists
 or an error occurs when persisting the Wuser instance,
 enqueue a message detailing the problem to the FacesContext.
     * If the user is created, move the user back to the login view.
     *
     * @return login if the user is created, otherwise returns null
     */
    public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser wuser = getUser();
        if (wuser == null) {
            /*  if (!password.equals(passwordv)) {
                FacesMessage message = new FacesMessage("The specified password"
                                           + " do not match. Please try again");
                context.addMessage(null, message);
                return null;
            }
            */
            /**
            * checks if the inserted mail is a valid mail
            */
            EmailValidator_ truemail = new EmailValidator_();
            if(!truemail.validate(mail)){
                FacesMessage message = new FacesMessage("The specified mail is "
                                              + "not valid.  Please try again");
                context.addMessage(null, message);
                return null;
            }
            /**
            * divide mail in 2 strings,one before and other after the @ symbol,
            * then check if the second part of the email is unipi.it -> Doc.
            */
            String [] splits = mail.split("\\@");
            boolean mailprof = splits[1].equals("unipi.it"); 
            // prof is true if email is a doc email (ends with @unipi.it)
            boolean prof = status.equals("Professore");
            //TODO SISTEMARE: non va bene il controllo ?_?
            if(prof){
                if(!mailprof){
                   FacesMessage message = new FacesMessage("The specified mail "
                         + "is not valid, if you are prof must are @unipi.it.  "
                                                          + "Please try again");
                   context.addMessage(null, message);
                   return null;
                }
            }
            
            wuser = new Wuser();
            wuser.setFirstname(fname);
            wuser.setLastname(lname);
            wuser.setMail(mail);
            wuser.setStatus(status);
            wuser.setPassword(password);
            try {
                utx.begin();
                em.persist(wuser);
                utx.commit();
                return "login";
            }catch (Exception e) {               
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                         "Error creating user!",
                                "Unexpected error when creating your account.  "
                                   + "Please contact the system Administrator");
                context.addMessage(null, message);
    Logger.getAnonymousLogger().log(Level.SEVERE,"Unable to create new user",e);
                return null;
            }
        } else {           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                    "Mail '"
                                                      + mail 
                                                      + "' already exists!  ",
                                            "Please choose a different mail.");
            context.addMessage(null, message);
            return null;
        }        
    }
    /**
     * When invoked, it'll invalidate the user's session and move them to the 
     * login view.
     *
     * @return login
     */
    public String logout() {
     HttpSession session = (HttpSession)
       FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";   
    }
    // --------------------------------------------------------- Private Methods
    /**
     * This will attempt to lookup a Wuser object based on the provided
 user name.
     *
     * @return a Wuser object associated with the current  mail, otherwise, 
 if no Wuser can be found,returns null.
     */
    private Wuser getUser(){
        try {
            Wuser user = (Wuser)
            em.createNamedQuery("Wuser.findByMail").setParameter("mail", mail)
                                                             .getSingleResult();
            return user; 
        }catch (NoResultException nre){return null;
        }
    }  
}
//---------------------------------------------------------------- validate mail
class EmailValidator {
	private Pattern pattern;
	private Matcher matcher;
        
	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator(){pattern = Pattern.compile(EMAIL_PATTERN);}
	/**
	 * Validate hex with regular expression
	 * @param hex for validation
	 * @return true  if hex is valid, false otherwise.
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}

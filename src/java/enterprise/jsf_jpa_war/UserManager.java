
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
 * <p>A simple managed bean to mediate between the user
 * and the persistence layer.</p>
 * @author rlubke
 */
public class UserManager {
    
    /**
     * <p>The key for the session scoped attribute holding the
     * appropriate <code>Wuser</code> instance.</p>
     */
    public static final String USER_SESSION_KEY = "user";
    
    /**
     * <p>The <code>PersistenceContext</code>.</p>
     */
    @PersistenceContext 
    private EntityManager em;
    
    /**
     * <p>The transaction resource.</p>
     */
    @Resource 
    private UserTransaction utx;
    
    /**
     * <p>User properties.</p>
     */
    private String mail;
    private String password;
    private String passwordv;
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
    
    //return true se e' professore
    public boolean checkStatus(){
        return (this.status.equals("professore"));
    }//quindi disabled=true, studente non puo' prenotare
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordv() {
        return passwordv;
    }
    
    public void setPasswordv(String passwordv) {
        this.passwordv = passwordv;
    }
    
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
     * <p>Validates the user.  If the user doesn't exist or the password
     * is incorrect, the appropriate message is added to the current
     * <code>FacesContext</code>.  If the user successfully authenticates,
     * navigate them to the page referenced by the outcome <code>app-main</code>.
     * </p>
     *
     * @return <code>app-main</code> if the user authenticates, otherwise
     *  returns <code>null</code>
     */
    public String validateUser() {   
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser user = getUser();
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                           "Login Failed!",
                                           "The password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }
            //non controllo lo stato quando faccio il login, controllo solo username e passwords
            /*else if(!user.getStatus().equals(status)){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                           "Login Failed!",
                                           "The status is wrong.");
                context.addMessage(null, message);
                return null;
            }*/
            
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, user);
            return "app-main";
        } else {           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "Mail '"
                    + mail
                    +
                    "' does not exist.");
            context.addMessage(null, message);
            return null;
        }
    }
    
    /**
     * <p>Creates a new <code>Wuser</code>.  If the specified user name exists
     * or an error occurs when persisting the Wuser instance, enqueue a message
     * detailing the problem to the <code>FacesContext</code>.  If the 
     * user is created, move the user back to the login view.</p>
     *
     * @return <code>login</code> if the user is created, otherwise
     *  returns <code>null</code>
     */
    public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser wuser = getUser();
        if (wuser == null) {
            if (!password.equals(passwordv)) {
                FacesMessage message = new FacesMessage("The specified passwords do not match.  Please try again");
                context.addMessage(null, message);
                return null;
            }
            
            //validate a mail
            EmailValidator truemail = new EmailValidator();
            if(!truemail.validate(mail)){
                FacesMessage message = new FacesMessage("The specified mail is not valid.  Please try again");
                context.addMessage(null, message);
                return null;
            }
            
            ///////////////////////////////////////////////////////////////////////////////////////////
            
            String [] splits = mail.split("\\@"); //divide a mail in 2 string, before and after a @
            boolean mailprof = splits[1].equals("unipi.it"); //true if is a prof
            
    //in fase di creazione voglio sapere lo status dell'utente per i permessi futuri        
            boolean prof = status.equals("professore");//true se professore
            //se sono prof la mail deve avere dominio unipi.it
            //TODO SISTEMARE: non va bene il controllo
            if(prof){//se sono professore
                if(!mailprof){//ma non ho messo un dominio da prof
                   FacesMessage message = new FacesMessage("The specified mail is not valid, if you are prof must are @unipi.it.  Please try again");
                   context.addMessage(null, message);
                   return null;
                }
            }
            
            ///////////////////////////////////////////////////////////////////////////////////////////
            
            wuser = new Wuser();
            wuser.setFirstname(fname);
            wuser.setLastname(lname);
            wuser.setMail(mail);
            wuser.setStatus(status);
            wuser.setPassword(password);
 //           wuser.setSince(new Date());
            try {
                utx.begin();
                em.persist(wuser);
                utx.commit();
                return "login";
            } catch (Exception e) {               
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                        "Error creating user!",
                                                        "Unexpected error when creating your account.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                                                "Unable to create new user",
                                                e);
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
     * <p>When invoked, it will invalidate the user's session
     * and move them to the login view.</p>
     *
     * @return <code>login</code>
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
     * <p>This will attempt to lookup a <code>Wuser</code> object
     * based on the provided user name.</p>
     *
     * @return a <code>Wuser</code> object associated with the current
     *  mail, otherwise, if no <code>Wuser</code> can be found,
     *  returns <code>null</code>
     */
    private Wuser getUser() {
        try {
            Wuser user = (Wuser)
            em.createNamedQuery("Wuser.findByMail").
                    setParameter("mail", mail).getSingleResult();
            return user; 
        } catch (NoResultException nre) {
            return null;
        }
    }
   
}


//-------------------------------------------------------------------
// validate mail

class EmailValidator_ {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator_() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}

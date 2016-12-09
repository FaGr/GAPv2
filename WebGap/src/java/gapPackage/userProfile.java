package gapPackage;

import java.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class userProfile implements Serializable {

	@Id
	//private final String id;
	private static String name;
	private static String emailU; //rimosso final
        private static String passwordU; //rimosso final
    
        @Id
        private Long id; //creato dai suggerimenti altrimenti dava errore
	
	public userProfile(String email, String password) {
            //this();
            //this.email= email;
            //this.password = password;
            emailU = email;
            passwordU = password;
            String [] s= email.split("\\@");
            name = s[0];
	}
	
	/*public String getId() {
		return id;
	}*/
	
	public static String getEmail() {
		return emailU;
	}
        
        public static String getName(){
            return name;
        }
	
        public static String getPassword(){
            return passwordU;
        }
        
        //come parametro ha la mail che deve controllare, prof oppure studente?
	public boolean getPosition(String email) {
            
                String [] splits = email.split("\\@"); //divide a mail in 2 string, before and after a @
		boolean prof = splits[1].equals("unipi.it"); //true if is a prof
                
		/*if (prof) {
                    return true;
                }
		else {
                    return false;
		}*/
                return prof;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

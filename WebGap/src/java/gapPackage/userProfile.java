package gapPackage;

import java.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class userProfile implements Serializable {

	@Id
	private final String id;
	private String name;
	private final String email;
	
	public userProfile(String id, String email) {
            //this();
            this.id = id;
            this.email= email;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean getPosition(String email) {
		
		String uni_email = new String("unipi.it");
		boolean prof = uni_email.equals(email);
		if (prof) {
                    return true;
                }
		else {
                    return false;
		}
        }
}

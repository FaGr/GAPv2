
import java.*

@Entity
public class userProfile {

	@Id
	private String id;
	private String name;
	private String email;
	
	public userProfile(String id, String email) {
            this();
            this.id = id;
			this.email= email;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public bool getPosition(email) {
		
		private static final Pattern rfc2822 = Pattern.compile ( "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$" );

		if (!rfc2822.matcher(email).matches()) {
												//throw new Exception("Invalid address");
		} else {
		String uni_email = new String("unipi.it");
		bool prof = uni_email.equals(email);
		if (prof) return 1
		else return 0
		}
	}
	
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

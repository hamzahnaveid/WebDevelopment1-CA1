import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class viewProfileAction {
	
	private String profileSearch;
	int profileId;
	private String profileEmail;
	
	public viewProfileAction() {
		
	}
	
	public String viewProfile() {
		String result = "error";

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement getProfile = null;
		try {
			getProfile = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = getProfile.executeQuery("SELECT user_id, email FROM user WHERE username = '" + profileSearch + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				result = "success";
				profileId = rs.getInt(1);
				profileEmail = rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getProfileSearch() {
		return profileSearch;
	}

	public void setProfileSearch(String profileSearch) {
		this.profileSearch = profileSearch;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getProfileEmail() {
		return profileEmail;
	}

	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registerAction {
	
	private String usernameReg;
	private String emailReg;
	private String passwordReg;
	private String passwordAgain;
	
	public registerAction() {

	}
	
	public String registerUser() {
		String result = "success";
		
		if(!(passwordReg.equals(passwordAgain))) {
			return "error";
		}
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement createVote = null;
		try {
			createVote = connection.prepareStatement(
			"INSERT into user "
			+ "(username, password, email)" +" VALUES (?, ?, ?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}
		//pass in the values as parameters
         try {
			createVote.setString(1, usernameReg);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
			createVote.setString(2, passwordReg);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
 			createVote.setString(3, emailReg);
 		} catch (SQLException e) {
 			result = "error";
 			e.printStackTrace();
 		}
		
		try {
			int rowsUpdated = createVote.executeUpdate();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		try {
			createVote.close();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		
		return result;
	}

	public String getUsernameReg() {
		return usernameReg;
	}

	public void setUsernameReg(String usernameReg) {
		this.usernameReg = usernameReg;
	}

	public String getEmailReg() {
		return emailReg;
	}

	public void setEmailReg(String emailReg) {
		this.emailReg = emailReg;
	}

	public String getPasswordReg() {
		return passwordReg;
	}

	public void setPasswordReg(String passwordReg) {
		this.passwordReg = passwordReg;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}
}

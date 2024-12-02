import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		if(usernameReg.equals("") || emailReg.equals("") || passwordReg.equals("") || passwordAgain.equals("")) {
			return "error";
		}
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//check if user already exists in db
		Statement checkUser = null;
		try {
			checkUser = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = checkUser.executeQuery("SELECT username, email FROM user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				if(rs.getString(1).equalsIgnoreCase(usernameReg) || rs.getString(2).equalsIgnoreCase(emailReg)) {
					return "error";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement createUser = null;
		try {
			createUser = connection.prepareStatement(
			"INSERT into user "
			+ "(username, password, email)" +" VALUES (?, ?, ?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}
		//pass in the values as parameters
         try {
			createUser.setString(1, usernameReg);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
			createUser.setString(2, passwordReg);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
 			createUser.setString(3, emailReg);
 		} catch (SQLException e) {
 			result = "error";
 			e.printStackTrace();
 		}
		
		try {
			int rowsUpdated = createUser.executeUpdate();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		try {
			createUser.close();
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class loginAction extends ActionSupport implements SessionAware {
	
	private String email;
	private String password;
	private String username;
	Map m;
	
	public loginAction() {
		
	}
	
	public String loginUser() {
		String result = "error";
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement checkLogin = null;
		try {
			checkLogin = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = checkLogin.executeQuery("SELECT username, email, password FROM user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				if(rs.getString(2).equalsIgnoreCase(email)&&rs.getString(3).equalsIgnoreCase(password)) {
					result = "success";
					username = rs.getString(1);
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.put("name", username);
		return result;
	}
	
	@Override
	public void setSession(Map m) {
		// TODO Auto-generated method stub
		this.m = m;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class addItemAction implements SessionAware {
		private String itemname;
		private String startbid;
		private int sellerid;
		private String currentuser;
		Map m;
		
		public addItemAction() {
			
		}
		
		public String addItem() {
			String result = "success";
			float bid = Float.parseFloat(startbid);
			if(bid < 0.01 || itemname.equals("")) {
				return "error";
			}
			currentuser = (String) m.get("name");
			
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//get current user id from db
			Statement getId = null;
			try {
				getId = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet rs = null;
			try {
				rs = getId.executeQuery("SELECT user_id FROM user WHERE username = '" + currentuser + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					sellerid = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//add item into db
			PreparedStatement createItem = null;
			try {
				createItem = connection.prepareStatement(
				"INSERT into item "
				+ "(item_name, current_bid, seller_id)" +" VALUES (?, ?, ?)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = "error";
				e.printStackTrace();
			}
			//pass in the values as parameters
	         try {
	        	 createItem.setString(1, itemname);
			} catch (SQLException e) {
				result = "error";
				e.printStackTrace();
			}
	         try {
	        	 createItem.setFloat(2, bid);
			} catch (SQLException e) {
				result = "error";
				e.printStackTrace();
			}
	         try {
	        	 createItem.setInt(3, sellerid);
	 		} catch (SQLException e) {
	 			result = "error";
	 			e.printStackTrace();
	 		}
			
			try {
				int rowsUpdated = createItem.executeUpdate();
			} catch (SQLException e) {
				result = "error";
				e.printStackTrace();
			}
			try {
				createItem.close();
			} catch (SQLException e) {
				result = "error";
				e.printStackTrace();
			}
			
			return result;
		}
		
		public String getItemname() {
			return itemname;
		}
		
		public void setItemname(String itemname) {
			this.itemname = itemname;
		}
		
		public String getStartbid() {
			return startbid;
		}
		
		public void setStartbid(String startbid) {
			this.startbid = startbid;
		}
		
		public int getSellerid() {
			return sellerid;
		}
		
		public void setSellerid(int sellerid) {
			this.sellerid = sellerid;
		}
		
		public String getCurrentuser() {
			return currentuser;
		}
		
		public void setCurrentuser(String currentuser) {
			this.currentuser = currentuser;
		}

		@Override
		public void setSession(Map m) {
			// TODO Auto-generated method stub
			this.m = m;
		}
}

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class makeBidAction implements SessionAware{
	
	private String itemid;
	private String bidamnt;
	Map m;
	
	public makeBidAction() {
		
	}
	
	public String makeBid() {
		String result = "success";
		
		if(bidamnt.equals("") || itemid.equals("")) {
			return "error";
		}
		
		int id = Integer.parseInt(itemid);
		float placedbid = Float.parseFloat(bidamnt);
		float currentbid = 0;
		
		String currentuser = (String) m.get("name");
		int bidderid = 0;
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//get current bid of item from db
		Statement getBid = null;
		try {
			getBid = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = getBid.executeQuery("SELECT current_bid FROM item WHERE item_id = '" + id + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		try {
			while(rs.next()) {
				currentbid = rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get current user's id from db
		Statement getUserId = null;
		try {
			getUserId = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs2 = null;
		try {
			rs2 = getUserId.executeQuery("SELECT user_id FROM user WHERE username = '" + currentuser + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		try {
			while(rs2.next()) {
				bidderid = rs2.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(placedbid <= currentbid) {
			return "error";
		}
		
		//update bid for item in db
		PreparedStatement updateBid = null;
		try {
			updateBid = connection.prepareStatement("UPDATE item SET current_bid = ? WHERE item_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}
		//pass in the values as parameters
         try {
        	 updateBid.setFloat(1, placedbid);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
        	 updateBid.setInt(2, id);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		
		try {
			int itemRowsUpdated = updateBid.executeUpdate();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		try {
			updateBid.close();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		
		//add bid into db
		PreparedStatement createBid = null;
		try {
			createBid = connection.prepareStatement(
			"INSERT into bid "
			+ "(item_id, bidder_id, bid_amount)" +" VALUES (?, ?, ?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}
		//pass in the values as parameters
         try {
        	 createBid.setInt(1, id);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
        	 createBid.setInt(2, bidderid);
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
         try {
        	 createBid.setFloat(3, placedbid);
 		} catch (SQLException e) {
 			result = "error";
 			e.printStackTrace();
 		}
		
		try {
			int bidRowsUpdated = createBid.executeUpdate();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		try {
			createBid.close();
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getBidamnt() {
		return bidamnt;
	}
	public void setBidamnt(String bidamnt) {
		this.bidamnt = bidamnt;
	}

	@Override
	public void setSession(Map m) {
		// TODO Auto-generated method stub
		this.m = m;
	}
	
}

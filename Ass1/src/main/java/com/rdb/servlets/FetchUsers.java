package com.rdb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * Servlet implementation class FetchUsers
 */
@WebServlet("/FetchUsers")
public class FetchUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiffin_service_db", "root", "ucs22m1018");
			if(con!=null) {
			
				List<JSONObject> li=new ArrayList<JSONObject>();
				String select_query="SELECT * FROM users";
				PreparedStatement ps=con.prepareStatement(select_query);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					JSONObject obj=new JSONObject();
					obj.put("name", rs.getString(1));
					obj.put("email", rs.getString(2));
					obj.put("phone_no", rs.getString(3));
					obj.put("college", rs.getString(4));
					obj.put("gender", rs.getString(5));
					obj.put("address", rs.getString(6));
					li.add(obj);
				}
				response.getWriter().print(li);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}

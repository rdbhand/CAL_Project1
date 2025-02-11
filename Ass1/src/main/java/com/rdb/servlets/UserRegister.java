package com.rdb.servlets;

import jakarta.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiffin_service_db","root","ucs22m1018");
			if(con!=null) {
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				String select_query="SELECT password FROM users WHERE email=?";
				PreparedStatement ps=con.prepareStatement(select_query);
				ps.setString(1, email);
				ResultSet res=ps.executeQuery();
				res.next();
				if(res.getString(1).equals(password)) {
					System.out.println("Login Successful !!");
					RequestDispatcher rd=request.getRequestDispatcher("user_dashboard.html");
					rd.forward(request, response);
				}
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
	}
	
	

}

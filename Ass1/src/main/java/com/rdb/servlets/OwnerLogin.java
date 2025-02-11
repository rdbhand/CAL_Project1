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
 * Servlet implementation class OwnerLogin
 */
@WebServlet("/OwnerLogin")
public class OwnerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerLogin() {
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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiffin_service_db", "root", "ucs22m1018");
			if(con!=null) {
				PreparedStatement ps=con.prepareStatement("SELECT password FROM owners where uname=?");
				String uname=request.getParameter("uname");
				String password=request.getParameter("password");
				ps.setString(1, uname);
				ResultSet res=ps.executeQuery();
				res.next();
				if(password.equals(res.getString(1))) {
					RequestDispatcher rd=request.getRequestDispatcher("owner_dashboard.html");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("owner_login.html");
					rd.forward(request, response);
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}

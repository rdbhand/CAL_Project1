

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserRegister() {
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
				String name=request.getParameter("name");
				String email=request.getParameter("email");
				String phone_no=request.getParameter("phone_no");
				String college=request.getParameter("college");
				String gender=request.getParameter("gender");
				String address=request.getParameter("address");
				String password=request.getParameter("password");
				String insert_query="INSERT INTO users (name, email, phone_no, college, gender, address, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps=con.prepareStatement(insert_query);
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, phone_no);
				ps.setString(4, college);
				ps.setString(5, gender);
				ps.setString(6, address);
				ps.setString(7, password);
				int res=ps.executeUpdate();
				if(res>0) {
					System.out.println("Insertion Successful !!");
				}
			}
		}
		catch(Exception e) {
			
		}
		
	}

}

package testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sample
 */
@WebServlet("/sample")
public class sample extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public sample() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String str1 = request.getParameter("username");
		String str2 = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","kishore15");
			PreparedStatement p = co.prepareStatement("insert into user values(?,?)");
			p.setString(1, str1);
			p.setString(2, str2);
			int i = p.executeUpdate();
			if(i>0)
			{
				out.printf("Welcome");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
}

package spotify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addartist
 */
@WebServlet("/addartist")
public class addartist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addartist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String song = request.getParameter("artist_name");
		String date = request.getParameter("DOB");
		String artist = request.getParameter("bio");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify_clone","root","kishore15");
			PreparedStatement p = co.prepareStatement("insert into artist values(?,?,?)");
			p.setString(1, song);
			p.setString(2, date);
			p.setString(3, artist);
			int i = p.executeUpdate();
			if(i>0)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AddSong.html");
				dispatcher.forward(request, response);
			}
			co.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}

}

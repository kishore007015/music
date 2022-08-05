package spotify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class fetch_servlet
 */
@WebServlet("/fetch_servlet")
public class fetch_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetch_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify_clone","root","kishore15");
//			PreparedStatement p = co.prepareStatement("select * from songs");
			

      
            out.println("<html>"
            		
            		+ "<head>\r\n"
            		+ "  <title>Home Page</title>\r\n"
            		+ "  <meta charset=\"utf-8\">\r\n"
            		+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
            		+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n"
            		+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n"
            		+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n"
            		+ " \r\n"
            		+ "  <link rel=\"stylesheet\" href=\"Spot.css\">\r\n"
            		+ "</head>"
            		+ "<body>");
            out.println("<nav class=\"navbar navbar-default\">\r\n"
            		+ "  <div class=\"container-fluid\">\r\n"
            		+ "    <div class=\"navbar-header\">\r\n"
            		+ "      <a class=\"navbar-brand\" href=\"#\"></a>\r\n"
            		+ "    </div>\r\n"
            		+ "    <ul class=\"nav navbar-nav\">\r\n"
            		+ "      <li class=\"active\"><a href=\"AddSong.html\">Home</a></li>\r\n"
            		+ " \r\n"
            		+ "    </ul>\r\n"
            		+ "  </div>\r\n"
            		+ "</nav>\r\n"
            		+ "\r\n"
            		+ "\r\n"
            		+ "<div class=\"container\">\r\n"
            		+ "  <div><h2>Top 10 Songs</h2> <button type=\"button\" class=\"btn btn-primary addSong\" onclick=\"location.href='AddSong.html'\">+ Add Song</button></div>");
            out.println("<table class=\"table table-striped\"><thead><tr>" + "<th><b>Song</b></th>" + "<th><b>Date of Release</b></th>"
                    + "<th><b>Artists</b></th>" + "<th><div><b>Rating</b></div></th></tr></thead>");
  
            Statement stmt = conn.createStatement();
            String sql = "select * from songs order by rating desc;";
            ResultSet rs = stmt.executeQuery(sql);
  
          
            while (rs.next()) {
            	String song_name = rs.getString("song_name");
                String mydate = rs.getString("mydate");
         
                String artist = rs.getString("artist");
                String rating = rs.getString("rating");
  
                out.println(
                		
                		"<tbody>" +
                		"<tr>" + "<td>" + song_name + "</td>" + "<td>" + mydate + "</td>"
                        + "<td>" + artist + "</td>" + "<td>" + rating + "</td>" +"</tr>");
  
            }
            out.println("</table>");
            
            
            out.println("<div class=\"container\" style=\"\r\n"
            		+ "    padding-left: 0px !important;\r\n"
            		+ "\">"
            		+ "<h2>Top 10 Artists</h2>");
            out.println("<table class=\"table table-striped\"><thead><tr>" + "<th><b>Song</b></th>" + "<th><b>Date of Release</b></th>"
                    + "<th><b>Bio</b></th>" + "<th><b>Rating</b></th></tr></thead>");
  
            
           
           sql = "select * from artist order by rating desc;";
           rs = stmt.executeQuery(sql);
            
           while (rs.next()) {
           	String artist_name = rs.getString("artist_name");
               String DoB = rs.getString("DoB");
               String bio = rs.getString("bio");
               String rating = rs.getString("rating");
               out.println(
               		
               		"<tbody>" +
               		"<tr>" + "<td>" + artist_name + "</td>" + "<td>" + DoB + "</td>"
                       + "<td>" + bio + "</td>"  + "<td>" + rating + "</td>"  +"</tr>");
 
           }
           
            
           out.println("</table></body></html>");
  
            rs.close();
            stmt.close();
            out.close();
  
        } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

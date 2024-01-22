

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		Connection con=null;
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try
		{
			con=DBConnection.get();
			String user=request.getParameter("username");
			String pwd=request.getParameter("password");
			String query="select password from register where uname=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				if(pwd.equals(rs.getString("password")))
				{
					HttpSession session=request.getSession();
					session.setAttribute("username", user);
					RequestDispatcher rd=request.getRequestDispatcher("/User.jsp");
					rd.include(request, response);
					
				}
				else
				{
					out.print("<h3 style='text-align : center'>Invalid User name and Password - Try Again</h3>");  
			        RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");  
			        rd.include(request, response);  

				}
			}
			else
			{
				out.print("<h3 style='text-align : center'>Invalid Record Details - Try Again</h3>");  
		        RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");  
		        rd.include(request, response);  

			}
			
		}
		catch(Exception e)
		{
			out.println("<h1>Exception:"+e.getMessage()+"</h1>");
			out.println("<h3>Lgin Failed-Try Again</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.include(request, response);
		}
		finally
		{
			if(con!=null)
			{
				try
				{
					con.close();
				}
				catch(SQLException e) {}
			}
		}
		
	}

}

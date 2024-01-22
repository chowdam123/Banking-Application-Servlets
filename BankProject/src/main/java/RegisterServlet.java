

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet
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
			String query="insert into register values(?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,user);
			ps.setString(2,pwd);
			int count=ps.executeUpdate();
			if(count>0)
			{
				out.println("<h3 style='text-align:center'> Successfully Registered-Login now</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.include(request, response);
			}
			else
			{
				out.println("<h3 style='text-align:center'> Registration Failed Try-Again</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
				rd.include(request, response);	
			}
			
		}
		catch(Exception e)
		{
			out.println("<h3 style='text-align:center'>Exception:"+ e.getMessage()+"</h3>");
			out.println("<h3 style='text-align:center'> Registration Failed Try-Again</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
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



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
import javax.servlet.http.HttpSession;


@WebServlet("/DeleteRecordServlet")
public class DeleteRecordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
			String name=(String)session.getAttribute("username");
			out.print("<h1 align='center'>Welcome,"+name+" continue with your transactions</h>");
			Connection con=null;
		
			try
			{
				con = DBConnection.get();
			    name=request.getParameter("accountname");

				String query ="delete from account where name=?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,name);
		
				int count=ps.executeUpdate();
				if(count>0)
				{
					out.print("<h2>account deleted successfully</h2>");
					RequestDispatcher rd=request.getRequestDispatcher("/User.jsp");  
				    rd.include(request, response); 
				}
				else
				{
					out.print("<h2>failed in deleting the account</h2>");
					request.getRequestDispatcher("/User.jsp").include(request, response); 
				}
			}
			catch(Exception e)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
				rd.include(request, response);
				out.print("<h1>Error: "+e.getMessage()+"</h1>");
			}
			finally
			{
				if(con!=null)
				{
					try 
					{
						con.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					
					}
				}
			}
		}
		else
		{
			out.print("<h3>You logged out from previous Session - Please Login</h3>");  
            request.getRequestDispatcher("Login.jsp").include(request, response);  
		}

	}
}

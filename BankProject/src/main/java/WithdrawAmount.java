

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

@WebServlet("/WithdrawAmount")
public class WithdrawAmount extends HttpServlet 
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
				String name1=request.getParameter("accountname");
				int balance1=Integer.parseInt(request.getParameter("withdraw"));
				String query1 ="select balance from account where name=?";
				PreparedStatement ps1 =con.prepareStatement(query1);
				ps1.setString(1, name1);
				ResultSet rs=ps1.executeQuery();
				if(rs.next())
				{
					int s = rs.getInt(1);
//					int balance=Integer.parseInt(request.getParameter(s));
					if(balance1<=s)
					{
						String query2="update account set balance=balance-? where name=?";
						PreparedStatement ps2 =con.prepareStatement(query2);
						ps2.setInt(1, balance1);
						ps2.setString(2,name1);
						ps2.executeUpdate();
						RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
						rd.include(request, response);
						out.println("<h2>withdraw successfull<h2/>");
						
					}
					else
					{
						RequestDispatcher rd1 = request.getRequestDispatcher("/withdraw.jsp");
						rd1.include(request, response);
						out.println("<h3> No such account present </h3>");
						
					}
				}
			}
			catch(Exception e)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
				rd.include(request, response);
				out.println("<h1>Error: "+e.getMessage()+"</h1>");
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

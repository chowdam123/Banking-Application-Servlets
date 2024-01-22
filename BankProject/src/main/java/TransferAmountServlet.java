

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

@WebServlet("/TransferAmountServlet")
public class TransferAmountServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
			String name=(String)session.getAttribute("username");
			pw.print("<h1 align='center'>Welcome,"+name+" continue with your transactions</h>");
			Connection con=null;
			try
			{
				con=DBConnection.get();
			    name=request.getParameter("accountname");
				String recipientname=request.getParameter("recipient");
				int amount=Integer.parseInt(request.getParameter("amount"));
				con.setAutoCommit(false);
				String query ="select balance from account where name=?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,recipientname);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) 
				{
					RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
					rd.include(request, response);
					int bal=rs.getInt(1);
					if(amount<=bal)
					{

						String query1="update account set balance=balance-? where name=?";
						PreparedStatement ps1=con.prepareStatement(query1);
						ps1.setInt(1, amount);
						ps1.setString(2, name);
						ps1.executeUpdate();
						String query2="update account set balance=balance+? where name=?";
						PreparedStatement ps2=con.prepareStatement(query2);
						ps2.setInt(1, amount);
						ps2.setString(2, recipientname);
						int count=ps2.executeUpdate();
						if(count>0)
						{
							pw.println("amount transfer successfully");
//							con.commit();
						}
						else
						{
							pw.println("destination account is not present ");
							con.rollback();
							System.out.println("amount will rollback soon ");
							
						}
						
						
					}
					else
					{
						pw.println("no such account");
					}
				}
			}
			catch(Exception e)
			{
				e.getMessage();
				
			}
			finally
			{
				if(con != null)
				{
					try {
						con.close();
					} catch (SQLException e) {}
				}
				
			}
		}
		else
		{
			pw.print("<h3>You logged out from previous Session - Please Login</h3>");  
            request.getRequestDispatcher("Login.jsp").include(request, response);  	
		}	
	}

}

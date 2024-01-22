

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

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet 
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
				name=request.getParameter("name");
				String fathername=request.getParameter("fathername");
				String birthday=request.getParameter("birthday");
				String gender=request.getParameter("gender");
				String address=request.getParameter("address");
				int balance=Integer.parseInt(request.getParameter("balance"));
				String aadharnumber=request.getParameter("aadharnumber");
				String query="insert into account values(?,?,?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,name);
				ps.setString(2,fathername);
				ps.setString(3,birthday);
				ps.setString(4,gender);
				ps.setString(5,address);
				ps.setInt(6,balance);
				ps.setString(7,aadharnumber);
				int count=ps.executeUpdate();
				if(count>0)
				{
					pw.println("<h3 align='center'>Account Created Successfully</h3>");
					RequestDispatcher rd=request.getRequestDispatcher("/User.jsp");  
				    rd.include(request, response); 
							
				}
				else
				{
					pw.print("<h3 align='center'>failed in account creation- Try Again</h3>");  
			        request.getRequestDispatcher("/User.jsp").include(request, response);  
				}
				
			}
			catch(Exception e)
			{
				pw.print("<h3 align='center'>"+e.getMessage()+"</h3>");  
				request.getRequestDispatcher("/User.jsp").include(request, response);
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

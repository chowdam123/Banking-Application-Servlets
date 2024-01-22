


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GetData")
public class GetData extends HttpServlet 
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
			pw.print("<h3 style='color:green;' align='center'>Welcome,"+name+" Your Info</h3>");
			Connection con=null;
			try
			{
				con=DBConnection.get();
				name=request.getParameter("name");
				String query="select * from account where name=?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,name);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					pw.print("<html>");
					pw.print("<body");
					pw.print("<form>");
					pw.print("<caption>"+name+ "Details</caption>");
					pw.print("<p>Name: <input type='text' value='"+rs.getString(1)+"' name='name'</p>");
					pw.print("<p> Father Name: <input type='text'value='"+rs.getString(2)+"'name='fathername'</p>");
					pw.print("<p> Birthday: <input type='text' value='"+rs.getString(3)+"'name='birthday'</p>");
					pw.print("<p> Gender: <input type='text'value='"+rs.getString(4)+"'name='gender'</p>");
					pw.print("<p> Address: <input type='text'value='"+rs.getString(5)+"'name='address'</p>");
					pw.print("<p> Balance: <input type='text'value='"+rs.getString(6)+"'name='balance'</p>");
					pw.print("<p> Aadharnumber: <input type='text'value='"+rs.getString(7)+"'name='aadharnumber'</p>");
					pw.print("</body");
					pw.print("</html>");
					 	
				}
				else
				{
					pw.println("<h3>given account holder name dosen't exist<h3/>");
		            request.getRequestDispatcher("Login.jsp").include(request, response);
					
				}
				con.close();
				
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


		}
				
	}

}

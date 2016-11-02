import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@SuppressWarnings("serial")
public class ExamsPage extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	HttpSession sess;
	int count=1,num;
	int correct=0,wrong=0,skip=0;
	int correctAns;
	String category;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		sess=request.getSession();
		category=(String)sess.getAttribute("category");
		String user=(String)sess.getAttribute("user");
		
		String query;
		String table;
		ResultSet rs;
		PreparedStatement ps;
		String que;
		String o1;
		String o2;
		String o3;
		String o4;
		
		if(category.equalsIgnoreCase("1"))
			table="java";
		else if(category.equalsIgnoreCase("2"))
			table="cpp";
		else
			table="c";
		String q1="select * from queamount where subject=?";
		try {
			PreparedStatement ps1=DBInfo.con.prepareStatement(q1);
			ps1.setString(1, table);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next())
				num=rs1.getInt(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(count<=num)
		{
			try {
				query="select * from "+table+" where qno=?";
				ps=DBInfo.con.prepareStatement(query);
				ps.setInt(1, count);
				rs=ps.executeQuery();
				while(rs.next())
				 {
					que=rs.getString(2);
					o1=rs.getString(3);
					o2=rs.getString(4);
					o3=rs.getString(5);
					o4=rs.getString(6);
				   correctAns=rs.getInt(7);
				
				out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'>"
						+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content"
						+ "='width=device-width, initial-scale=1'><!-- The above 3 meta tags *must* come "
						+ "first in the head; any other head content must come *after* these tags --><"
						+ "title>ProgrammersQuiz.com</title><!-- Bootstrap --><link href='../css/bootstrap"
						+ ".min.css' rel='stylesheet'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 "
						+ "elements and media queries --><!-- WARNING: Respond.js doesn't work if you view "
						+ "the page via file:// --><!--[if lt IE 9]><script src='https://oss.maxcdn.com/"
						+ "html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://oss.maxcdn.com"
						+ "/respond/1.4.2/respond.min.js'></script><![endif]--></head><body><header><div class"
						+ "='jumbotron'><div class='container'><h1><b>"+table.toUpperCase()+"</b></h1><h4 style='"
						+ "text-align:right' ><b>Welcome, "+user+" </b></h4><div class='col-xs-11'><a href='../Login"
						+ "Signup1.html' class='btn btn-danger btn-outline' style='float: right;'>Logout</a"
						+ "></div></div></div></header><div class='container'><h2 class='col-xs-offset-3'>"
						+ "Question "+count+" :- "+que+"</h2><form class='form-horizontal' method='post'><div class='"
						+ "form-groug col-xs-offset-3'>");
				
				out.println("<div class='radio'><label><input type='radio' name='option' value='1'>"+o1+ "</label></div>");
				out.println("<div class='radio'><label><input type='radio' name='option' value='2'>"+o2+ "</label></div>");
				out.println("<div class='radio'><label><input type='radio' name='option' value='3'>"+o3+ "</label></div>");
				out.println("<div class='radio'><label><input type='radio' name='option' value='4'>"+o4+ "</label></div>");
				 
				out.println("</div></div><div class='container'><div class='col-xs-offset-4'><input type='"
						+ "submit' class='btn btn-danger btn-md ' name='btn1' value='Submit & Next'><"
						+ "input type='submit' class='btn btn-danger btn-md col-xs-offset-1' name='btn2' "
						+ "value='Skip & Next'></div></div></form></div><hr><footer>"
						+ "<div class='container'>"
						+ "<img align='right' src='../images/rat.png'>"
						+ "<p> <b>About Us :-</b></b><br>Developed By :- Anubhav Shrimal <br>Guidance :- "
						+ "Mr. Abhishek Jain     </p></div></footer><!-- jQuery (necessary for Bootstrap'"
						+ "s JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/"
						+ "jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below)"
						+ ", or include individual files as needed --><script src='../js/bootstrap.min."
						+ "js'></script></body></html>");
				count++;
				 }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			count = 1;
			correct=0;
			wrong=0;
			skip=0;
			response.sendRedirect("SectionsPage");
		}
			
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String userans=request.getParameter("option");
		String btn2=request.getParameter("btn2");
		String right=""+correctAns;
		if(userans==null  || btn2!=null)
		{
		 skip++;
		 
		}
		else if(userans.equalsIgnoreCase(right))
		{
		    correct++;
		    
		}
		else
		{
		  
		   wrong++;
		  
		}
		if(category.equalsIgnoreCase("1"))
		{
			sess.setAttribute("javaskip", skip);
			sess.setAttribute("javacorrect", correct);
			sess.setAttribute("javawrong", wrong);
		}
		else if(category.equalsIgnoreCase("2"))
		{
			sess.setAttribute("cppskip", skip);
			sess.setAttribute("cppcorrect", correct);
			sess.setAttribute("cppwrong", wrong);
		}
		else
		{
			sess.setAttribute("cskip", skip);
			sess.setAttribute("ccorrect", correct);
			sess.setAttribute("cwrong", wrong);			
		}
		
		   doGet(request,response);
	}

}

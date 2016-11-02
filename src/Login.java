import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	String uname;
	String pass;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		uname=request.getParameter("uname");
		pass=request.getParameter("pass");
		String query="select * from info where username=? and password=?";
		try {
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, pass);
			
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				HttpSession sess=request.getSession();
				sess.setAttribute("user", res.getString("name"));
				sess.setAttribute("uname", uname);
				if(res.getString("usertype").equalsIgnoreCase("student"))
					response.sendRedirect("RulesPage");
				else
					response.sendRedirect("AdminPage");
			}
			else
			{
				doGet(request,response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'>"
					+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
					+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
					+ "<!-- The above 3 meta tags *must* come first in the head; any other head content "
					+ "must come *after* these tags --><title>ProgrammersQuiz.com</title>"
					+ "<!-- Bootstrap --><link href='../css/bootstrap.min.css' rel='stylesheet'>"
					+ "<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media "
					+ "queries --><!-- WARNING: Respond.js doesn't work if you view the page via "
					+ "file:// --><!--[if lt IE 9]><script src='https://oss.maxcdn.com/html5shiv/3.7.2/"
					+ "html5shiv.min.js'></script><script src='https://oss.maxcdn.com/respond/1.4.2/"
					+ "respond.min.js'></script><![endif]--></head><body><header><div class='jumbotron'>"
					+ "<div class='container'><h2><b>Welcome To Programmer's Quiz</b><a style='float: right;"
					+ "' href='../Signup.html' class='btn btn-danger btn-lg btn-outline'>Sign-Up</a></h2></div></div>"
					+ "</header><br><br><br><br><form class='form-horizontal' method='post'><div class='form-group "
					+ "has-error'><label for='inputUname' class='col-sm-4 control-label'>Username:</label><"
					+ "div class='col-sm-4'><input type='text' class='form-control' name='uname' id='inputUname' "
					+ "value='"+uname+"' required='true'></div></div><div class='form-group has-error'><label for='inputPass"
					+ "' class='col-sm-4 control-label'>Password:</label><div class='col-sm-4'><input type='password' "
					+ "class='form-control' name='pass' id='inputPass' placeholder='Password' required='true'><label for='inputUname' "
					+ "class='col-sm-9 control-label'><span class='glyphicon glyphicon-remove'></span> Invalid Username "
					+ "or Password!!</label></div></div><div class='form-group'><div class='col-xs-offset-"
					+ "5 col-xs-6'><button type='submit' class='btn btn-danger btn-md'>Log in</button></"
					+ "div></div></form></div><br><br><br><br><hr><footer><div class='container'><img align='right"
					+ "' src='../images/rat.png'><p><b>About Us :-</b></b><br>Developed By :- Anubhav Shrimal "
					+ "<br>Guidance :- Mr. Abhishek Jain</p></div></footer><!-- jQuery (necessary for Bootstrap"
					+ "'s JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/jquery/1"
					+ ".11.3/jquery.min.js'></script><!-- Include all compiled plugins (below), or include individual files as "
					+ "needed --><script src='../js/bootstrap.min.js'></script></body></html>");
		}
}

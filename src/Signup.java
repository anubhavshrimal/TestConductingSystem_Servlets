import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Signup extends HttpServlet {

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
	String name;
	String mobile;
	String email;
	String college;
	String uname;
	String pass;
	String utype;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		name=request.getParameter("name");
		mobile=request.getParameter("mobile");
		email=request.getParameter("email");
		college=request.getParameter("college");
		uname=request.getParameter("uname");
		pass=request.getParameter("pass");
		utype=request.getParameter("utype");
		System.out.println(utype);
		String query="select * from info where username=?";
		try {
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, uname);
			ResultSet res=ps.executeQuery();
			if(!res.next())
			{
				String query1="insert into info values(?,?,?,?,?,?,?)";
				PreparedStatement ps1=DBInfo.con.prepareStatement(query1);
				ps1.setString(1, name);
				ps1.setString(2, mobile);
				ps1.setString(3, email);
				ps1.setString(4, college);
				ps1.setString(5, uname);
				ps1.setString(6, pass);
				ps1.setString(7, utype);
				ps1.executeUpdate();
				out.print("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'>"
						+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
						+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
						+ "<!-- The above 3 meta tags *must* come first in the head; any other head "
						+ "content must come *after* these tags --><title>Signup</title><!-- Bootstrap -->"
						+ "<link href='../css/bootstrap.min.css' rel='stylesheet'>"
						+ "<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->"
						+ "<!-- WARNING: Respond.js doesn't work if you view the page via file:// --><!--[if lt IE 9]>"
						+ "<script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>"
						+ "<script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script><![endif]-->"
						+ "</head><body><header><div class='jumbotron'><div class='container'><h1><b>"
						+ "Registration Completed!!</b></h1></div></div></header><div class='col-xs-offset-5 col-xs-6'>"
						+ "<a href='../LoginSignup1.html' class='btn btn-danger btn-lg'>Back</a></div><br><hr><footer>"
						+ "<div class='container'><img align='right' src='../images/rat.png'><p><b>About Us :-</b>"
						+ "<br>Developed By :- Anubhav Shrimal <br>Guidance :- Mr. Abhishek Jain</p></div></footer>"
						+ "<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --><script src='https://ajax.googleapis"
						+ ".com/ajax/libs/jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below), or "
						+ "include individual files as needed --><script src='../js/bootstrap.min.js'></script></body></html>");
			}
			else
			{
				doGet(request,response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html><html lang='en'><head>"
				+ "<meta charset='utf-8'><meta http-equiv='X-UA-Compatible' "
				+ "content='IE=edge'><meta name='viewport' content='width=device-width,"
				+ " initial-scale=1'><!-- The above 3 meta tags *must* come first in the "
				+ "head; any other head content must come *after* these tags --><title>S"
				+ "ignup</title><!-- Bootstrap --><link href='../css/bootstrap.min.css' rel='stylesheet"
				+ "'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries "
				+ "--><!-- WARNING: Respond.js doesn't work if you view the page via file:// --><!--["
				+ "if lt IE 9]><script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv"
				+ ".min.js'></script><script src='https://oss.maxcdn.com/respond/1.4"
				+ ".2/respond.min.js'></script><![endif]--></head><body><header><div class='jumbotron'> "
				+ "<div class='container'><h1><b>Sign-up</b></h1><a style='float: right;'"
				+ " href='../LoginSignup1.html' class='btn btn-danger btn-lg btn-outline'>Login</a></h2></div></div></header><form class='form"
				+ "-horizontal' method='post'><div class='form"
				+ "-group'><label for='inputName' class='col-sm-4 control-label'>Name:</label><"
				+ "div class='col-sm-4'><input type='text' class='form-control' name='name' id='input"
				+ "Name' placeholder='Full Name' required='true' value="+name+"></div></div><div class"
				+ "='form-group'><label for='inputMobile' class='col-sm-4 control-label'>Mobile "
				+ "No.:</label><div class='col-sm-4'><input type='text' class='form-"
				+ "control' name='mobile' id='inputMobile' placeholder='Mobile Number' required='true'  "
				+ "value="+mobile+" onkeypress='return event.charCode >=48 && event.charCode <=57'></div></div><div class='form-group'><label for='"
				+ "inputEmail' class='col-sm-4 control-label'>Email-ID:</label><div class"
				+ "='col-sm-4'><input type='email' class='form-control' name='email' id='"
				+ "inputEmail' placeholder='Email' required='true'  value="+email+"></div></div><div class='"
				+ "form-group'><label for='inputCollege' class='col-sm-4 control-label'>"
				+ "College:</label><div class='col-sm-4'><input type='text' class='form-control' name='college' "
				+ "id='inputCollege' placeholder='College Name' required='true'  value="+college
				+"></div></div><div class='form-group has-error'><label for='inputUname' class='col-"
				+ "sm-4 control-label'>Username:</label><div class='col-sm-4'><input type='text' class='"
				+ "form-control' name='uname' id='inputUname' placeholder='Username' required='true'><label for='inputUname' "
				+ "class='col-sm-8 control-label'><span class='glyphicon glyphicon-remove'></span> Username Already Exists"
				+ "!!</label></div></div><div class='form-group'><label for='inputPass' class='"
				+ "col-sm-4 control-label'>Password:</label><div class='col-sm-4'><input type='password"
				+ "' class='form-control' name='pass' id='inputPass' required='true' value="+pass+"></div></div"
				+ "><div class='form-group'><label for='inputUtype' class='col-sm-4 "
				+ "control-label'>User Type:</label><div class='col-sm-4'><input type='text' class='"
				+ "form-control' name='utype' id='inputUtype' value='"+utype+"' readonly></div"
				+ "></div><div class='form-group'><div class='col-xs-offset-5 col-"
				+ "xs-6'><button type='submit' class='btn btn-danger btn-"
				+ "md'>Sign up</button></div></div></form></div><hr><footer><div class='"
				+ "container'><img align='right' src='../images/rat.png'><p><b>About Us "
				+ ":-</b></b><br>Developed By :- Anubhav Shrimal <br>Guidance "
				+ ":- Mr. Abhishek Jain</p></div></footer><!-- jQuery (necessary for Bootstrap'"
				+ "s JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/"
				+ "libs/jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below"
				+ "), or include individual files as needed --><script src='../js/bootstrap.min."
				+ "js'></script></body></html>");
	}
	

}

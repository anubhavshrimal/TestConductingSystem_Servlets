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
public class ResultPage extends HttpServlet {

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
	int correct,wrong,skip,i=0,num[]=new int[3];
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		sess=request.getSession();

		String user=(String)sess.getAttribute("user");
		int javacorrect=(int)sess.getAttribute("javacorrect");
		int javawrong=(int)sess.getAttribute("javawrong");
		int cppcorrect=(int)sess.getAttribute("cppcorrect");
		int cppwrong=(int)sess.getAttribute("cppwrong");
		int ccorrect=(int)sess.getAttribute("ccorrect");
		int cwrong=(int)sess.getAttribute("cwrong");
	
		
		String q1="select * from queamount";
		try {
			PreparedStatement ps1=DBInfo.con.prepareStatement(q1);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next())
			{
				num[i]=rs1.getInt(2);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'>"
				+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content"
				+ "='width=device-width, initial-scale=1'><!-- The above 3 meta tags *must* come "
				+ "first in the head; any other head content must come *after* these tags --><"
				+ "title>Result-ProgrammersQuiz.com</title><!-- Bootstrap --><link href='../css/bootstrap"
				+ ".min.css' rel='stylesheet'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 "
				+ "elements and media queries --><!-- WARNING: Respond.js doesn't work if you view "
				+ "the page via file:// --><!--[if lt IE 9]><script src='https://oss.maxcdn.com/"
				+ "html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://oss.maxcdn.com"
				+ "/respond/1.4.2/respond.min.js'></script><![endif]--></head><body><header><div class"
				+ "='jumbotron'><div class='container'><h1><b>Result</b></h1><h4 style='"
				+ "text-align:right' ><b>Welcome, "+user+" </b></h4><div class='col-xs-11'><a href='../Login"
				+ "Signup1.html' class='btn btn-danger btn-outline' style='float: right;'>Logout</a"
				+ "></div></div></div></header>");
		
		out.println("<div class='container'><div class='table-responsive'><table class='table "
				+ "table-hover table-striped table-bordered'><thead><tr><th>Section </th><th>Total "
				+ "Questions</th><th>Questions Attempted</th><th>Correct </th><th>Wrong </th></tr"
				+ "></thead><tbody><tr><td>JAVA</td><td>"+num[0]+"</td><td>"+(javacorrect+javawrong)+"</td"
				+ "><td>"+javacorrect+"</td><td>"+javawrong+"</td></tr><tr><td>C++</td><td"
				+ ">"+num[1]+"</td><td>"+(cppcorrect+cppwrong)+"</td><td>"+cppcorrect+"</td>"
				+ "<td>"+cppwrong+"</td></tr><tr><td>C</td><td>"+num[2]+"</td>"
				+ "<td>"+(ccorrect+cwrong)+"</td><td>"+ccorrect+"</td><td>"+cwrong+"</td></tr>"
				+ "</tbody></table><button class='btn btn-danger col-xs-offset-5' onclick=\"myFunction()\">Print this page</button></div></div>");
		
		out.println("<script>function myFunction() {  window.print();}</script><hr><footer>"
				+ "<div class='container'>"
				+ "<img align='right' src='../images/rat.png'>"
				+ "<p> <b>About Us :-</b></b><br>Developed By :- Anubhav Shrimal <br>Guidance :- "
				+ "Mr. Abhishek Jain     </p></div></footer><!-- jQuery (necessary for Bootstrap'"
				+ "s JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/"
				+ "jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below)"
				+ ", or include individual files as needed --><script src='../js/bootstrap.min."
				+ "js'></script></body></html>");
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}

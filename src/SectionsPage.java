import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class SectionsPage extends HttpServlet {

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
	String sel1;
	String sel2;
	String sel3;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		sess=request.getSession();
		
		sel1=(String)sess.getAttribute("sel1");
		sel2=(String)sess.getAttribute("sel2");
		sel3=(String)sess.getAttribute("sel3");
		String user=(String)sess.getAttribute("user");
		
		if(!(sel3==null))
		{
			response.sendRedirect("ResultPage");
		}
		else
		{
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
					+ "='jumbotron'><div class='container'><h1><b>Programmer's Quiz</b></h1><h4 style='"
					+ "text-align:right' ><b>Welcome, "+user+" </b></h4><div class='col-xs-11'><a href='../Login"
					+ "Signup1.html' class='btn btn-danger btn-outline' style='float: right;'>Logout</a"
					+ "></div></div></div></header><div class='container'><h2 class='col-xs-offset-4'>"
					+ "Select a category:-</h2><form class='form-horizontal' method='post'><div class='"
					+ "form-groug col-xs-offset-4'>");
			
			if(!((sel1!=null && sel1.equalsIgnoreCase("1")) || (sel2!=null && sel2.equalsIgnoreCase("1"))))
			{
				out.println("<div class='radio'><label><input type='radio' name='category' value='1'>Java"
						+ "</label></div>");
			}
		
			if(!((sel1!=null && sel1.equalsIgnoreCase("2")) || (sel2!=null && sel2.equalsIgnoreCase("2"))))
			{
				out.println("<div class='radio'><label><input type='radio' name='category' value='2'>C++</label></div>");
			}
			
			if(!((sel1!=null && sel1.equalsIgnoreCase("3")) || (sel2!=null && sel2.equalsIgnoreCase("3"))))
			{
				out.println("<div class='radio'><label><input type='radio' name='category' value='3'>C</label></div>");
			}
			
			out.println("</div><div class='col-xs-offset-5 col-xs-6'><button type='submit' class='btn"
					+ " btn-danger btn-md'>Start</button></div></form></div><hr><footer><div class='container'>"
					+ "<img align='right' src='../images/rat.png'>"
					+ "<p> <b>About Us :-</b></b><br>Developed By :- Anubhav Shrimal <br>Guidance :- "
					+ "Mr. Abhishek Jain     </p></div></footer><!-- jQuery (necessary for Bootstrap'"
					+ "s JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/"
					+ "jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below)"
					+ ", or include individual files as needed --><script src='../js/bootstrap.min."
					+ "js'></script></body></html>");	
			
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
		
		String category=request.getParameter("category");
		
		sess.setAttribute("category", category);
		
		if(sel1==null)
			sess.setAttribute("sel1", category);
		else if(sel2==null)
			sess.setAttribute("sel2", category);
		else
			sess.setAttribute("sel3", category);
		
		response.sendRedirect("ExamsPage");
		
	}

}

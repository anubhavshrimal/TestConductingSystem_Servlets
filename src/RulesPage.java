import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class RulesPage extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv='X-"
				+ "UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width"
				+ ", initial-scale=1'><!-- The above 3 meta tags *must* come first in the head; "
				+ "any other head content must come *after* these tags --><title>Rules</title><!-- "
				+ "Bootstrap --><link href='../css/bootstrap.min.css' rel='stylesheet'><!-- "
				+ "HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries "
				+ "--><!-- WARNING: Respond.js doesn't work if you view the page via file"
				+ ":// --><!--[if lt IE 9]><script src='https://oss.maxcdn.com/html5shiv/"
				+ "3.7.2/html5shiv.min.js'></script><script src='https://oss.maxcdn.com/respond/"
				+ "1.4.2/respond.min.js'></script><![endif]--></head><body><header><div class='"
				+ "jumbotron'><div class='container'><h1><b>Rules</b></h1><h4 style='text-align:right' >"
				+ "<b>Welcome, "+user+" </b></h4><form method='post'><div class='col-xs-11'><input  "
				+ "type='submit' class='btn btn-danger btn-outline' style='float: right;' "
				+ "value='Logout'></div></form></div></div></header><"
				+ "form class='form-horizontal' action='SectionsPage' method='get'><div class='"
				+ "form-group'><div class='container'><div class='list-group'><a href='#' "
				+ "class='list-group-item'><h4 class='list-group-item-heading'>1. Divided into "
				+ "3 Sections</h4><p class='list-group-item-text'>There are 3 sections (C, C++, JAVA"
				+ ") each contains 25 MCQ questions with a single correct answer.</p></a><a "
				+ "href='#' class='list-group-item'><h4 class='list-group-item-heading'>2. "
				+ "No negative marking</h4><p class='list-group-item-text'>There is no negative marking "
				+ "for a wrong answer, so attempt as many as possible.</p></a><a href='#' class"
				+ "='list-group-item'><h4 class='list-group-item-heading'>3. Time Limit</h4><"
				+ "p class='list-group-item-text'>You'll be given 75 minutes to attempt all the questions"
				+ ". </p></a></div></div><div><button type='submit"
				+ "' class='btn btn-danger btn-md col-xs-offset-5'>I Agree</button> <button type='button' class='btn "
				+ "btn-danger btn-md' data-toggle='modal' data-target='#modal-1'>I Disagree</button></"
				+ "div></div></form><div class='modal fade' id='modal-1'><div class='modal-dialog modal"
				+ "-sm'><div class='modal-content'><div class='modal-header'><button type='button' "
				+ "class='close' data-dismiss='modal'>&times;</button><h3 class='modal-title'>Confirmation"
				+ "!</h3></div><div class='modal-body' style='text-align:center'>Are you sure you want to quit"
				+ "??<br>You'll be logged out if you click 'Yes'.</div><div class='modal-footer'><form method='post'"
				+ " ><input type='submit' class='btn btn-primary' value='Yes'><a href='' class='btn "
				+ "btn-primary'  data-dismiss='modal'>No</a></form></div></div>  </div></div><hr><footer><"
				+ "div class='container'><"
				+ "img align='right' src='../images/rat.png'><p> <b>About Us :-</b></b><br>Developed By :- "
				+ "Anubhav Shrimal <br>Guidance :- Mr. Abhishek Jain     </p></div></footer><!-- jQuery "
				+ "(necessary for Bootstrap's JavaScript plugins) --><script src='https://ajax.googleapis."
				+ "com/ajax/libs/jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins ("
				+ "below), or include individual files as needed --><script src='../js/bootstrap.min.js'></script></"
				+ "body></html>");
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
		sess.invalidate();
		response.sendRedirect("../LoginSignup1.html");
		
	}

}

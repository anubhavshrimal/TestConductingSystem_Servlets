import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class AdminAddQue extends HttpServlet {

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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv="
				+ "'X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width"
				+ "=device-width, initial-scale=1'><title>ProgrammersQuiz.com</title><!-- Bootstrap "
				+ "--><link href='../css/bootstrap.css' rel='stylesheet'><!-- HTML5 shim and Respond"
				+ ".js for IE8 support of HTML5 elements and media queries --><!-- WARNING: Respond.js "
				+ "doesn't work if you view the page via file:// --><!--[if lt IE 9]><script src"
				+ "='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://"
				+ "oss.maxcdn.com/respond/1.4.2/respond.min.js'></script><![endif]--></head><body><"
				+ "header><div class='jumbotron'><div class='container'><h1><b>Add Question</b><a "
				+ "style='float: right;' href='../LoginSignup1.html' class='btn btn-danger btn-lg btn-outline"
				+ "'>Logout</a></h1></div></div></header><form class='form-horizontal' method='post'><div "
				+ "class='form-group'><label for='sections' class='col-sm-3 control-label'>Section:</"
				+ "label><div class='col-sm-8 col-sm-offset-0'><select id='sections' name='section' required"
				+ "><option value='java'>Java</option><option value='cpp'>C++</option><option value='c"
				+ "'>C</option></select></div></div><div class='form-group'><label for='inputQue' class"
				+ "='col-sm-3 control-label'>Question:</label><div class='col-sm-8'><input name='question' "
				+ "type='text' required='true' class='form-control' id='inputQue' placeholder='Enter the Question"
				+ "'></div></div><div class='form-group'><label for='option1' class='col-sm-3 control-label"
				+ "'>Options:</label><div class='col-sm-8'><input type='text' class='form-control' name='option1' "
				+ "id='option1' placeholder='Enter Option 1' required='true'></div></div><div class='form-group"
				+ "'><div class='col-sm-8 col-sm-offset-3'><input type='text' class='form-control' name='option2"
				+ "' id='option2' placeholder='Enter Option 2' required='true'></div></div><div class='form-group"
				+ "'><div class='col-sm-8 col-sm-offset-3'><input type='text' class='form-control' name='option3"
				+ "' id='option3' placeholder='Enter Option 3' required='true'></div></div><div class='form-group"
				+ "'><div class='col-sm-8 col-sm-offset-3'><input type='text' class='form-control' name='option4"
				+ "' id='option4' placeholder='Enter Option 4' required='true'></div></div><div class='form-group"
				+ "'><label for='inputCorrect' class='col-sm-3 control-label'>Correct Option:</label><div class='"
				+ "col-sm-8 col-sm-offset-0'><select name='correct' id='inputCorrect' required><option value='"
				+ "1'>Option 1</option><option value='2'>Option 2</option><option value='3'>Option 3</option><"
				+ "option value='4'>Option 4</option></select></div></div><div class='form-group'><div class='col"
				+ "-xs-offset-5 col-xs-6'><button type='submit' class='btn btn-danger btn-md'>Add Question</button"
				+ "></div></div></form><hr><footer><div class='container'><img alt='rat logo' align='right' src"
				+ "='../images/rat.png'><p> <b>About Us :-</b><br>Developed By :- Anubhav Shrimal <br>Guidance "
				+ ":- Mr. Abhishek Jain     </p></div></footer><!-- jQuery (necessary for Bootstrap's JavaScript "
				+ "plugins) --> <script src='../js/jquery-1.11.2.min.js'></script><!-- Include all compiled "
				+ "plugins (below), or include individual files as needed --> <script src='../js/bootstrap."
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
		String question=request.getParameter("question");
		String option1=request.getParameter("option1");
		String option2=request.getParameter("option2");
		String option3=request.getParameter("option3");
		String option4=request.getParameter("option4");
		String correct=request.getParameter("correct");
		String section=request.getParameter("section");
		int queNum=0;
		
		String q1="select * from queamount where subject=?";
		String q2="insert into "+section+" values(?,?,?,?,?,?,?)";
		String q3="update queamount set count=? where subject=?";
		
		PreparedStatement ps1,ps2,ps3;
		
		try {
			ps1=DBInfo.con.prepareStatement(q1);
			ps1.setString(1, section);
			ResultSet res1=ps1.executeQuery();
			while(res1.next())
				queNum=res1.getInt(2)+1;
			
			ps2=DBInfo.con.prepareStatement(q2);
			ps2.setInt(1, queNum);
			ps2.setString(2, question);
			ps2.setString(3, option1);
			ps2.setString(4, option2);
			ps2.setString(5, option3);
			ps2.setString(6, option4);
			ps2.setString(7, correct);
			
			ps2.executeUpdate();
			
			ps3=DBInfo.con.prepareStatement(q3);
			ps3.setInt(1, queNum);
			ps3.setString(2, section);
			ps3.executeUpdate();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-"
				+ "equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width="
				+ "device-width, initial-scale=1'><!-- The above 3 meta tags *must* come first in "
				+ "the head; any other head content must come *after* these tags --><title>Question "
				+ "Added</title><!-- Bootstrap --><link href='../css/bootstrap.min.css' "
				+ "rel='stylesheet'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements "
				+ "and media queries --><!-- WARNING: Respond.js doesn't work if you view the "
				+ "page via file:// --><!--[if lt IE 9]><script src='https://oss.maxcdn.com/"
				+ "html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://oss."
				+ "maxcdn.com/respond/1.4.2/respond.min.js'></script><![endif]--></head><body><header"
				+ "><div class='jumbotron'><div class='container'><h3><strong> Question Added Successfully!!</strong></"
				+ "h3></div></div></header><div class='col-xs-offset-5 col-xs-5 col-md-offset-4"
				+ "'><p><strong>Do you want to add more questions?</strong></p><a href='Admin"
				+ "AddQue' class='btn btn-danger btn-sm col-sm-offset-4'>Yes</a>&nbsp;&nbsp;<a href='../"
				+ "AdminPage.html' class='btn btn-danger btn-sm'>No</a></div><br><hr><footer><div class='container"
				+ "'><img align='right' src='../images/rat.png'><p><b>About Us :-</b><br>Developed By :- Anubhav Shrimal <br"
				+ ">Guidance :- Mr. Abhishek Jain</p></div></footer><!-- jQuery (necessary for Bootstrap's JavaScript plugins"
				+ ") --><script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery"
				+ ".min.js'></script><!-- Include all compiled plugins (below), or include individual files as "
				+ "needed --><script src='../js/bootstrap.min.js'></script></body></html>");
	}

}

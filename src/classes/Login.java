package classes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Form
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		
		String userComp = "[a-z0-9]{0,9}";
		Pattern p1 = Pattern.compile(userComp);
		Matcher m1 = p1.matcher(user);
		String passComp = "[a-z0-9]{8,9}";
		Pattern p2 = Pattern.compile(passComp);
		Matcher m2 = p2.matcher(pass);
		String emailComp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
		Pattern p3 = Pattern.compile(emailComp);
		Matcher m3 = p3.matcher(email);
		
		if(m1.matches() && m2.matches() && m3.matches()) {
			
			request.getSession().setAttribute("Usuario", user);

			getServletContext().getRequestDispatcher("/JSP/oklogin.jsp").forward(request, resp);
		       
		}else {
			request.getSession().setAttribute("Usuario", user);
			getServletContext().getRequestDispatcher("/JSP/errorlogin.jsp").forward(request, resp);
		}
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

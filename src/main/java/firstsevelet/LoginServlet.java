package firstsevelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Login servlet",
        urlPatterns = {"/LoginServlet"}       
)

public class LoginServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (isNameValid(user) && isValidPassword(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Invalid Username Or Password</font>");
            rd.include(request, response);
        }
     }
	
	 private boolean isNameValid(String name) {
	        return Pattern.matches("^[A-Z][a-zA-Z]{2,}$", name);
	 }
	 
	 private boolean isValidPassword(String password) {
	        return Pattern.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.{8,})[0-9a-zA-Z]*[^0-9a-zA-Z][0-9a-zA-Z]*$", password);
	 }
}

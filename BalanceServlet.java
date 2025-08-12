package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BalanceServlet
 */
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter pw = response.getWriter();
	    pw.println("<!DOCTYPE html>");
	    pw.println("<head>");
	    pw.println("<title>残高照会</title>");
	    pw.println("</head>");
	    pw.println("<body>");
	    pw.println("<h1>残高照会</h1>");
	    pw.println("<p calss=\"text\">照会する名前を入力してください</p>");
	    pw.println("<form action='bank' method='get'>");
	    pw.println("<div class='text'>ユーザー名 : <input type='text' name='name' size='20'/><br><br>");
	    pw.println("<input type='hidden' name='command' value='blance'/>");
	    pw.println("<input type='submit' value='OK'/>");
	    pw.println("<input type='reset' value='CANCEL'/><br>");
	    pw.println("</form>");
	    pw.println("<a href='index.html'>戻る</a>");
	    pw.println("</div>");
	    pw.println("</body>");
	    pw.println("</html>");
	}

}
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepositServlet
 */
public class DepositServlet extends HttpServlet {
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
		    pw.println("<title>貯金</title>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println("<h1>コイン購入</h1>");
		    pw.println("<p calss=\"text\">ユーザー名と購入額を入力して[OK]を押してください</p>");
		    pw.println("<form action='bank' method='get'>");
		    pw.println("<div class='text'>ユーザー名 : <input type='text' name='name' size='20'/><br>");
		    pw.println("<div class='text'>購入額 : <input type='text' name='amount' size='20'/><br><br>");
		    pw.println("<input type='hidden' name='command' value='deposit'/>");
		    pw.println("<input type='submit' value='OK'/>");
		    pw.println("<input type='reset' value='CANCEL'/><br>");
		    pw.println("</form>");
		    pw.println("<a href='index.html'>戻る</a>");
		    pw.println("</div>");
		    pw.println("</body>");
		    pw.println("</html>");
	}

}
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class BankServlet
 */
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ExtendedBank bank;
	public BankServlet(){
		bank = new ExtendedBank();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
		String name = request.getParameter("name");
		String amount = request.getParameter("amount");
		int ans;
		int num;
		if(command.equals("open")) {
			if(name.length()==0) {
				num = 1;
				missing(num,response);
			}else {
				ans = bank.open(name);
				if(ans == 0) {
					open_success(name,response);
				}else {
					open_failed(name,response);
				}
			}
		}else if(command.equals("close")) {
			if(name.length()==0) {
				num = 2;
				missing(num,response);
			}else {
				ans = bank.close(name);
				if(ans == 0) {
					close_success(name,response);
				}else {
					close_failed(name,ans,response);
				}
			}
		}else if(command.equals("deposit")) {
			if(name.length()==0 || amount.length()==0) {
				num =  3;
				missing(num,response);
			}else {
				ans = bank.deposit(name,amount);
				if(ans == 0) {
					deposit_success(name,amount,response);
				}else {
					deposit_failed(name,amount,ans,response);
				}
			}
		}else if(command.equals("withdraw")) {
			if(name.length()==0 || amount.length()==0) {
				num = 4;
				missing(num,response);
			}else {
				ans = bank.withdraw(name,amount);
				if(ans == 0) {
					withdraw_success(name,amount,response);
				}else {
					withdraw_failed(name,amount,ans,response);
				}
			}
		}else if(command.equals("blance")) {
			if(name.length()==0) {
				num =5;
				missing(num,response);
			}else {
				ans = bank.showBalance(name);
				if(ans >= 0) {
					blance_success(name,response);
				}else {
					blance_failed(name,response);
				}
			}
		} 
		
	}
private void missing(int num,HttpServletResponse response) throws ServletException, IOException {	
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>未入力</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>未入力です</h1>");
	pw.println("<p class='text'>入力してください</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	if(num==1) {
		pw.println("<a class='text'href='open'>OK</a>");
	}else if(num==2) {
		pw.println("<a class='text'href='close'>OK</a>");
	}else if(num==3) {
		pw.println("<a class='text'href='deposit'>OK</a>");
	}else if(num==4) {
		pw.println("<a class='text'href='withdraw'>OK</a>");
	}else{
		pw.println("<a class='text'href='balance'>OK</a>");
	}
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
}
private void open_success(String name,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang='jp'>");
		pw.println("<head>");
		pw.println("<meta charset='UTF-8'>");
		pw.println("<link rel='stylesheer' href='css/global.css'>");
		pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
		pw.println("<title>開設成功</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div class=='text'>");
		pw.println("<h1>ユーザー登録完了</h1>");
		pw.println("<p class='text'>"+name+"さんの登録完了</p>");
		pw.println("<p class='text'>「OK」を押してください</p>");
		pw.println("<a class='text'href='index.html'>OK</a>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		
}
private void open_failed(String name,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>開設失敗</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div clss=='text'>");
	pw.println("<h1>ユーザー登録失敗</h1>");
	pw.println("<p class='text'>"+name+"さんの登録失敗</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void close_success(String name,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>ユーザー解約成功</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>ユーザー解約成功</h1>");
	pw.println("<p class='text'>"+name+"さんの口座を解約しました(結果コード200)</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void close_failed(String name,int ans,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>ユーザー解約失敗</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>ユーザー解約失敗</h1>");
	if(ans==-1) {
	pw.println("<p class='text'>"+name+"さんの口座の残高コインが0枚ではないため解約できませんでした(結果コード201)</p>");
	}else {
		pw.println("<p class='text'>"+name+"さんの口座は口座リストにありません(結果コード207)</p>");
	}
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void deposit_success(String name,String amount,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	int num = bank.showBalance(name);
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>コインの追加成功</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>コイン追加成功</h1>");
	pw.println("<p class='text'>"+amount+"枚のコインが追加されました。残高はコインは"+num+"枚です</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void deposit_failed(String name,String amount,int ans,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>開設失敗</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>コインの追加失敗</h1>");
	if(ans==-3) {
		pw.println("<p class='text'>指定された金額が0以下のため"+name+"さんに"+amount+"枚の追加は失敗しました(結果コード303)</p>");
	}else if(ans==-4) {
		pw.println("<p class='text'>指定された金額が整数でないため"+name+"さんに"+amount+"枚の追加は失敗しました(結果コード304)</p>");
	}else {
		pw.println("<p class='text'>"+name+"さんの口座は口座リストにありません(結果コード307)</p>");
	}
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void withdraw_success(String name,String amount,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	int num = bank.showBalance(name);
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>コインの引き出し成功</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>コインの引き出し成功</h1>");
	pw.println("<p class='text'>"+amount+"枚のコインが引き出しをされました。。残高はコインは"+num+"枚です(結果コード400)</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void withdraw_failed(String name,String amount,int ans,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>コインの引き出し失敗</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>コインの引き出し失敗</h1>");
	if(ans==-1) {
		pw.println("<p class='text'>残高コインを越えるコインを引き出そうとしたため"+name+"さんの口座から"+amount+"枚の引き出しは失敗しました(結果コード401)</p>");
	}else if(ans==-3) {
		pw.println("<p class='text'>0以下のコインを引き出そうとしたため"+name+"さんの口座から"+amount+"枚の引き出しは失敗しました(結果コード403)</p>");
	}else if(ans==-4) {
		pw.println("<p class='text'>指定されたコインが整数でないため"+name+"さんの口座から"+amount+"枚の引き出しは失敗しました(結果コード404)</p>");
	}else {
		pw.println("<p class='text'>"+name+"さんの口座は口座リストにありません(結果コード407)</p>");
	}
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void blance_success(String name,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	int num = bank.showBalance(name);
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>残高コイン参照成功</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>残高コイン参照成功</h1>");
	pw.println("<p class='text'>"+name+"さんの残高コインは"+num+"枚です(結果コード500)</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
private void blance_failed(String name,HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html lang='jp'>");
	pw.println("<head>");
	pw.println("<meta charset='UTF-8'>");
	pw.println("<link rel='stylesheer' href='css/global.css'>");
	pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
	pw.println("<title>残高コイン照会失敗</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<div class=='text'>");
	pw.println("<h1>残高コイン照会失敗</h1>");
	pw.println("<p class='text'>"+name+"さんの口座は存在しないため参照に失敗しました(結果コード507)</p>");
	pw.println("<p class='text'>「OK」を押してください</p>");
	pw.println("<a class='text'href='index.html'>OK</a>");
	pw.println("</div>");
	pw.println("</body>");
	pw.println("</html>");
	
}
}

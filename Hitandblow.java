package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class OpenServlet
 */
public class Hitandblow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter pw = response.getWriter();
		    HttpSession session = request.getSession();
		    int i;
		    ArrayList<Integer> list = new ArrayList<>();
		    
		    //historyの取得
		    int[][] history = (int[][])session.getAttribute("historydata");  
		    //historyの初期化
		    if (history == null) { 
		        history = new int[10][3]; // 最大10回分の履歴を格納
		    }
		    
		    //hitとblowの取得
		    int[] hit_temp = (int[]) session.getAttribute("hitdata");  
		    int[] blow_temp = (int[]) session.getAttribute("blowdata");

		    //hitとblowがnullなら初期化
		    if (hit_temp == null) {
		        hit_temp = new int[10];  
		    }
		    if (blow_temp == null) {
		        blow_temp = new int[10]; 
		    }
		    
		    //カウントの取得、カウントの設定
		    Integer count = (Integer) session.getAttribute("countdata");
		    if(count == null) {
		    	count =1;
		    }
		    
		    if(count == 1) {
		    //リストを作成
		    for(i = 1 ; i <= 10 ; i++) {
		    	list.add(i);
			}
		    Collections.shuffle(list);
		    }else {
			    //データを取得
			    list = (ArrayList<Integer>) session.getAttribute("listdata");
			    //listをセッションに登録
			    session.setAttribute("listdata",list);
		    }
		    
		    //リストをセッションに登録
		    session.setAttribute("listdata",list);
		     
		    pw.println("<!DOCTYPE html>");
		    pw.println("<html>");
		    pw.println("<head>");
		    pw.println("<title>口座開設</title>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println("<h1>10回挑戦可能</h1>");
		    pw.println("<h1>"+count+"回目</h1>");
		    pw.println("<h1>1～10の数を入力してください。</h1>");
		    pw.println("<p class=\"text\">数字を登録して[OK]を押してください</p>");
		    pw.println("<form action='Judgment' method='get'>");
		    if(count==1) {
		    	//ユーザー登録
		    	 pw.println("<div class='text'>ユーザー名 : <input type='text' name='name' size='10'/><br>");
		    	//倍率入力
		    	pw.println("<div class='text'>倍率 : <input type='text' name='mag' size='10'/><br>");
		    	//金額入力
		    	pw.println("使用するコイン数を入力 : <input type='text' name='coin' size='10'/><br>");
		    	pw.println("</div>");
		    }else {
		    	Integer mag = (Integer) session.getAttribute("magdata");
		    	Integer coin = (Integer) session.getAttribute("coindata");
		    	Integer gain = (Integer) session.getAttribute("gaindata");
		    	
		    	pw.println("<h3>倍率　:  "+mag+"倍</h3>");
		    	pw.println("<h3>コイン　:  "+coin+"枚</h3>");
		    	pw.println("<h3>成功で"+gain+"枚獲得、失敗で"+gain+"枚失う</h3>");
		    	session.setAttribute("gaindata",gain);
		    }
		    pw.println("<form action='Judgment' method='get'>");
		    pw.println("<div class='text'>数字1を入力 : <input type='text' name='number1' size='5'/><br>");
		    pw.println("数字2を入力 : <input type='text' name='number2' size='5'/><br>");
		    pw.println("数字3を入力 : <input type='text' name='number3' size='5'/><br>");
		    pw.println("<input type='submit' value='OK'/>");
		    pw.println("<input type='reset' value='CANCEL'/><br>");
		    pw.println("</form>");
		    pw.println("<a href='index.html'>戻る</a>");
		    pw.println("</div>"); 
		    pw.println("<h2>履歴<h2>");
		    if(count>0) {
		    for(int k=1;k<count;k++) {
		    	pw.println("<h3>"+k+"回目の入力値 : "+history[k][0] +","+ history[k][1] +","+ history[k][2]+", hit数 : "+hit_temp[k] + ",blow数 : "+blow_temp[k]+ "<h3>");
		    	
		    }
		    }
		    pw.println("</body>");
		    pw.println("</html>");
		  //カウントをセッションに登録
		    session.setAttribute("countdata",count);
		    //historyをセッションに追加
		    session.setAttribute("historydata",history); 
		    //hitとblow数をセッションに登録
		    session.setAttribute("hitdata", hit_temp);
			session.setAttribute("blowdata", blow_temp);    

	}

}

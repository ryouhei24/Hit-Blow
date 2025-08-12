package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class BankServlet
 */
public class JudgmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ExtendedBank bankdata;
	public JudgmentServlet(){
		bankdata = new ExtendedBank();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		ArrayList<Integer> list = new ArrayList<>();
		String number1 = request.getParameter("number1");//入力した数1
	    String number2 = request.getParameter("number2");//入力した数2
	    String number3 = request.getParameter("number3");//入力した数3
	    String mag = request.getParameter("mag");//倍率
	    String coin = request.getParameter("coin");//使用金額
	    String name = request.getParameter("name");//口座名
	    int temp1=0,temp2=0,temp3=0;
	    //カウントを取得
	  	Integer count = (Integer) session.getAttribute("countdata");
		if(count==null) {
			count=1;
		}
		
	    //未入力の時
	    if(count==1) {
	    if(number1.length()==0 || number1.length()==0 || number1.length()==0 || mag.length()==0 || coin.length()==0 || name.length()==0) {
			missing(response);
		}
	    }else {
	    	 if(number1.length()==0 || number1.length()==0 || number1.length()==0 ) {
	    		 missing(response); 
	    	 }
		}
	    
	    
	    int[][] history = (int[][]) session.getAttribute("historydata");  
	    int[] hit_temp = (int[]) session.getAttribute("hitdata");  
	    int[] blow_temp = (int[]) session.getAttribute("blowdata");  
	    // historyがnullの場合は初期化
	    if(history == null) { 
	     	history = new int[11][3]; // 最大10回分の履歴を格納
	    }
	    
	    //hitとblowがnullなら初期化
	    if (hit_temp == null) {
	        hit_temp = new int[11];  
	    }
	    if (blow_temp == null) {
	        blow_temp = new int[11]; 
	    }

	    if(count==1) {
	    int mag_temp = Integer.parseInt(mag);
	    int coin_temp = Integer.parseInt(coin);
	    int gain = mag_temp * coin_temp;
	    
	    //セッションに登録
	    session.setAttribute("namedata",name);
	    session.setAttribute("magdata",mag_temp);
	    session.setAttribute("coindata",coin_temp);
	    session.setAttribute("gaindata",gain);
	    }
	    
	    list = (ArrayList<Integer>) session.getAttribute("listdata");
	    
	    //受けった数の型をintに変換,未入力なら再入力
	    if (number1 != null && !number1.isEmpty()) {
	        temp1 = Integer.parseInt(number1);
	    } else {
	        // ここでデフォルト値を設定するか、エラーメッセージを表示する
	        missing(response);
	        return;
	    }

	    if (number2 != null && !number2.isEmpty()) {
	        temp2 = Integer.parseInt(number2);
	    } else {
	        missing(response);
	        return;
	    }

	    if (number3 != null && !number3.isEmpty()) {
	        temp3 = Integer.parseInt(number3);
	    } else {
	        missing(response);
	        return;
	    }
	    
	    //履歴を表示するための数を保存
	    if(count>=1 && count<10) {
	    	history[count][0] = temp1;
	 	    history[count][1] = temp2;
	 	    history[count][2] = temp3;
	    }
	    
	    
	    //historyの保存
	    session.setAttribute("historydata", history);
	    
	    //変数宣言
		int hit=0,blow=0,i;
			
		int[] temp = new int[list.size()];
		
		for (i = 0; i < list.size(); i++) {
			temp[i] = list.get(i);
		}
		
		/*intにカードの型を変換*/
		int card1 = temp[0];
		int card2 = temp[1];
		int card3 = temp[2];

		/*hit&blowの判定*/
		if(card1==temp1) {
			hit++;
		}else if(card1==temp2 || card1==temp3) {
			blow++;
		}
			
		if(card2==temp2) {
			hit++;
		}else if(card2==temp1 || card2==temp3) {
			blow++;
		}
			
		if(card3==temp3) {
			hit++;
		}else if(card3==temp1 || card3==temp2) {
			blow++;
		}
		
		//hit数とblow数の履歴の配列作成
	   if(count>=1 && count<10) {
	    	hit_temp[count] = hit;
	 	    blow_temp[count] = blow;
	  }
	   
	   session.setAttribute("hitdata", hit_temp);
	   session.setAttribute("blowdata", blow_temp);    
		
		
		/*HTML表示*/
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang='jp'>");
		pw.println("<head>");
		pw.println("<meta charset='UTF-8'>");
		pw.println("<link rel='stylesheer' href='css/global.css'>");
		pw.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		pw.println("<meta name='viewport' contentt='width =device-width,inital-scale=1.0'>");
		pw.println("<title>結果</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div class=='text'>");
		pw.println("<h1>比較結果</h1>");
		
		if(hit==3) {
			//勝利した場合
			Integer amount_temp = (Integer) session.getAttribute("gaindata");
			String name_temp = (String) session.getAttribute("namedata");
			pw.println("<p class='text'>hitが"+hit+"回ですべて一致です。成功です。</p>");
			pw.println("<h1>"+amount_temp+"枚獲得しました。</h1>");
			pw.println("<p class='text'>「OK」を押してください</p>");
			session.removeAttribute("listdata");
            session.removeAttribute("countdata");
            pw.println("<form action='bank' method='get'>");
            pw.println("<input type='hidden' name='name' value='" + name_temp + "'/>");
            pw.println("<input type='hidden' name='amount' value='" + amount_temp + "'>");
            pw.println("<input type='hidden' name='command' value='deposit'/>");
            pw.println("<input type='submit' value='OK'/>");
			pw.println("</form>");
			
		}else {
			if(count<10) {
			//負けた場合
			pw.println("<p class='text'>hitが"+hit+"回です</p>");
			pw.println("<p class='text'>blowが"+blow+"回です</p>");
			pw.println("<form action='coin' method='get'>");		
			//Coin.javaにlistの値とカウントの値を渡す。
			//セッションへの登録
			session.setAttribute("listdata",list);
			
			
			pw.println("<p class='text'>「OK」を押してください</p>");
			pw.println("<a class='text'href='hit'>OK</a>");
			}
			//10回で終了
			if (count >= 10) {
				Integer amount_temp = (Integer) session.getAttribute("gaindata");
				String name_temp = (String) session.getAttribute("namedata");
				pw.println("<h1>ゲーム失敗です。もう一度挑戦しましょう</h1>");
				pw.println("<h1>"+amount_temp+"枚失いました</h1>");
	            session.removeAttribute("listdata");
	            session.removeAttribute("countdata");
	            session.removeAttribute("historydata");
	            session.removeAttribute("hitdata");
	            session.removeAttribute("blowdata");
	            pw.println("<form action='bank' method='get'>");
	            pw.println("<input type='hidden' name='name' value='" + name_temp + "'/>");
	            pw.println("<input type='hidden' name='amount' value='" + amount_temp + "'>");
	            pw.println("<input type='hidden' name='command' value='withdraw'/>");
	            pw.println("<input type='submit' value='OK'/>");
				pw.println("</form>");
				
			}else {
				count++;
				session.setAttribute("countdata",count);
			}
			
		}
		
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		
		
	}
	//未入力のとき
	private void missing(HttpServletResponse response) throws ServletException, IOException {	
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
		pw.println("<a class='text'href='hit'>OK</a>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
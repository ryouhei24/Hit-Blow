package servlet;

public class Account {
	private String name; /* 口座名 */
	private int balance; /* 口座の残高 */
	
	public Account(String myName) { 
		this.name = myName;
		this.balance = 0;
		
	} /* コンストラクタ */
	public int deposit(int amount) {
		if(amount > 0) {
			this.balance += amount;
			return 0;
		}else {
			return -3;
		}
	} /* 預金 */
	public int withdraw(int amount) {
		if(0<amount && amount<=balance) {
			this.balance -=amount;
			return 0;
		}else if(amount>balance){
			return -1;
		}else {
			return -3;
		}
	} /* 払い戻し*/
	public int showBalance() {
		return balance;
	} /* 残高照会 */
}

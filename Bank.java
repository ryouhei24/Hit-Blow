package servlet;
import java.util.Hashtable;
public class Bank {
	private Hashtable<String, Account> customer;
	public Bank() {
		customer = new Hashtable<String,Account>();
	}
	public int open(String name) {
		if(customer.containsKey(name)) {
			return -7;
	}
	Account account = new Account(name);
		customer.put(name, account);
		return 0;
	} /* 口座開設 */
		
	public int close(String name) {
		if(!customer.containsKey(name)) {
			return -7;
		}
		if(customer.get(name).showBalance() !=0 ) {
			return -1;
		}
		customer.remove(name);
			return 0;
	} /* 口座解約 */
	public int deposit(String name, int amount) {
		if(!customer.containsKey(name)) {
			return -7;
		}
		int result = customer.get(name).deposit(amount);
		return result;
	} /* 預金 */
	public int withdraw(String name, int amount) { 
		if(!customer.containsKey(name)) {
			return -7;
		}
		int result = customer.get(name).withdraw(amount);
		return result;
	} /* 払い戻し*/
	public int showBalance(String name) { 
		if(!customer.containsKey(name)) {
			return -7;
		}
		return customer.get(name).showBalance();
	}/*残高照会*/

}

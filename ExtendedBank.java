package servlet;
public class ExtendedBank extends Bank {
	public ExtendedBank() {
		super();
	}
	
	int i,coin;
	@Override
	public int open(String name) {
		int result = super.open(name);
		if(result==0) {
			super.deposit(name,0);
		}
		return result;
	}
	public int deposit(String name, String amount) { 
		int amountInt;
		if(super.deposit(name, 0)==-7) {
			return -7;
		}
		try {
			amountInt = Integer.parseInt(amount);
		}catch (NumberFormatException e){
			return -4;
		}
		return super.deposit(name,amountInt);  
		}
	
	public int withdraw(String name, String amount) { 
		int amountInt;
		if(super.withdraw(name, 0)==-7) {
			return -7;
		}
		try {
			amountInt = Integer.parseInt(amount);
		}catch (NumberFormatException e){
			return -4;
		}
		return super.withdraw(name, amountInt); 
	}
	
}


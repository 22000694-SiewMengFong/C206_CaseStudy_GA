package StartPage;


public class Authentication {
	
	public static boolean CreateAccount() {
		//TODO Check if account is already in sql by email. Using CheckDB
		//TODO prevent SQL Injection
		return true;
	}
	
	public static boolean LoginAccount(String email, String password) {
		//TODO Check if account email and password is correct
		//TODO prevent SQL Injection by email and password
		return true;
	}
	
	private static String SQLInjection(String x) {
		//TODO Prevent SQL injectiion of string
		return "x";
	}
	
	private static boolean CheckDB(String email){
		//TODO Looks at DB and check if email is found
		return true;
	}
}

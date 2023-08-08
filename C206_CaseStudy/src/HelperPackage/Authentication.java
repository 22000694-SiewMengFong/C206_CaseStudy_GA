package HelperPackage;

public class Authentication {

	// ===============================
	// Create account and returns Login Credential
	// (DONE - NEED CHECKING)
	// ===============================

	/**
	 * Method CreateAccount
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param access
	 * @return
	 */
	private static DBData CreateAccount(String name, String email, String password, String access, String[] otherInfo) {

		String picture = null;
		
		// Return Credential
		DBData Credential = new DBData(email, password, access, name, picture, otherInfo);

		return Credential;
	} // End of CreateAccount

	/**
	 * Method CreateAccountNormal
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static DBData CreateAccountNormal(String name, String email, String password, String[] otherInfo) {

		String access = "normal";

		// Create normal account
		DBData Credential = CreateAccount(name, email, password, access, otherInfo);
		
		return Credential;

	} // End of CreateAccountNormal

	/**
	 * Method CreateAccountVendor
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static DBData CreateAccountVendor(String name, String email, String password, String[] otherInfo) {

		String access = "vendor";

		// Create vendor account
		DBData Credential = CreateAccount(name, email, password, access, otherInfo);

		return Credential;

	} // End of CreateAccountVendor

	/**
	 * Method CreateAccountAdmin
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static DBData CreateAccountAdmin(String name, String email, String password) {

		String access = "admin";
		
		String[] otherInfo = null;
		
		// Create admin account
		DBData Credential = CreateAccount(name, email, password, access, otherInfo);

		return Credential;

	} // End of CreateAccountAdmin

	// ===============================
	// Login to account and returns Login Credential
	// (DONE - NEED CHECKING)
	// ===============================
	
	public static DBData LoginAccount(String email, String password) {
		DBData Credential = new DBData(email, password);
		
		return Credential;
	}

} // End of Class

package com.cg.iter.main;
import java.util.Scanner;


import com.cg.iter.exception.UserCreateException;
import com.cg.iter.loginservice.LoginService;
import com.cg.iter.loginservice.LoginServiceImp;
import com.cg.iter.util.Validator;
public class LoginMain {
	LoginService logService;
	Validator valid = new Validator();
	public LoginMain() {
		Scanner sc = new Scanner(System.in);
		logService = new LoginServiceImp();
		int choice = 0;
		while(true) {
			choice = getChoice(sc);
			//i++;
			switch (choice) {
			case 1:
				loginScreen(logService,sc);
				break;
			case 2:
				adminloginScreen(logService,sc);
				break;
			case 3:
				forgetPasswordScreen(logService,sc);
				break;
			case 4:
				registerScreen(logService,sc);
				break;

			default:
				System.out.println("Invalid input!! Please enter number between 1 to 4 only!");
				break;
			}
		}
			
	}
	
	
	
	private void adminloginScreen(LoginService logService, Scanner sc) {
		boolean status = false;
		//System.out.println(logService.getDao());
		System.out.println("Enter Admin ID");
		String AdminID = sc.nextLine();		
		System.out.println("Enter Your Password");
		String password = sc.nextLine();
		if(logService.validateAdminUseridAndPassword(AdminID, password)) {
			status = true;
		}
			
		if(status==true) {
		System.out.println("Sucessfully Logged in as Admin!");
		System.exit(0);
		}
		return;
		
	}



	private void registerScreen(LoginService logService, Scanner sc) {
		
		System.out.println("Enter your name :" );
		String name = sc.nextLine();
		System.out.println("Enter userID :" );
		String userID = sc.nextLine();
		System.out.println("Enter your mobile number :" );
		String phoneno = sc.nextLine();
		if(valid.checkNumber(phoneno)) {
			System.out.println("Enter your email : ");
			String email = sc.nextLine();
			if(valid.checkMail(email)) {
				System.out.println("Please enter new password");
				String password = sc.nextLine();
				if(valid.checkPassword(password)) {
					User user = new User(name, phoneno, email, password,userID);
					
					//exception to check if the account is successfully created or not
					
					try {
						//System.out.println("main: "+user);
						logService.register(user);
						System.out.println("Congratulation!!!  You have successfully created your account!");
					} catch (UserCreateException e) {
						System.out.println(e.getMessage());
						//e.printStackTrace();
					}
					
				}
			}
		}
		return;
	}

	
	
	private void forgetPasswordScreen(LoginService logService, Scanner sc) {

		System.out.println("Please enter your userID :");
		String userID = sc.nextLine();
		if(logService.checkUseridExist(userID)) {
			System.out.println("Please enter your email : ");
			String email = sc.nextLine();
			//check email 
			if(valid.checkMail(email) && logService.checkEmailExist(userID,email)) {
				
				System.out.println("Enter user name : ");
				String name = sc.nextLine();
				System.out.println("Enter mobile number :");
				String phoneno = sc.nextLine();
				
				if(logService.checkCorrectMobileNumber(userID, phoneno) && logService.checkCorrectName(userID, name)) {
					System.out.println("Please enter new password");
					String newPass = sc.nextLine();
					if(valid.checkPassword(newPass)) {
						System.out.println("Enter password again");
						String checkPass = sc.nextLine();
						
						if(newPass.equals(checkPass)) {
							System.out.println(logService.forgetPassword(userID,newPass));
						}
						
						else {
							System.out.println("Password not matched");
						}
					}
				}
				else {
					System.out.println("Invalid details!");
				}
			}
		}

		else {
			if(userID.trim().length()==0) {
				System.out.println("User ID cannot be blank!");
			}
			else
			System.out.println("User ID does not exist. Please try again!");
		}
		return;
	}

	
	//Login implementation
	private void loginScreen(LoginService logService, Scanner sc ) {
	
		boolean status = false;
		//System.out.println(logService.getDao());
		System.out.println("Enter User ID");
		String userID = sc.nextLine();	
		if(userID.trim().length()==0) {
			System.out.println("Please enter the user ID!");
			return;
		}
		System.out.println("Enter Your Password");
		String password = sc.nextLine();
		if(logService.validateUseridAndPassword(userID, password)) {
			status = true;
		}
			
		if(status==true) {
		System.out.println("Sucessfully Logged in!");
		System.exit(0);
		}
		return;
	}

	//Getting the choice from the user
	private int getChoice(Scanner scan) {
		System.out.println();
		System.out.println();
		
		System.out.println("+-------------------------------+");
		System.out.println("|       1. Login                |");
		System.out.println("|       2. Admin Login	        |");
		System.out.println("|       3. Forget Password      |");
		System.out.println("|       4. Register             |");
		//System.out.println("|       4. Exit System          |");
		System.out.println("+-------------------------------+");
		System.out.println("******* ENTER YOU CHOICE ********");
		System.out.println();
		System.out.println();
		int choice = 0;
		while((choice=valid.checkChoice())==0 || choice < 0) {
			if(choice < 0) {
				System.out.println("Invalid input!! Don't enter negative values.");
			}
		}
		//while(!valid.checkChoice(choice));
		return choice;
	}

	
	public static void main(String[] args) {
		new LoginMain();
	}

}

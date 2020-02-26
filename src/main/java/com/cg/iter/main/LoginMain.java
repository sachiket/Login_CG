package com.cg.iter.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.cg.iter.Dao.UserDao;
import com.cg.iter.Dao.UserDaoImp;
import com.cg.iter.Exception.UserCreateException;
import com.cg.iter.LoginService.LoginService;
import com.cg.iter.LoginService.LoginServiceImp;
import com.cg.iter.util.Validator;

public class LoginMain {
	LoginService logService;
	Validator valid = new Validator();
	public LoginMain**() {
		Scanner sc = new Scanner(System.in);
		UserDao dao = new UserDaoImp();
		
		logService = new LoginServiceImp(dao);
		int choice = 0;
		while(true) {
			choice = getChoice(sc);
			sc.nextLine();
			switch (choice) {
			case 1:
				loginScreen(logService,sc);
				break;
			case 2:
				forgetPasswordScreen(logService,sc);
				break;
			case 3:
				AddUserScreen(logService,sc);
				break;
			case 4:
				System.exit(0);
				break;

			default:
				System.out.println("Choose 1 to 4 only");
				break;
			}
		}
			
	}
	
	
	
	private void AddUserScreen(LoginService logService, Scanner sc) {
		
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
						logService.addUser(user);
					} catch (UserCreateException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					System.out.println("Congratulation!!!  You have successfully created your account!");
				}
			}
		}
		return;
	}

	
	
	private void forgetPasswordScreen(LoginService logService, Scanner sc) {

		System.out.println("Please enter your userID :");
		String userID = sc.nextLine();
		System.out.println("Please enter your email : ");
		String email = sc.nextLine();
		//check email 
		if(valid.checkMail(email) && logService.checkUseridExist(userID) && logService.checkEmailExist(userID,email)) {
			
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
		else {
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
		int choice = 0;
		System.out.println();
		System.out.println();
		System.out.println("******* ENTER YOU CHOICE ********");
		System.out.println("+-------------------------------+");
		System.out.println("|       1. Login                |");
		System.out.println("|       2. Forget Password      |");
		System.out.println("|       3. Register             |");
		//System.out.println("|       4. Exit System          |");
		System.out.println("+-------------------------------+");
		System.out.println();
		System.out.println();
		
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter numbers only!");
			scan.nextLine();// consume the keyboard value
		}
		return choice;
	}

	
	public static void main(String[] args) {
		new LoginMain();
	}

}

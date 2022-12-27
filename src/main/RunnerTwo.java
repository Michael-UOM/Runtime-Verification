package main;

import java.util.Random;

public class RunnerTwo {
	
	public void badLogin() {
		System.out.println("Bad login!");
	}
	
	public void goodLogin(){
		System.out.println("Good login!");
	}
	
	public void logOut(){
		System.out.println("Logging out!");
	}
	
	public void attemptViewAlerts(LoginSystem loginSystem){
		if (loginSystem.getLoggedInStatus()){
			viewAlerts();
		}
	}
	
	public void viewAlerts(){
		System.out.println("Viewing alerts!");
	}
	
	public void run(final LoginSystem loginSystem) {
		final Random rand = new Random();
		while(true){
			final int randomNumber = rand.nextInt(3);
			
			switch(randomNumber){
			case 0:
				this.badLogin();
				loginSystem.updateLoginStatus(false);
				break;
			case 1:
				this.goodLogin();
				loginSystem.updateLoginStatus(true);
				int randomNumberTwo = rand.nextInt(2);
				
				if (randomNumberTwo == 0){
					// Log out again
					this.logOut();
					loginSystem.updateLoginStatus(false);
				} else {
					this.attemptViewAlerts(loginSystem);
					loginSystem.updateViewingAlertStatus(true);
					
					// Log out
					this.logOut();
					loginSystem.updateLoginStatus(false);
					loginSystem.updateViewingAlertStatus(false);
				}
				break;
			case 2:
				this.attemptViewAlerts(loginSystem);
				break;
			}
			
			// Small pause
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		final RunnerTwo m = new RunnerTwo();
		final LoginSystem loginSystem = m.new LoginSystem(false, false);
		m.run(loginSystem);
	}
	
	public class LoginSystem{
		private boolean loggedIn = false;
		public boolean viewingAlerts = false;
		
		public LoginSystem(final boolean loggedIn, final boolean viewingAlerts) {
			super();
			this.loggedIn = loggedIn;
			this.viewingAlerts = viewingAlerts;
		}
		
		public void updateLoginStatus(boolean loginStatus){
			if (loginStatus){
				this.loggedIn = true;
			} else {
				this.loggedIn = false;
			}
		}
		
		public void updateViewingAlertStatus(boolean viewAlertStatus) {
			if (viewAlertStatus){
				this.viewingAlerts = true;
			} else {
				this.viewingAlerts = false;
			}
		}
		
		public boolean getLoggedInStatus() {
			return loggedIn;
		}
		
		public boolean getViewingAlertStatus() {
			return viewingAlerts;
		}
	}

}

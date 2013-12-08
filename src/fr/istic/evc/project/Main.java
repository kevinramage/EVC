package fr.istic.evc.project;

public class Main {

	public static void main(String[] args) {
		
		// User
		User user = new User("login", "password");
		user.connect();
		
		// Environment
		Environment environment = new Environment();
		environment.init();
		environment.loadWorld("resources/World/01.xml");
		environment.createWorld();
		environment.start(user);
	}

}

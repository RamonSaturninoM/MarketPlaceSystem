import java.util.Scanner;

/**
 * This class acts as the main class to make the calls to every other class
 * needed to run.
 * 
 * @author ramonsaturnino
 */
public class LoginSystem {
    
    /**
     * The below declarations are the hard coded credentials provided by professor.
     * I used them to authenticate user and manager accounts.
     * 
     * @author ramonsaturnino
    */
    
    private static final String MANAGER_USERNAME = "admin"; 
    private static final String MANAGER_PASSWORD = "admin123"; 
    private static final String USER_USERNAME = "user"; 
    private static final String USER_PASSWORD = "user123"; 
    
    public static Object authMethod(String username, String password){
        if(username.equals(MANAGER_USERNAME) && password.equals(MANAGER_PASSWORD)){
//            Manager newManager = new Manager();
            return Manager.getInstance();
        } 
        else if(username.equals(USER_USERNAME) && password.equals(USER_PASSWORD)){
            User newUser = new User(Manager.getInstance());
            return newUser;
        }
        else{
            System.out.println("Invalid credentials");
            return null;
        }
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println("Welcome to MarketPlace!\n");
            System.out.print("Username: ");
            String username = scanner.nextLine(); 
            System.out.print("Password: ");
            String password = scanner.nextLine();
            Object user = authMethod(username, password);
            if(user != null) {
                if(user instanceof Manager) {
                System.out.println("Login successful as: Manager");
                ((Manager)user).showMenu();
                } else {
                System.out.println("Login successful as: User");
                ((User)user).showMenu();
                }        
            }  
        }
    }
}

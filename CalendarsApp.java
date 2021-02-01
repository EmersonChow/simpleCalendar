import java.util.ArrayList;
import java.util.Scanner;

public class CalendarsApp{
    private ArrayList<User> users;

    private User currentUser;
    public Scanner scan;

    public CalendarsApp(){
        users = new ArrayList<User>();
        scan = new Scanner(System.in);
        currentUser = null;
    }

    public void addUser()
    {
        System.out.println("Enter a username: ");
        String username = scan.nextLine();
        User newUser = new User(username);
        users.add(newUser);
        System.out.println("Added "+newUser.getUserId());
    }

    public void removeUser(String chosenId)
    {
        for (int i =0; i<users.size(); i++)
        {
            if(users.get(i).getUserId().equals(chosenId))
            {
                users.remove(users.get(i));
                System.out.println("Removed "+ chosenId);
            }
        }
    }

    public void chooseUser(String chosenId)
    {
        for (int i =0; i<users.size(); i++)
        {
            if(users.get(i).getUserId().equals(chosenId))
            {
                currentUser = users.get(i);
                System.out.println("Current User: "+ chosenId);         
            }
            else
            {
                System.out.println("User does not exist. You typed: " + chosenId);
            }
        }
    }

    public void displayUsers()
    {
        for (int i =0; i<users.size(); i++)
        {
            System.out.println(users.get(i).getUserId());
        }
    }

    public void showOptions()
    {
        System.out.println("Add User");
        System.out.println("Remove User");
        System.out.println("Change User");
        System.out.println("Display Current User");
        System.out.println("Display Users");
        System.out.println("Show Options");
        System.out.println("Enter User");
        System.out.println("Quit");
    }

    public String getCurrentUserId()
    {
        return currentUser.getUserId();
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public static void main(String[] args)
    {
        System.out.println(
            "Welcome to the Calendars App. Here are your options. Please type one of them to proceed. Actions for users will occur on current user.");
        CalendarsApp session = new CalendarsApp();
        boolean done = false;
        session.showOptions();
        System.out.println("------------------------------------------------------------------------------------------");

        while (!done){
            String choice = session.scan.nextLine();
            System.out.println("------------------------------------------------------------------------------------------");
            switch(choice)
            {
                case "Add User":
                    session.addUser();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                case "Remove User":
                    System.out.println("Input userId to be removed: ");
                    String userIdToBeRemoved = session.scan.nextLine();
                    session.removeUser(userIdToBeRemoved);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                case "Change User":
                    System.out.println("Input userId to be selected: ");
                    String userIdToBeSelected = session.scan.nextLine();
                    session.chooseUser(userIdToBeSelected);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                case "Display Current User":
                    try{
                        System.out.println("Current user is: " + session.getCurrentUserId());
                        System.out.println("------------------------------------------------------------------------------------------");
                        break;
                        }
                    catch(Exception e)
                    {
                        if (session.getCurrentUser() == null){
                            System.out.println("No current User.");
                            System.out.println("------------------------------------------------------------------------------------------");
                            break;
                        }
                        else{
                            System.out.println("Error. " + e);
                            break;
                        }     
                    }
                    
                case "Display Users":
                    session.displayUsers();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                case "Show Options":
                    session.showOptions();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Enter User":
                    session.getCurrentUser().begin(session.scan);
                    System.out.println("In User Selection.");
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                case "Quit":
                    done = true;
                    break;
                
                default:
                    System.out.println("You typed something wrong. You typed: "+choice+ ". Please type a proper choice.");
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
            }
        }
        
        session.scan.close();
        System.out.println("Goodbye.");
    }
}
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

enum Theme{
    Dark,
    Light
}

public class User {
    
    private String userId;
    private ArrayList<Calendar> calendars;
    private Theme theme;
    //Note: All enums implicitly extend java.lang.Enum. Because a class can only extend one parent (see Declaring Classes), the Java language does not support multiple inheritance of state (see Multiple Inheritance of State, Implementation, and Type), and therefore an enum cannot extend anything else.
    private ZoneId timezone;
    private Calendar currentCalendar;

    public User(String userId)
    {
        this.userId = userId;
        timezone = ZoneId.systemDefault();
        theme = Theme.Dark;
        calendars = new ArrayList<Calendar>();
        currentCalendar = null;
    }

    public void addCalendar(String calendarId)
    {
        Calendar newCal = new Calendar(calendarId);
        newCal.shareWith(userId);
        newCal.setOwner(userId);
        calendars.add(newCal);
        System.out.println("Added "+newCal.getCalendarId());
    }

    public void removeCalendar(String chosenId)
    {
        for (int i =0; i<calendars.size();i++)
        {
            if (calendars.get(i).getCalendarId().equals(chosenId))
            {
                calendars.remove(calendars.get(i));
                System.out.println("Removed "+ chosenId);
            }
        }
    }

    public void updateCalendar(String chosenId)
    {

        try 
        {
            currentCalendar.setCalendarId(chosenId);
            System.out.println("New calendar id "+ chosenId);
        } catch (Exception e) {
            System.out.println("Failed to update Calendar. You entered: "+chosenId);
        }
                
    }


    public void chooseCalendar(String chosenId)
    {
        for (int i =0; i<calendars.size(); i++)
        {
            if(calendars.get(i).getCalendarId().equals(chosenId))
            {
                try 
                {
                    currentCalendar = calendars.get(i);
                    System.out.println("Current Calendar "+ chosenId);
                } catch (Exception e) {
                    System.out.println("Calendar does not exist. You typed: " + chosenId);
                }
                
            }
        }
    }

    public void changeTheme()
    {
        if(theme == Theme.Dark)
        {
            theme = Theme.Light;
        }
        else{
            theme = Theme.Dark;
        }
    }

    public void displayCalendars()
    {
        for (int i =0; i<calendars.size(); i++)
        {
            System.out.println(calendars.get(i).getCalendarId());
        }
    }

    public void showOptions()
    {
        System.out.println("Add Calendar");
        System.out.println("Remove Calendar");
        System.out.println("Update Calendar");
        System.out.println("Change Current Calendar");
        System.out.println("Change Theme");
        System.out.println("Change Timezone");
        System.out.println("Enter Calendar");
        System.out.println("Display Calendars");
        System.out.println("Show Options");
        System.out.println("Return to User Selection");
    }

    public String getUserId()
    {
        return userId;
    }

    public String getTheme()
    {
        return theme.toString();
    }

    public ZoneId getTimeZone()
    {
        return timezone;
    }

    public void changeTimeZone(String newTimeZone)
    {
        try {
            timezone = ZoneId.of(newTimeZone);
            for(int i=0; i<calendars.size();i++)
            {
                calendars.get(i).adjustTime(timezone);
            }
            System.out.println("New timezone is: "+ timezone);
        } catch (Exception e) {
            System.out.println("Error. New timezone not set. You typed: " + newTimeZone);
        }
         
    }

    public Calendar getCurrentCalendar()
    {
        return currentCalendar;
    }


    public void begin(Scanner scan)
    {
        System.out.println("Welcome "+ this.getUserId() + ". Here are your options. Please select one.");
        this.showOptions();
        System.out.println("------------------------------------------------------------------------------------------");
        boolean done = false;
        while (!done){
            String choice = scan.nextLine();
            System.out.println("------------------------------------------------------------------------------------------");
            switch(choice)
            {
                case "Add Calendar":
                    System.out.println("Input Calendar id: ");
                    String calendarId = scan.nextLine();
                    this.addCalendar(calendarId);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Remove Calendar":
                    System.out.println("Input Calendar id: ");
                    String calendarIdToBeRemoved = scan.nextLine();
                    this.removeCalendar(calendarIdToBeRemoved);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Update Calendar":
                    System.out.println("Input new Calendar id: ");
                    String newCalendarId = scan.nextLine();
                    this.updateCalendar(newCalendarId);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Change Current Calendar":
                    System.out.println("Input calendarId to be selected: ");
                    String calendarIdToBeSelected = scan.nextLine();
                    this.chooseCalendar(calendarIdToBeSelected);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Change Theme":
                    System.out.println("Current Theme is: "+ this.getTheme());
                    System.out.println("Press Y to change themes, N to keep current theme.");
                    String response = scan.nextLine();
                    if(response.equals("Y"))
                    {
                        this.changeTheme();
                        System.out.println("New theme is: "+ this.getTheme());
                    }
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Change Timezone":
                    System.out.println("Current Timezone is: "+ this.timezone);
                    System.out.println("Available timezones are: ");
                    
                    Object[] allZoneIds = ZoneId.getAvailableZoneIds().toArray();
                    for (int i=0; i< allZoneIds.length;i++)
                    {
                        System.out.println(allZoneIds[i]);
                    }
                    System.out.println("Pick a new Timezone:");
                    String newTimezone = scan.nextLine();
                    this.changeTimeZone(newTimezone);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Enter Calendar":
                    this.getCurrentCalendar().begin(scan, this.getTimeZone());
                    System.out.println("In Calendar Selection.");
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                
                case "Show Options":
                    this.showOptions();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Display Calendars":
                    this.displayCalendars();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                    
                case "Return to User Selection":
                    done = true;
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                
                default:
                    System.out.println("You typed something wrong. You typed: "+choice+ ". Please type a proper choice.");
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
            }
        }
    }

}

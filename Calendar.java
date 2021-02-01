import java.util.ArrayList;
import java.time.ZoneId;
import java.util.Scanner;

public class Calendar {



    private String calendarId;
    private ArrayList <Event> events; 
    private ArrayList<String> sharedWith;
    private Event currentEvent;
    private String ownerId;


    public Calendar(String calendarId)
    {
        this.calendarId = calendarId;
        events = new ArrayList<Event>();
        sharedWith = new ArrayList<String>();
        currentEvent = null;
    }

    public void setOwner(String userId)
    {
        ownerId = userId;
    }

    public void shareWith(String userId)
    {
        sharedWith.add(userId);
    }

    public void addEvent(String eventId, ZoneId timeZone, Scanner scan)
    {
        Event newEvent = new Event(eventId, timeZone, scan, this.sharedWith);
        events.add(newEvent);
    }

    public void removeEvent(String eventId)
    {
        for (int i =0; i<events.size();i++)
        {
            if (events.get(i).getEventId().equals(eventId))
            {
                events.remove(events.get(i));
                System.out.println("Removed "+ eventId);
            }
        }
    }

    public void updateEvent(String newId)
    {
        currentEvent.setEventId(newId);
        System.out.println("New event title: " + newId);
    }

    public void changeCurrentEvent(String eventId)
    {
        for (int i =0; i<events.size();i++)
        {
            if (events.get(i).getEventId().equals(eventId))
            {
                this.currentEvent = events.get(i);
                System.out.println("Current Event title: " + eventId);
            }
        }
    }

    public void adjustTime(ZoneId newTimezone)
    {
        for (int i =0; i<events.size();i++)
        {
            events.get(i).adjustTime(newTimezone);
        }
    }

    public String getCalendarId()
    {
        return calendarId;
    }

    public void setCalendarId(String newId)
    {
        calendarId = newId;
    }

    public void displayEvents()
    {
        for (int i =0; i<events.size(); i++)
        {
            System.out.println(events.get(i).getEventId());
        }
    }

    public void getCurrentEvent()
    {
        currentEvent.displayEvent();
    }

    public void showOptions()
    {
        System.out.println("Add Event");
        System.out.println("Remove Event");
        System.out.println("Update Event");
        System.out.println("Display Events");
        System.out.println("Display Current Event");
        System.out.println("Change Current Event");
        System.out.println("Show Options");
        System.out.println("Return to Calendar Selection");
    }



    public void begin(Scanner scan, ZoneId timeZone)
    {
        System.out.println("Welcome to Calendar: "+ this.getCalendarId() + ". Here are your options. Please select one.");
        this.showOptions();
        System.out.println("------------------------------------------------------------------------------------------");
        boolean done = false;
        while (!done)
        {
            String choice = scan.nextLine();
            switch(choice)
            {
                case "Add Event":
                    System.out.println("Input Event title: ");
                    String eventId = scan.nextLine();
                    this.addEvent(eventId, timeZone, scan);
                    String throwaway = scan.nextLine();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Remove Event":
                    System.out.println("Input Event-to-be-removed title: ");
                    String eventToRemoveId = scan.nextLine();
                    this.removeEvent(eventToRemoveId);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Update Event":
                    System.out.println("Input New title: ");
                    String newTitle = scan.nextLine();
                    this.updateEvent(newTitle);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
            
                case "Display Events":
                    this.displayEvents();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Display Current Event":
                    this.getCurrentEvent();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Change Current Event":
                    System.out.println("Input Event to be selected: ");
                    String eventIdToBeSelected = scan.nextLine();
                    this.changeCurrentEvent(eventIdToBeSelected);
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;
                
                case "Show Options":
                    this.showOptions();
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case "Return to Calendar Selection":
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

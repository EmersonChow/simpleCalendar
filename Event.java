import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.ArrayList;

public class Event{
    private String name;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ArrayList<String> sharedWith;

    public Event(String eventId, ZoneId zone, Scanner scan, ArrayList<String>sharedList)
    {
        this.name = eventId;
        this.sharedWith = sharedList;

        System.out.println("Insert start year, month, day, hour, and minute: ");
        int year = scan.nextInt();
        int month = scan.nextInt();
        int day = scan.nextInt();
        int hour = scan.nextInt();
        int minute = scan.nextInt();
        this.start = ZonedDateTime.of(year,month,day,hour,minute,0,0,zone);

        System.out.println("Insert end year, month, day, hour, and minute: ");
        int year2 = scan.nextInt();
        int month2 = scan.nextInt();
        int day2 = scan.nextInt();
        int hour2 = scan.nextInt();
        int minute2 = scan.nextInt();
        this.end = ZonedDateTime.of(year2,month2,day2,hour2,minute2,0,0,zone);

        this.displayEvent();
    }

    public String getEventId()
    {
        return name;
    }

    public void setEventId(String newId)
    {
        this.name = newId;
    }
    
    public void displayEvent()
    {
        System.out.println("Event name: "+this.name);
        System.out.println("Start date (month/day/year): "+ 
        this.start.getMonthValue() + "/" + this.start.getDayOfMonth() + "/" + this.start.getYear() + 
        " at " + this.start.getHour() + " hours and " + this.start.getMinute()+ " minutes");
        System.out.println("End date (month/day/year): "+ 
        this.end.getMonthValue() + "/" + this.end.getDayOfMonth() + "/" + this.end.getYear() + 
        " at " + this.end.getHour() + " hours and " + this.end.getMinute()+ " minutes");
        System.out.println("Shared with: ");
        for (int i=0;i<sharedWith.size();i++)
        {
            System.out.println(sharedWith.get(i));
        }
    }

    public void adjustTime(ZoneId newTimezone)
    {
        start = start.withZoneSameInstant(newTimezone);
        end = end.withZoneSameInstant(newTimezone);
    }

}
 
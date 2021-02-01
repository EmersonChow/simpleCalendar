Java 11

For the most part, the console will tell you what you can do. Only 
specific nuance is that you must make sure you use Change User/Calendar/Event to set a current object before you try to Enter it or
Display it. The order is: Add Object -> Change Object -> Do what you like. The specific actions are applied to the Current object.
This is because I did not have the time to go through and add safeguards on the null and current -Object- is originally set to null.


These are the 8 covered criteria:


2. Each calendar in an app can have different sets of events that the user 
can set hours and minutes for, including starting and ending times.

4. Users can add, remove, and update Calendar events.

5. Calendars supports multiple users each of which may have multiple calendars.

6. Users can add, remove, and update individual calendars.

7. Calendars supports multiple time zones. Changing time zones for the app automatically updates the time for events in a user's calendar.

8. Calendars provides users with a choice of a dark theme or light theme.

9. Calendars has a screen that allows the user to configure the app for a particular time zone or change the app's theme.

16. Selecting a particular event should show details of an event, including the 
time it starts and ends, the title of the event, and any users the event is shared with.  


Note: #16 is a bit funky because I did not implement the rest of the sharing options. The only person that 
the event is shared with is its owner. The framework is there to implement more if time permits. 
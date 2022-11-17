package agenda;

import java.time.*;
import java.time.temporal.Temporal;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }



    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        LocalDateTime end = getStart().plus(getDuration());
        boolean occurs = false;
        if (getStart().toLocalDate().isBefore(aDay) && end.toLocalDate().isAfter(aDay)) {occurs = true;}
        else if (getStart().toLocalDate().isEqual(aDay) || end.toLocalDate().isEqual(aDay)) {occurs = true;}
        else
        {occurs = false;}
        return occurs;
    }

   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    public String toString() {
        return this.myTitle;
    }

   
    
}

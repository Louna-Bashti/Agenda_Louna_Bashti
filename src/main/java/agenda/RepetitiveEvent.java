package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */

    private ChronoUnit frequency;
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        if(frequency == ChronoUnit.WEEKS || frequency == ChronoUnit.DAYS || frequency == ChronoUnit.MONTHS)
        this.frequency = frequency;
        else {throw new IllegalArgumentException("Fréquence non comprise dans les possibles");}

    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    ArrayList<LocalDate> exceptions = new ArrayList<LocalDate>();
    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    public boolean IsInDay(LocalDate date) {

        LocalDateTime end = getStart().plus(getDuration());
        LocalDateTime nextDateStart = getStart();
        LocalDateTime nextDateEnd = end;

        boolean occurs = false;

        if (exceptions.contains(date))
        // la date est dans les exceptions, donc on n'a pas à chercher plus loin
        {occurs = false;}
        // on vérifie que la date est bien comprise dans l'évènement au début (gestion des overlapping events incluse)
        else if (getStart().toLocalDate().isBefore(date) && end.toLocalDate().isAfter(date)) {occurs = true;}
        else if (getStart().toLocalDate().isEqual(date) || end.toLocalDate().isEqual(date)) {occurs = true;}
        else {
            //on vérifie toutes les occurrences de l'évènement jusqu'à ce que celle vérifiée dépasse la date donnée :
            //Si la date en fait partie, on arrête la boucle while et on rend true, si on dépasse sans que la date en fasse partie, on rend false

                do {
                    nextDateStart = nextDateStart.plus(1, frequency);
                    nextDateEnd = nextDateEnd.plus(1, frequency);
                    if (nextDateStart.toLocalDate().isBefore(date) && nextDateEnd.toLocalDate().isAfter(date))
                    {
                        occurs = true;
                        break;
                    }
                    else if (nextDateStart.toLocalDate().isEqual(date) || nextDateEnd.toLocalDate().isEqual(date))
                    {
                        occurs = true;
                        break;}

                }
                while (date.isAfter(nextDateEnd.toLocalDate()));
                }
        return occurs;

        }


    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
    return this.frequency;
    }

}

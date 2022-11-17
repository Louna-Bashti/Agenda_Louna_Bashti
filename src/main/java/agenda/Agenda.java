package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    private ArrayList<Event> myAgenda;



    public Agenda() {
        myAgenda = new ArrayList<Event>();

    }

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        myAgenda.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> agendaInDay = new ArrayList<Event>();
        for (int i=0; i<= myAgenda.size(); i++) {
            Event e = myAgenda.get(i);
            if (e.isInDay(day)) {agendaInDay.add(e);}
        }
        return agendaInDay;
    }
}

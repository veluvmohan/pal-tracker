package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    List<TimeEntry> timeEntries = new LinkedList<TimeEntry>();
   // public static long sequence = 0;

    public TimeEntry create(TimeEntry timeEntry){
        timeEntry.setId(timeEntries.size()+1);
        this.timeEntries.add(timeEntry);
        return timeEntry;

    }

    public InMemoryTimeEntryRepository(){
       // sequence=0;
    }

  /*  public  long getSequence(){
      //  return ++sequence;
        //return new Long(timeEntries.size()+1);
    }*/

    public TimeEntry find (long id){
        TimeEntry timeEntry = new TimeEntry();
        for (TimeEntry eachTimeEntry: timeEntries) {
            if(eachTimeEntry.getId()==id){
                timeEntry = eachTimeEntry;
                return timeEntry;
            }

        }
        return null;

    }


    public List<TimeEntry> list() {
        return timeEntries;
    }

    public TimeEntry update(long id, TimeEntry newTimeEntry) {
        for(int i=0; i<timeEntries.size();i++){
            if(timeEntries.get(i).getId()==id){
                newTimeEntry.setId(id);
                //timeEntries.add(i,newTimeEntry);
                timeEntries.set(i,newTimeEntry);

            }

        }

        return newTimeEntry;

    }

    public void delete(long id) {
        for(int i=0;i<timeEntries.size();i++){
            if(timeEntries.get(i).getId() ==id)
                timeEntries.remove(i);

        }

    }
}

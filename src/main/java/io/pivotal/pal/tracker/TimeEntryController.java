package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        ResponseEntity responseEntity = null;
        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<> (timeEntry, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = this.timeEntryRepository.find(id);
                if(timeEntry ==null)
                    return new ResponseEntity <> (HttpStatus.NOT_FOUND);
                else
                    return new ResponseEntity<> (timeEntry, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry expected) {
        TimeEntry timeEntry = this.timeEntryRepository.update(id,expected);
        if(timeEntry ==null)
            return new ResponseEntity <> (HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<> (timeEntry, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
           this.timeEntryRepository.delete(id);
           return new ResponseEntity<> (HttpStatus.NO_CONTENT);

    }
}

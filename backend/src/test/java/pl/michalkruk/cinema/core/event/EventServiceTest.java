package pl.michalkruk.cinema.core.event;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    private EventService eventService;
    private EventRepository eventRepository;

    private List<Event> events;

    @Before
    public void init() {
        this.eventRepository = mock(EventRepository.class);
        this.eventService = new EventService(eventRepository, "");
        this.events = new ArrayList<>();

        events.add(Event.builder().id(1L).eventDate(LocalDate.of(2018, 12, 31)).build());
        events.add(Event.builder().id(2L).eventDate(LocalDate.of(2018, 12, 15)).build());
        events.add(Event.builder().id(3L).eventDate(LocalDate.of(2018, 12, 8)).build());
        events.add(Event.builder().id(4L).eventDate(LocalDate.of(2018, 12, 19)).build());
    }

    @Test
    public void should_return_last_n_events() {
        // given
        int numberOfEventsToFind = 2;
        when(eventRepository.findAll()).thenReturn(events);

        // when
        List<Event> events = eventService.findLastNEventsSortedByEventDate(numberOfEventsToFind);

        // then
        Assert.assertEquals(numberOfEventsToFind, events.size());
        Assert.assertEquals((Long) 1L, events.get(0).getId());
        Assert.assertEquals(LocalDate.of(2018, 12, 31), events.get(0).getEventDate());
        Assert.assertEquals((Long) 4L, events.get(1).getId());
        Assert.assertEquals(LocalDate.of(2018, 12, 19), events.get(1).getEventDate());
    }
}

package com.flightbooking;

import com.flightbooking.controller.FlightController;
import com.flightbooking.entity.Flight;
import com.flightbooking.repository.FlightRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightController flightController;

    @Test
    void getAllFlightsReturnsFlights() {
        Flight flight = new Flight();
        flight.setAirline("IndiGo");

        when(flightRepository.findAll())
                .thenReturn(List.of(flight));

        List<Flight> flights = flightController.getAllFlights();

        assertEquals(1, flights.size());
        assertEquals("IndiGo", flights.get(0).getAirline());
    }
}


package com.flightbooking.model;

import jakarta.validation.constraints.NotBlank;

public class Booking {

    private Long id;

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    public Booking() {
    }

    public Booking(Long id, String passengerName,
                   String source, String destination) {
        this.id = id;
        this.passengerName = passengerName;
        this.source = source;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

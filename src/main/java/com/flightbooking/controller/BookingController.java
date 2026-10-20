
package com.flightbooking.controller;

import com.flightbooking.model.Booking;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final ConcurrentMap<Long, Booking> bookings =
            new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @Valid @RequestBody Booking booking) {

        long id = idGenerator.getAndIncrement();
        booking.setId(id);
        bookings.put(id, booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookings.values().stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Booking booking = bookings.get(id);

        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        Booking removed = bookings.remove(id);

        if (removed == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

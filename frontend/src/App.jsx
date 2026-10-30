
import { useState } from 'react'

function App() {
  const [source, setSource] = useState('')
  const [destination, setDestination] = useState('')
  const [flights, setFlights] = useState([])
  const [message, setMessage] = useState('')

  const searchFlights = async (event) => {
    event.preventDefault()

    if (!source.trim() || !destination.trim()) {
      setMessage('Please enter both source and destination.')
      setFlights([])
      return
    }

    try {
      setMessage('Searching flights...')

      const response = await fetch(
        `/api/flights/search?source=${encodeURIComponent(source)}&destination=${encodeURIComponent(destination)}`
      )

      if (!response.ok) {
        throw new Error('Unable to fetch flights.')
      }

      const data = await response.json()
      setFlights(data)
      setMessage(data.length ? '' : 'No flights found for this route.')
    } catch (error) {
      setFlights([])
      setMessage('Unable to connect to the flight booking service.')
    }
  }

  return (
    <div className="app">
      <header className="navbar">
        <h2>✈ FlightBook</h2>
        <span>Flight Booking System</span>
      </header>

      <main className="hero">
        <div className="hero-content">
          <p className="eyebrow">YOUR JOURNEY STARTS HERE</p>
          <h1>Find your next flight.</h1>
          <p className="subtitle">
            Search available flights and explore your next destination.
          </p>

          <form className="search-card" onSubmit={searchFlights}>
            <div className="field">
              <label>FROM</label>
              <input
                type="text"
                placeholder="Departure city"
                value={source}
                onChange={(e) => setSource(e.target.value)}
              />
            </div>

            <div className="field">
              <label>TO</label>
              <input
                type="text"
                placeholder="Destination city"
                value={destination}
                onChange={(e) => setDestination(e.target.value)}
              />
            </div>

            <button type="submit">Search Flights</button>
          </form>

          {message && <p className="message">{message}</p>}

          {flights.length > 0 && (
            <section className="results">
              <h2>Available Flights ({flights.length})</h2>

              {flights.map((flight) => (
                <article className="flight-card" key={flight.id}>
                  <div className="flight-header">
                    <h3>✈ {flight.airline}</h3>
                    <span>Flight ID: {flight.id}</span>
                  </div>

                  <div className="flight-route">
                    <div>
                      <span className="flight-label">FROM</span>
                      <h3>{flight.source}</h3>
                    </div>

                    <span className="route-arrow">→</span>

                    <div>
                      <span className="flight-label">TO</span>
                      <h3>{flight.destination}</h3>
                    </div>
                  </div>

                  <div className="flight-footer">
                    <div>
                      <span className="flight-label">DEPARTURE</span>
                      <p>
                        {new Date(
                          flight.departure_time
                        ).toLocaleString('en-IN')}
                      </p>
                    </div>

                    <div>
                      <span className="flight-label">PRICE</span>
                      <h2>
                        ₹{Number(flight.price).toLocaleString('en-IN')}
                      </h2>
                    </div>
                  </div>
                </article>
              ))}
            </section>
          )}
        </div>
      </main>

      <footer>
        FlightBook · Powered by Spring Boot, React and MySQL
      </footer>
    </div>
  )
}

export default App

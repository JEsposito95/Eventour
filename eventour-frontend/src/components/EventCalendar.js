import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Calendar from 'react-calendar';

const EventCalendar = () => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios.get('https://api.eventour.com/events') // Reemplaza con la URL correcta de la API
      .then(response => {
        setEvents(response.data);
      })
      .catch(error => {
        console.error('Error fetching events:', error);
      });
  }, []);

  const eventDates = events.map(event => new Date(event.date));

  return (
    <div>
      <h2>Calendario de Eventos</h2>
      <Calendar
        tileContent={({ date, view }) => {
          return eventDates.find(d => d.getTime() === date.getTime()) ? <span>●</span> : null;
        }}
      />
    </div>
  );
};

export default EventCalendar;

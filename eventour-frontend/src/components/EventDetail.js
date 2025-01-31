import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const EventDetail = () => {
  const { id } = useParams();
  const [event, setEvent] = useState(null);

  useEffect(() => {
    axios.get(`https://api.eventour.com/events/${id}`) // Reemplaza con la URL correcta de la API
      .then(response => {
        setEvent(response.data);
      })
      .catch(error => {
        console.error('Error fetching event:', error);
      });
  }, [id]);

  if (!event) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>{event.name}</h2>
      <p>{event.description}</p>
      <p>{event.date}</p>
    </div>
  );
};

export default EventDetail;

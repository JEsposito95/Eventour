import React, { useState } from 'react';
import axios from 'axios';

const AddEventForm = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const newEvent = { name, description, date };
    axios.post('https://api.eventour.com/events', newEvent) // Reemplaza con la URL correcta de la API
      .then(response => {
        alert('Evento cargado exitosamente');
        setName('');
        setDescription('');
        setDate('');
      })
      .catch(error => {
        console.error('Error cargando evento:', error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Nombre del Evento:
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
      </label>
      <label>
        Descripción:
        <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} required />
      </label>
      <label>
        Fecha:
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
      </label>
      <button type="submit">Cargar Evento</button>
    </form>
  );
};

export default AddEventForm;

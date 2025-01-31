import React, { useState } from 'react';
import axios from 'axios';

const EventSearch = () => {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = (e) => {
    e.preventDefault();
    axios.get(`https://api.eventour.com/events/search?query=${query}`) // Reemplaza con la URL correcta de la API
      .then(response => {
        setResults(response.data);
      })
      .catch(error => {
        console.error('Error searching events:', error);
      });
  };

  return (
    <div>
      <h2>Búsqueda de Eventos</h2>
      <form onSubmit={handleSearch}>
        <input 
          type="text" 
          value={query} 
          onChange={(e) => setQuery(e.target.value)} 
          placeholder="Buscar eventos..." 
        />
        <button type="submit">Buscar</button>
      </form>
      <ul>
        {results.map(event => (
          <li key={event.id}>
            <h3>{event.name}</h3>
            <p>{event.description}</p>
            <p>{event.date}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EventSearch;

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import EventList from './components/EventList';
import EventDetail from './components/EventDetail';
import EventCalendar from './components/EventCalendar';
import EventSearch from './components/EventSearch';

function App() {
  return (
    <Router>
      <div style={{ padding: '20px' }}>
        <h1>Página de Eventos</h1>
        <div style={{ display: 'flex', justifyContent: 'space-around', marginBottom: '20px' }}>
          <EventSearch />
          <EventCalendar />
        </div>
        <EventList />
        <Routes>
          <Route path="/events/:id" element={<EventDetail />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

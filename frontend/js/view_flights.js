document.addEventListener('DOMContentLoaded', function() {
  // Try to load flights from localStorage
  let flights = [];
  try {
    flights = JSON.parse(localStorage.getItem('flights')) || [];
    if (!Array.isArray(flights)) flights = [];
  } catch (e) {
    flights = [];
  }

  // Find the table body in view_flights.html
  const table = document.querySelector('table');
  if (!table || flights.length === 0) return;

  // Remove all rows except the header
  while (table.rows.length > 1) table.deleteRow(1);

  // Add each flight as a row
  flights.forEach(f => {
    const row = table.insertRow();
    row.insertCell().innerText = f.flightId;
    row.insertCell().innerText = f.source;
    row.insertCell().innerText = f.destination;
    row.insertCell().innerText = f.date;
    row.insertCell().innerText = f.landingDate;
    row.insertCell().innerText = f.ecoCost;
    row.insertCell().innerText = f.businessCost;
    row.insertCell().innerText = f.firstCost;
  });
});

// Placeholder for future dynamic flight loading, search/filter, and export features

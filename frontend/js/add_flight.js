function addFlight(event) {
  event.preventDefault();
  const flightId = document.getElementById('flightId').value.trim();
  const date = document.getElementById('date').value.trim();
  const landingDate = document.getElementById('landingDate').value.trim();
  const source = document.getElementById('source').value.trim();
  const destination = document.getElementById('destination').value.trim();
  const ecoCost = document.getElementById('ecoCost').value.trim();
  const businessCost = document.getElementById('businessCost').value.trim();
  const firstCost = document.getElementById('firstCost').value.trim();

  if (!flightId || !date || !landingDate || !source || !destination || !ecoCost || !businessCost || !firstCost) {
    document.getElementById('output').innerText = 'All fields are required.';
    return;
  }

  let flights = [];
  try {
    flights = JSON.parse(localStorage.getItem('flights')) || [];
    if (!Array.isArray(flights)) flights = [];
  } catch (e) {
    flights = [];
  }

  // Prevent duplicate flightId
  if (flights.some(f => f.flightId === flightId)) {
    document.getElementById('output').innerText = 'Flight ID already exists.';
    return;
  }

  flights.push({ flightId, date, landingDate, source, destination, ecoCost, businessCost, firstCost });
  localStorage.setItem('flights', JSON.stringify(flights));
  document.getElementById('output').innerText = `Flight Added:\nID: ${flightId}\nDate: ${date}\nLanding: ${landingDate}\nSource: ${source}\nDestination: ${destination}\nEconomy: ${ecoCost}\nBusiness: ${businessCost}\nFirst: ${firstCost}`;
  document.getElementById('addFlightForm').reset();
}

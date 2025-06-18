function deleteFlight(event) {
  event.preventDefault();
  const flightId = document.getElementById('flightId').value;
  // Placeholder for backend integration
  document.getElementById('output').innerText = `Flight Deleted:\nID: ${flightId}`;
  document.getElementById('deleteFlightForm').reset();
}

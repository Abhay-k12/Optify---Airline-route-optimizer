function updateFlight(event) {
  event.preventDefault();
  const flightId = document.getElementById('flightId').value;
  const date = document.getElementById('date').value;
  const landingDate = document.getElementById('landingDate').value;
  const source = document.getElementById('source').value;
  const destination = document.getElementById('destination').value;
  const ecoCost = document.getElementById('ecoCost').value;
  const businessCost = document.getElementById('businessCost').value;
  const firstCost = document.getElementById('firstCost').value;
  if (!date && !landingDate && !source && !destination && !ecoCost && !businessCost && !firstCost) {
    document.getElementById('output').innerText = 'Please enter at least one field to update.';
    return;
  }
  // Placeholder for backend integration
  document.getElementById('output').innerText = `Flight Updated:\nID: ${flightId}\nDate: ${date}\nLanding: ${landingDate}\nSource: ${source}\nDestination: ${destination}\nEconomy: ${ecoCost}\nBusiness: ${businessCost}\nFirst: ${firstCost}`;
  document.getElementById('updateFlightForm').reset();
}

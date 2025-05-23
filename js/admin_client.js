function submitFlight(event) {
  event.preventDefault();
  const flightId = document.getElementById('flightId').value;
  const source = document.getElementById('source').value;
  const destination = document.getElementById('destination').value;
  const date = document.getElementById('date').value;
  const landingDate = document.getElementById('landingDate').value;
  const ecoCost = document.getElementById('ecoCost').value;
  const businessCost = document.getElementById('businessCost').value;
  const firstCost = document.getElementById('firstCost').value;
  // Placeholder for backend API call
  document.getElementById('output').innerText =
    `Flight Added:\nID: ${flightId}\nSource: ${source}\nDestination: ${destination}\nDate: ${date}\nLanding: ${landingDate}\nEconomy: 9${ecoCost}\nBusiness: 9${businessCost}\nFirst: 9${firstCost}`;
  document.getElementById('flightForm').reset();
}

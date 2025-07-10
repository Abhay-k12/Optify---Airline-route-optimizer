function addFlight(event) {
  event.preventDefault();
  const flightData = {
    flightId: document.getElementById('flightId').value.trim(),
    flightDate: document.getElementById('date').value.trim(),
    landingDate: document.getElementById('landingDate').value.trim(),
    source: document.getElementById('source').value.trim(),
    destination: document.getElementById('destination').value.trim(),
    ecoCost: document.getElementById('ecoCost').value.trim(),
    businessCost: document.getElementById('businessCost').value.trim(),
    firstCost: document.getElementById('firstCost').value.trim()
  }
  console.log("Button clicked.");
  fetch("/Optifly/AddFlightServlet",{
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(flightData)
  })
  .then(response => response.text())
  .then(data => {
    document.getElementById("output").innerHTML = data;
  })
  .catch(error => {
    document.getElementById("output").innerHTML = "Error:"+error;
  });
}


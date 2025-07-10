// Placeholder for future dynamic flight loading, search/filter, and export features
function viewFlight(event) {
  event.preventDefault();
  console.log("abe oooooooooo");
  const flightData = {
    flightId: document.getElementById('flightId').value.trim()
  };

  fetch("/Optifly/ViewFlightServlet", {
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
    document.getElementById("output").innerHTML = "Error: " + error;
  });
}

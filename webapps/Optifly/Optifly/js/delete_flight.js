function deleteFlight(event) {
  event.preventDefault();
  const flightData = {
    flightId: document.getElementById('flightId').value.trim()
  };

  fetch("/Optifly/DeleteFlightServlet", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(flightData)
  })
  .then(response => response.text())
  .then(data => {
    document.getElementById("output").innerHTML = data;
    document.getElementById("deleteFlightForm").reset();
  })
  .catch(error => {
    document.getElementById("output").innerHTML = "Error: " + error;
  });
}

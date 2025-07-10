function getOptimizedPath() {
  const source = document.getElementById('source').value.trim();
  const destination = document.getElementById('destination').value.trim();
  const priority = document.getElementById('priority').value;

  if (!source || !destination) {
    document.getElementById('output').innerText = 'Please enter both source and destination.';
    return;
  }

  const requestData = {
    source: source,
    destination: destination,
    priority: priority
  };

  console.log("Button clicked.");

  fetch("/Optifly/OptimisePathServlet", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(requestData)
  })
  .then(response => {
    if (!response.ok) throw new Error("Server Error");
      return response.text();
  })
  .then(data => {
      document.getElementById('output').innerText = data;
  })
  .catch(err => {
    document.getElementById('output').innerText = "Error: " + err.message;
  });
}


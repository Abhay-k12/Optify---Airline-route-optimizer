function deleteClient(event) {
  event.preventDefault();
  const clientId = document.getElementById('clientId').value;
  // Placeholder for backend integration
  document.getElementById('output').innerText = `Client Deleted:\nID: ${clientId}`;
  document.getElementById('deleteClientForm').reset();
}

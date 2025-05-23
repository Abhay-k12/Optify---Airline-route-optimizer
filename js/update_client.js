function updateClient(event) {
  event.preventDefault();
  const clientId = document.getElementById('clientId').value;
  const clientName = document.getElementById('clientName').value;
  const email = document.getElementById('email').value;
  const phone = document.getElementById('phone').value;
  if (!clientName && !email && !phone) {
    document.getElementById('output').innerText = 'Please enter at least one field to update.';
    return;
  }
  // Placeholder for backend integration
  document.getElementById('output').innerText = `Client Updated:\nID: ${clientId}\nName: ${clientName}\nEmail: ${email}\nPhone: ${phone}`;
  document.getElementById('updateClientForm').reset();
}

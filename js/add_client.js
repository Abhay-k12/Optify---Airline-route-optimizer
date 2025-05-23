function addClient(event) {
  event.preventDefault();
  const clientId = document.getElementById('clientId').value.trim();
  const clientName = document.getElementById('clientName').value.trim();
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();

  if (!clientId || !clientName || !email || !phone) {
    document.getElementById('output').innerText = 'All fields are required.';
    return;
  }

  let clients = [];
  try {
    clients = JSON.parse(localStorage.getItem('clients')) || [];
    if (!Array.isArray(clients)) clients = [];
  } catch (e) {
    clients = [];
  }

  // Prevent duplicate clientId
  if (clients.some(c => c.clientId === clientId)) {
    document.getElementById('output').innerText = 'Client ID already exists.';
    return;
  }

  clients.push({ clientId, clientName, email, phone });
  localStorage.setItem('clients', JSON.stringify(clients));
  document.getElementById('output').innerText = `Client Added:\nID: ${clientId}\nName: ${clientName}\nEmail: ${email}\nPhone: ${phone}`;
  document.getElementById('addClientForm').reset();
}

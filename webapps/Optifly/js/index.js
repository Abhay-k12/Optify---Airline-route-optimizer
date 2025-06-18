function getOptimizedPath() {
  const source = document.getElementById('source').value;
  const destination = document.getElementById('destination').value;
  const priority = document.getElementById('priority').value;
  if (!source || !destination) {
    document.getElementById('output').innerText = 'Please enter both source and destination.';
    return;
  }
  // Placeholder: Replace with actual API call to backend
  document.getElementById('output').innerText =
    `Fetching optimized route from ${source} to ${destination}\nPriority: ${priority}\n\n→ Sample Path: ${source} → XYZ → ${destination}`;
}

document.addEventListener('DOMContentLoaded', function() {
  // Example: Smooth scroll for internal links (if you add section anchors)
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
      e.preventDefault();
      const target = document.querySelector(this.getAttribute('href'));
      if (target) {
        target.scrollIntoView({ behavior: 'smooth' });
      }
    });
  });

  // Animate h1 on load
  const h1 = document.querySelector('.about-container h1');
  if (h1) {
    h1.style.opacity = 0;
    h1.style.transform = 'translateY(-30px)';
    setTimeout(() => {
      h1.style.transition = 'all 0.8s cubic-bezier(.68,-0.55,.27,1.55)';
      h1.style.opacity = 1;
      h1.style.transform = 'translateY(0)';
    }, 200);
  }

  // Expand/collapse features
  document.querySelectorAll('.about-features li').forEach(li => {
    li.style.cursor = 'pointer';
    li.addEventListener('click', function() {
      this.classList.toggle('expanded');
    });
  });

  // Button ripple effect
  document.querySelectorAll('.nav-btn').forEach(btn => {
    btn.addEventListener('click', function(e) {
      const circle = document.createElement('span');
      circle.className = 'ripple';
      circle.style.left = e.offsetX + 'px';
      circle.style.top = e.offsetY + 'px';
      this.appendChild(circle);
      setTimeout(() => circle.remove(), 600);
    });
  });
});
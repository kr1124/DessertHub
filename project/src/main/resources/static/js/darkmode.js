const themeToggleButton = document.getElementById('theme-toggle');

    // 페이지 로드 시, 저장된 테마 확인
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
      document.body.setAttribute('data-theme', 'dark');
      themeToggleButton.textContent = '🌕';
    } else {
      document.body.removeAttribute('data-theme');
      themeToggleButton.textContent = '🌙';
    }

    // 테마 토글 이벤트
    themeToggleButton.addEventListener('click', () => {
      const currentTheme = document.body.getAttribute('data-theme');
      if (currentTheme === 'dark') {
        document.body.removeAttribute('data-theme');
        themeToggleButton.textContent = '🌙';
        localStorage.setItem('theme', 'light');
      } else {
        document.body.setAttribute('data-theme', 'dark');
        themeToggleButton.textContent = '🌕';
        localStorage.setItem('theme', 'dark');
      }
    });
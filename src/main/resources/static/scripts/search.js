const search = document.getElementById('search');
const input = search.querySelector('input');
const enterKey = "Enter";

search.addEventListener('keypress', event => {
  if(event.key === enterKey) {
    event.preventDefault();

    localStorage.clear();
    localStorage.setItem('username', input.value);

    location.reload();
  }
});
const search = document.getElementById('search');
const input = search.querySelector('input');
const repoTemplate = document.querySelector('[repo-template]');
const displayBox = document.querySelector('.display-box');
const enterKey = "Enter";
let value;

search.addEventListener('keypress', event => {
  if(event.key === enterKey) {
    
  }
});

if(value !== null) {
  fetch(`http://localhost:8080/repository-management/repositories/${input.value}`)
  .then(res => res.json())
  .then(data => {
    data.foreach(repo => {
      const template = repoTemplate.content.cloneNode(true);

      const name = template.querySelector('.name');
      const visibility = template.querySelector('.visibility');
      const language = template.querySelector('.language');

      name.textContent = repo.full_name;
      visibility.textContent = repo.visibility;
      language.textContent = repo.language;

      displayBox.appendChild(template);
    });
  });
}

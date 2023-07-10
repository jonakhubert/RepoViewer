const repoTemplate = document.querySelector('[repo-template]');
const displayBox = document.querySelector('.display-box');
const errorMessage = document.querySelector('.error-message');
let value = localStorage.getItem('username');

if(value !== null && value !== '') {
  fetch(`http://localhost:8080/repository-management/repositories/${value}`)
  .then(res => res.json())
  .then(data => {
    if(data.httpStatus)
      errorMessage.textContent = data.message;
    else {
      data.forEach(repo => {
        const template = repoTemplate.content.cloneNode(true);

        const name = template.querySelector('.name');
        const visibility = template.querySelector('.visibility');
        const language = template.querySelector('.language');

        name.textContent = repo.full_name;
        name.href = repo.url;
        name.target = "_blank";
        visibility.textContent = repo.visibility;
        language.textContent = repo.language;

        displayBox.appendChild(template);
      });
    }
  localStorage.clear();
  });
}
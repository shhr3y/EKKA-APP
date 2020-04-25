
const gotoSignUpButton = document.getElementById('gotoSignUp');
const gotoSignInButton = document.getElementById('gotoSignIn');
const container = document.getElementById('container');


gotoSignUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

gotoSignInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

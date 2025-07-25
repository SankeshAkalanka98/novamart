// Toggle password visibility for login page
const loginTogglePassword = document.querySelectorAll('.toggle-password');
const loginPassword = document.querySelector('#loginPassword');

if (loginTogglePassword && loginPassword) {
    loginTogglePassword.forEach(button => {
        button.addEventListener('click', function() {
            const type = loginPassword.getAttribute('type') === 'password' ? 'text' : 'password';
            loginPassword.setAttribute('type', type);
            this.querySelector('i').classList.toggle('fa-eye');
            this.querySelector('i').classList.toggle('fa-eye-slash');
        });
    });
}
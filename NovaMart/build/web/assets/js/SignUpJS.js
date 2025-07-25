document.addEventListener('DOMContentLoaded', function() {
    // Toggle password visibility
    const togglePassword = document.querySelector('.toggle-password');
    const password = document.querySelector('#password');
    
    if (togglePassword && password) {
        togglePassword.addEventListener('click', function() {
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            this.querySelector('i').classList.toggle('fa-eye');
            this.querySelector('i').classList.toggle('fa-eye-slash');
        });
    }
    
    // Form validation
//    const form = document.querySelector('form');
//    if (form) {
//        form.addEventListener('submit', function(e) {
//            const password = document.querySelector('#password');
//            const confirmPassword = document.querySelector('#confirmPassword');
//            
//            if (password.value !== confirmPassword.value) {
//                e.preventDefault();
//                alert('Passwords do not match!');
//                confirmPassword.focus();
//            }
//            
//            // Add more validation as needed
//        });
//    }
});
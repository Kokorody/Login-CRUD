//document.addEventListener('DOMContentLoaded', () => {
//    const container = document.querySelector('.container');
//    const loginButton = document.querySelector('.login-section header');
//    const loginForm = document.getElementById('login-form');
//
//    loginButton.addEventListener('click', () => {
//        container.classList.add('active');
//    });
//
//
//    loginForm.addEventListener('submit', (event) => {
//        event.preventDefault();
//        const email = document.getElementById('login-email').value.trim();
//        const password = document.getElementById('login-password').value.trim();
//        let valid = true;
//
//        if (email === '') {
//            showError('login-email-error', 'Please fill in your email address.');
//            valid = false;
//        } else if (!validateEmail(email)) {
//            showError('login-email-error', 'Please enter a valid email address.');
//            valid = false;
//        } else {
//            clearError('login-email-error');
//        }
//
//        if (password === '') {
//            showError('login-password-error', 'Please fill in your password.');
//            valid = false;
//        } else {
//            clearError('login-password-error');
//        }
//
//        if (valid) {
//            // If all validations pass, submit the form
//            loginForm.submit();
//        }
//    });
//
//    // Show error message
//    function showError(elementId, message) {
//        document.getElementById(elementId).textContent = message;
//    }
//
//    // Clear error message
//    function clearError(elementId) {
//        document.getElementById(elementId).textContent = '';
//    }
//
//    // Email validation function
//    function validateEmail(email) {
//        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//        return re.test(email);
//    }
//});

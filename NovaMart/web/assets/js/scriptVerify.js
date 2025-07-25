document.addEventListener('DOMContentLoaded', function() {
    // Countdown timer for verification link (5 minutes)
    let countdown = 5 * 60; // 5 minutes in seconds
    const countdownElement = document.getElementById('countdown');
    
    const countdownInterval = setInterval(function() {
        const minutes = Math.floor(countdown / 60);
        const seconds = countdown % 60;
        
        countdownElement.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
        
        if (countdown <= 0) {
            clearInterval(countdownInterval);
            countdownElement.textContent = "Expired";
            countdownElement.style.color = "#dc3545";
        } else {
            countdown--;
        }
    }, 1000);
    
    // Resend email button functionality
    const resendBtn = document.getElementById('resendBtn');
    let resendCooldown = 60; // 60 seconds cooldown
    
    const resendInterval = setInterval(function() {
        resendBtn.textContent = `Resend Email (${resendCooldown}s)`;
        
        if (resendCooldown <= 0) {
            clearInterval(resendInterval);
            resendBtn.textContent = "Resend Email";
            resendBtn.disabled = false;
            resendBtn.classList.add('btn-pulse');
        } else {
            resendCooldown--;
        }
    }, 1000);
    
    resendBtn.addEventListener('click', function() {
        // Simulate sending verification email
        this.disabled = true;
        this.classList.remove('btn-pulse');
        this.textContent = "Email sent!";
        
        // Show success message
        const alertDiv = document.createElement('div');
        alertDiv.className = 'alert alert-success mt-3';
        alertDiv.textContent = 'Verification email has been resent successfully!';
        this.parentNode.insertBefore(alertDiv, this.nextSibling);
        
        // Reset button after 5 seconds
        setTimeout(() => {
            alertDiv.remove();
            resendCooldown = 60;
            this.textContent = `Resend Email (${resendCooldown}s)`;
            
            // Restart the cooldown counter
            const newInterval = setInterval(function() {
                resendBtn.textContent = `Resend Email (${resendCooldown}s)`;
                
                if (resendCooldown <= 0) {
                    clearInterval(newInterval);
                    resendBtn.textContent = "Resend Email";
                    resendBtn.disabled = false;
                    resendBtn.classList.add('btn-pulse');
                } else {
                    resendCooldown--;
                }
            }, 1000);
        }, 5000);
    });
});

async function signUp() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

//    console.log(firstName);
//    console.log(lastName);
//    console.log(email);
//    console.log(password);

    const user = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
    };

    const userJson = JSON.stringify(user);

    const response = await fetch(
            "SignUp",
            {
                method: "POST",
                body: userJson,
                headers: {
                    "Content-Type": "application/json"
                }
            });

    if (response.ok) {
        const json = await response.json();
//        console.log(responsejson);
        if (json.status) {
//            window.location = "verify-account.html";
        } else {
            console.log(json.message);
            document.getElementById("message").innerHTML = json.message;
            
        }
    } else {
        document.getElementById("message").innerHTML = "Registration failed. Please try again";
    }
}



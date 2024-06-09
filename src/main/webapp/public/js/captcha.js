/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//function generateCaptcha() {
//        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        var captchaCode = "";
//        for (var i = 0; i < 6; i++) {
//            var randomIndex = Math.floor(Math.random() * characters.length);
//            captchaCode += characters.charAt(randomIndex);
//        }
//        document.getElementById("captcha").innerText = captchaCode; // Display captcha code
//    }
//
//    // Function to validate captcha
//    function validateCaptcha() {
//        var serverCaptcha = document.getElementById("captcha").innerText;
//        var userInput = document.getElementById("uc").value;
//
//        if (serverCaptcha === userInput) {
//            return true; // Captcha is valid, proceed with form submission
//        } else {
//            alert("Captcha does not match!"); // Display error message
//            return false; // Prevent form submission
//        }
//    }


//    function rememberMe() {
//    const email = document.getElementById("emailaddress").value;
//    const password = document.getElementById("password").value;
//    const rememberMeCheckbox = document.getElementById("rememberMeCheckbox");
//
//    if (rememberMeCheckbox.checked) {
//        localStorage.setItem("email", email);
//        localStorage.setItem("password", password);
//    } else {
//        localStorage.removeItem("email");
//        localStorage.removeItem("password");
//    }
//}
//
//function fillLoginForm() {
//    const email = localStorage.getItem("email");
//    const password = localStorage.getItem("password");
//    const rememberMeCheckbox = document.getElementById("rememberMeCheckbox");
//
//    if (email && password) {
//        document.getElementById("emailaddress").value = email;
//        document.getElementById("password").value = password;
//        rememberMeCheckbox.checked = true;
//    }
//}


//function validateCategory() {
//    var categoryInput = document.getElementById('exampleInputEmail1');
//    var errorSpan = document.getElementById('catErr');
//    var categoryName = categoryInput.value.trim();
//    if (categoryName === "") {
//        errorSpan.innerHTML = "Category Name is required";
//        return false;
//    } else {
//        errorSpan.innerHTML = ""; // Clear any previous error message
//        return true;
//    }
//}

//function ajaxAction(actionUrl, cartId, quantity, productId) {
//    var xhr = new XMLHttpRequest();
//    xhr.open("POST", actionUrl, true);
//    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//    xhr.onreadystatechange = function () {
//        if (xhr.readyState === 4 && xhr.status === 200) {
//            // Update the part of the page with the response
//            document.getElementById("cartSection").innerHTML = xhr.responseText;
//        }
//    };
//    xhr.send("cartId=" + cartId + "&quantity=" + quantity + "&productId=" + productId);
//}

function validateCategoryForm() {
    var categoryName = document.getElementById("catname").value;
    var errorMsg = document.getElementById("catErr");

    if (categoryName.trim().length < 2) {
        errorMsg.innerHTML = "Category Name must be at least 2 characters long.";
        errorMsg.style.color = "red";
        return false;
    }

    errorMsg.innerHTML = "";
    return true;
}
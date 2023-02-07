// Selectors

const firstName = document.getElementById("first-name");
const lastName = document.getElementById("last-name");
const email = document.getElementById("email");
const password = document.getElementById("password_el");
const eyeClick = document.querySelector("[data-password]");
const phone = document.getElementById("phone");
const birthdate = document.getElementById("bd");





// Event - Eye Click
eyeClick.onclick = () => {
  const icon = eyeClick.children[0];
  icon.classList.toggle("fa-eye-slash");
  if (password.type === "password") {
    password.setAttribute("type", "text");
  } else if (password.type === "text") {
    password.setAttribute("type", "password");
  }
};

// Function CheckInputs
function checksInputs() {
  const firstNameValue = firstName.value.trim();
  const lastNameValue = lastName.value.trim();
  const emailValue = email.value.trim();
  const passwordValue = password.value.trim();
  const phoneValue = phone.value.trim();
  const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

  if (firstNameValue === "") {
    firstName.style.border = "2px solid red";
    return false;

    
  } else {
    firstName.style.border = "2px solid green";
    true;
  }

  if (lastNameValue === "") {
    lastName.style.border = "2px solid red";
    return false;

   
  } else {
    lastName.style.border = "2px solid green";
    true;
  }

  if (!emailValue === "" || emailValue.match(mailformat)) {
    email.style.border = "2px solid green";
    true;
  } else {
    email.style.border = "2px solid red";
    return false;

  }

  if (passwordValue === "") {
    password.style.border = "2px solid red";
    return false;

  } else {
    password.style.border = "2px solid green";
    true;
  }

  if (phoneValue === "") {
    phone.style.border = "2px solid red";
    return false;

  } else {
    phone.style.border = "2px solid green";
    true;
  }

  if (birthdate.value === "") {
    birthdate.style.border = "2px solid red";
    return false;

  } else {
    birthdate.style.border = "2px solid green";
    true;
  }
}


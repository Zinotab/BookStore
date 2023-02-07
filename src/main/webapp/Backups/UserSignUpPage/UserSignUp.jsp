<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="ISO-8859-1">
    <meta charset="UTF-8" />
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    
    
<link rel="stylesheet" type="text/css" href="/BookStore/Backups/UserSignUpPage/style.css" />
    <title>Create Account</title>
  </head>

  <body>
    <!-- Form -->
    <form class="signup-form" onsubmit="return checksInputs()" method="post" action="SignUpUser" >
      <!-- Form Title -->
      <h1 class="title">Create Account</h1>
      <!-- Input Wrapper -->
      <div class="input-wrapper">
        <label class="label">
          <!-- First Name  -->
          <input
            id="first-name"
            class="mv-right"
            type="text"
            name="First Name"
            placeholder="&nbsp;"
          />
          <p class="label-text">First Name</p>
          <i class="fa-solid fa-address-card f-name"></i>
        </label>
        <!-- Last Name -->
        <label class="label">
          <input
            id="last-name"
            type="text"
            name="Last Name"
            placeholder="&nbsp;"
          />
          <p class="label-text">Last Name</p>
          <i class="fa-solid fa-address-card"></i>
        </label>
      </div>
      <!-- Email -->
      <label class="label">
        <input id="email" type="email" name="Email" placeholder="&nbsp;" />
        <p class="label-text">Email</p>
        <i class="fa-solid fa-envelope"></i>
      </label>
      <!-- Password -->
      <label class="label">
        <input
          id="password_el"
          type="password"
          name="Password"
          placeholder="&nbsp;"
          autocomplete="on"
        />
        <p class="label-text">Password</p>
        <span data-password> <i class="fa-solid fa-eye"></i></span>
      </label>
      <label class="label">
        <input
          id="phone"
          type="text"
          name="Tel"
          placeholder="&nbsp;"
          autocomplete="on"
        />
        <p class="label-text">Phone Number</p>
      </label>
      <label class="label">
        <input
          id="bd"
          type="date"
          name="birthdate"
          placeholder="&nbsp;"
          autocomplete="on"
          class="BDDD"
        />
        <p class="label-text">Birthdate</p>
      </label>
      <div class="group">
        <!-- Submit Button -->
        <button class="submit-btn" type="submit" id="btn">
          Create Account
        </button>
        <a class="link-Login" href="/BookStore/Backups/UserLoginPage/UserLoginPage.jsp">LOGIN</a>
      </div>
    </form>

    <!-- Script -->
    <script src="app.js"></script>
  </body>
</html>

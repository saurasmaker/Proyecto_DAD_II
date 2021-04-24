function showPasswordLog() {
  var x = document.getElementById("login-form-pass");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
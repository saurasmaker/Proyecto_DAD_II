function showPasswordReg(){
    var x = document.getElementById("reg-form-pass");
    var y = document.getElementById("reg-form-repeatpass");
    if (x.type === "password"){
      x.type = "text";
    } else {
      x.type = "password";
    }
    if (y.type === "password"){
        y.type = "text";
    }else {
        y.type = "password";
    }
  }
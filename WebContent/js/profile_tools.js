function showPassword(){
	 var x = document.getElementById("user-input-update-password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
}
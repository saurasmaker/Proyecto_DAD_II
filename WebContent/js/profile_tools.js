function showPasswordProfile(){
	 var x = document.getElementById("user-input-update-password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
}

function openUpdateProfile(){
	document.getElementById("open-update-form").style.display = "none";
	document.getElementById("edit-buttons").style.display = "block";
	
	document.getElementById("user-input-update-username").readOnly = false;
    document.getElementById("user-input-update-email").readOnly = false;
    document.getElementById("user-input-update-password").readOnly = false;
    
}

function cancelUpdateProfile(id, username, email, password, signUpDate, signUpTime, lastSignInDate, lastSignInTime, isAdmin){
		
	document.getElementById("edit-buttons").style.display = "none";
	document.getElementById("open-update-form").type = "block";
	
	document.getElementById("user-input-update-username").value = username;
    document.getElementById("user-input-update-email").value = email;
    document.getElementById("user-input-update-password").value = password;
    
    document.getElementById("user-input-update-username").readOnly = true;
    document.getElementById("user-input-update-email").readOnly = true;
    document.getElementById("user-input-update-password").readOnly = true;
}
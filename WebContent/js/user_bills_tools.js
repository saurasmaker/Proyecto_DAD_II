function showOrHide(buttonId){
	var button = document.getElementById(buttonId);
	
	if(button.textContent.includes("Ver"))
		button.textContent = "Ocultar";
	else if(button.textContent.includes("Ocultar"))
		button.textContent = "Ver";
}
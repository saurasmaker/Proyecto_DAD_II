const links = document.querySelectorAll(".smooth-scroller ul a");

for (const link of links) {
	alert(link);
  link.addEventListener("click", clickHandler);
}

function clickHandler(e) {
	
	alert("ey");
	
  e.preventDefault();
  const href = this.getAttribute("href");
  const offsetTop = document.querySelector(href).offsetTop;

  scroll({
    top: offsetTop,
    behavior: "smooth"
  });
}
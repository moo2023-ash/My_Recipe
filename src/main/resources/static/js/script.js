
document.querySelectorAll('.ingredients-detail-container dl ').forEach(function(element) {
	if (!element.textContent || element.textContent.trim() === '') {
		element.style.background = 'none';
	}
});

const addButton = document.getElementById("add-row");
addButton.addEventListener("click", function() {

	const hiddenRows = document.getElementsByClassName("display-none");
	if (hiddenRows.length > 0) {
		hiddenRows[0].classList.remove("display-none");
	}
	event.preventDefault()
});

const addTextAreaButton = document.getElementById("add-textarea");
addTextAreaButton.addEventListener("click", function (event) {
    
    const hiddenRows = document.querySelectorAll(".display-none-textarea");
    if (hiddenRows.length > 0) {
        hiddenRows[0].classList.remove("display-none-textarea");
    }
    event.preventDefault();
});


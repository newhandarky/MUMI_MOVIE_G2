	// 文章內文開關
//	var articleContain = document.querySelector(".articleContain");
//	var btn_contain = document.querySelector("#btn_contain");
//	var close_contain = document.querySelector("#close_contain");

//window.addEventListener("load", function() {
//
//
//	btn_contain.addEventListener("click", showContain());
//	close_contain.addEventListener("click", closeContain());
//
//	function showContain() {
//		console.log("開了");
//		this.articleContain.style.display = "block";
//	}
//
//	function closeContain() {
//		console.log("關了");
//		this.articleContain.style.display = "none";
//	}
//
//});

$(document).on("click", ".btn_contain", function() {
	console.log($(this).closest("tr").find("div[class*=articleContain]"));
	$(this).closest("tr").find("div[class*=articleContain]").attr("style" , "display :block");
})

$(document).on("click", ".close_contain", function() {
	console.log($(this).closest(".articleContain"));
	$(this).closest(".articleContain").attr("style" , "display :none");
})








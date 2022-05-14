'use strict'

function find(el, selector) {
	let finded
	return (finded = el.querySelector(selector)) ? finded : null
}

function siblings(el) {
	const siblings = []
	for (let sibling of el.parentNode.children) {
		if (sibling !== el) {
			siblings.push(sibling)
		}
	}
	return siblings
}

const showAsideBtn = document.querySelector('.show-side-btn')
const sidebar = document.querySelector('.sidebar')
const wrapper = document.querySelector('#wrapper')

showAsideBtn.addEventListener('click', function() {
	document.querySelector(`#${this.dataset.show}`).classList.toggle('show-sidebar')
	wrapper.classList.toggle('fullwidth')
})

if (window.innerWidth < 767) {
	sidebar.classList.add('show-sidebar');
}
// 
document.querySelector(".navbar-toggler").addEventListener("click", function() {
	document.querySelector('.sidebar').classList.remove("show-sidebar");
})



window.addEventListener('resize', function() {
	if (window.innerWidth > 767) {
		sidebar.classList.remove('show-sidebar')
	}

})

// dropdown menu in the side nav
var slideNavDropdown = document.querySelector('.sidebar-dropdown');

document.querySelector('.sidebar .categories').addEventListener('click', function(event) {
	// event.preventDefault()

	const item = event.target.closest('.has-dropdown')

	if (!item) {
		return
	}

	item.classList.toggle('opened')

	siblings(item).forEach(sibling => {
		sibling.classList.remove('opened')
	})

	if (item.classList.contains('opened')) {
		const toOpen = find(item, '.sidebar-dropdown')

		if (toOpen) {
			toOpen.classList.add('active')
		}

		siblings(item).forEach(sibling => {
			const toClose = find(sibling, '.sidebar-dropdown')

			if (toClose) {
				toClose.classList.remove('active')
			}
		})
	} else {
		find(item, '.sidebar-dropdown').classList.toggle('active')
	}
})

document.querySelector('.sidebar .close-aside').addEventListener('click', function() {
	document.querySelector(`#${this.dataset.close}`).classList.add('show-sidebar')
	wrapper.classList.remove('margin')
})

// 工作區JS 要用jquery記得在html檔插入
// <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


$(function() {
	// 設定總座位數
	$(".seat_rc_btn").click(function() {
		let i = $(".seat_row").val();
		let j = $(".seat_col").val();
		for (let b = 1; b <= j; ) {
			let list_html = "";
			list_html = `
                <div class="seat-container">
                    <div class="seat-row-${b}">   
                    </div>
                </div>
                `;
			$(".seat-start").append(list_html);
			for (let a = 1; a <= i; ) {
				let list_html2 = "";
				list_html2 = `
                    <div class="seat">
                        <p>${String.fromCodePoint(b + 64)}${a}</p>
	                	<input id="seat_name" type="hidden" name="seat_name" value="${String.fromCodePoint(b + 64)}${a}">
	                	<input id="seat_state" type="hidden" name="seat_state" value="1">
	                	<input id="seat_no" type="hidden" name="seat_no" value="${(b*i)-i+a}">
                    </div>             
                `;
				$(".seat-row-" + b).append(list_html2);
				a++;
			}
			b++;
		}
		$("input.seat_row").val(i);
		$("input.seat_col").val(j);
	})



	// 重製座位
	$(".reset_btn").on("click", function() {
		$(".seat-container").remove();
		$(".seat-start").children(".aisle").remove();
		$("input.seat_row").val("");
		$("input.seat_col").val("");
		$("input.seat_left").val("");
		$("input.seat_right").val("");
		$("input.seat_row_aisle1").val("");
		$("input.seat_row_aisle2").val("");
	})

	// 設定身障位或刪除位置
	$(document).on("click", ".seat", function() {
		if ($(this).hasClass("disabled")) {
			$(this).attr("class", "seat close");
			$(this).find("p").attr("style", "display:true");
			$(this).find("p").css("opacity", "0");
			$(this).css("opacity", "0");
			$(this).children("img").remove();
			$(this).find("input#seat_state").attr("value", "0");		
		} else if ($(this).hasClass("close")) {
			$(this).attr("class", "seat");
			$(this).find("p").attr("style", "display:true");
			$(this).find("p").css("opacity", "1");
			$(this).css("opacity", "1");
			$(this).children("img").remove();
			$(this).find("input#seat_state").attr("value", "1");
		} else {
			$(this).attr("class", "seat disabled");
			$(this).find("p").attr("style", "display:none");
			let list_html = "";
			list_html = `
            <img src="./images/icons/disabled.png" alt="" class="disabled">
                `;
			$(this).append(list_html);
			$(this).find("input#seat_state").attr("value", "2");
		}
	})

	// 設定走道(垂直)
	$(document).on("click", ".seat_lr_btn", function() {
		const i = $(".seat_left").val();
		const j = $(".seat_right").val();
		// 找幾排
		const child_count = $(".seat-container").children("div").length;
		// 找總座位數
		const total_seat = $(".seat-container").find("div.seat").length;
		for (let a = 1; a <= child_count; a++) {
			$(".seat-row-" + a + " > div:nth-child(" + i + ")").css("margin-right", " 50px");
		}
		for (let b = 1; b <= child_count; b++) {
			$(".seat-row-" + b + " > div:nth-child(" + ((total_seat / child_count) - j) + ")").css("margin-right", " 50px");
		}

		$("input.seat_left").val(i);
		$("input.seat_right").val(j);
	})

	// 設定走道(水平)
	$(document).on("click", ".seat_row_aisle_btn", function() {
		const i = parseInt($(".seat_row_aisle1").val());
		const k = parseInt($(".seat_row_aisle2").val());
		const j = parseInt($(".seat_row_aisle2").val()) + 1;
		let list_html = "";
		list_html = `
            <div class="aisle" style="height: 40px"></div>
                `;
		if (i > 0) {
			$(".seat-start" + " div.seat-container:nth-child(" + i + ")").after(list_html);
		};
		if (i == 0 && k > 0) {
			$(".seat-start" + " div.seat-container:nth-child(" + k + ")").after(list_html);
		};
		if (i > 0 && k > 0){ 
			$(".seat-start" + " div.seat-container:nth-child(" + j + ")").after(list_html); 
		};
		
		$("input.seat_row_aisle1").val(i);
		$("input.seat_row_aisle2").val(k);
	})
});
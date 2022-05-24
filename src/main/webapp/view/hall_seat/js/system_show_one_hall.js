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

$(function() {
	$(document).ready(function() {
		let row = parseInt($(".hall_row").attr("id"))
		let left = parseInt($(".hall_left").attr("id"))
		let right = parseInt($(".hall_right").attr("id"))
		let row_aisle1 = parseInt($(".hall_row_aisle1").attr("id"))
		let row_aisle2 = parseInt($(".hall_row_aisle2").attr("id"))
		$('.seat').each(function() {
			if (($(this).attr('name')) % row == 0) {
				$(this).after("</br>");
			};
			if (($(this).attr('name')) % row == left) {
				$(this).css("margin-right", " 50px");
			};
			if (($(this).attr('name')) % row == (row-right)) {
				$(this).css("margin-right", " 50px");
			};
			if (($(this).attr('name')) == row * row_aisle1) {
				$(this).after("<div class='aisle'>");
			};
			if (($(this).attr('name')) == row * row_aisle2) {
				$(this).after("<div class='aisle'>");
			};
		});
	});
	
	// 設定身障位或刪除位置
	$(document).ready(function() {
		$('.seat').each(function() {
			if($(this).attr('id') == 0){
				$(this).attr('class', 'seat close');
				$(this).css('opacity', '0');
			};
			if($(this).attr('id') == 2){
				$(this).attr('class', 'seat disabled');
				$(this).find("p").attr('style', 'display:none');
				$(this).append("<img src='./images/icons/disabled.png' alt='' class='disabled'>");
			};	
		});
	});
    
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
});
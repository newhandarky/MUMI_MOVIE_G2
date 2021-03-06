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

showAsideBtn.addEventListener('click', function () {
    document.querySelector(`#${this.dataset.show}`).classList.toggle('show-sidebar')
    wrapper.classList.toggle('fullwidth')
})

if (window.innerWidth < 767) {
    sidebar.classList.add('show-sidebar');
}
// 
document.querySelector(".navbar-toggler").addEventListener("click", function () {
    document.querySelector('.sidebar').classList.remove("show-sidebar");
})



window.addEventListener('resize', function () {
    if (window.innerWidth > 767) {
        sidebar.classList.remove('show-sidebar')
    }

})

// dropdown menu in the side nav
var slideNavDropdown = document.querySelector('.sidebar-dropdown');

document.querySelector('.sidebar .categories').addEventListener('click', function (event) {
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

document.querySelector('.sidebar .close-aside').addEventListener('click', function () {
    document.querySelector(`#${this.dataset.close}`).classList.add('show-sidebar')
    wrapper.classList.remove('margin')
})

// ?????????JS ??????jquery?????????html?????????
// <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


if($(".time1").hasClass("time1")){
 $(".time1").attr("value", "06:00")
}
if($(".time2").hasClass("time2")){
 $(".time2").attr("value", "09:30")
}
if($(".time3").hasClass("time3")){
 $(".time3").attr("value", "13:00")
}
if($(".time4").hasClass("time4")){
 $(".time4").attr("value", "16:30")
}
if($(".time5").hasClass("time5")){
 $(".time5").attr("value", "20:00")
}



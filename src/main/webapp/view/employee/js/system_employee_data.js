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

$('.sidebar .close-aside').on('click', function() {
    $(`#${this.dataset.close}`).classList.add('show-sidebar')
    wrapper.classList.remove('margin')
})

// 查看會員照片
$(".btn-success").on("click", function() {
    Swal.fire({
        title: '會員編號001',
        imageUrl: 'http://localhost:8081/MUMI_MOVIE/mem/image/others/48176.jpg',
        imageWidth: 400,
        imageHeight: 400,
        imageAlt: 'Custom image',
    })
})

if($(".memstate1").hasClass("memstate1")){
	$(".memstate1").text("啟用");
}

if($(".memstate0").hasClass("memstate0")){
	$(".memstate0").text("停用");
}


// 會員帳號狀態按鈕
//$(".btn-primary").on("click", function() {
//    if (!$("#-on").hasClass("memstate0")) {
//        Swal.fire({
//            title: '確定要更動此會員的帳號狀態?',
//            text: '請留意, 除非此會員違反影城規定, 否則此舉可能造成會員投訴',
//            icon: 'warning',
//            showCancelButton: true,
//            confirmButtonColor: '#3085d6',
//            cancelButtonColor: '#d33',
//            confirmButtonText: 'Yes'
//        }).then((result) => {
//            if (result.isConfirmed) {
//                Swal.fire(
//                    '已停用!',
//                    '此會員帳號已停用',
//                    'success'
//                )
//                $("#-on").addClass('memstate0');   
//                $("#-on").removeClass('memstate1');             
//				$("#-on").text("停用");
//                
//            }
//        })
//    } 
//
//})
//
//
//$(".btn-primary").on("click", function() {
//    if ($("#-on").hasClass("memstate0")) {
//        Swal.fire({
//            icon: 'success',
//            title: '已變更此會員帳號狀態',
//            text: '此帳號已成功啟用!',
//        })
//
//        $("#-on").removeClass('memstate0');
//        $("#-on").addClass('memstate1');
//		$("#-on").text("啟用");
//    }
//
//})

// 會員性別判定
if($("#mem_gender").hasClass("0")){
	$("td#mem_gender").text("男");
}else{
	$("td#mem_gender").text("女");
}

if($(".empstate1").hasClass("empstate1")){
	$(".empstate1").text("在職中");
}

if($(".empstate0").hasClass("empstate0")){
	$(".empstate0").text("已離職");
}



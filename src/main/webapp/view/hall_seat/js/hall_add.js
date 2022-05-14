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

// 工作區JS 要用jquery記得在html檔插入
// <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


$(function () {
    // 設定總座位數
    $(".B_btn").click(function () {
        console.log();
        var i = $(".A").val();
        var j = $(".B").val();
        for (var b = 1; b <= j; b++) {
            let list_html = "";
            list_html = `
                <div class="seat-container">
                    <div class="seat-row-${b}">   
                    </div>
                </div>
                `;
            $(".seat-start").append(list_html);
            for (var a = 1; a <= i; a++) {
                let list_html2 = "";
                list_html2 = `
                    <div class="seat">
                        <p>${String.fromCodePoint(b + 64)}${a}</p>
                    </div>             
                `;
                $(".seat-row-" + b).append(list_html2);
            }
        }
        $("input.A").val("");
        $("input.B").val("");
    })

    // 重製座位
    $(".reset_btn").on("click", function () {
        $(".seat-container").remove();
        $(".seat-start").children(".aisle").remove();
        $("input.A").val("");
        $("input.B").val("");
        $("input.C").val("");
        $("input.D").val("");
        $("input.E").val("");
        $("input.F").val("");
    })

    // 設定身障位或刪除位置
    $(document).on("click", ".seat", function () {
        if ($(this).hasClass("disabled")) {
            $(this).attr("class", "seat close");
            $(this).find("p").attr("style", "display:true");
            $(this).find("p").css("opacity", "0");
            $(this).css("opacity", "0");
            $(this).children("img").remove();
        } else if ($(this).hasClass("close")) {
            $(this).attr("class", "seat");
            $(this).find("p").attr("style", "display:true");
            $(this).find("p").css("opacity", "1");
            $(this).css("opacity", "1");
            $(this).children("img").remove();
        } else {
            $(this).attr("class", "seat disabled");
            $(this).find("p").attr("style", "display:none");
            let list_html = "";
            list_html = `
            <img src="image/icons/disabled.png" alt="" class="disabled">
                `;
            $(this).append(list_html);
        }
    })

    // 設定走道(垂直)
    $(document).on("click", ".D_btn", function () {
        var i = $(".C").val();
        var j = $(".D").val();
        // 找幾排
        var child_count = $(".seat-container").children("div").length;
        console.log(child_count);
        // 找總座位數
        var total_seat = $(".seat-container").find("div.seat").length;
        for (var a = 1; a <= child_count; a++) {
            $(".seat-row-" + a + " div:nth-child(" + i + ")").css("margin-right", " 50px");
            console.log($(".seat-row-" + a + " div:nth-child(" + i + ")"));
        }
        for (var b = 1; b <= child_count; b++) {
            $(".seat-row-" + b + " div:nth-child(" + ((total_seat / child_count) - j) + ")").css("margin-right", " 50px");
        }
        $("input.C").val("");
        $("input.D").val("");
    })

    // 設定走道(垂直)
    $(document).on("click", ".F_btn", function () {
        var i = $(".E").val();
        var j = parseInt($(".F").val()) + 1 ;
        let list_html = "";
        list_html = `
            <div class="aisle" style="height: 40px"></div>
                `;
        console.log($(".seat-start" + " div.seat-container:nth-child(" + j + ")"));
        $(".seat-start" + " div.seat-container:nth-child(" + i + ")").after(list_html);
        $(".seat-start" + " div.seat-container:nth-child(" + j + ")").after(list_html);
        $("input.E").val("");
        $("input.F").val("");
    })
});
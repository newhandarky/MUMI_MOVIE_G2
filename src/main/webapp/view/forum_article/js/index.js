let vm = new Vue({
    el: "#app",
    data: {
        slideList: [
            { src: '../IMAGE/carousel/3d24f9cb36fb47d89b4be3bc1614ea2a.jpg', desc: "蝙蝠俠" },
            { src: '../IMAGE/carousel/8ad709b813e49f42da3809a494f1f0fc.jpg', desc: "奇異博士2" },
            { src: '../IMAGE/carousel/JUJUTSU KAISEN ZERO_Poster_TW_1450x608.jpg', desc: "咒術迴戰" },
            { src: '../IMAGE/carousel/60fe5d79c7bfb570c4b41fffb1468e3c.jpg', desc: "魔比斯" },
            { src: '../IMAGE/carousel/27ca24800b7dbfbbe67f6279deb4bb89.jpg', desc: "音速小子2" },

        ]
    }
});

let vm2 = new Vue({
    el: "#app2",
    data: {
        slideList: [
            { src: '', desc: "" },
            { src: '../IMAGE/moviestand/1825.jpg ', desc: "咒術迴戰" },
            { src: '../IMAGE/moviestand/film_20220214007.jpg ', desc: "蝙蝠俠" },
            { src: '../IMAGE/moviestand/GoddamnedAsura_180x270_Poster.jpg ', desc: "阿修羅" },
            { src: '../IMAGE/moviestand/5JoLmuOv0wzXt2DbER0f-1080x1538.jpg ', desc: "月球殞落" },
            { src: '../IMAGE/moviestand/Blacklight_180x270_Poster.jpg ', desc: "黑光行動" },

        ]
    }
});

let vm3 = new Vue({
    el: "#app3",
    data: {
        slideList: [
            { src: '../IMAGE/41089.jpg', desc: "pet01" },
            { src: '../IMAGE/41090.jpg', desc: "pet02" },
            { src: '../IMAGE/41091.jpg', desc: "pet03" },
            { src: '../IMAGE/41092.jpg', desc: "pet04" },
            { src: '../IMAGE/41093.jpg', desc: "pet05" },
            { src: '../IMAGE/41094.jpg', desc: "pet06" },
            { src: '../IMAGE/41095.jpg', desc: "pet07" },
            { src: '../IMAGE/41096.jpg', desc: "pet08" },
            { src: '../IMAGE/41097.jpg', desc: "pet09" },
            { src: '../IMAGE/41098.jpg', desc: "pet10" },
            { src: '../IMAGE/41099.jpg', desc: "pet11" },
            { src: '../IMAGE/41100.jpg', desc: "pet12" },
            { src: '../IMAGE/41101.jpg', desc: "pet13" },
            { src: '../IMAGE/41102.jpg', desc: "pet14" },
            { src: '../IMAGE/41103.jpg', desc: "pet15" },
            { src: '../IMAGE/41104.jpg', desc: "pet16" },

        ]
    }
});

// 物件宣告
var ytmovie = [
    "https://www.youtube.com/embed/PZpIUgOK9Rg",
    "https://www.youtube.com/embed/39qGk7E6OuY",
    "https://www.youtube.com/embed/S3vJy9EG6JE",
    "https://www.youtube.com/embed/N_Yaz9n53SQ",
    "https://www.youtube.com/embed/-i0Gm3rN8s4",
    "https://www.youtube.com/embed/9ibVkGjiYHw"
]



$(function() {
    // 現正熱映與即將上映切換
    $("#btn_now").on("click", function() {
        $("#div_cs").addClass("-off");
        $("#div_now").removeClass("-off");
    });
    $("#btn_cs").on("click", function() {
        $("#div_now").addClass("-off");
        $("#div_cs").removeClass("-off");
    });

    // 頁面開關燈
    $("#btn-light").on("click", function() {
        $("#btn-light").addClass("-off");
        $("#btn-dark").removeClass("-off");
        $(".picouter").addClass("-dark");
        $(".mainouter").addClass("-dark");
        $("#picouter").addClass("-dark");
        $("#map_h1").addClass("-dark");
        // $("info_h1").addClass("-dark");
        $(".map_rwd").addClass("-dark");
    });
    $("#btn-dark").on("click", function() {
        $("#btn-dark").addClass("-off");
        $("#btn-light").removeClass("-off");
        $(".picouter").removeClass("-dark");
        $(".mainouter").removeClass("-dark");
        $("#picouter").removeClass("-dark");
        $("#map_h1").removeClass("-dark");
        // $("info_h1").removeClass("-dark");
        $(".map_rwd").removeClass("-dark");
    });

    // 停止導覽列的標頭預設行為
    // $(".nav_ul").on("click", function(e) {
    //     e.preventDefault();
    // });

    $("#a_loupe").on("click", function(event) {
        event.preventDefault();
    });

    // 漢堡選單按鈕停止預設行為
    $(".hamberger_menu").on("click", function(event) {
        event.preventDefault();
    })

    // 漢堡選單側邊攔位
    $(".hamberger_menu").on("click", function() {
        $(".hide_menu").toggleClass("-bye");
    })


    // 下拉選單
    $('.nav-item .nav_ul').on('click', function() {
        if ($(this).hasClass("switchDisplay") == false) {
            $('.nav-item .nav_ul,.nav-item ul').removeClass('switchDisplay');
        }
        $(this).toggleClass('switchDisplay').siblings().toggleClass('switchDisplay');
    });

    // 切換youtube預告
    $("#moviebar").on("change", function(e) {
        $(".embed-in iframe").attr("src", `${ytmovie[e.target.value]}`)
    })








});
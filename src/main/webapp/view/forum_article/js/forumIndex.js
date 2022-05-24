/**
 * 
 */
 
let url;
let hotUrl;
let OneArticleUrl;
let memInfoUrl;
let memId;
let mem_mail;

const tbodyAll = document.querySelector('#boardAll');
const checkSession = document.querySelector('.checksession');
const submitcCheck = document.querySelector('#submitcCheck');
const mem_name = document.querySelector('#mem_name');

// 轉換板塊文章
function changeBoard(board_num) {
	history.pushState(null, null, "http://localhost:8080/test/view/forum_article/page=" + board_num);

	tbodyAll.innerHTML = "";
	$(document).find("#tableAll").attr("style", "display: block");
	$(document).find(".articleOne").attr("style", "display: none");
	url = "GetBoardArticleServlet?page=" + board_num;
	console.log("board_num=" + board_num);
	console.log("click#boardDiscuss=" + url);
	showBoardArticle(url);
}

//     	// 網頁轉換觸發事件
//     	window.addEventListener('popstate', () => {  
//      	 　　changeBoard(board_num)
//      	});

// 會員資訊


function showMemInfo(memInfoUrl) {
	fetch(memInfoUrl)
		.then(resp => resp.json())
		.then(memInfo => {
			mem_name.textContent = memInfo.mem_name;
			var mem_data_obj = {
				memId: memInfo.mem_id,
			};
			console.log(mem_data_obj);
			sessionStorage.setItem("mem_data", JSON.stringify(mem_data_obj));

		});
};


// 顯示熱門文章
function showHotArticle(hotUrl) {
	fetch(hotUrl)
		.then(resp => resp.json())
		.then(hotAllList => {
			for (let h of hotAllList) {
				const hotAricle = document.querySelector('#hotAricle');
				hotAricle.insertAdjacentHTML('beforeend', `
            			<div class="feature col">
            				<img src="ShowPicServlet?article_id=${h.article_id}" class="img-fluid col-md-4 col-sea">
            				<h2>${h.article_subject}</h2>
            				<a href="#" class="icon-link">點我看更多^^</a>
            			</div>	
            		`)
			}
		});
}


// 顯示板塊文章
function showBoardArticle(url) {
	fetch(url)
		.then(resp => resp.json())
		.then(list => {
			for (let e of list) {
				const tbodyAll = document.querySelector('#boardAll');
				tbodyAll.insertAdjacentHTML('beforeend', `
                    <tr>
                        <td>
                            <p class="badge bg-light text-dark">${e.article_board}<br><span>【${e.article_type}】</span></p>
                        </td>
                        <td>
                            <img src="ShowPicServlet?article_id=${e.article_id}" class="img-fluid">
                        </td>
                        <td>
                            <a href="javascript:void(0)" onclick="OneArticleDetail(${e.article_id})">${e.article_subject}</a>
                        </td>
                        <td>
                            <p class="badge bg-light text-dark">${e.article_like_num}</p>
                        </td>
                        <td>${e.article_updated}</td>
                    </tr>`);
			}
		});
}

// 顯示單一文章內容 
function OneArticleDetail(aID) {

	const article = document.querySelector('article');


	$(document).find("#tableAll").attr("style", "display: none");
	OneArticleUrl = "GetOneArticleServlet?article_id=" + aID;
	console.log("顯示單一文章內容的URL=" + OneArticleUrl);

	fetch(OneArticleUrl)
		.then(resp => resp.json())
		.then(detail => {
			console.log(detail);
			article.insertAdjacentHTML('beforeend', `
	    					<div class="articleOne" style="display: block">
								<h2 class="blog-post-title">
									<span>【${detail.article_type}】</span>${detail.article_subject}
								</h2>
								<hr>
								<p class="blog-post-meta">作者： ${detail.mem_id}</p>
								<p class="blog-post-meta">發表時間： ${detail.article_updated}</p>
								<div>
									<img src="ShowPicServlet?article_id=${detail.article_id}" class="img-fluid">
									<p>${detail.article_contain}</p>
								</div>
								<form action="ChangeArticleStateServlet" method="post">
									<input type="hidden" name="article_id" value="${detail.article_id}"></input>
									<input type="hidden" name="state" value="0"></input>
									<button type="submit" class="btn btn-danger btn-sm" style="float: right">刪除</button>
								</form>
								<button id="btn_report" class="btn btn-warning btn-sm" style="float: right">檢舉</button>
								<div class="report_choose" style="display: none">
									<p>請選擇檢舉原因: </p>
								    <div>
								      <input type="radio" id="reason1" name="report_article_reason" value="檢舉原因1" checked>
								      <label for="reason1">檢舉原因1</label>
								    </div>
								    <div>
								      <input type="radio" id="reason2" name="report_article_reason" value="檢舉原因2">
								      <label for="reason1">檢舉原因2</label>
								    </div>
								    <div>
								      <input type="radio" id="reason3" name="report_article_reason" value="檢舉原因3">
								      <label for="reason1">檢舉原因3</label>
								    </div>
								    <div>
								    	<button id="report_submit" class="btn btn-warning btn-sm" style="float: right">送出</button>
								    	<button id="report_cancel" class="btn btn-sm" style="float: right">取消</button>
								    <div>
								</div>
								<button id="btn_reply_submit" class="btn btn-success btn-sm" style="float: right">回覆</button>
								<form action="UpdateArticleServlet" method="post">
									<input type="hidden" name="article_id" value="${detail.article_id}"></input>
									<input type="hidden" name="action" value="getOne_For_Update"></input>								
									<button type="submit" class="btn btn-primary btn-sm" style="float: right">修改</button>
								</form>
							</div>`);

			// 回覆文章
			var btn_reply_submit_data = document.querySelector("#btn_reply_submit");
			btn_reply_submit_data.addEventListener("click", function() {
				var data_obj = {
					re_article_subject: detail.article_subject,
					re_article_board: detail.article_board,
					re_article_type: detail.article_type,
					re_article_bord: detail.article_bord,
					re_article_id: detail.re_article_id
				};

				console.log(data_obj);
				sessionStorage.setItem("reply_data", JSON.stringify(data_obj));
				location.href = "replyArticle.jsp";
			});
		});
}

// 檢舉文章
$(document).on("click", "#btn_report", function() {
	$(this).closest(".articleOne").find("div[class*=report_choose]").attr("style", "display :block");
});

$(document).on("click", "#report_cancel", function() {
	$(this).closest(".report_choose").attr("style", "display :none");
})



// 修改文章
function updateArticle(aID) {
	var update_url = "UpdateArticleServlet?article_id=" + aID;
	console.log("修改文章的URL=" + update_url);
	fetch(update_url);
}

// 網頁load後顯示全部文章
window.addEventListener('load', () => {

	checkSession.addEventListener("click", function() {
		console.log("XXXXX");
		document.querySelector('#submitcCheck').submit();
	});

	hotUrl = 'GetAllHotArticleServlet';

	if (url == null) {
		url = 'IndexGetAllArticleServlet'; //網址
	}
	console.log("url=" + url);
	console.log("hotUrl=" + hotUrl);


	// 取出sessionStorage資料
	var storage_data = JSON.parse(sessionStorage.getItem("login_data"));

	console.log("login_data: " + storage_data.login_mail); // 檢查用
	mem_mail = storage_data.login_mail;

	memInfoUrl = "ForumMemInfoServlet?mem_account=" + mem_mail;


	showHotArticle(hotUrl);
	showBoardArticle(url);
	showMemInfo(memInfoUrl);

	console.log("memId02" + memId);


});
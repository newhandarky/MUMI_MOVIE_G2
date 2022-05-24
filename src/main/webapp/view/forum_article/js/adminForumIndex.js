/**
 * 
 */

let ArticleUrl;
let ReportUrl;
let board_num;

const tbodyAll = document.querySelector('#boardAll');
const tableAll = document.querySelector('#tableAll');
const tableReport = document.querySelector('#tableReport');
const tbodyReport = document.querySelector('#reportAll');



// 網頁load後顯示全部文章
window.addEventListener('load', () => {

	if (ArticleUrl == null) {
		ArticleUrl = 'GetAllArticleServlet'; //網址
	}
	console.log("window=" + ArticleUrl);
	showBoardArticle(ArticleUrl);

});


// 轉換檢舉文章
function changeBoard(board_num) {
	
	tbodyReport.innerHTML = "";
	switch (board_num) {
		case 1:
			$(document).find("#tableAll").attr("style", "display: block");
			$(document).find("#tableReport").attr("style", "display: none");
			break;
		case 2:
			$(document).find("#tableAll").attr("style", "display: none");
			$(document).find("#tableReport").attr("style", "display: block");
			ReportUrl = "GetAllReportServlet";
			showReportArticle(ReportUrl)
			break;
		case 3:
			$(document).find("#tableAll").attr("style", "display: none");
			$(document).find("#tableReport").attr("style", "display: block");
			ReportUrl = "GetDoneReportServlet";
			showReportArticle(ReportUrl)
			ReportUrl = "";
			break;
	}
	

}

// 顯示板塊文章
function showBoardArticle(ArticleUrl) {
	fetch(ArticleUrl)
		.then(resp => resp.json())
		.then(list => {
			for (let e of list) {

				tbodyAll.insertAdjacentHTML('beforeend', `
		                    <tr>
		                        <td>${e.article_id}</td>
		                        <td>${e.article_subject}</td>
		                        <td>${e.article_publish}</td>
		                        <td>${e.article_updated}</td>
		                        <td>
		                            <p class="badge bg-light text-dark">${e.article_visit_count}</p>
		                        </td>
		                        <td>${e.mem_id}</td>
		                        <td>${e.article_state}</td>
		                        <td><button type="button" class="btn_contain">內文</button></td>
		                        <td><button type="button" class="btn_state">修改狀態</button></td>
		                        <td>
		                        	<form action="DeleteArticleServlet" method="post">
		                        		<input type="hidden" name="article_id" value="${e.article_id}"></input>
		                      			<button type="submoit" class="btn_entirelyDelete">完全刪除</button>
		                      			</form>
		                      	</td>
		                        <td>
									<div class="articleContain" style="display: none">
										<div class="close_contain">X</div>
										<div class="articleDetail">${e.article_contain}</div>
									</div>
		                        </td>
		                    </tr>
					`);
			}
		});
}

// 顯示檢舉文章
function showReportArticle(ReportUrl) {
	fetch(ReportUrl)
		.then(resp => resp.json())
		.then(list => {
			for (let e of list) {
				tbodyReport.insertAdjacentHTML('beforeend', `
		                    <tr>
		                        <td>${e.report_article_id}</td>
		                        <td>${e.mem_id}</td>
		                        <td>${e.article_subject}</td>
		                        <td>
		                            <p class="badge bg-light text-dark">${e.article_visit_count}</p>
		                        </td>
		                        <td>${e.emp_id}</td>
		                        <td>${e.report_article_time}</td>
		                        <td>${e.report_update_time}</td>
		                        <td>${e.article_state}</td>
		                        <td>${e.report_article_state}</td>
		                        <td><button type="button" class="btn_contain">內文</button></td>
		                        <td><button type="button" class="btn_state">修改狀態</button></td>
		                        <td>
		                        	<form action="DeleteArticleServlet" method="post">
		                        		<input type="hidden" name="article_id" value="${e.article_id}"></input>
		                      			<button type="submoit" class="btn_entirelyDelete">完全刪除</button>
		                      			</form>
		                      	</td>
		                        <td>
									<div class="articleContain" style="display: none">
										<div class="close_contain">X</div>
										<div class="articleDetail">${e.article_contain}</div>
									</div>
		                        </td>
		                    </tr>
					`);
			}
		});
}

// 板塊文章開關控制
$(document).on("click", ".btn_contain", function() {
	console.log($(this).closest("tr").find("div[class*=articleContain]"));
	$(this).closest("tr").find("div[class*=articleContain]").attr("style", "display :block");
})

$(document).on("click", ".close_contain", function() {
	console.log($(this).closest(".articleContain"));
	$(this).closest(".articleContain").attr("style", "display :none");
})
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
	tbodyAll.innerHTML = "";
	switch (board_num) {
		case 0:
			$(document).find("#tableAll").attr("style", "display: block");
			$(document).find("#tableReport").attr("style", "display: none");
			ArticleUrl = "GetAllArticleServlet";
			showBoardArticle(ArticleUrl);
			break;	
		case 1:
			$(document).find("#tableAll").attr("style", "display: block");
			$(document).find("#tableReport").attr("style", "display: none");
			ArticleUrl = "IndexGetAllArticleServlet";
			showBoardArticle(ArticleUrl);
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
		                        <td><button type="button" class="btn_state">修改</button></td>
		                        <td>
		                        	<form action="DeleteArticleServlet" method="post">
		                        		<input type="hidden" name="article_id" value="${e.article_id}"></input>
		                      			<button type="submit" class="btn_entirelyDelete">刪除</button>
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
		                        <td>${e.emp_id}</td>
		                        <td>${e.report_article_time}</td>
		                        <td>${e.report_update_time}</td>
		                        <td>${e.report_article_reason}</td>
		                        <td>${e.article_state}</td>
		                        <td>${e.report_article_state}</td>
		                        <td><button type="button" class="btn btn_contain btn-success btn-sm">內文</button></td>
		                        <td>
		                        	<button type="button" id="btn_changeReport" class="btn btn-warning btn-sm">修改</button>
		                        	<div class="emp_change" style="display: none">
		                        		<form action="ChangeReportStateServlet" method="post">
			                        		<select name="report_article_state" id="choose-board" class="form-select">
												<option value="" selected>請選擇</option>
												<option value="1">管理員已核准</option>
												<option value="0">管理員已刪除</option>
											</select>
											<input type="hidden" name="article_id" value="${e.article_id}"></input>
											<button type="submit" id="change_submit" class="btn btn-sm" style="float: middle">送出</button>
											<button type="button" id="change_cancel" class="btn btn-sm" style="float: middle">取消</button>
										</form>
		                        	</div>
		                        </td>
		                        <td>
		                        	<form action="DeleteArticleServlet" method="post">
		                        		<input type="hidden" name="article_id" value="${e.article_id}"></input>
		                      			<button type="submit" id="btn_entirelyDelete" class="btn btn-danger btn-sm">刪除</button>
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
	$(this).closest("tr").find("div[class*=articleContain]").attr("style", "display :block");
})

$(document).on("click", ".close_contain", function() {
	$(this).closest(".articleContain").attr("style", "display :none");
})

// 修改檢舉狀態開關控制
$(document).on("click", "#btn_changeReport", function() {
	$(this).closest("td").find("div[class*=emp_change]").attr("style", "display :block");
});

$(document).on("click", "#change_cancel", function() {
	$(this).closest(".emp_change").attr("style", "display :none");
})
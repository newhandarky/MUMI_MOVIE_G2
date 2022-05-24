/**
 * 
 */

let url;
const tbodyAll = document.querySelector('#boardAll');
const tableAll = document.querySelector('#tableAll');

// 網頁load後顯示全部文章
window.addEventListener('load', () => {

	if (url == null) {
		url = 'GetAllArticleServlet'; //網址
	}
	console.log("window=" + url);
	showBoardArticle(url);

});

// 顯示板塊文章
function showBoardArticle(url) {
	fetch(url)
		.then(resp => resp.json())
		.then(list => {
			for (let e of list) {

				tableAll.insertAdjacentHTML('beforeend', `
	                    <tbody>
		                    <tr>
		                        <td>${e.article_id}</td>
		                        <td>${e.article_subject}</td>
		                        <td>${e.article_publish}</td>
		                        <td>${e.article_updated}</td>
		                        <td>
		                            <p class="badge bg-light text-dark">${e.article_like_num}</p>
		                            <span>／</span>
		                            <p class="badge bg-light text-dark">${e.article_dislike_num}</p>
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
	                    </tbody>
					`);
			}
		});
}

$(document).on("click", ".btn_contain", function() {
	console.log($(this).closest("tr").find("div[class*=articleContain]"));
	$(this).closest("tr").find("div[class*=articleContain]").attr("style", "display :block");
})

$(document).on("click", ".close_contain", function() {
	console.log($(this).closest(".articleContain"));
	$(this).closest(".articleContain").attr("style", "display :none");
})
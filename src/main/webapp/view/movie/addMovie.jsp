<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%
  MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>電影資料新增 - addMovie.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 650px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>電影資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ENCTYPE="multipart/form-data" ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet" name="form1">
<table>
	<tr>
		<td>電影中文名稱:</td>
		<td><input type="TEXT" name="movie_ch" size="50" 
			 value="<%= (movieVO==null)? "電影中文名稱" : movieVO.getMovie_ch()%>" /></td>
	</tr>
	<tr>
		<td>電影英文名稱:</td>
		<td><input type="TEXT" name="movie_en" size="50"
			 value="<%= (movieVO==null)? "MANAGER" : movieVO.getMovie_en()%>" /></td>
	</tr>
	<tr>
		<td>電影狀態:</td>
		<td><input type="TEXT" name="movie_state_id" size="50"
			 value="<%= (movieVO==null)? "20" : movieVO.getMovie_state_id()%>" /></td>
	</tr>
	<tr>
		<td>電影分級:</td>
		<td><input type="TEXT" name="movie_rating_id" size="50"
			 value="<%= (movieVO==null)? "201" : movieVO.getMovie_rating_id()%>" /></td>
	</tr>
	<tr>
		<td>長度:</td>
		<td><input type="TEXT" name="movie_runtime" size="50"
			 value="<%= (movieVO==null)? "1" : movieVO.getMovie_runtime()%>" /></td>
	</tr>
	<tr>
		<td>上映日期:</td>
		<td><input name="release_date" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>簡介:</td>
		<td><input type="TEXT" name="movie_intro" size="50"
			 value="<%= (movieVO==null)? "神作:/?" : movieVO.getMovie_intro()%>" /></td>
	</tr>
	<tr>
		<td>演員:</td>
		<td><input type="TEXT" name="casts" size="50"
			 value="<%= (movieVO==null)? "帥哥、美女" : movieVO.getCasts()%>" /></td>
	</tr>
	<tr>
		<td>導演:</td>
		<td><input type="TEXT" name="director" size="50"
			 value="<%= (movieVO==null)? "帥哥、" : movieVO.getDirector()%>" /></td>
	</tr>
	<tr>
		<td>預告:</td>
		<td><input type="TEXT" name="trailer" size="50"
			 value="<%= (movieVO==null)? "http://localhost:8081/movie_info/movie/movie.do" : movieVO.getTrailer()%>" /></td>
	</tr>
	<tr>
		<td>海報:</td>
		<td><input type="FILE" name="movie_poster"/></td>
	</tr>
	<tr>
		<td>輪播海報:</td>
		<td><input type="FILE" name="movie_slide_poster"/></td>
	</tr>
	 

</table>
<h5>選擇類型</h5>
                    <div class="col-12">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                動作片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                冒險片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                動畫片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                喜劇片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                懸疑片
                            </label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                科幻片
                            </label>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                恐怖片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                警匪片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                武俠片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                倫理片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                戰爭片
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label">
                                史詩片
                            </label>
                        </div>
                    </div>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date release_date = null;
  try {
	    release_date = movieVO.getRelease_date();
   } catch (Exception e) {
	   release_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=release_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>
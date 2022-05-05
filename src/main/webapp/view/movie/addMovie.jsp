<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%
  MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
%>
	--<%= movieVO==null %>--${movieVO==null}--${empVO.deptno}
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�q�v��Ʒs�W - addMovie.jsp</title>

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
		 <h3>�q�v��Ʒs�W - addEmp.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ENCTYPE="multipart/form-data" ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet" name="form1">
<table>
	<tr>
		<td>�q�v����W��:</td>
		<td><input type="TEXT" name="movie_ch" size="50" 
			 value="<%= (movieVO==null)? "�q�v����W��" : movieVO.getMovie_ch()%>" /></td>
	</tr>
	<tr>
		<td>�q�v�^��W��:</td>
		<td><input type="TEXT" name="movie_en" size="50"
			 value="<%= (movieVO==null)? "MANAGER" : movieVO.getMovie_en()%>" /></td>
	</tr>
	<tr>
		<td>�q�v���A:</td>
		<td><input type="TEXT" name="movie_state_id" size="50"
			 value="<%= (movieVO==null)? "20" : movieVO.getMovie_state_id()%>" /></td>
	</tr>
	<tr>
		<td>�q�v����:</td>
		<td><input type="TEXT" name="movie_rating_id" size="50"
			 value="<%= (movieVO==null)? "201" : movieVO.getMovie_rating_id()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="movie_runtime" size="50"
			 value="<%= (movieVO==null)? "1" : movieVO.getMovie_runtime()%>" /></td>
	</tr>
	<tr>
		<td>�W�M���:</td>
		<td><input name="release_date" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>²��:</td>
		<td><input type="TEXT" name="movie_intro" size="50"
			 value="<%= (movieVO==null)? "���@:/?" : movieVO.getMovie_intro()%>" /></td>
	</tr>
	<tr>
		<td>�t��:</td>
		<td><input type="TEXT" name="casts" size="50"
			 value="<%= (movieVO==null)? "�ӭ��B���k" : movieVO.getCasts()%>" /></td>
	</tr>
	<tr>
		<td>�ɺt:</td>
		<td><input type="TEXT" name="director" size="50"
			 value="<%= (movieVO==null)? "�ӭ��B" : movieVO.getDirector()%>" /></td>
	</tr>
	<tr>
		<td>�w�i:</td>
		<td><input type="TEXT" name="trailer" size="50"
			 value="<%= (movieVO==null)? "http://localhost:8081/movie_info/movie/movie.do" : movieVO.getTrailer()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="FILE" name="movie_poster"/></td>
	</tr>
	<tr>
		<td>��������:</td>
		<td><input type="FILE" name="movie_slide_poster"/></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=release_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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
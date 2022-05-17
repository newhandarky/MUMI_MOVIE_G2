<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%
MovieVO movieVO = (MovieVO) request.getAttribute("movieVO"); //MovieServlet.java (Concroller) �s�Jreq��movieVO���� (�]�A�������X��movieVO, �]�]�A��J��ƿ��~�ɪ�movieVO����)
%>
<html>
<head>
<meta charset="UTF-8">
<title>�v����x�޲z�t��</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/movie/css/system_movie_update.css">

</head>

<body>
	<!-- partial:index.partial.html -->
	<aside
		class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left"
		id="show-side-navigation1">
		<i class="uil-bars close-aside d-md-none d-lg-none"
			data-close="show-side-navigation1"></i>
		<div
			class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">

			<!-- �j�Y�ӳ]�w -->
			<img class="rounded-pill img-fluid" width="80"
				src="./IMAGE/icons/clapperboard.png" alt="">
			<div class="ms-2">
				<h5 class="fs-6 mb-0">
					<a class="text-decoration-none" href="#">Tibame�v��</a>
				</h5>
				<p class="mt-1 mb-0">��O�޲z�t��</p>
			</div>
		</div>

		<div class="search position-relative text-center px-4 py-3 mt-2">
			<input type="text" class="form-control w-100 border-0 bg-transparent"
				placeholder="Search here"> <i
				class="fa fa-search position-absolute d-block fs-6"></i>
		</div>


		<!-- ���䰼����\��C -->
		<ul class="categories list-unstyled">
			<li class="has-dropdown"><i class="uil-estate fa-fw"></i><a
				href="#"> �|���޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�|����Ƭd��</a></li>
					<li><a href="#">�ק�|�����</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
			<li class="has-dropdown"><i class="uil-calendar-alt"></i><a
				href="#"> �q�v�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�q�v�W�[</a></li>
					<li><a href="#">�q�v�U�[</a></li>
					<li><a href="#">�۰ʱƵ{�]�w</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-envelope-download fa-fw"></i><a
				href="#"> �ӫ~�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�ӫ~�W�[</a></li>
					<li><a href="#">�ӫ~�U�[</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-shopping-cart-alt"></i><a
				href="#"> �Q�װϺ޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�d�����|����</a></li>
					<li><a href="#">ipsum dolor</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-bag"></i><a href="#">
					����޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�۰ʱƵ{</a></li>
					<li><a href="#">�H�H����</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-setting"></i><a href="#">
					�y��޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�v�U�y��]�w</a></li>
					<li><a href="#">�O�d��]�w</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-chart-pie-alt"></i><a
				href="#"> ���u�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">���u�d��</a></li>
					<li><a href="#">�s�W���u</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-panel-add"></i><a
				href="#"> ��L</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="admin_login.html">�����b��</a></li>
					<li><a href="admin_login.html">�n�X</a></li>
					<li><a href="index.html">�^��e�x����</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-map-marker"></i><a href="#"> Maps</a>
            </li> -->
		</ul>
	</aside>

	<section id="wrapper">
		<nav class="navbar navbar-expand-md">
			<div class="container-fluid mx-2">
				<div class="navbar-header">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#toggle-navbar"
						aria-controls="toggle-navbar" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="uil-bars text-white"></i>
					</button>
					<a class="navbar-brand" href="#">�v����x�޲z�t��</a>
				</div>
				<div class="collapse navbar-collapse" id="toggle-navbar">
					<ul class="navbar-nav ms-auto">
						<!-- <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Settings
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <a class="dropdown-item" href="#">My account</a>
                                </li>
                                <li><a class="dropdown-item" href="#">My inbox</a>
                                </li>
                                <li><a class="dropdown-item" href="#">Help</a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Log out</a></li>
                            </ul>
                        </li> -->
						<!-- <li class="nav-item">
                            <a class="nav-link" href="#"><i class="uil-comments-alt"></i><span>23</span></a>
                        </li> -->
						<!-- <li class="nav-item">
                            <a class="nav-link" href="#"><i class="uil-bell"></i><span>98</span></a>
                        </li> -->
						<li class="nav-item"><a class="nav-link" href="#"> <i
								data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>



		<!-- �D�n�u�@�� -->
		<div class="main">
			<h2>�ק�q�v</h2>

			<div class="card">
				<div class="container">
					<div class="row g-3" col="12">
						<div class="col-md-6">
							<a class="btn btn-secondary" href="system_movie_add.jsp">�^�s�W����</a>
						</div>
						<div class="col-md-6">
							<a class="btn btn-secondary" href='system_movie_listAll.jsp'>�Ҧ��q�v</a>
							<jsp:useBean id="movie_Svc" scope="page"
								class="web.movie.service.MovieService" />
						</div>

						<%-- ���~��C --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>


						<form method="post"
							action="<%=request.getContextPath()%>/view/movie/MovieServlet"
							name="form1" ENCTYPE="multipart/form-data">
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�q�v�s���G</label>
								<div class="col-sm-3">
									<%=movieVO.getMovie_id()%>
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�q�v����W��:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_ch"
										value="<%=movieVO.getMovie_ch()%>"
										aria-label="default input example">
								</div>
								<label class="col-sm-2 col-form-label">�q�v�^��W��:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_en"
										value="<%=movieVO.getMovie_en()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row"></div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�q�v���A:</label>
								<div class="col-sm-3">
									<jsp:useBean id="releasingSvc" scope="page"
										class="web.releasing.service.ReleasingService" />
									<select name="movie_state_id" class="form-select">
										<c:forEach var="ReleasingVO" items="${releasingSvc.all}">
											<option value="${ReleasingVO.movie_state_id}"
												${(movieVO.movie_state_id==ReleasingVO.movie_state_id)?'selected':'' }>
												${ReleasingVO.movie_state}
										</c:forEach>
									</select>
								</div>

								<label class="col-sm-2 col-form-label">�q�v����:</label>
								<div class="col-sm-3">
									<jsp:useBean id="movie_ratingSvc" scope="page"
										class="web.movie_rating.service.Movie_ratingService" />
									<select name="movie_rating_id" class="form-select">
										<c:forEach var="Movie_ratingVO" items="${movie_ratingSvc.all}">
											<option value="${Movie_ratingVO.movie_rating_id}"
												${(movieVO.movie_rating_id==Movie_ratingVO.movie_rating_id)?'selected':'' }>
												${Movie_ratingVO.movie_rating_ch}
										</c:forEach>
									</select>
								</div>
							</div>





							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">����:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_runtime"
										value="<%=movieVO.getMovie_runtime()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�W�M���:</label>
								<div class="col-sm-3">
									<input class="form-control" type="date" name="release_date"
										value="<%=movieVO.getRelease_date()%>"
										aria-label="default input example" id="f_date1">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">²��:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_intro"
										value="<%=movieVO.getMovie_intro()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�t��:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="casts"
										value="<%=movieVO.getCasts()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�ɺt:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="director"
										value="<%=movieVO.getDirector()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">�w�i:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="trailer"
										value="<%=movieVO.getTrailer()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">����:</label>
								<div class="col-sm-3">
									<input class="form-control" type="file" name="movie_poster"
										value="<%=movieVO.getMovie_poster()%>"
										aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">��������:</label>
								<div class="col-sm-3">
									<input class="form-control" type="file"
										name="movie_slide_poster"
										value="<%=movieVO.getMovie_slide_poster()%>"
										aria-label="default input example">
								</div>
							</div>

							<img src="DBGifReader?movie_id=${movieVO.movie_id}"> <img
								src="DBGifReader2?movie_id=${movieVO.movie_id}"> <br>
							<input type="hidden" name="movie_updated_time"> <input
								type="hidden" name="action" value="update"> <input
								type="hidden" name="movie_id" value="<%=movieVO.getMovie_id()%>">
							<input type="submit" class="btn btn-primary" value="�U�@�B">
						</form>

					</div>
				</div>
			</div>






















		</div>

		<!-- �u�@�ϵ��� -->


	</section>
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
	<script
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.jshttps://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/view/movie/js/system_movie_update.js"></script>

</body>
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=movieVO.getRelease_date()%>
	', // value:   new Date(),
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
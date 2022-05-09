<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.hall_seat.dao.*"%>
 
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>�v����x�޲z�t��</title>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
    <link rel="stylesheet" href="css/system_testVer.css">

</head>

<body>
    <!-- partial:index.partial.html -->
    <aside class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left" id="show-side-navigation1">
        <i class="uil-bars close-aside d-md-none d-lg-none" data-close="show-side-navigation1"></i>
        <div class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">

            <!-- �j�Y�ӳ]�w -->
            <img class="rounded-pill img-fluid" width="80" src="./images/icons/clapperboard.png" alt="">
            <div class="ms-2">
                <h5 class="fs-6 mb-0">
                    <a class="text-decoration-none" href="#">Tibame�v��</a>
                </h5>
                <p class="mt-1 mb-0">��O�޲z�t��</p>
            </div>
        </div>

        <div class="search position-relative text-center px-4 py-3 mt-2">
            <input type="text" class="form-control w-100 border-0 bg-transparent" placeholder="Search here">
            <i class="fa fa-search position-absolute d-block fs-6"></i>
        </div>


        <!-- ���䰼����\��C -->
        <ul class="categories list-unstyled">
            <li class="has-dropdown">
                <i class="uil-estate fa-fw"></i><a href="#"> �|���޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�|����Ƭd��</a></li>
                    <li><a href="#">�ק�|�����</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
            <li class="has-dropdown">
                <i class="uil-calendar-alt"></i><a href="#"> �q�v�޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�q�v�W�[</a></li>
                    <li><a href="#">�q�v�U�[</a></li>
                    <li><a href="#">�۰ʱƵ{�]�w</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-envelope-download fa-fw"></i><a href="#"> �ӫ~�޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�ӫ~�W�[</a></li>
                    <li><a href="#">�ӫ~�U�[</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-shopping-cart-alt"></i><a href="#"> �Q�װϺ޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�d�����|����</a></li>
                    <li><a href="#">ipsum dolor</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-bag"></i><a href="#"> ����޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�۰ʱƵ{</a></li>
                    <li><a href="#">�H�H����</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-setting"></i><a href="#"> �y��޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">�v�U�y��]�w</a></li>
                    <li><a href="#">�O�d��]�w</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-chart-pie-alt"></i><a href="#"> ���u�޲z</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">���u�d��</a></li>
                    <li><a href="#">�s�W���u</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-panel-add"></i><a href="#"> ��L</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="admin_login.html">�����b��</a></li>
                    <li><a href="admin_login.html">�n�X</a></li>
                    <li><a href="index.html">�^��e�x����</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <!-- <li class="">
                <i class="uil-map-marker"></i><a href="#"> Maps</a>
            </li> -->
        </ul>
    </aside>

    <section id="wrapper">
        <nav class="navbar navbar-expand-md">
            <div class="container-fluid mx-2">
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#toggle-navbar" aria-controls="toggle-navbar" aria-expanded="false"
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
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>



        <!-- �D�n�u�@�� -->
        <main>
            <div class="main">
                <h1>IMAX�Ʀ�B�U</h1>
                <div class="hell">
                    <div class="seat-state">
                        <div class="seat-info"></div>
                        <p>�i��</p>
                        <div class="seat-info selected"></div>
                        <p>�z���y��</p>
                        <div class="seat-info used"></div>
                        <p>�w�ϥ�</p>
                        <div class="seat-info hold"></div>
                        <p>�O�d��</p>
                    </div>

                    <div class="screen">
                        <h3>(�ȹ�)�^ �M �} �v �u �� �n �q �v(�ȹ�)</h3>
                    </div>

                    <div>
                        <form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" name="form1">
                            <input type="text" class="seat_row" placeholder="�C�ƴX�Ӧ�m" name="seat_row" value="${hall_seatVO.getSeat_row()}">
                            <input type="text" class="seat_col" placeholder="�`�@�X��" name="seat_col" value="${hall_seatVO.getSeat_col()}">
                            <button type="button" class="seat_rc_btn btn btn-primary">�v�U�y��w��</button>
                            <br>
                            <br>
                            <input type="text" class="seat_left" placeholder="����X�Ӧ�m" name="seat_left" value="${hall_seatVO.getSeat_left()}">
                            <input type="text" class="seat_right" placeholder="�k��X�Ӧ�m" name="seat_right" value="${hall_seatVO.getSeat_right()}">
                            <button type="button" class="seat_lr_btn btn btn-primary">���k���D�w��</button>
                            <br>
                            <br>
                            <input type="text" class="seat_row_aisle1" placeholder="�ĴX�ƼW�[���D" name="seat_row_aisle1" value="${hall_seatVO.getseat_row_aisle1()}">
                            <input type="text" class="seat_row_aisle2" placeholder="�ĴX�ƼW�[���D" name="seat_row_aisle2" value="${hall_seatVO.getseat_row_aisle2()}">
                            <button type="button" class="seat_row_aisle_btn btn btn-primary">�e�ᨫ�D�w��</button>
                            <br>
                            <br>
                            <input type="text" placeholder="�ĴX�U" name="hall_id" value="${hall_seatVO.getHall_id()}">
                            <br>
                            <br>
                            <button type="button" class="reset_btn btn btn-secondary">���]</button>
                            <input type="hidden" name="action" value="insert">
							<input class="btn btn-primary" type="submit" value="�T�{�s�W" id="sava_data">
                        
                    		<!-- ���D -->
                    		<div class="aisle"></div>
                    		<div class="seat-start">
                    		</div>

                    		<!-- ���D -->
                    		<div class="aisle"></div>
                    	</form>
                    </div>

                </div>
            </div>
        </main>

        <!-- �u�@�ϵ��� -->

    </section>
    <!-- partial -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/system_testVer.js"></script>
    <script >$("input#seat_name").val(hall_seatVO.getSeat_name());</script>
    <script >$("input#seat_state").val(hall_seatVO.getSeat_state());</script>
    <script >$("input#seat_no").val(hall_seatVO.getSeat_no());</script>
	
</body>

</html>
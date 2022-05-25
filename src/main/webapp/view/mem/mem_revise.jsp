<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="java.sql.*"%>

<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
%>   

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員資料修改</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/mem/css/mem_revise.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<%@ include file="/view/index/header.jsp" %>
    <!-- 主要工作區 -->
    <main>
        <div class="register">
            <form id="register" METHOD="post" ACTION="<c:url value="/view/mem/MemServlet" />" enctype="multipart/form-data">
                <h2>會員資料修改</h2>
                <div class="logo">
                    <img src="DBGifReader4?mem_id=${memVO.mem_id}">
                </div>
                <div class="container">
                    <div class="row row-cols-sm-1 row-cols-md-2">

                        <div class="col-md-3">
                            <p>會員帳號 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" placeholder="${memVO.getMem_account()} " aria-label="Disabled input example" disabled>
                        </div>
                        <div class="col-md-3">
                            <p>會員姓名 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_name" value="<%= (memVO==null)? "會員姓名" : memVO.getMem_name()%>" aria-label="default input example">
                        </div>
                        <div class="col-md-3">
                            <p>會員暱稱 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_nickname" value="<%= (memVO==null)? "會員暱稱" : memVO.getMem_nickname()%>" aria-label="default input example">
                        </div>
                        <div class="col-md-3">
                            <p>聯絡電話 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_phone" value="<%= (memVO==null)? "聯絡電話" : memVO.getMem_phone()%>" aria-label="default input example" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="10">
                        </div>
                        <div class="col-md-3">
                            <p>會員生日 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="date" name="mem_birthday" value="<%= (memVO==null)? "會員生日" : memVO.getMem_birthday()%>">
                        </div>
                        <div class="col-md-3">
                            <p>會員照片 : </p>
                        </div>
                        <div class="col-md-9">
                        	<input class="form-control" name="mem_pic" type="file">
                        </div>
                        <div class="col-md-3">
                            <p>會員性別 : </p>
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="form-check col-6">
                                    <input class="form-check-input radio" type="radio" id="flexRadioDefault1" name="mem_gender" value="0" <%=(memVO.getMem_gender()==0)? "checked": ""%> >
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        男
                                    </label>
                                </div>
                                <div class="form-check col-6">
                                    <input class="form-check-input radio" type="radio" id="flexRadioDefault2" name="mem_gender" value="1" <%=(memVO.getMem_gender()==1)? "checked": ""%> >
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        女
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 ">
                            <p>帳號密碼 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="password"  name="mem_password" value="<%= (memVO==null)? "帳號密碼" : memVO.getMem_password()%>" aria-label="default input example">
                        </div>

                        <div class="col-md-3">
                            <p>會員地址 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_address" value="<%= (memVO==null)? "會員地址" : memVO.getMem_address()%>" aria-label="default input example">
                        </div>
                    </div>
                </div>
                <div class="announcement">
                    <a class="alist" href="<%=request.getContextPath()%>/view/index/index.jsp">
                        <button type="button" class="btn btn-secondary" id="cancel">取消變更, 返回首頁</button>
                    </a>
					<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
					<input type="hidden" name="action" value="update">
                    <button type="button" class="btn btn-primary" id="commit" value="送出修改">確認送出</button>
                    <hr>
                    <p>提醒您！ MUMI MOVIE 吾映良影不會以電話通知更改付款方式或要求改以ATM重新轉帳。 </p>
                    <p>亦不會委託廠商以電話通知變更付款方式或要求提供ATM匯款帳號。 </p>
                </div>
            </form>
        </div>
    </main>
    <!-- 主要工作區結束 -->
<%@ include file="/view/index/footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/mem/js/mem_revise.js"></script>
</body>
</html>
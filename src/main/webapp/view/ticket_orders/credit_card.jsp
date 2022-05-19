<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUMI MOVIE 吾映良影 電子錢包MUMI PAY 儲值頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/ticket_orders/css/credit_card.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp" %>

    <!-- 主要工作區 -->

    <main>
        <div class="mumipay">
            <h3>歡迎來到MUMI PAY儲值頁面</h3>
            <img src="<%=request.getContextPath()%>/view/ticket_orders/images/others/mujilogo2.png" alt="">
            <form action="">

                <div class="container">
                    <div class="row">
                        <div class="form-floating col-12">
                            <h3>您的MUNI PAY餘額尚有 : </h3>
                        </div>
                        <div class="form-floating col-12">
                            <input class="form-control" type="text" value="0" readonly>
                        </div>
                        <div class="form-floating col-12">
                            <p>信用卡卡號</p>
                        </div>
                        <div class="form-floating col-3">
                            <input type="text" class="form-control cardnumber" onkeyup="value=value.replace(/[^0-9\@\.]/g,'')" maxlength="4">
                        </div>
                        <div class="form-floating col-3">
                            <input type="text" class="form-control cardnumber" onkeyup="value=value.replace(/[^0-9\@\.]/g,'')" maxlength="4">
                        </div>
                        <div class="form-floating col-3">
                            <input type="text" class="form-control cardnumber" onkeyup="value=value.replace(/[^0-9\@\.]/g,'')" maxlength="4">
                        </div>
                        <div class="form-floating col-3">
                            <input type="text" class="form-control cardnumber" onkeyup="value=value.replace(/[^0-9\@\.]/g,'')" maxlength="4">
                        </div>
                        <div class="form-floating col-12">
                            <p>持卡人姓名</p>
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-floating col-6">
                            <p>卡片有效期限</p>
                            <input type="month" class="form-control">
                        </div>
                        <div class="form-floating col-6">
                            <p>安全碼</p>
                            <input type="password" class="form-control" onkeyup="value=value.replace(/[^0-9\@\.]/g,'')" maxlength="3">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-2">

                        </div>
                        <div class="col-4">

                            <button type="button" class="btn btn-primary" id="btn_primary">確認送出</button>
                        </div>
                        <div class="col-4">
                            <a href="mem_shopping sheet.html">
                                <button type="button" class="btn btn-secondary">取消返回</button>
                            </a>
                        </div>
                    </div>
                </div>
            </form>

        </div>

    </main>

    <!-- 主要工作區結束 -->

<%@ include file="/view/index/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/ticket_orders/js/credit_card.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 網頁標題記得修改 -->
    <title>前台頁面底板Ver04</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/ticket_orders/css/show_ticket.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp" %>

    <!-- 主要工作區 -->

    <main>

        <div class="showticket">
            <img src="image/others/mujilogo2.png" alt="">
            <h3>XXX 您好, 這次您的訂票明細如下</h3>
            <form action="">

                <div class="container">
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-8">
                            <h4 class="order_number">訂單編號 : 45678</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <table class="table table-success table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">電影名稱</th>
                                        <th class="tableval" scope="col" colspan="3">咒術迴戰</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">影城影廳</th>
                                        <th class="tableval" scope="col" colspan="3">A廳 4DX豪華影廳</th>
                                    </tr>
                                    <tr>
                                    </tr>
                                    <th scope="row">電影場次</th>
                                    <th class="tableval" scope="col" colspan="3">5/25 14:30</th>
                                    <tr>
                                        <th scope="row">票券張數</th>
                                        <th class="tableval" scope="col" colspan="3">全票 3 張</th>
                                    </tr>
                                    <tr>
                                        <th scope="row">場次座位</th>
                                        <th class="tableval" scope="col" colspan="3">H5, H6, H7</th>
                                    </tr>
                                    <tr>
                                        <th scope="row">合計金額</th>
                                        <th class="tableval" scope="col" colspan="3">900</th>
                                    </tr>
                                </tbody>
                            </table>
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
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/ticket_orders/js/show_ticket.js"></script>
</body>

</html>
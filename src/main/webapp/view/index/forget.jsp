<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUMI MOVIE 吾映良影會員登入頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/forget.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
    <!-- 主要工作區 -->
    <main>
        <div class="loginarea">
            <div class="register">
                <form id="forget" METHOD="post" ACTION="<%=request.getContextPath()%>/member/SendEmail">
                    <div class="logo">
                        <img src="<%=request.getContextPath()%>/view/index/image/others/mujilogo2.png" alt="">
                    </div>
                    <h2>忘記密碼</h2>
                    <div class="container">
                        <div class="mb-3">
                            <input type="email" name="mem_account" value="${memVO.mem_account}" class="form-control" id="exampleFormControlInput1" placeholder="請輸入您的電子郵件信箱(帳號)">
                        </div>
                        <div class="mb-3">
                    		<input type="hidden" name="action" value="forget">	
                            <button type="button" id="btnforget" class="btn btn-primary">確認送出</button>
                        </div>
                    </div>
                </form>
                
                <form id="checkaccount" METHOD="post" ACTION="<%=request.getContextPath()%>/member/SendEmail">
                  	<input type="hidden" name="mem_account" value="${memVO.mem_account}">
          			<input type="hidden" name="action" value="checkaccount">	
                </form>
                
                
            </div>
        </div>
    </main>
    <!-- 主要工作區結束 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/index/js/forget.js"></script>
</body>

</html>
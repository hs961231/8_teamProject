<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="/scts/resources/img/favicon.png">
	<script src="/scts/resources/js/jquery-3.2.1.min.js"></script>
    <title>Login Page 2 | Creative - Bootstrap 3 Responsive Admin Template</title>

    <!-- Bootstrap CSS -->    
    <link href="/scts/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="/scts/resources/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="/scts/resources/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="/scts/resources/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="/scts/resources/css/style.css" rel="stylesheet">
    <link href="/scts/resources/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="/scts/resources/js/html5shiv.js"></script>
    <script src="/scts/resources/js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-img3-body">

    <div class="container">

      <form class="login-form" >        
      
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" class="form-control" placeholder="Username" id="user_id" name="user_id" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="Password" id="user_pw" name="user_pw">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="button" id="login">Login</button>
            <button class="btn btn-info btn-lg btn-block" id="signUp" type="button">Sign up</button>
        </div>
      </form>
    <div class="text-right">
            <div class="credits">
                <!-- 
                    All the links in the footer should remain intact. 
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
                <a href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
    </div>
	
	<script type="text/javascript">
		$("#signUp").on("click", function(){
			self.location = "signUp";
		});
		
		var isLogin = false;
		
		$("#login").on("click", function(){
			
			if(isLogin) return false;
			
			var user_id = $("#user_id").val();
			var user_pw = $("#user_pw").val();
			
			if(!user_id){
				alert("아이디를 입력하세요");
				$("#user_id").focus();
				
			}
			if(!user_pw){
				alert("비밀번호를 입력하세요");
				$("#user_pw").focus();
				
			}
			
			isLogin = true;
			
			$.ajax({
				type:"post",
				url:"login",
				data: {
					user_id : user_id,
					user_pw : user_pw
				},
				success: function(data){
					if(data == "0"){
						alert("아이디나 비밀번호가 틀렸습니다.");
						$("#user_id").focus();
					}else{
						alert("로그인 성공!");
						self.location = "index";
					}
				}
			});
		});
	</script>
	
	

  </body>
</html>

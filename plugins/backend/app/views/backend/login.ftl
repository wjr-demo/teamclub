<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>登录</title>

        <!-- Bootstrap Core CSS -->
        <link href="/assets/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

        <link href="/assets/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="/assets/css/font-awesome.css" rel="stylesheet">

        <!-- <link href="/assets/css/main.css" rel="stylesheet"> -->

		<style>
			body{
				background: url('/assets/images/new_year_background.png') repeat;
			}
			.btn-lg, .panel, .form-control {
				border-radius: 2px;
			}
		</style>
        <script src="/assets/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="/assets/bower_components/JavaScript-MD5/js/md5.js"></script>
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="/assets/js/thrid/html5shiv.js"></script>
		<script src="/assets/js/thrid/respond.min.js"></script>
    	<![endif]-->

	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">请登录</h3>
						</div>
						<div class="panel-body">
							<form role="form">
								<fieldset>
									<div class="form-group" style="padding-left: 0px;">
										<input class="form-control" placeholder="用户名" name="username" id="username" type="text" autofocus="">
									</div>
									<div class="form-group" style="padding-left: 0px;">
										<input class="form-control" placeholder="密码" name="password" id="password" type="password" value="">
									</div>
                                    <div class="form-group" style="padding-left: 0px;">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="captcha" placeholder="验证码" aria-describedby="basic-addon2">
                                            <span class="input-group-addon" style="border:0px; padding: 0px;" id="basic-addon2"><img id="imgCaptcha" style="height: 34px;"/></span>
                                        </div>
                                    </div>
									<!-- Change this to a button or input when using this as a form -->
									<a id="login" href="#" class="btn btn-lg btn-success btn-block">登录</a>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
            <div class="modal-dialog modal-sm" id="tip" style="display: none; top: -200px; z-index: 100;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">提示</h4>
                    </div>
                    <div class="modal-body">
						<div id="tipMessage"></div>
					</div>
                    <div class="modal-footer">
                        <button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
		</div>
		<script>
			$.postJSON = function(url,data,s,e){
				if($.isFunction(data)){
					e = s;
					s = data;
					data = {};
				}
				$.ajax({
					url:url,
					type:'post',
					data: JSON.stringify(data),
					contentType: "application/json",
					dataType: "json",
					success:s,
					error:function(e) {
						alert('ajax调用失败');
					}
				});
			};
			$(function(){
				var loadCaptcha = function(){
                    $.postJSON('/login/captcha', {}, function(d) {
                        $('#imgCaptcha').attr('src', d['captcha'])
                        $('#imgCaptcha').attr('token', d['token'])
                    })
				}
				loadCaptcha();
				$('#imgCaptcha').on('click', function() {
                    loadCaptcha();
				})
                var showTip = function(msg) {
                    $('#tipMessage').text(msg)
                    $('#tip').show();
                };
                var hideTip = function() {
                    $('#tip').hide();
                    $('#tipMessage').empty();
                };
                var enterPress = function(e){ //传入 event
                    var e = e || window.event;
                    if(e.keyCode == 13){
                        $('#login').trigger('click')
                    }
                };
				$('#password').on('keypress', enterPress)
                $('#closeBtn').on('click', function(){
                    hideTip()
                });
				$('#login').on('click', function(){
					var username = $('#username').val();
					var password = $('#password').val();
					var d = {};
					d['username'] = username;
					d['password'] = md5(password);
					d['captcha'] = $('#captcha').val();
					d['token'] = $('#imgCaptcha').attr('token');
					$.postJSON('/login/loginInvoke', d, function(d){
						if(d['status'] != 0) {
							loadCaptcha()
                            showTip(d['message'])
						} else {
                            window.location.href = "/backend/index"
						}
					})
				})
			})
		</script>
	</body>
</html>
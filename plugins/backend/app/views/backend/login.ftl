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

        <link href="/assets/css/main.css" rel="stylesheet">

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
									<div class="form-group">
										<input class="form-control" placeholder="用户名" name="username" id="username" type="text" autofocus="">
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="密码" name="password" id="password" type="password" value="">
									</div>
									<!-- Change this to a button or input when using this as a form -->
									<a id="login" href="#" class="btn btn-lg btn-success btn-block">Login</a>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
            <div class="modal-dialog modal-sm" id="tip" style="display: none;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">提示</h4>
                    </div>
                    <div class="modal-body">
						<div id="tipMessage"></div>
					</div>
                    <div class="modal-footer">
                        <button type="button" id="closeBtn"　class="btn btn-default" data-dismiss="modal">关闭</button>
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
					$.postJSON('/login/loginInvoke', d, function(d){
						if(d['status'] != 0) {
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
<#macro html title css="" js="">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>${title}</title>

		<!-- Bootstrap Core CSS -->
		<link href="/assets/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

		<!-- MetisMenu CSS -->
		<link href="/assets/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

        <link href="/assets/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

        <link href="/assets/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

		<!-- Custom CSS -->
		<link href="/assets/css/sb-admin-2.css" rel="stylesheet">

		<!-- Custom Fonts -->
		<link href="/assets/css/font-awesome.css" rel="stylesheet">

        <link href="/assets/css/main.css" rel="stylesheet">

		${css}
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="/assets/js/thrid/html5shiv.js"></script>
		<script src="/assets/js/thrid/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<#nested />
		<!-- jQuery -->
    	<script data-main="/assets/js/main" src="/assets/bower_components/requirejs/require.js"></script>
		${js}
	</body>
</html>

</#macro>
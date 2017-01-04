<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link href="/assets/bower_components/bootstrap-fileinput/css/fileinput.css" rel="stylesheet">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="/assets/bower_components/bootstrap-fileinput/js/fileinput.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/assets/bower_components/bootstrap-fileinput/js/locales/zh.js"></script>
	</head>
	<body>
    	<input type="file" id="uploadfile" multiple class="file-loading" />
	</body>
	<script type="text/javascript">
        $("#uploadfile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: "http://localhost:9000/backend/upload/file", //上传的地址
//            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
        //异步上传返回结果处理
        $('#uploadfile').on('fileerror', function(event, data, msg) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            // get message
            console.log(msg)
        });
        //异步上传返回结果处理
        $("#uploadfile").on("fileuploaded", function (event, data, previewId, index) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            var obj = data.response;
			console.log(obj)

        });

        //同步上传错误处理
        $('#uploadfile').on('filebatchuploaderror', function(event, data, msg) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
        });
        //同步上传返回结果处理
        $("#uploadfile").on("filebatchuploadsuccess", function (event, data, previewId, index) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            var obj = data.response;
            alert(JSON.stringify(data.success));
        });

        //上传前
        $('#uploadfile').on('filepreupload', function(event, data, previewId, index) {
            var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
            console.log('File pre upload triggered');
        });
    </script>

    </script>
</html>
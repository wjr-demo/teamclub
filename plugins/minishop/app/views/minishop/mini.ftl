<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
        <meta name="referrer" content="unsafe-url">
        <#--<meta http-equiv="refresh" content="0; url=http://zhangmeng.test.szjyyg.cn/minishop/mocktencent" />-->
	</head>
	<body>
		<form action="http://www.baidu.com" method="POST" id="tencent">
		</form>
		<#--<iframe src="http://zhangmeng.test.szjyyg.cn/minishop/mocktencent"></iframe>-->
	</body>
	<script>
		window.onload = function(){
            document.getElementById('tencent').submit()
		};
//		var href = "http://zhangmeng.test.szjyyg.cn/minishop/mocktencent"
//        function goTo(url) {
//            var a = document.createElement("a");
//            if(!a.click) { //only IE has this (at the moment);
//                window.location = url;
//                return;
//            }
//            a.setAttribute("href", url);
//            a.style.display = "none";
//            document.body.appendChild(a);
//            a.click();
//        }
//		goTo(href)
	</script>
</html>

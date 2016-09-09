<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<script src="/assets/bower_components/jquery/dist/jquery.min.js"></script>
	</head>
	<body>
		<div>HELLO WORLD</div>
		<button id="btn">ADD</button>
		<button id="remove">REMOVE</button>
        <script>
            $(function(){
				var $el = $('<button>NEW ONE</button>');
                $('#btn').on('click', function(){
                    $el.on('click', function(){
                        console.log('HAHA');
                    });
					$('body').append($el);
				});
				$('#remove').on('click', function(){
					$el.remove()
				})
            })
        </script>
	</body>
</html>
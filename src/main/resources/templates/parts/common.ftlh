<#macro page>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title>Quoter</title>
		<link rel="stylesheet" href="/static/style.css" />
		
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/css/bootstrap.min.css" integrity="sha384-VCmXjywReHh4PwowAiWNagnWcLhlEJLA5buUprzK8rxFgeH0kww/aWY76TfkUoSX" crossorigin="anonymous" />
		<script src="https://www.google.com/recaptcha/api.js" async="true" defer="defer"></script>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script defer="defer" src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script defer="defer" src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>

		<!-- Font Awesome. -->
		<script src="https://kit.fontawesome.com/69a74d55b8.js" crossorigin="anonymous"></script>

		<!-- Turbolinks. -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/5.2.0/turbolinks.js"></script>

		<style>
			.turbolinks-progress-bar {
				height: 3px;
				background-color: #007bff;
			}

		</style>
	</head>
	<body>
		<#include "navbar.ftlh" />
		<div class="container mt-5">
			<#nested>
		</div>
		
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js" integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J" crossorigin="anonymous"></script>
	</body>
</html>

</#macro>

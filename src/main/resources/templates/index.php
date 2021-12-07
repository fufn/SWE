<!DOCTYPE html>
<meta charset="utf-8">
<html>
<head>
	<title>Hotels</title>
	<link rel="stylesheet" type="text/css" href="style/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
	<?php
		include('header.php');
	?>

	<?php 
		include('carousel.php');
	?>
	<div class="info-part">
		<?php
			include('info-section.php');
		?>
		<?php
			include('contact.php');
		?>
	</div>

	<?php
		include('footer.php');

	?>


</body>
	<script type="text/javascript" src="javascript/jquery-2.2.1.min.js"></script>
	<script type="text/javascript" src="javascript/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="javascript/script.js"></script>
</html>
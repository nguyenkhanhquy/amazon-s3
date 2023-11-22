<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>S3 File Upload</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>S3 File Upload</h1>
		</div>
		
		<div>
		<form action="upload" method="post" enctype="multipart/form-data">		
			<p><input type="file" name="file" required></p>
			
			<p><button type="submit">Submit</button></p>
		</form>
	</div>
	</div>
</body>
</html>
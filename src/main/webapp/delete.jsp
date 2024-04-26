<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>S3 File Delete</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>S3 File Delete</h1>
		</div>
		
		<div>
			<form action="delete" method="post">
				<p>File name: <input type="text" name="fileName" size="30" required></p>
				
				<p><button type="submit">Submit</button></p>
			</form>
		</div>
	</div>
</body>
</html>
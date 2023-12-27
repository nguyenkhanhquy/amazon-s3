<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>S3 File Upload</title>
    <style>
        #image-preview {
            max-width: 100%;
            max-height: 300px;
            border: 1px solid #ddd;
        }

        #file-input-label {
            cursor: pointer;
        }
    </style>
</head>
<body>
	<div align="center">
	    <form action="upload" method="post" enctype="multipart/form-data"> 
	        <label id="file-input-label" for="file-input">
	            <img id="image-preview" src="" alt="Ảnh hiển thị">
	            <br>
	            Tải ảnh mới        
	            <input type="file" id="file-input" name="file" accept="image/*" style="display:none;" required>
	        </label>
	        <p><button type="submit">Submit</button></p>
	    </form>
	</div>
        
    <script>
        document.getElementById('file-input').addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const imageUrl = URL.createObjectURL(file);
                document.getElementById('image-preview').src = imageUrl;
            }
        });
    </script>
</body>
</html>

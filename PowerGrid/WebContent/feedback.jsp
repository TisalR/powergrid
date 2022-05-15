<%@page import="feedback.Feedbackmodel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/feedback.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
			<h1>Items Management4545</h1>
			<form  id="formItem" name="formItem" >
				 Item code: <input id="Issue_name" name="issueName" type="text" class="form-control form-control-sm"><br> 
				 Item name: <input id="Issue_Status" name="issueStatus" type="text" class="form-control form-control-sm"><br> 
				 Item price: <input id="Date" name="Date" type="text" class="form-control form-control-sm"><br> 
				 Item description: <input  id="Description" name="Description" type="text" class="form-control form-control-sm"><br> 
			 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			 	
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divItemsGrid">
				 <%
				 Feedbackmodel itemObj = new Feedbackmodel(); 
					 out.print(itemObj.readItems()); 
				 %>
			</div>

		</div>
	</div>

</div>	
</body>
</html>
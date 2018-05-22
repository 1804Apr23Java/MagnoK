<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Reimbursement</title>
<link rel="stylesheet" href="css/SubmitReimb.css" />
<!-- Linking Bootstrap-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body>
	<form action="submitreimb" method="post">
		<!-- Request Notes -->
		<input type="text" name="reqNotes">
		<input type="number" name="amount">
		
		<div>
			<button class="btn btn-primary" type="submit" value="Submit">Submit
				Reimbursement</button>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data">
		id: <input type="text" name="id" /> Document: <input type="file"
			name="document" /> <input type="submit" name="submit" value="Upload" />

	</form>

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Download</th>
			</tr>
		</thead>
		<c:forEach var="temp" items="${doc}">
			<tbody>
				<tr>
					<td>${temp.id}</td>
					<td>${temp.name}</td>
					<td><a href="download?id=${temp.id}">Download</a></td>
				</tr>
			</tbody>

		</c:forEach>

	</table>

</body>
</html>
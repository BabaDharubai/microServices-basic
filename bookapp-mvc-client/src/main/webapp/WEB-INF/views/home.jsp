<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form >
<a href="cart" >cart</a>
Enter author<input type="text" name="param">
<input type="submit" value="search-author" formaction="search-author">
<input type="submit" value="search-Id" formaction="search-Id"><br>
${books}
</form>
</body>
</html>
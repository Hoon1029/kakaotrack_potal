<%@page contentType="text/html; charset=UTF-8" %>
<html>
<h1>
    <h1>LOGIN</h1>
</h1>
<a>${message}</a>
<form action="/login_request" method="post" enctype="multipart/form-data">
    <div><a>ID: </a><input type="test" name="id"/></div>
    <div><a>PW: </a><input type="password" name="password"/></div>
    <div><input type="submit"/></div>
</form>
</html>
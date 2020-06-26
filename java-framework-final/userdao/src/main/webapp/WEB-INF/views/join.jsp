<%@page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h1>Welcome to "My coupon"</h1>
    <h3>회원가입</h3>

    <a>${message}</a>
    <form RRI action="/joinRequest" method="post" enctype="multipart/form-data">
        <div><a>ID: </a><input type="text" name="id"/></div>
        <div><a>PW: </a><input type="password" name="password"/></div>
        <div><a>Name: </a><input type="text" name="name"/></div>
        <div><input type="submit" value="가입하기"/></div>
    </form>
    <div><button type="button" onclick="location.href='/login'">로그인 화면</button></div>
</body>
</html>
<script>

</script>
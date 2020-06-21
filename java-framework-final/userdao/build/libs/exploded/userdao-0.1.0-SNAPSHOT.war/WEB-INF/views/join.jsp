<%@page contentType="text/html; charset=UTF-8" %>
<html>
<body>
    <h1>회원가입</h1>

    <a>${message}</a>
    <form action="/join_request" method="post" enctype="multipart/form-data">
        <div><a>ID: </a><input type="text" name="id"/></div>
        <div><a>PW: </a><input type="password" name="password"/></div>
        <div><a>Name: </a><input type="text" name="name"/></div>
        <div><input type="submit" value="확인"/></div>
    </form>
    <div><button type="button" onclick="location.href='/login'">로그인</button></div>
</body>
</html>
<script>

</script>
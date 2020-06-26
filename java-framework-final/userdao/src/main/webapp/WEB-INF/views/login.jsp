<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>

</head>
<style>
</style>
<script>
</script>
<body>
<h1>Welcome to "My coupon"</h1>

    <h3>로그인</h3>
    <a>${message}</a>
    <form action="/loginRequest" method="post" enctype="multipart/form-data">
        <div>
            <div  padding-bottom:15px;">
                <div><a><text style="padding-left:10px; padding-right:10px; width:50px;">ID: </text></a><input type="text" name="id"/></div>
                <div><a><text style="padding-left:10px; padding-right:10px; width:50px;">PW: </text></a><input type="password" name="password"/></div>
            </div>
            <div><input type="submit" value="로그인"/></div>
        </div>
    </form>
    <div><button type="button" onclick="location.href='/join'">회원가입 화면</button></div>
</body>
</html>

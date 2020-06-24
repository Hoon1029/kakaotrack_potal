<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style></style>
<head></head>
<body>
    <a href = "/customer/shopList">customer</a>
    <a href = "/logout">logout</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>
    <h3>나의 매장</h3>
    <script>
        var shops = ${shopsJson};
        if(shops.length == 0){
            document.write("등록된 매장이 없습니다.");
        }
        for(var i=0 ; i<shops.length ; i++){
            var value = shops[i].name;
            var url = "\"/owner/couponList/{"+shops[i].id+"}/\""
            document.write("<div>매장"+(i+1)+": <a href=" + url + ">" + value + "</a></div>");
        }
    </script>
</body>
<h3>매장 추가하기</h3>
<form action="/owner/createShop" method="post" enctype="multipart/form-data">
    <div>
        <div>
            <div><a>매장 이름: </a><input type="text" name="name"/></div>
            <div><a>매장 주소: </a><input type="text" name="address"/></div>
            <div><a>X좌표: </a><input type="text" name="locateX"/></div>
            <div><a>Y좌표: </a><input type="text" name="locateY"/></div>
        </div>
        <div><input type="submit" value="등록하기"/></div>
    </div>
</form>
</html>
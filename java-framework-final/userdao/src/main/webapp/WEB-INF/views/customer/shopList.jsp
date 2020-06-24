<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>

</style>
<head>

</head>
<body>
<a href = "/owner/shopList">owner_mode</a>
<a href = "/logout">logout</a>
<hr>
<h1>Welcome to "My coupon"</h1>
<hr>

    <script>
        var shops = ${shopsJson};
        for(var i=0 ; i<shops.length ; i++){
            document.write("<div><a href=\"/customer/couponList/"+shops[i].id+"\">"+shops[i].name+"</a></div>")
        }

    </script>
<%--    <div><a href="/couponList/shops[i].name">shops[i].name</a></div>--%>
</body>
</html>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>

</style>
<head>

</head>
<body>
    <h1>Hello coupon world!!!</h1>


    <script>
        var shops = ${shopsJson};
        for(var i=0 ; i<shops.length ; i++){
            document.write("<div><a href=\"/customer/couponList/"+shops[i].id+"\">"+shops[i].name+"</a></div>")
        }

    </script>
<%--    <div><a href="/couponList/shops[i].name">shops[i].name</a></div>--%>
</body>
</html>
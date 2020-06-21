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
            document.write("<div>"+shops[i].name+"</div>")
        }

    </script>
<div></div>
</body>
</html>
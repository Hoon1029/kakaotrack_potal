<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>
    details {margin_top:10px;}
</style>
<head>

</head>
<body>
<a href = "/owner/shopList">OWNER MODE</a>
<a href = "/logout">LOGOUT</a>
<hr>
<h1>Welcome to "My coupon"</h1>
<hr>
<h1>${shop.name}</h1>

<script>
    var coupons = ${couponsJson};
    for(var i=0 ; i<coupons.length ; i++){
        var couponInforId = coupons[i].couponInforId;
        var productName = coupons[i].productName;
        var productPrice = coupons[i].productPrice;
        var couponName = coupons[i].couponName;
        var stampNum = coupons[i].stampNum;
        var url = "\"/coupon/{"+couponInforId+"}/\""
        var value = productName+"("+productPrice+"): "+stampNum+"/10"
        document.write("<div>쿠폰"+(i+1)+": <a href=" + url + ">" + value + "</a></div>");
    }
</script>
</body>
</html>
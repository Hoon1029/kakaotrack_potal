<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>

</style>
<head>

</head>
<body>
<h1>Hello coupon world!!!</h1>

<h1>${shop.name}</h1>
${couponsJson}
${couponsJson}
<script>
    var coupons = ${couponsJson};
    document.write(coupons);
    document.write(coupons[0]);
    for(var i=0 ; i<coupons.length ; i++){
        var productName = coupons[i].productName;
        var productPrice = coupons[i].productPrice;
        var couponName = coupons[i].couponName;
        var stampNum = coupons[i].stampNum;
        document.write("<div><a href=\"/coupon/\"+coupons[i].couponInfor.name>"+"coupons[0].couponInfor.name"+coupons[i].num+"</a></div>");
    }

</script>
</body>
</html>
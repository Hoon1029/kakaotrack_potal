<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>
    details {margin_top:10px;}
</style>
<script>
    var shopName = ${shop.name};
    var enrolledCoupons = ${couponsJson};
</script>
<head>

</head>
<body>
<a href = "/owner/shopList">OWNER MODE</a>
<a href = "/logout">LOGOUT</a>
<hr>
<h1>Welcome to "My coupon"</h1>
<hr>

<script>
    document.write(shopName);

    if(enrolledShops.length == 0){
        document.write("등록된 쿠폰이 없습니다.");
    }else{
        document.write("<table>" +
            "<tr bgcolor=white align=center>" +
            "<td> 번호 </td>" +
            "<td> 쿠폰명 </td>" +
            "<td> 상품 </td>" +
            "<td> 상품가격 </td>" +
            "<td> 스템프 </td>" +
            "<td> 삭제하기 </td>" +
            "</tr>");
        for(var i=0 ; i<enrolledShops.length ; i++){
            var couponManagementUrl = "/customer/couponList/"+enrolledShops[i].id;
            var withdrawalShopUrl = "/customer/withdrawShop/"+enrolledShops[i].id;
            var value = "<tr id=\"shop\" align=center>" +
                "<td>"+(i+1)+"</td>" +
                "<td>"+enrolledShops[i].name+"</td>" +
                "<td>"+enrolledShops[i].address+"</td>" +
                "<td><input type=\"button\" value = \"쿠폰보기\" onClick=\"location.href=\'"+couponManagementUrl+"\'\"></td>" +
                "<td><input type=\"button\" value = \"탈퇴하기\" onClick=\"location.href=\'"+withdrawalShopUrl+"\'\"></td>" +
                "</tr>";
            document.write(value);
        }
        document.write("</table>");
    }


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
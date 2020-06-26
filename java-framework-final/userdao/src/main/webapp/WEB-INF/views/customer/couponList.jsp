<%@page contentType="text/html; charset=UTF-8" %>
<html>
<style>
    table, td { border: black 1px solid; }
</style>
<script>

    var enrolledCouponDatas = ${enrolledCouponDatasJson};
    var unEnrolledCouponDatas = ${unEnrolledCouponDatasJson};
</script>
<head>

</head>
<body>
    <a href = "/owner/shopList">owner_mode</a>
    <a href = "/logout">logout</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>
    <h1>${shop.name}</h1>

    <h3>My Coupon List</h3>
    <script>
        if(enrolledCouponDatas.length == 0){
            document.write("등록된 쿠폰이 없습니다..");
        }else{
            document.write("<table>" +
                "<tr bgcolor=white align=center>" +
                "<td> 번호 </td>" +
                "<td> 쿠폰명 </td>" +
                "<td> 상품명 </td>" +
                "<td> 상품가격 </td>" +
                "<td> 스탬프 </td>" +
                "<td> 스탬프 요청 </td>" +
                "<td> 삭제하기 </td>" +
                "</tr>");
            for(var i=0 ; i<enrolledCouponDatas.length ; i++){
                var dropCouponUrl = "/customer/dropCoupon/"+enrolledCouponDatas[i].couponInforId;
                var value = "<tr id=\"shop\" align=center>" +
                    "<td>"+(i+1)+"</td>" +
                    "<td>"+enrolledCouponDatas[i].couponName+"</td>" +
                    "<td>"+enrolledCouponDatas[i].productName+"</td>" +
                    "<td>"+enrolledCouponDatas[i].productPrice+"</td>" +
                    "<td>"+enrolledCouponDatas[i].stampNum+"/"+enrolledCouponDatas[i].maxStampNum+"</td>" +
                    "<td><input id=\"stampRequestNum"+i+"\" style=\"width:40px;\" type=\"text\" value=1><input type=\"button\" value = \"요청하기 \" onClick=\"stampRequest("+i+");\"></td>" +
                    "<td><input type=\"button\" value = \"삭제하기\" onClick=\"location.href=\'"+dropCouponUrl+"\'\"></td>" +
                    "</tr>";
                document.write(value);
            }
            document.write("</table>");
        }

        function stampRequest(i){
            var stampRequestUrl = "/customer/stampRequest/"+enrolledCouponDatas[i].couponInforId;
            var stampNum = document.getElementById("stampRequestNum"+i).value;
            window.location = stampRequestUrl+"/"+stampNum;
            // httpRequest.open("GET", stampRequestUrl+"/"+stampNum, true);
            // httpRequest.send();
        }

    </script>
    <details class="details2">
        <summary class="details2">Another Coupon List</summary>
        <div class="details2_content">
            <form id = "couponEnrollmentTag" method="post" enctype="multipart/form-data">
                <script>
                    if(unEnrolledCouponDatas.length == 0){
                        document.write("등록할 쿠폰이 업습니다..");
                    }else{
                        document.write("<table>" +
                            "<tr bgcolor=white align=center>" +
                            "<td> 번호 </td>" +
                            "<td> 쿠폰명 </td>" +
                            "<td> 상품명 </td>" +
                            "<td> 상품가격 </td>" +
                            "<td> 스탬프 </td>" +
                            "<td> 등록하기 </td>" +
                            "</tr>");
                        for(var i=0 ; i<unEnrolledCouponDatas.length ; i++){
                            var enrollCouponUrl = "/customer/enrollCoupon/"+unEnrolledCouponDatas[i].couponInforId;
                            var value = "<tr align=center>" +
                                "<td>"+(i+1)+"</td>" +
                                "<td>"+unEnrolledCouponDatas[i].couponName+"</td>" +
                                "<td>"+unEnrolledCouponDatas[i].productName+"</td>" +
                                "<td>"+unEnrolledCouponDatas[i].productPrice+"</td>" +
                                "<td>"+unEnrolledCouponDatas[i].maxStampNum+"</td>" +
                                "<td><input type=\"button\" value = \"등록하기\" onClick=\"location.href=\'"+enrollCouponUrl+"\'\"></td>" +
                                "</tr>";
                            document.write(value);
                        }
                        document.write("</table>");
                    }
                </script>
            </form>
        </div>
    </details>
</body>
</html>
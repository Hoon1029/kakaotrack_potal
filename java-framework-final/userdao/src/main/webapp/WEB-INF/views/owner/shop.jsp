<%@page contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>

<% request.setCharacterEncoding("euc-kr"); %>
<html>
<style>
    * {
        background-color: black;
        color: white
    }
    input {
        background-color: white;
        color: black;
    }
    details {
        margin-top: 5px;
        margin-bottom: 5px;
    }
    summary {

    }
    summary.details1 {
        font-size : 25px;
        background-color: black;
        color : white;
    }
    summary.details2 {
        font-size : 15px;
    }

    .details2_content {
        margin-left: 20px;
    }
    table, td { border: white 1px solid; }
</style>
<script>
    //var userId = ${userId};
    var shopId = ${shopId};
    var products = ${productsJson};
    var couponInfors = ${couponInforsJson};
    var stampRequests = ${stampRequestsJson};
    var stampRequestNames = ${stampRequestNamesJson};
</script>
<head>
</head>
<body>
    <a href = "/customer/shopList">CUSTOMER MODE</a>
    <a href = "/logout">LOGOUT</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>

    <details class="details1">
        <summary class="details1">Product List</summary>
        <div class="details1_content">
            <script open="true">


                if(products.length == 0){
                    document.write("등록된 제품이 없습니다.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> 번호 </td>" +
                        "<td> 제품 ID </td>" +
                        "<td> 제품명 </td>" +
                        "<td> 제품가격 </td>" +
                        "<td> 판매 여부 </td>" +
                        "<td> 수정 </td>" +
                        "<td> 삭제 </td>" +
                        "</tr>")
                    for(var i=0 ; i<products.length ; i++){
                        var deleteProductUrl = "/owner/deleteProduct/"+shopId+"/"+products[i].id;
                        var value = "<tr align=center onclick='modifyPage(i)'>" +
                            "<td>"+(i+1)+"</td>" +
                            "<td>"+products[i].id+"</td>" +
                            "<td>"+products[i].name+"</td>" +
                            "<td>"+products[i].price+"</td>" +
                            "<td>"+products[i].sellFlag+"</td>" +
                            "<td><input type=\"button\" value = \"수정\" onClick= openModifPage()></td>" +
                            "<td><input type=\"button\" value = \"삭제\" onClick=\"location.href=\'"+deleteProductUrl+"\'\"></td>" +
                            "</tr>"
                        document.write(value);
                    }
                    document.write("</table>");
                }
            </script>

            <details class="details2">
                <summary class="details2">PRODUCT_ENROLLMENT</summary>
                <div class="details2_content">
                    <form id = "productEnrollmentTag" method="post" enctype="multipart/form-data">
                    <script>
                        var tag = document.getElementById("productEnrollmentTag")
                        tag.action = "/owner/enrollProduct/"+shopId;
                    </script>
                        <div>
                            <div>
                                <div>제품 명<input type="text" name="name"/></div>
                                <div>제품 가격<input type="text" name="price"/></div>
                                <div>제품 이미지<input type="file" name="image"/></div>
                            </div>
                            <div><input type="submit" value="등록하기"/></div>
                        </div>
                    </form>
                </div>
            </details>
        </div>
    </details>
    <hr>
    <details class="details1">
        <summary class="details1">Coupone List</summary>
        <div class="details1_content">
            <script>
                if(couponInfors.length == 0){
                    document.write("등록된 쿠폰이 없습니다.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> 번호 </td>" +
                        "<td> 쿠폰ID </td>" +
                        "<td> 쿠폰명 </td>" +
                        "<td> 상품ID </td>" +
                        "<td> 스탬프 </td>" +
                        "<td> 수정하기 </td>" +
                        "<td> 삭제하기 </td>" +
                        "</tr>");
                    for(var i=0 ; i<couponInfors.length ; i++){
                        var deleteCouponInforUrl = "/owner/deleteCouponInfor/"+shopId+"/"+couponInfors[i].id;
                        var value = "<tr align=center>" +
                            "<td>"+(i+1)+"</td>" +
                            "<td>"+couponInfors[i].id+"</td>" +
                            "<td>"+couponInfors[i].name+"</td>" +
                            "<td>"+couponInfors[i].productId+"</td>" +
                            "<td>"+couponInfors[i].maxStampNum+"</td>" +
                            "<td><input type=\"button\" value = \"수정하기\" onClick= openModifPage()></td>" +
                            "<td><input type=\"button\" value = \"삭제하기\" onClick=\"location.href=\'"+deleteCouponInforUrl+"\'\"></td>" +
                            "</tr>";
                        document.write(value);
                    }
                    document.write("</table>");
                }
            </script>
            <details class="details2">
                <summary class="details2">COUPONE_ENROLLMENT</summary>
                <div class="details2_content">
                    <form id = "couponEnrollmentTag" method="post" enctype="multipart/form-data">
                        <script>
                            var tag = document.getElementById("couponEnrollmentTag")
                            tag.action = "/owner/enrollCoupon/"+shopId;
                        </script>
                        <div>
                            <div>
                                <div>쿠폰명<input type="text" name="name"/></div>
                                <div>상품ID<input type="text" name="productId"/></div>
                                <div>도장개수<input type="text" name="maxStampNum"/></div>
                                <div>배경이미지 <input type="file" name="bagroundImgId"/></div>
                                <div>도장이미지<input type="file" name="stampImgId"/></div>
                            </div>
                            <div><input type="submit" value="등록하기"/></div>
                        </div>
                    </form>
                </div>
            </details>
        </div>
    </details>
    <hr>
    <details class="details1">
        <summary class="details1">Stamp Request List</summary>
        <div class="details1_content">
            <script open="true">
                if(stampRequests.length == 0){
                    document.write("처리할 요청이 없습니다.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> 번호 </td>" +
                        "<td> 쿠폰 ID </td>" +
                        "<td> 쿠폰 명 </td>" +
                        "<td> 요청자 ID </td>" +
                        "<td> 요청량 </td>" +
                        "<td> 수락 </td>" +
                        "<td> 거부 </td>" +
                        "</tr>")
                    for(var i=0 ; i<stampRequests.length ; i++){
                        var acceptRequestUrl = "/owner/acceptStampRequest/"+stampRequests[i].id;
                        var refuseRequestUrl = "/owner/refuseStampRequest/"+stampRequests[i].id;
                        var value = "<tr align=center onclick='modifyPage(i)'>" +
                            "<td>"+(i+1)+"</td>" +
                            "<td>"+stampRequests[i].couponInforId+"</td>" +
                            "<td>"+stampRequestNames[i]+"</td>" +
                            "<td>"+stampRequests[i].customerId+"</td>" +
                            "<td>"+stampRequests[i].stampNum+"</td>" +
                            "<td><input type=\"button\" value = \"수락\" onClick=\"location.href=\'"+acceptRequestUrl+"\'\"></td>" +
                            "<td><input type=\"button\" value = \"거부\" onClick=\"location.href=\'"+refuseRequestUrl+"\'\"></td>" +
                            "</tr>"
                        document.write(value);
                    }
                    document.write("</table>");
                }
            </script>
        </div>
    </details>
</body>
</html>
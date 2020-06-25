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
</style>
<script>
    var shopId = ${shopId};
    var products = ${productsJson};
    var couponInfors = ${couponInforsJson}
</script>
<head>
</head>
<body>
    <a href = "/customer/shopList">customer</a>
    <a href = "/logout">logout</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>

    <details class="details1">
        <summary class="details1">Product List</summary>
        <div class="details1_content">
            <script open="true">


                if(products.length == 0){
                    document.write("��ϵ� ��ǰ�� �����ϴ�.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> ��ǰ ID </td>" +
                        "<td> ��ǰ�� </td>" +
                        "<td> ��ǰ���� </td>" +
                        "<td> �Ǹ� ���� </td>" +
                        "<td> ���� </td>" +
                        "<td> ���� </td>" +
                        "</tr>")
                    for(var i=0 ; i<products.length ; i++){
                        var deleteProductUrl = "/owner/deleteProduct/"+shopId+"/"+products[i].id;
                        var value = "<tr align=center onclick='modifyPage(i)'>" +
                            "<td>"+products[i].id+"</td>" +
                            "<td>"+products[i].name+"</td>" +
                            "<td>"+products[i].price+"</td>" +
                            "<td>"+products[i].sellFlag+"</td>" +
                            "<td><input type=\"button\" value = \"����\" onClick= openModifPage()></td>" +
                            "<td><input type=\"button\" value = \"����\" onClick=\"location.href=\'"+deleteProductUrl+"\'\"></td>" +
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
                                <div>��ǰ ��<input type="text" name="name"/></div>
                                <div>��ǰ ����<input type="text" name="price"/></div>
                                <div>��ǰ �̹���<input type="file" name="image"/></div>
                            </div>
                            <div><input type="submit" value="����ϱ�"/></div>
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
                    document.write("��ϵ� ������ �����ϴ�.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> ���� �� </td>" +
                        "<td> ��ǰID </td>" +
                        "<td> ������ </td>" +
                        "<td> ���� </td>" +
                        "<td> ���� </td>" +
                        "</tr>");
                    for(var i=0 ; i<couponInfors.length ; i++){
                        var deleteCouponInforUrl = "/owner/deleteCouponInfor/"+shopId+"/"+couponInfors[i].id;
                        var value = "<tr align=center>" +
                            "<td>"+couponInfors[i].name+"</td>" +
                            "<td>"+couponInfors[i].productId+"</td>" +
                            "<td>"+couponInfors[i].maxStampNum+"</td>" +
                            "<td><input type=\"button\" value = \"����\" onClick= openModifPage()></td>" +
                            "<td><input type=\"button\" value = \"����\" onClick=\"location.href=\'"+deleteCouponInforUrl+"\'\"></td>" +
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
                                <div>���� ��<input type="text" name="name"/></div>
                                <div>��ǰ ID<input type="text" name="productId"/></div>
                                <div>���� ����<input type="text" name="maxStampNum"/></div>
                                <div>��� �̹��� <input type="file" name="bagroundImgId"/></div>
                                <div>���� �̹���<input type="file" name="stampImgId"/></div>
                            </div>
                            <div><input type="submit" value="����ϱ�"/></div>
                        </div>
                    </form>
                </div>
            </details>
        </div>
    </details>
</body>
</html>
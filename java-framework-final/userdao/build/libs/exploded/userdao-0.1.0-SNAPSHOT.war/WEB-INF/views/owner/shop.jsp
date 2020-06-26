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
                    document.write("��ϵ� ��ǰ�� �����ϴ�.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> ��ȣ </td>" +
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
                            "<td>"+(i+1)+"</td>" +
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
                        "<td> ��ȣ </td>" +
                        "<td> ����ID </td>" +
                        "<td> ������ </td>" +
                        "<td> ��ǰID </td>" +
                        "<td> ������ </td>" +
                        "<td> �����ϱ� </td>" +
                        "<td> �����ϱ� </td>" +
                        "</tr>");
                    for(var i=0 ; i<couponInfors.length ; i++){
                        var deleteCouponInforUrl = "/owner/deleteCouponInfor/"+shopId+"/"+couponInfors[i].id;
                        var value = "<tr align=center>" +
                            "<td>"+(i+1)+"</td>" +
                            "<td>"+couponInfors[i].id+"</td>" +
                            "<td>"+couponInfors[i].name+"</td>" +
                            "<td>"+couponInfors[i].productId+"</td>" +
                            "<td>"+couponInfors[i].maxStampNum+"</td>" +
                            "<td><input type=\"button\" value = \"�����ϱ�\" onClick= openModifPage()></td>" +
                            "<td><input type=\"button\" value = \"�����ϱ�\" onClick=\"location.href=\'"+deleteCouponInforUrl+"\'\"></td>" +
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
                                <div>������<input type="text" name="name"/></div>
                                <div>��ǰID<input type="text" name="productId"/></div>
                                <div>���尳��<input type="text" name="maxStampNum"/></div>
                                <div>����̹��� <input type="file" name="bagroundImgId"/></div>
                                <div>�����̹���<input type="file" name="stampImgId"/></div>
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
        <summary class="details1">Stamp Request List</summary>
        <div class="details1_content">
            <script open="true">
                if(stampRequests.length == 0){
                    document.write("ó���� ��û�� �����ϴ�.");
                }else{
                    document.write("<table>" +
                        "<tr bgcolor=white align=center>" +
                        "<td> ��ȣ </td>" +
                        "<td> ���� ID </td>" +
                        "<td> ���� �� </td>" +
                        "<td> ��û�� ID </td>" +
                        "<td> ��û�� </td>" +
                        "<td> ���� </td>" +
                        "<td> �ź� </td>" +
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
                            "<td><input type=\"button\" value = \"����\" onClick=\"location.href=\'"+acceptRequestUrl+"\'\"></td>" +
                            "<td><input type=\"button\" value = \"�ź�\" onClick=\"location.href=\'"+refuseRequestUrl+"\'\"></td>" +
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
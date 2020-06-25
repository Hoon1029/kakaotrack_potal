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
<head>
</head>
<script>
    var products = ${productsJson};
    var shopId = ${shopId};
</script>
<body>
    <a href = "/customer/shopList">customer</a>
    <a href = "/logout">logout</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>

    <details class="details1">
        <summary class="details1">PRODUCT</summary>
        <div class="details1_content">
            <details class="details2">
                <summary class="details2">PRODUCT_LIST</summary>
                <div class="details2_content">
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
                            "</tr>")
                        for(var i=0 ; i<products.length ; i++){
                            var value = "<tr align=center>" +
                                "<td>"+products[i].id+"</td>" +
                                "<td>"+products[i].name+"</td>" +
                                "<td>"+products[i].price+"</td>" +
                                "<td>"+products[i].sellFlag+"</td>" +
                                "</tr>"
                            document.write(value);
                        }
                        document.write("</table>");
                    }

                </script>
                </div>
            </details>

            <details class="details2">
                <summary class="details2">PRODUCT_ENROLLMENT</summary>
                <div class="details2_content">
                    <form id = "enrollmentTag" method="post" enctype="multipart/form-data">
                    <script>
                        var tag = document.getElementById("enrollmentTag")
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
        <summary class="details1">COUPONN</summary>
        <div class="details1_content">
            <details class="details2">
                <summary class="details2">COUPONE_LIST</summary>
                <div class="details2_content">
                </div>
            </details>
            <details class="details2">
                <summary class="details2">COUPONE_ENROLLMENT</summary>
                <div class="details2_content">
                </div>
            </details>
        </div>
    </details>
</body>
</html>
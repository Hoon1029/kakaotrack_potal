<%@page contentType="text/html; charset=UTF-8" %>
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
                    var products = ${productsJson};

                    if(products.length == 0){
                        document.write("등록된 제품이 없습니다.");
                    }else{
                        document.write("<table>" +
                            "<tr bgcolor=white align=center>" +
                            "<td> 제품 ID </td>" +
                            "<td> 제품명 </td>" +
                            "<td> 제품가격 </td>" +
                            "<td> 판매 여부 </td>" +
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

                    for(var i=0 ; i<shops.length ; i++){
                        var value = shops[i].name;
                        var url = "\"/owner/couponList/{"+shops[i].id+"}/\""
                        document.write("<div>매장"+(i+1)+": <a href=" + url + ">" + value + "</a></div>");
                    }
                </script>
            </details>
            <details class="details2">
                <summary class="details2">PRODUCT_ENROLLMENT</summary>
                <div class="details2_content">
                    <form action="/owner/enrollProduct" method="post" enctype="multipart/form-data">
                        <div>
                            <div>
                                <div>제품 명<input type="text" name="name"/></div>
                                <div>제품 가격<input type="text" name="address"/></div>
                                <div>제품 이미지<input type="file" name="file"/></div>
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
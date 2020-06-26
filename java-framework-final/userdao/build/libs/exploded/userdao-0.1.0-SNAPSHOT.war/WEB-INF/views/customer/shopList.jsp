<%@page contentType="text/html; charset=UTF-8" %>
<html>

<style>
    table, td { border: black 1px solid; }
    details {margin_top:10px;}
    text {padding-right:7px;
        padding-lert:7px;
        font-size: 20px;
    }
</style>

<script>
    var enrolledShops = ${enrolledShopsJson};
    var unEnrolledShops = ${unEnrolledShopsJson};
    //var userId = ${userId};
</script>

<head></head>

<body>
<a href = "/owner/shopList"><text class="menuButton">owner_mode</text></a>
<a href = "/logout"><text class="menuButton">logout</text></a>

<hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>
    <h3>My Shop List</h3>
    <script>
        if(enrolledShops.length == 0){
            document.write("가입된 매장이 없습니다.");
        }else{
            document.write("<table>" +
                "<tr bgcolor=white align=center>" +
                "<td> 번호 </td>" +
                "<td> 매장명 </td>" +
                "<td> 매장주소 </td>" +
                "<td> 쿠폰보기 </td>" +
                "<td> 탈퇴하기 </td>" +
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
    </script>
    <details class="details2">
        <summary class="details2">Another Shop List</summary>
        <div class="details2_content">
            <form id = "couponEnrollmentTag" method="post" enctype="multipart/form-data">
                <script>
                    if(unEnrolledShops.length == 0){
                        document.write("가입할 매장이 없습니다.");
                    }else{
                        document.write("<table>" +
                            "<tr bgcolor=white align=center>" +
                            "<td> 번호 </td>" +
                            "<td> 매장명 </td>" +
                            "<td> 매장주소 </td>" +
                            "<td> 등록하기 </td>" +
                            "</tr>");
                        for(var i=0 ; i<unEnrolledShops.length ; i++){
                            var withdrawalShopUrl = "/customer/enrollShop/"+unEnrolledShops[i].id;
                            var value = "<tr id=\"shop\" align=center>" +
                                "<td>"+(i+1)+"</td>" +
                                "<td>"+unEnrolledShops[i].name+"</td>" +
                                "<td>"+unEnrolledShops[i].address+"</td>" +
                                "<td><input type=\"button\" value = \"등록하기\" onClick=\"location.href=\'"+withdrawalShopUrl+"\'\"></td>" +
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
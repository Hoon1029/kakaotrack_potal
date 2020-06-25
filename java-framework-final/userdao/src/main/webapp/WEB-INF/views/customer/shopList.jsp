<%@page contentType="text/html; charset=UTF-8" %>
<html>

<style></style>

<script>
    var shops = ${shopsJson};
    var userId = ${userId};
</script>

<head></head>

<body>
    <a href = "/owner/shopList">owner_mode</a>
    <a href = "/logout">logout</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>
    <h3>Shop List</h3>
    <script open="true">
        if(shops.length == 0){
            document.write("가입된 매장이 없습니다.");
        }else{
            document.write("<table>" +
                "<tr bgcolor=white align=center>" +
                "<td> 매장 명</td>" +
                "<td> 쿠폰 보기 </td>" +
                "<td> 탈퇴 </td>" +
                "</tr>");
            for(var i=0 ; i<shops.length ; i++){
                var couponManagementUrl = "/customer/couponList/"+shops[i].id;
                var deleteUrl = "/owner/deleteShop/"+shops[i].id;
                var value = "<tr id=\"shop\" align=center>" +
                    "<td>"+(i+1)+"</td>" +
                    "<td>"+shops[i].name+"</td>" +
                    "<td><input type=\"button\" value = \"쿠폰 보기\" onClick=\"location.href=\'"+managementUrl+"\'\"></td>" +
                    "<td><input type=\"button\" value = \"탈퇴\" onClick=\"location.href=\'"+deleteUrl+"\'\"></td>" +
                    "</tr>";
                document.write(value);
            }
            document.write("</table>");
        }
    </script>
        <script>

            for(var i=0 ; i<shops.length ; i++){
                document.write("<div><a href=\"/customer/couponList/"+shops[i].id+"\">"+shops[i].name+"</a></div>")
            }

        </script>
    <%--    <div><a href="/couponList/shops[i].name">shops[i].name</a></div>--%>
</body>
</html>
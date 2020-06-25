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
</style>
<script>
    //var userId = ${userId};
    var shops = ${shopsJson};
</script>
<head></head>
<body>
<a href = "/customer/shopList">CUSTOMER MODE</a>
<a href = "/logout">LOGOUT</a>
    <hr>
    <h1>Welcome to "My coupon"</h1>
    <hr>
    <h3>Shop List</h3>
    <script open="true">


        if(shops.length == 0){
            document.write("등록된 매장이 없습니다.");
        }else{
            document.write("<table>" +
                "<tr bgcolor=white align=center>" +
                "<td> 번호</td>" +
                "<td> 매장명 </td>" +
                "<td> 매장주소</td>" +
                "<td> X좌표 </td>" +
                "<td> Y좌표 </td>" +
                "<td> 관리하기 </td>" +
                "<td> 수정하기 </td>" +
                "<td> 삭제하기 </td>" +
                "</tr>");
            for(var i=0 ; i<shops.length ; i++){
                var managementUrl = "/owner/shop/"+shops[i].id;
                var deleteUrl = "/owner/deleteShop/"+shops[i].id;
                    var value = "<tr id=\"shop\" align=center>" +
                        "<td>"+(i+1)+"</td>" +
                        "<td>"+shops[i].name+"</td>" +
                        "<td>"+shops[i].address+"</td>" +
                        "<td>"+shops[i].locateX+"</td>" +
                        "<td>"+shops[i].locateY+"</td>" +
                        "<td><input type=\"button\" value = \"관리하기\" onClick=\"location.href=\'"+managementUrl+"\'\"></td>" +
                        "<td><input type=\"button\" value = \"수정하기\" onClick= openModifPage()></td>" +
                        "<td><input type=\"button\" value = \"삭제하기\" onClick=\"location.href=\'"+deleteUrl+"\'\"></td>" +
                        "</tr>";
                    document.write(value);
            }
            document.write("</table>");
        }
    </script>
    <script>

        // if(shops.length == 0){
        //     document.write("등록된 매장이 없습니다.");
        // }
        // for(var i=0 ; i<shops.length ; i++){
        //     var value = shops[i].name;
        //     var url = "\"/owner/shop/"+shops[i].id+"\""
        //     document.write("<div>매장"+(i+1)+": <a href=" + url + ">" + value + "</a></div>");
        // }
    </script>
    <details>
        <summary>Create Shop</summary>
        <form action="/owner/createShop" method="post" enctype="multipart/form-data">
            <div>
                <div>
                    <div><a>매장이름: </a><input type="text" name="name"/></div>
                    <div><a>매장주소: </a><input type="text" name="address"/></div>
                    <div><a>X좌표: </a><input type="text" name="locateX"/></div>
                    <div><a>Y좌표: </a><input type="text" name="locateY"/></div>
                </div>
                <div><input type="submit" value="생성하기"/></div>
            </div>
        </form>
    </details>

</body>
</html>
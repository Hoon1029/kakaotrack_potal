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
    var userId = ${userId};
    var shops = ${shopsJson};
</script>
<head></head>
<body>
    <a href = "/customer/shopList">customer</a>
    <a href = "/logout">logout</a>
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
                "<td> 매장 번호</td>" +
                "<td> 매장 명 </td>" +
                "<td> 매장 주소</td>" +
                "<td> X좌표 </td>" +
                "<td> Y좌표 </td>" +
                "<td> 관리 </td>" +
                "<td> 수정 </td>" +
                "<td> 삭제 </td>" +
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
                        "<td><input type=\"button\" value = \"관리\" onClick=\"location.href=\'"+managementUrl+"\'\"></td>" +
                        "<td><input type=\"button\" value = \"수정\" onClick= openModifPage()></td>" +
                        "<td><input type=\"button\" value = \"삭제\" onClick=\"location.href=\'"+deleteUrl+"\'\"></td>" +
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
        <summary>매장 추가하기</summary>
        <form action="/owner/createShop" method="post" enctype="multipart/form-data">
            <div>
                <div>
                    <div><a>매장 이름: </a><input type="text" name="name"/></div>
                    <div><a>매장 주소: </a><input type="text" name="address"/></div>
                    <div><a>X좌표: </a><input type="text" name="locateX"/></div>
                    <div><a>Y좌표: </a><input type="text" name="locateY"/></div>
                </div>
                <div><input type="submit" value="등록하기"/></div>
            </div>
        </form>
    </details>

</body>
</html>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<h1>
    File UPload!!!
</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
<script>
    document.write("<img src=\"${url}\"/>");
</script>
<img src="${url}"/>
</html>
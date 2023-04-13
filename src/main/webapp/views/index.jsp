<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/13/2023
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>Crud Employee</title>
</head>
<body>
<c:url var="url" value="/nhanvien"/>
<div class="container">
    <h2 class="text-center fw-bold">Quản lý nhân viên</h2>
    <div class="row">
        <form class="form-group" action="${url}/index" method="post">
            <div class="col">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" value="${entity.ma}" class="form-control" readonly>
            </div>
            <div class="col">
                <label class="form-label">Tên</label>
                <input type="text" name="fullname"value="${entity.fullname}" class="form-control">
            </div>
            <div class="col">
                <label class="form-label">Giới tính</label>
                <input type="radio" value="Nam"value="${entity.gioiTinh}" name="gioiTinh" class="form-check-input" checked>Nam
                <input type="radio" value="Nữ"value="${entity.gioiTinh}" name="gioiTinh" class="form-check-input">Nữ
            </div>
            <div class="col">
                <label class="form-label">Địa chỉ</label>
                <input type="text"value="${entity.diaChi}" name="diaChi" class="form-control">
            </div>
            <div class="col">
                <button formaction="${url}/add" class="float-start btn btn-success">Add</button>
            </div>
            <h7 class="text-danger float-end">${message}</h7>
        <table class="table table-bordered table-info">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Giới tính</th>
                    <th>Địa chỉ</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="i" items="${list}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${i.ma}</td>
                    <td>${i.fullname}</td>
                    <td>${i.gioiTinh}</td>
                    <td>${i.diaChi}</td>
                    <td>
                        <a href="${url}/detail/${i.id}" class="btn btn-primary">Detail</a>
                        <button formaction="${url}/delete" class="btn btn-danger">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </form>
    </div>
</div>

</body>
</html>

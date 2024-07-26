<%@page import="java.util.List"%>
<%@page import="Model.AdminBean"%>
<%@page import="Controller.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("login") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Items</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <nav class="navbar">
        <ul class="nav-list">
            <li class="nav-item active"><a href="manage-items.jsp">Manage Items</a></li>
            <li class="nav-item"><a href="add-items.jsp">Add Items</a></li>
        </ul>
        <div class="logout-container">
            <a class="logout-button" href="LogoutServlet">LOG OUT</a>
        </div>
    </nav>
    <div class="admin-container">
        <div class="main-content">
            <div class="content-header">
                <h2>Manage Items</h2>
            </div>
            <div class="content-body">
                <table class="item-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            AdminDAO dao = new AdminDAO();
                            List<AdminBean> items = dao.getAllItems();
                            
                            for (AdminBean item : items) {
                        %>
                        <tr>
                            <td><%= item.getId_barang() %></td>
                            <td><%= item.getNama_barang() %></td>
                            <td><%= item.getDesc_barang() %></td>
                            <td>Rp <%= item.getHarga_barang() %></td>
                            <td><%= item.getStok() %></td>
                            <td>
                                <a href="edit-items.jsp?id=<%= item.getId_barang() %>" class="edit">edit</a>
                                <a href="deleteAdminServlet?id=<%= item.getId_barang() %>" class="delete">delete</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

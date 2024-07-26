<%@ page import="Model.AdminBean" %>
<%@ page import="Controller.AdminDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Edit Item</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        #file-upload {
            display: none;
        }
        #file-container {
            margin-top: 20px;
        }
        #file-container img {
            max-width: 30%;
            height: auto;
            margin: 20px auto;
            display: block;
        }
        #file-name {
            text-align: center;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <ul class="nav-list">
            <li class="nav-item"><a href="manage-items.jsp">Manage Items</a></li>
            <li class="nav-item"><a href="add-items.jsp">Add Items</a></li>
        </ul>
        <div class="logout-container">
            <a class="logout-button" href="LogoutServlet">LOG OUT</a>
        </div>
    </nav>
    <div class="admin-container">
        <div class="main-content">
            <div class="content-header">
                <h2>Edit Item</h2>
            </div>
            <div class="content-body">
                <% 
                    // Mendapatkan ID barang dari parameter URL
                    int id = Integer.parseInt(request.getParameter("id"));
                    
                    // Memuat data barang berdasarkan ID
                    AdminDAO dao = new AdminDAO();
                    AdminBean item = dao.getItemById(id);
                %>
                <form action="updateAdminServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id_barang" value="<%= item.getId_barang() %>">
                    <input type="text" name="nama_barang" value="<%= item.getNama_barang() %>" required>
                    <input type="text" name="desc_barang" value="<%= item.getDesc_barang() %>" required>
                    <input type="text" name="harga_barang" value="<%= item.getHarga_barang() %>" required>
                    <input type="text" name="stok" value="<%= item.getStok() %>" required>
                    <button type="submit">Update</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
package Controller;

import Model.AdminBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
public class AddItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminBean wb = new AdminBean();
        
        // Mengatur properti AdminBean dari parameter request
        wb.setNama_barang(request.getParameter("nama_barang"));
        wb.setDesc_barang(request.getParameter("desc_barang"));
        wb.setHarga_barang(Integer.parseInt(request.getParameter("harga_barang")));
        wb.setStok(Integer.parseInt(request.getParameter("stok")));

        // Menyimpan objek AdminBean menggunakan AdminDAO
        int i = AdminDAO.simpan(wb);
        
        // Mengarahkan ke halaman yang sesuai berdasarkan hasil operasi simpan
        if (i > 0) {
            response.sendRedirect("addAdminSuccess.jsp");
        } else {
            response.sendRedirect("addAdminError.jsp");
        }
    }
}

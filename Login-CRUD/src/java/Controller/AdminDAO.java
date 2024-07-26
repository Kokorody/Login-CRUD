package Controller;

import Database.DBConnection;
import Model.AdminBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    
    // Metode untuk menyimpan barang baru ke tabel tbl_barang
    public static int simpan(AdminBean wb) {
        int status = 0;
        try {
            // Membuat koneksi ke datbase
            DBConnection db = new DBConnection();
            Connection conn = db.setConnection();
            // Query SQL untuk menyimpan data barang baru
            String sql = "INSERT INTO tbl_barang (nama_barang, desc_barang, harga_barang, stok) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            // Mengatur parameter dari objek AdminBean
            ps.setString(1, wb.getNama_barang());
            ps.setString(2, wb.getDesc_barang());
            ps.setInt(3, wb.getHarga_barang());
            ps.setInt(4, wb.getStok());
            // Menjalankan pernyataan SQL dan mendapatkan status
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }
    
    // Metode untuk mengambil semua barang dari tabel tbl_barang
    public static List<AdminBean> getAllItems() {
        List<AdminBean> itemList = new ArrayList<>();
        
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.setConnection();
            // Query SQL untuk mengambil semua data barang
            String sql = "SELECT * FROM tbl_barang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            // Mengambil setiap baris data dari hasil query
            while (rs.next()) {
                AdminBean item = new AdminBean();
                item.setId_barang(rs.getInt("id_barang"));
                item.setNama_barang(rs.getString("nama_barang"));
                item.setDesc_barang(rs.getString("desc_barang"));
                item.setHarga_barang(rs.getInt("harga_barang"));
                item.setStok(rs.getInt("stok"));
                itemList.add(item);
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return itemList;
    }
    
    // Metode untuk mengambil data barang berdasarkan ID
    public static AdminBean getItemById(int id) {
        AdminBean item = null;
        
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.setConnection();
            // Query SQL untuk mengambil data barang berdasarkan ID
            String sql = "SELECT * FROM tbl_barang WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            // Mengambil data dari hasil query jika ada
            if (rs.next()) {
                item = new AdminBean();
                item.setId_barang(rs.getInt("id_barang"));
                item.setNama_barang(rs.getString("nama_barang"));
                item.setDesc_barang(rs.getString("desc_barang"));
                item.setHarga_barang(rs.getInt("harga_barang"));
                item.setStok(rs.getInt("stok"));
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return item;
    }
    
    // Metode untuk meng-update data barang
    public static int updateItem(AdminBean wb) {
        int status = 0;
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.setConnection();
            // Query SQL untuk meng-update data barang
            String sql = "UPDATE tbl_barang SET nama_barang=?, desc_barang=?, harga_barang=?, stok=? WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            // Mengatur parameter dari objek AdminBean
            ps.setString(1, wb.getNama_barang());
            ps.setString(2, wb.getDesc_barang());
            ps.setInt(3, wb.getHarga_barang());
            ps.setInt(4, wb.getStok());
            ps.setInt(5, wb.getId_barang());
            // Menjalankan pernyataan SQL dan mendapatkan status
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }
    
    // Metode untuk menghapus barang berdasarkan ID
    public static int deleteItem(int id_barang) {
        int status = 0;
        
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.setConnection();
            // Query SQL untuk menghapus barang berdasarkan ID
            String sql = "DELETE FROM tbl_barang WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_barang);
            // Menjalankan pernyataan SQL dan mendapatkan status
            status = ps.executeUpdate();
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return status;
    }
    
}

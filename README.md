### Prerequisites

- Web browser (Chrome, Firefox, Safari, etc.)
- Web server with PHP support (e.g., Apache, Tomcat)
- SQL database (e.g., MySQL)

### Cloning

1. Clone the repository:

    ```bash
    git clone https://github.com/Kokorody/Login-CRUD.git
    ```

2. Navigate to the project directory:

    ```bash
    cd [Login-CRUD
    ```
## Flow

### Login
> Login.jsp
> LoginServlet.java

### Manage-item
> Manage-items.jsp
> AdminDAO.java
> AdminBean.java

### Edit-item
> Manage-items.jsp
> edit-items.jsp 
> AdminDAO.java (showing current data)
> edit-items.jsp
> UpdateAdminServlet.java
> AdminDAO.java

```Code Flow
 // menampilkan data sebelum update

            // edit-items.jsp >
            
                    // mengambil data (ID) dari databasa dan 
                    // mengubah datanya menjadi (int), id sebagai identifikasi suatu barang
                        int id = Integer.parseInt(request.getParameter("id"));
                                        
                        // Memuat data barang berdasarkan ID
                        // memanggil methode getitemby id(ngambil data barang berdasarkan 
                        // id barang dari sql) dari file controller (adminDAO)
                        // kemudian menyimpan data ke adminbean
                        AdminDAO dao = new AdminDAO();
                        AdminBean item = dao.getItemById(id);


            // adminDAO. java >

                    // Metode untuk mengambil data barang berdasarkan ID
                    public static AdminBean getItemById(int id) {
                        AdminBean item = null;
                        
                        try {
                            //koneksi ke database
                            DBConnection db = new DBConnection();
                            Connection conn = db.setConnection();

                            // Query SQL untuk mengambil data barang berdasarkan ID
                            String sql = "SELECT * FROM tbl_barang WHERE id_barang=?";
                            // menyiapkan pernyataan sql sebelum eksekusi, PreparedStatement 
                            // digunakan untuk menghindari SQL injection dan mengatur parameter 
                            // secara aman.
                            PreparedStatement ps = conn.prepareStatement(sql);

                            // Mengatur parameter pertama pada query SQL dengan 
                            // nilai id yang diberikan. ? dalam query SQL digantikan oleh nilai ini.
                            ps.setInt(1, id);

                            // menjalankan query lalu mendapatkan hasil
                            ResultSet rs = ps.executeQuery();
                            
                            // Mengambil data dari hasil query jika ada
                            // data ini akan ditampilkan di page edit
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
                            // jika salah, menampilkan kesalahan
                            System.out.println(e);
                        }
                        
                        return item;
                    }

        // melakukan update data 
                    
            // edit-items.jsp >       
                    
                    // saat update button di click, tombol akan mensubmit data dan 
                    // membuat file updateadminservlet  berjalan
                    <form action="updateAdminServlet" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id_barang" value="<%= item.getId_barang() %>">
                            <input type="text" name="nama_barang" value="<%= item.getNama_barang() %>" required>
                            <input type="text" name="desc_barang" value="<%= item.getDesc_barang() %>" required>
                            <input type="text" name="harga_barang" value="<%= item.getHarga_barang() %>" required>
                            <input type="text" name="stok" value="<%= item.getStok() %>" required>
                            <button type="submit">Update</button>
                        </form> 
                        
            // updateAdminServlet >      

                            // Ambil parameter dari form edit-items.jsp
                            // data yang diubah dari input user
                            int id_barang = Integer.parseInt(request.getParameter("id_barang"));
                            String nama_barang = request.getParameter("nama_barang");
                            String desc_barang = request.getParameter("desc_barang");
                            int harga_barang = Integer.parseInt(request.getParameter("harga_barang"));
                            int stok = Integer.parseInt(request.getParameter("stok"));
                    
                            // Update data barang, data baru akan masuk di adminbean
                            AdminBean item = new AdminBean();
                            item.setId_barang(id_barang);
                            item.setNama_barang(nama_barang);
                            item.setDesc_barang(desc_barang);
                            item.setHarga_barang(harga_barang);
                            item.setStok(stok);
                    
                            // Panggil AdminDAO untuk melakukan update
                            int status = AdminDAO.updateItem(item);
                    
                            if (status > 0) {
                                // Redirect kembali ke manage-items.jsp jika berhasil update
                                response.sendRedirect("manage-items.jsp");
                            } else {
                                // Handle jika terjadi error
                                response.getWriter().println("Failed to update item.");
                            }
                        }
                    
            
            // AdminDAO >
            
                    // Metode untuk meng-update data barang
                    public static int updateItem(AdminBean wb) {
                        int status = 0;
                        try {
                            // koneksi ke database
                            DBConnection db = new DBConnection();
                            Connection conn = db.setConnection();
                            
                            // Query SQL untuk meng-update data barang berdasarkan id 
                            String sql = "UPDATE tbl_barang SET nama_barang=?, desc_barang=?, harga_barang=?, stok=? WHERE id_barang=?";
                           
                            // menyiapkan pernyataan sql sebelum eksekusi, 
                            // PreparedStatement digunakan untuk menghindari SQL injection dan mengatur parameter secara aman.
                            PreparedStatement ps = conn.prepareStatement(sql);
                           
                            // Mengatur parameter dari objek AdminBean, 
                            // menggantikan ? ke data yang diberikan
                            ps.setString(1, wb.getNama_barang());
                            ps.setString(2, wb.getDesc_barang());
                            ps.setInt(3, wb.getHarga_barang());
                            ps.setInt(4, wb.getStok());
                            ps.setInt(5, wb.getId_barang());
                            // Menjalankan pernyataan SQL dan mendapatkan status
                            status = ps.executeUpdate();

                            conn.close();
                        } catch (SQLException e) {
                            // jika salah, menampilkan kesalahan
                            System.out.println(e);
                        }
                        return status;
                    }
``` 

### Delete-item
> Manage=items.jsp
> AdminDAO.java

```Code Flow
// manage-items > deleteservlet >

            // Mengambil parameter id dan mengonversinya 
            // menjadi integer untuk digunakan sebagai ID barang yang akan dihapus.
            int id_barang = Integer.parseInt(request.getParameter("id"));

            // Panggil AdminDAO untuk menghapus barang dari database
            int status = AdminDAO.deleteItem(id_barang);

            if (status > 0) {
                // Redirect kembali ke manage-items.jsp jika berhasil delete
                response.sendRedirect("manage-items.jsp");
            } else {
                // Handle jika terjadi error
                response.getWriter().println("Failed to delete item.");
            }
        }

        //adminDAO >
         // Metode untuk menghapus barang berdasarkan ID
            public static int deleteItem(int id_barang) {
                int status = 0;
                
                try {
                    // konek ke database
                    DBConnection db = new DBConnection();
                    Connection conn = db.setConnection();
                    // Menyiapkan query SQL untuk menghapus barang berdasarkan ID
                    String sql = "DELETE FROM tbl_barang WHERE id_barang=?";
                    // Menyiapkan pernyataan SQL untuk menghapus barang 
                    // berdasarkan ID dan mengatur parameter ID barang.
                    PreparedStatement ps = conn.prepareStatement(sql);
                    // Mengatur parameter ID barang dalam pernyataan SQL.
                    // Parameter ? dalam query SQL digantikan oleh nilai ini.
                    ps.setInt(1, id_barang);
                    // Menjalankan pernyataan SQL dan mendapatkan status
                    status = ps.executeUpdate();
                    
                    // Menutup koneksi database setelah operasi selesai.
                    conn.close();
                } catch (SQLException e) {
                    //jika salah, error msg
                    System.out.println(e);
                }
                
                return status;
            }
``` 
  
### Add-item    
> Manage.items.jsp
> add-items.jsp
> additemservlet.java
> adminDAO.java

```Code Flow
// add-items.jsp >

    // saat update button di click, tombol akan mensubmit data dan 
    // membuat file additemsservlet  berjalan
    <form action="AddItemServlet" method="post" enctype="multipart/form-data">
        //tempat input data baru/ kosong
        <input type="text" name="nama_barang" placeholder="Item Name" required>
        <input type="text" name="desc_barang" placeholder="Description" required>
        <input type="text" name="harga_barang" placeholder="Price" required>
        <input type="text" name="stok" placeholder="Stock" required>
        <button type="submit">Post</button>
    </form>
    </div>

    // additemservlet.java >

        public class AddItemServlet extends HttpServlet {

            protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                // Membuat objek AdminBean baru
                AdminBean wb = new AdminBean();
                
                // Mengatur properti AdminBean dari parameter request
                // Mengambil nilai parameter dari form dan mengaturnya ke dalam AdminBean

                wb.setNama_barang(request.getParameter("nama_barang"));
                wb.setDesc_barang(request.getParameter("desc_barang"));
                wb.setHarga_barang(Integer.parseInt(request.getParameter("harga_barang")));
                wb.setStok(Integer.parseInt(request.getParameter("stok")));
        
                // Menyimpan objek AdminBean menggunakan AdminDAO
                // lalu menjalankan method simpan di admindao
                int i = AdminDAO.simpan(wb);
                
                // Mengarahkan ke halaman yang sesuai berdasarkan hasil operasi simpan
                // Jika operasi simpan berhasil, arahkan ke halaman sukses
                // Jika operasi simpan gagal, arahkan ke halaman error

                if (i > 0) {    
                    response.sendRedirect("addAdminSuccess.jsp");
                } else {
                    response.sendRedirect("addAdminError.jsp");
                }
            }
        }

        // adminDAO >
        
            // Metode untuk menyimpan barang baru ke tabel tbl_barang
                public static int simpan(AdminBean wb) {
                    // Variabel untuk menyimpan status hasil operasi penyimpanan
                    // 0 = Gagal, 1 = Berhasil
                    int status = 0;
                    try {
                        // Membuat koneksi ke datbase
                        DBConnection db = new DBConnection();
                        Connection conn = db.setConnection();
                        // Query SQL untuk menyimpan data barang baru
                        String sql = "INSERT INTO tbl_barang (nama_barang, desc_barang, harga_barang, stok) VALUES (?,?,?,?)";
                        //menyiapkan pernyataan sql sebelum eksekusi
                        PreparedStatement ps = conn.prepareStatement(sql);
                        // Mengatur parameter dari objek AdminBean, ? dalam query SQL digantikan oleh nilai ini.
                        ps.setString(1, wb.getNama_barang());
                        ps.setString(2, wb.getDesc_barang());
                        ps.setInt(3, wb.getHarga_barang());
                        ps.setInt(4, wb.getStok());
                        // Menjalankan pernyataan SQL dan mendapatkan status
                        status = ps.executeUpdate();

                        // Menutup koneksi ke database dan mengembalikan status hasil operasi
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return status;
                }
``` 

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Data.Mode.Model_customer
import com.example.myapplication.Data.Model.Model_CTPhieuNhap
import com.example.myapplication.Data.Model.Model_Order
import com.example.myapplication.Data.Model.Model_OrderDetail
import com.example.myapplication.Data.Model.Model_Phieunhap
import com.example.myapplication.Data.Model.Model_account
import com.example.myapplication.Data.Model.Model_cart
import com.example.myapplication.Data.Model.Model_inventory
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.Data.Model.Model_producttype
import com.example.myapplication.Data.Model.Model_staff
import java.text.SimpleDateFormat
import java.util.Locale

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "6"


        //Bảng và cột login

        private const val TABLE_ACCOUNT = "Account"
        private const val idaccount = "account_id"
        private const val accname = "accname"
        private const val accemail = "accemail"
        private const val accsdt = "accsdt"
        private const val accpass = "accpass"

        //Baảng và cột sản phẩm
        private const val TABLE_PRODUCTS = "Products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_MASP = "masp"
        private const val COLUMN_TENSP = "tensp"
        private const val COLUMN_GIA = "gia"
        private const val COLUMN_MALOAI = "maloai"
        private const val COLUMN_DONVI = "donvi"
        private const val COLUMN_IMAGE = "image"

        // Bảng khách hàng và cột (chưa dùng)
        private const val TABLE_KH = "customer"
        private const val KH_ID = "idkh"
        private const val KH_TENKH = "tenkh"
        private const val KH_DIACHI = "diachikh"
        private const val KH_NGAYSINH = "ngaysinhkh"
        private const val KH_GIOITINH = "gioitinh"
        private const val KH_SODT = "sdtkh"
        private const val KH_GHICHU = "ghichu"
        private const val KH_KHOANGOAI = "account_id"

        // Bảng nhân viên và cột
        private const val TABLE_NHANVIEN = "nhanvien"
        private const val NV_ID = "idnv"
        private const val NV_TEN = "tennv"
        private const val NV_GIOITINH = "gtnv"
        private const val NV_NAMSINH = "nsnv"
        private const val NV_CHUCVU = "cvunv"
        private const val NV_PHONGBAN = "pbanvn"
        private const val NV_NGAYLAM = "ngaylam"
        private const val NV_DIACHI = "dcnv"
        private const val NV_SDT = "sdtnv"
        private const val NV_EMAIL = "emailnv"

        //Bảng loại sản phẩm
        private const val TABLE_LOAISANPHAM = "loaisp"
        private const val lsp_id = "idlsp"
        private const val tenlsp = "tenlsp"
        private const val imglsp = "imglsp"


        //Bảng cart
        private const val TABLE_CART = "cart"
        private const val cartid = "cartid"
        private const val quantity = "quantity"
        private const val total = "total"
        private const val product_id  = "product_id"
        private const val customer_id  = "customer_id"

        // Bảng đơn hàng
        private const val TABLE_ORDERS = "orders"
        private const val order_id = "order_id"
        private const val order_date = "order_date"
        private const val order_customer_id = "customer_id"
        private const val order_totalall = "totalall"

        // Bảng chi tiết đơn hàng
        private const val TABLE_ORDER_DETAILS = "order_details"
        private const val order_details_id = "order_details_id"
        private const val order_id_fk = "order_id"
        private const val product_id_fk = "product_id"
        private const val order_quantity = "quantity"
        private const val order_total = "total"
        // Bảng ton kho (inventory)
        private const val TABLE_INVENTORY = "inventory"
        private const val inventory_id = "inventory_id"
        private const val inventory_product_id = "product_id"
        private const val inventory_quantity = "quantity"

        private const val TABLE_PHIEU_NHAP = "import_receipts"
        private const val PN_ID = "id"
        private const val PN_MAPN = "mapn"
        private const val PN_NGAY_NHAP = "ngay_nhap"
        private const val PN_NSX = "nsx"
        private const val PN_THANH_TIEN = "thanh_tien"
        private const val PN_GHICHU = "ghichu"

        // Bảng chi tiết nhập và cột
        private const val TABLE_CHI_TIET_NHAP = "import_receipt_details"
        private const val CTN_ID = "id"
        private const val CTN_SO_LUONG = "so_luong"
        private const val CTN_DON_GIA = "don_gia"
        private const val CTN_THANH_TIEN = "thanh_tien"
        private const val CTN_MA_SP = "ma_sp"
        private const val CTN_PN_ID = "pn_id"

        //order status
        private const val TABLE_ORDER_STATUS = "order_status"
        private const val OS_id = "id"
        private const val OS_orderid = "order_id"
        private const val OS_status = "status"

        //order status
        private const val TABLE_PHIEUNHAP_STATUS = "phieunhap_status"
        private const val PS_id = "id"
        private const val PS_phieunhapid = "phieunhap_id"
        private const val PS_status = "status"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE $TABLE_PRODUCTS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_MASP TEXT,"
                + "$COLUMN_TENSP TEXT,"
                + "$COLUMN_GIA REAL,"
                + "$COLUMN_MALOAI TEXT,"
                + "$COLUMN_DONVI TEXT,"
                + "$COLUMN_IMAGE TEXT)")
        db?.execSQL(CREATE_PRODUCTS_TABLE)

        val CREATE_STAFF_TABLE = ("CREATE TABLE $TABLE_NHANVIEN ("
                + "$NV_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$NV_TEN TEXT,"
                + "$NV_DIACHI TEXT,"
                + "$NV_NAMSINH TEXT,"
                + "$NV_GIOITINH TEXT,"
                + "$NV_SDT TEXT,"
                + "$NV_EMAIL TEXT,"
                + "$NV_NGAYLAM TEXT,"
                + "$NV_PHONGBAN TEXT,"
                + "$NV_CHUCVU TEXT)")
        db?.execSQL(CREATE_STAFF_TABLE)

        val CREATE_LSP_TABLE = ("CREATE TABLE $TABLE_LOAISANPHAM ("
                + "$lsp_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$tenlsp TEXT,"
                + "$imglsp TEXT)")
        db?.execSQL(CREATE_LSP_TABLE)

        val CREATE_ACCOUNT_TABLE = ("CREATE TABLE $TABLE_ACCOUNT("
                +"$idaccount INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$accname TEXT,"
                + "$accemail TEXT,"
                + "$accsdt TEXT,"
                + "$accpass TEXT)")
        db?.execSQL(CREATE_ACCOUNT_TABLE)

        val CREATE_KH_TABLE = ("CREATE TABLE $TABLE_KH ("
                + "$KH_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$KH_TENKH TEXT, "
                + "$KH_DIACHI TEXT, "
                + "$KH_NGAYSINH TEXT, "
                + "$KH_GIOITINH TEXT, "
                + "$KH_SODT TEXT, "
                + "$KH_GHICHU TEXT, "
                + "$KH_KHOANGOAI INTEGER, "
                + "FOREIGN KEY ($KH_KHOANGOAI) REFERENCES $TABLE_ACCOUNT($idaccount))")
        db?.execSQL(CREATE_KH_TABLE)


        val CREATE_CART_TABLE = ("CREATE TABLE $TABLE_CART("
                + "$cartid INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  "$quantity INTEGER,"
                +  "$total double,"
                + "$product_id TEXT,"
                + "$customer_id INTEGER,"
                + "FOREIGN KEY ($product_id) REFERENCES $TABLE_PRODUCTS($COLUMN_MASP),"

                + "FOREIGN KEY ($customer_id) REFERENCES $TABLE_KH($KH_ID))")
        db?.execSQL(CREATE_CART_TABLE)


        val CREATE_ORDERS_TABLE = ("CREATE TABLE $TABLE_ORDERS ("
                + "$order_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$order_date TEXT,"
                + "$order_totalall double,"
                + "$order_customer_id INTEGER,"
                + "FOREIGN KEY ($order_customer_id) REFERENCES $TABLE_KH($KH_ID))")
        db?.execSQL(CREATE_ORDERS_TABLE)

        val CREATE_ORDER_DETAILS_TABLE = ("CREATE TABLE $TABLE_ORDER_DETAILS ("
                + "$order_details_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$order_id_fk INTEGER,"
                + "$product_id_fk INTEGER,"
                + "$order_quantity INTEGER,"
                + "$order_total double,"
                + "FOREIGN KEY ($order_id_fk) REFERENCES $TABLE_ORDERS($order_id),"
                + "FOREIGN KEY ($product_id_fk) REFERENCES $TABLE_PRODUCTS($COLUMN_ID))")
        db?.execSQL(CREATE_ORDER_DETAILS_TABLE)

        val CREATE_INVENTORY_TABLE = ("CREATE TABLE $TABLE_INVENTORY ("
                + "$inventory_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$inventory_product_id INTEGER,"
                + "$inventory_quantity INTEGER,"
                + "FOREIGN KEY ($inventory_product_id) REFERENCES $TABLE_PRODUCTS($COLUMN_ID))")
        db?.execSQL(CREATE_INVENTORY_TABLE)

        val CREATE_PHIEU_NHAP_TABLE = ("CREATE TABLE $TABLE_PHIEU_NHAP ("
                + "$PN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$PN_MAPN TEXT,"
                + "$PN_NGAY_NHAP TEXT,"
                + "$PN_NSX TEXT,"
                + "$PN_GHICHU TEXT,"
                + "$PN_THANH_TIEN REAL)")
        db?.execSQL(CREATE_PHIEU_NHAP_TABLE)

        val CREATE_CHI_TIET_NHAP_TABLE = ("CREATE TABLE $TABLE_CHI_TIET_NHAP ("
                + "$CTN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$CTN_SO_LUONG INTEGER,"
                + "$CTN_DON_GIA REAL,"
                + "$CTN_THANH_TIEN REAL,"
                + "$CTN_MA_SP TEXT,"
                + "$CTN_PN_ID INTEGER,"
                + "FOREIGN KEY ($CTN_PN_ID) REFERENCES $TABLE_PHIEU_NHAP($PN_MAPN),"
                + "FOREIGN KEY ($CTN_MA_SP) REFERENCES $TABLE_PRODUCTS($COLUMN_MASP))")
        db?.execSQL(CREATE_CHI_TIET_NHAP_TABLE)

        val CREATE_ORDER_STATUS_TABLE = ("CREATE TABLE order_status ("
                + " $OS_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$OS_orderid INTEGER,"
                + "$OS_status TEXT,"
                + "FOREIGN KEY ($OS_orderid) REFERENCES $TABLE_ORDERS($order_id))")
        db?.execSQL(CREATE_ORDER_STATUS_TABLE)

        // Create import receipt status table
        val CREATE_PHIEUNHAP_STATUS_TABLE = ("CREATE TABLE phieunhap_status ("
                + "$PS_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$PS_phieunhapid INTEGER,"
                + "$PS_status TEXT,"
                + "FOREIGN KEY (import_receipt_id) REFERENCES $TABLE_PHIEU_NHAP($PN_ID))")
        db?.execSQL(CREATE_PHIEUNHAP_STATUS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NHANVIEN")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_LOAISANPHAM ")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_KH")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_CART ")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_ACCOUNT ")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER_DETAILS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_INVENTORY")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PHIEU_NHAP")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CHI_TIET_NHAP")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER_STATUS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PHIEUNHAP_STATUS")
        onCreate(db)
    }

    fun tongchitheonam(nam: Int): Double{
        val db = this.readableDatabase
        var totalyear = 0.0

       val query = " SELECT SUM($PN_THANH_TIEN) FROM $TABLE_PHIEU_NHAP WHERE strftime('%Y', $PN_NGAY_NHAP) = ? "

        val cursor: Cursor = db.rawQuery(query, arrayOf(nam.toString()))
        if(cursor.moveToFirst()){
            totalyear =   cursor.getDouble(0)
        }
        return totalyear

    }

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    fun tongchitheoQuyNam(quy: Int, nam: Int): Double {
        val db = this.readableDatabase
        var totalAmount = 0.0

        // Xác định khoảng thời gian của từng quý
        val startMonth: String
        val endMonth: String

        when (quy) {
            1 -> {
                startMonth = "01"
                endMonth = "03"
            }
            2 -> {
                startMonth = "04"
                endMonth = "06"
            }
            3 -> {
                startMonth = "07"
                endMonth = "09"
            }
            4 -> {
                startMonth = "10"
                endMonth = "12"
            }
            else -> {
                throw IllegalArgumentException("Quý không hợp lệ")
            }
        }

        val query = """
        SELECT SUM($PN_THANH_TIEN)
        FROM $TABLE_PHIEU_NHAP
        WHERE strftime('%Y', $PN_NGAY_NHAP) = ? 
        AND strftime('%m', $PN_NGAY_NHAP) BETWEEN ? AND ?
    """
        val cursor: Cursor = db.rawQuery(query, arrayOf(nam.toString(), startMonth, endMonth))

        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }

        cursor.close()
        return totalAmount
    }

    fun tongchitheoThangNam(month: Int, year: Int): Double {
        val db = this.readableDatabase
        var totalAmount = 0.0

        val query = "SELECT SUM($PN_THANH_TIEN) FROM $TABLE_PHIEU_NHAP WHERE strftime('%Y', $PN_NGAY_NHAP) = ? AND strftime('%m', $PN_NGAY_NHAP) = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(year.toString(), String.format("%02d", month)))

        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }

        cursor.close()
        return totalAmount
    }
    fun tongchitheongay(startDate: String, endDate: String): Double {
        val db = this.readableDatabase
        var totalall = 0.0

        val query = "SELECT SUM($PN_THANH_TIEN)  FROM $TABLE_PHIEU_NHAP WHERE $PN_NGAY_NHAP BETWEEN ? AND ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(startDate, endDate))

        if (cursor.moveToFirst()) {
            val totalAmount = cursor.getDouble(0)

            totalall += totalAmount
        }

        cursor.close()


        return totalall
    }
    fun tongtonkho():Int{
        val query = "SELECT SUM($inventory_quantity) AS total_all FROM $TABLE_INVENTORY "
        val db = this.writableDatabase
        val  cursor = db.rawQuery(query, null)

        var total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getInt(cursor.getColumnIndexOrThrow("total_all"))
        }
        cursor.close()
        return total

    }
    fun tongsanpham():Int{
        val query = "SELECT COUNT($COLUMN_MASP) AS total_all FROM $TABLE_PRODUCTS "
        val db = this.writableDatabase
        val  cursor = db.rawQuery(query, null)

        var total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getInt(cursor.getColumnIndexOrThrow("total_all"))
        }
        cursor.close()
        return total

    }
    fun tongdoanhthu():Double{
        val query = "SELECT SUM($order_totalall) AS total_all FROM $TABLE_ORDERS "
        val db = this.writableDatabase
        val  cursor = db.rawQuery(query, null)

        var totalall = 0.0;
        if(cursor.moveToFirst()){
            val total = cursor.getDouble(cursor.getColumnIndexOrThrow("total_all"))
            totalall+=total;
        }
        cursor.close()
        return totalall

    }

    fun tongchi():Double{
        val query = "SELECT ($PN_THANH_TIEN) AS total_all FROM $TABLE_PHIEU_NHAP "
        val db = this.writableDatabase
        val  cursor = db.rawQuery(query, null)

        var totalall = 0.0;
        if(cursor.moveToFirst()){
            val total = cursor.getDouble(cursor.getColumnIndexOrThrow("total_all"))
            totalall+=total;
        }
        cursor.close()
        return totalall

    }

    fun addAccount(account: Model_account): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(accname, account.name)
            put(accemail, account.email)
            put(accsdt, account.phone)
            put(accpass, account.pass)
        }
        val accountId = db.insert(TABLE_ACCOUNT, null, values)

        return accountId
    }


    fun addNameCus(customer: Model_customer): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KH_TENKH, customer.namecus)
        values.put(KH_DIACHI, customer.addresscus)
        values.put(KH_NGAYSINH, customer.daycus)
        values.put(KH_GIOITINH, customer.gender)
        values.put(KH_SODT, customer.phonecus)
        values.put(KH_GHICHU, customer.notecus)
        values.put(KH_KHOANGOAI, customer.idaccount)

        val success = db.insert(TABLE_KH, null, values)
        return success
    }
    fun updateCustomerProfile(customer: Model_customer): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KH_TENKH, customer.namecus)
        values.put(KH_DIACHI, customer.addresscus)
        values.put(KH_NGAYSINH, customer.daycus)
        values.put(KH_GIOITINH, customer.gender)
        values.put(KH_SODT, customer.phonecus)
        values.put(KH_GHICHU, customer.notecus)

        // Thay đổi tham số trong mảng selectionArgs
        val affectedRows = db.update(TABLE_KH, values, "$KH_KHOANGOAI = ?", arrayOf(customer.idaccount.toString()))
        return affectedRows
    }


    fun addProduct(product: Model_product): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_MASP, product.masp)
        values.put(COLUMN_TENSP, product.tensp)
        values.put(COLUMN_GIA, product.gia)
        values.put(COLUMN_MALOAI, product.maloai)
        values.put(COLUMN_DONVI, product.donvi)
        values.put(COLUMN_IMAGE, product.img)

        // Inserting Row
        val success = db.insert(TABLE_PRODUCTS, null, values)

        return success
    }

    fun addInventory(invetory: Model_inventory): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(inventory_product_id, invetory.productId)
            put(inventory_quantity, invetory.quantity)
        }
        val result = db.insert(TABLE_INVENTORY, null, values)

        return result
    }

    fun addStaff(staff: Model_staff): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NV_TEN, staff.tennv)
        values.put(NV_GIOITINH, staff.gtnv)
        values.put(NV_NAMSINH, staff.nsnv)
        values.put(NV_CHUCVU, staff.cvunv)
        values.put(NV_PHONGBAN, staff.pbanvn)
        values.put(NV_NGAYLAM, staff.ngaylam)
        values.put(NV_DIACHI, staff.dcnv)
        values.put(NV_SDT, staff.sdtnv)
        values.put(NV_EMAIL, staff.emailnv)

        val success = db.insert(TABLE_NHANVIEN, null, values)
        return success
    }

    fun addLoaiSP(lsp: Model_producttype):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(tenlsp, lsp.tenlsp)
        values.put(imglsp, lsp.imglsp)

        val success = db.insert(TABLE_LOAISANPHAM, null, values)
        return success
    }

    fun addCustomer(customer: Model_customer): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KH_TENKH, customer.namecus)
        values.put(KH_DIACHI, customer.addresscus)
        values.put(KH_NGAYSINH, customer.daycus)
        values.put(KH_GIOITINH, customer.gender)
        values.put(KH_SODT, customer.phonecus)
        values.put(KH_GHICHU, customer.notecus)

        val success = db.insert(TABLE_KH, null, values)

        return success;
    }

    fun addCart(cart: Model_cart): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(total, cart.thanhtien)
        values.put(quantity, cart.quantity)
        values.put(product_id, cart.product_id)
        values.put(customer_id, cart.customer_id)
        val success = db.insert(TABLE_CART, null, values)

        return success
    }

    fun addPhieuNhap(phieunhap: Model_Phieunhap): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(PN_MAPN, phieunhap.mapn)
            put(PN_NGAY_NHAP, phieunhap.ngayNhap)
            put(PN_NSX, phieunhap.nsx)
            put(PN_GHICHU, phieunhap.ghichu)
            put(PN_THANH_TIEN, phieunhap.thanhTien)
        }
        return db.insert(TABLE_PHIEU_NHAP, null, values)
    }

    fun addChiTietNhap(ctphieunhap: Model_CTPhieuNhap): Long {
        val db = this.writableDatabase
        var total = 0.0;
        val values = ContentValues().apply {
            put(CTN_SO_LUONG, ctphieunhap.soLuong)
            put(CTN_DON_GIA, ctphieunhap.donGia)
            put(CTN_THANH_TIEN, ctphieunhap.thanhTien)
            put(CTN_MA_SP, ctphieunhap.maSp)
            put(CTN_PN_ID, ctphieunhap.pnId)
            total = total+ctphieunhap.thanhTien;
        }
        val insertresult =  db.insert(TABLE_CHI_TIET_NHAP, null, values)


        if (insertresult != -1L) {
            // Update the total amount in the import receipt
            val updateValues = ContentValues().apply {
                put(PN_THANH_TIEN, total)
            }
            val whereClause = "$PN_MAPN = ?"
            val whereArgs = arrayOf(ctphieunhap.pnId)
            db.update(TABLE_PHIEU_NHAP, updateValues, whereClause, whereArgs)
        }

        return  insertresult
    }

    @SuppressLint("Range")
    fun getAllPhieuNhap(): ArrayList<Model_Phieunhap> {
        val phieuNhapList = ArrayList<Model_Phieunhap>()
        val selectQuery = "SELECT * FROM $TABLE_PHIEU_NHAP"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val phieuNhap = Model_Phieunhap(
                    ngayNhap = cursor.getString(cursor.getColumnIndex(PN_NGAY_NHAP)),
                    nsx = cursor.getString(cursor.getColumnIndex(PN_NSX)),
                    thanhTien = cursor.getDouble(cursor.getColumnIndex(PN_THANH_TIEN)),
                    ghichu = cursor.getString(cursor.getColumnIndex(PN_GHICHU)),
                    mapn = cursor.getString(cursor.getColumnIndex(PN_MAPN)),
                )
                phieuNhapList.add(phieuNhap)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return phieuNhapList
    }
//
//    @SuppressLint("Range")
//    fun getAllChiTietNhap(pnId: Long): ArrayList<ChiTietNhap> {
//        val chiTietNhapList = ArrayList<ChiTietNhap>()
//        val selectQuery = "SELECT * FROM $TABLE_CHI_TIET_NHAP WHERE $CTN_PN_ID = ?"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(selectQuery, arrayOf(pnId.toString()))
//
//        if (cursor.moveToFirst()) {
//            do {
//                val chiTietNhap = ChiTietNhap(
//                    id = cursor.getLong(cursor.getColumnIndex(CTN_ID)),
//                    soLuong = cursor.getInt(cursor.getColumnIndex(CTN_SO_LUONG)),
//                    donGia = cursor.getDouble(cursor.getColumnIndex(CTN_DON_GIA)),
//                    thanhTien = cursor.getDouble(cursor.getColumnIndex(CTN_THANH_TIEN)),
//                    maSp = cursor.getString(cursor.getColumnIndex(CTN_MA_SP)),
//                    pnId = cursor.getLong(cursor.getColumnIndex(CTN_PN_ID))
//                )
//                chiTietNhapList.add(chiTietNhap)
//            } while (cursor.moveToNext())
//        }
//        cursor.close()
//        return chiTietNhapList
//    }



    fun addOrder(order: Model_Order): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(order_date, order.orderday)
        values.put(order_customer_id, order.customerid)

        val success = db.insert(TABLE_ORDERS, null, values)
        return success
    }

    fun addOrderDetails(orderdetail:  Model_OrderDetail): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(order_id_fk, orderdetail.orderid)
        values.put(product_id_fk, orderdetail.productid)
        values.put(order_quantity, orderdetail.quantity)

        val success = db.insert(TABLE_ORDER_DETAILS, null, values)
        return success
    }

//    fun checkout(cartItems: List<Pair<Long, Int>>, customerId: Long): Long {
//        val db = this.writableDatabase
//        db.beginTransaction()
//        var orderId: Long = -1
//
//        try {
//            // Create the order
//            val orderValues = ContentValues().apply {
//                put(order_date, System.currentTimeMillis().toString()) // or use a proper date format
//                put(order_customer_id, customerId)
//            }
//
//            orderId = db.insert(TABLE_ORDERS, null, orderValues)
//
//            if (orderId != -1L) {
//                // Create order details for each item in the cart
//                for ((productId, quantity) in cartItems) {
//                    val orderDetailValues = ContentValues().apply {
//                        put(order_id_fk, orderId)
//                        put(product_id_fk, productId)
//                        put(order_quantity, quantity)
//                    }
//                    db.insert(TABLE_ORDER_DETAILS, null, orderDetailValues)
//                }
//
//                // Clear the cart after successful order creation
//                val whereClause = "$customer_id = ?"
//                val whereArgs = arrayOf(customerId.toString())
//                db.delete(TABLE_CART, whereClause, whereArgs)
//
//                db.setTransactionSuccessful()
//            }
//        } finally {
//            db.endTransaction()
//        }
//        return orderId
//    }

    fun checkout(cartItems: List<Pair<Model_product, Int>>, customerId: Long): Long {
        // Mở kết nối đến cơ sở dữ liệu
        val db = this.writableDatabase
        // Bắt đầu giao dịch
        db.beginTransaction()
        // Khởi tạo orderId
        var orderId: Long = -1

        try {
            // Tạo đơn hàng
            val orderValues = ContentValues().apply {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val todayDate = dateFormat.format(System.currentTimeMillis())
                put(order_date, todayDate) // hoặc sử dụng định dạng ngày thích hợp
                put(order_customer_id, customerId)
            }

            // Thêm đơn hàng vào cơ sở dữ liệu và nhận orderId
            orderId = db.insert(TABLE_ORDERS, null, orderValues)

            // Nếu orderId khác -1 (không xảy ra lỗi)
            if (orderId != -1L) {
                var totalAll = 0.0
                // Tạo chi tiết đơn hàng cho từng sản phẩm trong giỏ hàng
                for ((product, quantity) in cartItems) {
                    val productId = getIdProduct(product.masp) // Lấy ID của sản phẩm từ mã sản phẩm
                    val total = product.gia * quantity
                    totalAll += total
                    val orderDetailValues = ContentValues().apply {
                        put(order_id_fk, orderId)
                        put(product_id_fk, productId)
                        put(order_quantity, quantity)
                        put(order_total, total)

                    }
                    // Thêm chi tiết đơn hàng vào cơ sở dữ liệu
                    db.insert(TABLE_ORDER_DETAILS, null, orderDetailValues)
                }

                val orderUpdateValues = ContentValues().apply {
                    put(order_totalall, totalAll)
                }
                val whereClause = "$order_id = ?"
                val whereArgs = arrayOf(orderId.toString())
                db.update(TABLE_ORDERS, orderUpdateValues, whereClause, whereArgs)

                // Clear the cart after successful order creation
                val cartDeleteWhereClause = "$customer_id = ?"
                val cartDeleteWhereArgs = arrayOf(customerId.toString())
                db.delete(TABLE_CART, cartDeleteWhereClause, cartDeleteWhereArgs)

                // Đặt giao dịch thành công
                db.setTransactionSuccessful()
            }
        } finally {
            // Kết thúc giao dịch
            db.endTransaction()
        }
        // Trả về orderId
        return orderId
    }




    @SuppressLint("Range")
    fun getAllproduct(): ArrayList<Model_product> {
        val productList: ArrayList<Model_product> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTS "
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val product = Model_product(
                    masp = cursor.getString(cursor.getColumnIndex(COLUMN_MASP)),
                    tensp = cursor.getString(cursor.getColumnIndex(COLUMN_TENSP)),
                    gia = cursor.getDouble(cursor.getColumnIndex(COLUMN_GIA)),
                    maloai = cursor.getString(cursor.getColumnIndex(COLUMN_MALOAI)),
                    donvi = cursor.getString(cursor.getColumnIndex(COLUMN_DONVI)),
                    img = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
                )
                productList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }
    @SuppressLint("Range")
    fun getAllProductType(): ArrayList<Model_producttype>{
        val producttypeList: ArrayList<Model_producttype> = ArrayList()
        val selectQuery = "SELECT *FROM $TABLE_LOAISANPHAM"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            do {
                val producttype = Model_producttype(
                    tenlsp = cursor.getString(cursor.getColumnIndex(tenlsp)),
                    imglsp = cursor.getString(cursor.getColumnIndex(imglsp))
                )
                producttypeList.add(producttype)
            }while (cursor.moveToNext())
        }
        cursor.close()

        return producttypeList
    }
    @SuppressLint("Range")
    fun getAllCustomer(): ArrayList<Model_customer>{
        val customerList: ArrayList<Model_customer> = ArrayList()
        val selecQuery  ="SELECT *FROM $TABLE_KH"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selecQuery, null)
        if (cursor.moveToNext()){
            do {
                val customer = Model_customer(
                    namecus = cursor.getString(cursor.getColumnIndex(KH_TENKH)),
                    addresscus = cursor.getString(cursor.getColumnIndex(KH_DIACHI)),
                    daycus = cursor.getString(cursor.getColumnIndex(KH_NGAYSINH)),
                    gender = cursor.getString(cursor.getColumnIndex(KH_GIOITINH)),
                    phonecus = cursor.getString(cursor.getColumnIndex(KH_GIOITINH)),
                    notecus = cursor.getString(cursor.getColumnIndex(KH_GHICHU)),
                    idaccount = cursor.getLong(cursor.getColumnIndex(KH_ID)),
                )
                customerList.add(customer)
            }while (cursor.moveToNext())

        }
        cursor.close()

        return customerList
    }

    @SuppressLint("Range")
    fun getAllStaff() : ArrayList<Model_staff>{
        val staffList: ArrayList<Model_staff> = ArrayList()
        val selectQuery = "SELECT *FROM $TABLE_NHANVIEN"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            do {
                val staff = Model_staff(
                    tennv = cursor.getString(cursor.getColumnIndex(NV_TEN)),
                    dcnv = cursor.getString(cursor.getColumnIndex(NV_DIACHI)),
                    emailnv = cursor.getString(cursor.getColumnIndex(NV_EMAIL)),
                    nsnv = cursor.getString(cursor.getColumnIndex(NV_NAMSINH)),
                    gtnv = cursor.getString(cursor.getColumnIndex(NV_GIOITINH)),
                    sdtnv = cursor.getString(cursor.getColumnIndex(NV_SDT)),
                    ngaylam = cursor.getString(cursor.getColumnIndex(NV_NGAYLAM)),
                    pbanvn = cursor.getString(cursor.getColumnIndex(NV_PHONGBAN)),
                    cvunv = cursor.getString(cursor.getColumnIndex(NV_CHUCVU)),
                )
                staffList.add(staff)
            }while (cursor.moveToNext())
        }
        cursor.close()

        return staffList
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_ACCOUNT WHERE $accemail = ? AND $accpass = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }
    fun getAccountIdByEmail(email: String): Long {
        val db = this.readableDatabase
        val query = "SELECT $idaccount FROM $TABLE_ACCOUNT WHERE $accemail = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        var accountId: Long = -1
        if (cursor.moveToFirst()) {
            accountId = cursor.getLong(cursor.getColumnIndexOrThrow(idaccount))
        }
        cursor.close()

        return accountId
    }

    fun getIDKH(accountId: Long): Long{
        val db = this.readableDatabase
        val query = "SELECT $KH_ID FROM $TABLE_KH WHERE $KH_KHOANGOAI = ? "
        val cursor = db.rawQuery(query, arrayOf(accountId.toString()))
        var idkh : Long = -1
        if(cursor.moveToFirst()){
            idkh = cursor.getLong(cursor.getColumnIndexOrThrow(KH_ID))
        }
        cursor.close()

        return idkh
    }

    fun getIdProduct(masp: String):Long{
        val db = this.writableDatabase
        val query = "SELECT $COLUMN_ID FROM $TABLE_PRODUCTS WHERE $COLUMN_MASP = ?"
        val cursor = db.rawQuery(query, arrayOf(masp))
        var idsp : Long= -1
        if(cursor.moveToFirst()){
            idsp = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
        }
        cursor.close()

        return  idsp
    }

    @SuppressLint("Range")
    fun getCartWithQuantity(customerId: Long): List<Pair<Model_product, Int>> {
        val cartProducts: ArrayList<Pair<Model_product, Int>> = ArrayList()
        val query = """
        SELECT $TABLE_PRODUCTS.$COLUMN_MASP, $TABLE_PRODUCTS.$COLUMN_TENSP, 
               $TABLE_PRODUCTS.$COLUMN_GIA, $TABLE_PRODUCTS.$COLUMN_MALOAI, 
               $TABLE_PRODUCTS.$COLUMN_DONVI, $TABLE_PRODUCTS.$COLUMN_IMAGE,
               $TABLE_CART.$quantity
        FROM $TABLE_PRODUCTS
        INNER JOIN $TABLE_CART ON $TABLE_PRODUCTS.$COLUMN_ID = $TABLE_CART.$product_id
        WHERE $TABLE_CART.$customer_id = ?
    """
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, arrayOf(customerId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val product = Model_product(
                    masp = cursor.getString(cursor.getColumnIndex(COLUMN_MASP)),
                    tensp = cursor.getString(cursor.getColumnIndex(COLUMN_TENSP)),
                    gia = cursor.getDouble(cursor.getColumnIndex(COLUMN_GIA)),
                    maloai = cursor.getString(cursor.getColumnIndex(COLUMN_MALOAI)),
                    donvi = cursor.getString(cursor.getColumnIndex(COLUMN_DONVI)),
                    img = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
                )
                val quantity = cursor.getInt(cursor.getColumnIndex(quantity))
                cartProducts.add(product to quantity)
            } while (cursor.moveToNext())
        }
        cursor.close()

        return cartProducts
    }

}

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Data.Mode.Model_customer
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.Data.Model.Model_producttype
import com.example.myapplication.Data.Model.Model_staff

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "p8"
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

        val CREATE_KH_TABLE = ("CREATE TABLE $TABLE_KH("
                + "$KH_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$KH_TENKH TEXT,"
                + "$KH_DIACHI TEXT,"
                + "$KH_NGAYSINH TEXT,"
                + "$KH_GIOITINH TEXT,"
                + "$KH_SODT TEXT,"
                + "$KH_GHICHU TEXT)")
        db?.execSQL(CREATE_KH_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NHANVIEN")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_LOAISANPHAM ")
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_KH")
        onCreate(db)
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
//        db.close() // Closing database connection
        return success
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
        db.close()
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
        db.close()
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
                    notecus = cursor.getString(cursor.getColumnIndex(KH_GHICHU))
                )
                customerList.add(customer)
            }while (cursor.moveToNext())

        }
        cursor.close()
        db.close()
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
        db.close()
        return staffList
    }
}

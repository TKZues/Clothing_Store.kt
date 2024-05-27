import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "p2"
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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NHANVIEN")
        onCreate(db)
    }

    fun addProduct(masp: String, tensp: String, gia: Double, maloai: String, donvi: String, image: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_MASP, masp)
        values.put(COLUMN_TENSP, tensp)
        values.put(COLUMN_GIA, gia)
        values.put(COLUMN_MALOAI, maloai)
        values.put(COLUMN_DONVI, donvi)
        values.put(COLUMN_IMAGE, image)

        // Inserting Row
        val success = db.insert(TABLE_PRODUCTS, null, values)
//        db.close() // Closing database connection
        return success
    }

    fun addStaff(tennv: String, gtnv: String, nsnv: String, cvunv: String, pbanvn: String, ngaylam: String, dcnv: String, sdtnv: String, emailnv: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NV_TEN, tennv)
        values.put(NV_GIOITINH, gtnv)
        values.put(NV_NAMSINH, nsnv)
        values.put(NV_CHUCVU, cvunv)
        values.put(NV_PHONGBAN, pbanvn)
        values.put(NV_NGAYLAM, ngaylam)
        values.put(NV_DIACHI, dcnv)
        values.put(NV_SDT, sdtnv)
        values.put(NV_EMAIL, emailnv)

        val success = db.insert(TABLE_NHANVIEN, null, values)
        return success
    }
}

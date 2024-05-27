package com.example.myapplication.Data.Model

public class Model_staff {
    private var idnv: Long = 0
    private var tennv: String? = null
    private var gtnv: String? = null
    private var nsnv: String? = null
    private var cvunv: String? = null
    private var pbanvn: String? = null
    private var ngaylam: String? = null
    private var dcnv: String? = null
    private var sdtnv: String? = null
    private var emailnv: String? = null

    fun Model_staff() {}

    fun Model_staff(
        tennv: String?,
        gtnv: String?,
        nsnv: String?,
        cvunv: String?,
        pbanvn: String?,
        ngaylam: String?,
        dcnv: String?,
        sdtnv: String?,
        emailnv: String?
    ) {
        this.tennv = tennv
        this.gtnv = gtnv
        this.nsnv = nsnv
        this.cvunv = cvunv
        this.pbanvn = pbanvn
        this.ngaylam = ngaylam
        this.dcnv = dcnv
        this.sdtnv = sdtnv
        this.emailnv = emailnv
    }

    fun getIdnv(): Long {
        return idnv
    }

    fun setIdnv(idnv: Int) {
        this.idnv = idnv.toLong()
    }

    fun getTennv(): String {
        return tennv!!
    }

    fun setTennv(tennv: String?) {
        this.tennv = tennv
    }

    fun getGtnv(): String {
        return gtnv!!
    }

    fun setGtnv(gtnv: String?) {
        this.gtnv = gtnv
    }

    fun getNsnv(): String {
        return nsnv!!
    }

    fun setNsnv(nsnv: String?) {
        this.nsnv = nsnv
    }

    fun getCvunv(): String {
        return cvunv!!
    }

    fun setCvunv(cvunv: String?) {
        this.cvunv = cvunv
    }

    fun getPbanvn(): String {
        return pbanvn!!
    }

    fun setPbanvn(pbanvn: String?) {
        this.pbanvn = pbanvn
    }

    fun getNgaylam(): String {
        return ngaylam!!
    }

    fun setNgaylam(ngaylam: String?) {
        this.ngaylam = ngaylam
    }

    fun getDcnv(): String {
        return dcnv!!
    }

    fun setDcnv(dcnv: String?) {
        this.dcnv = dcnv
    }

    fun getSdtnv(): String {
        return sdtnv!!
    }

    fun setSdtnv(sdtnv: String?) {
        this.sdtnv = sdtnv
    }

    fun getEmailnv(): String {
        return emailnv!!
    }

    fun setEmailnv(emailnv: String?) {
        this.emailnv = emailnv
    }
}
package com.example.myapplication.Data.Model

class Model_customer {
    private var id_kh: Long = 0
    private var namecus: String? = null
    private var addresscus: String? = null
    private var daycus: String? = null
    private var gender: String? = null
    private var phonecus: String? = null
    private var notecus: String? = null

    fun Model_customer() {}

    fun Model_customer(
        namecus: String?,
        addresscus: String?,
        daycus: String?,
        gender: String?,
        phonecus: String?,
        notecus: String?
    ) {
        this.namecus = namecus
        this.addresscus = addresscus
        this.daycus = daycus
        this.gender = gender
        this.phonecus = phonecus
        this.notecus = notecus
    }

    fun getId_kh(): Long {
        return id_kh
    }

    fun setId_kh(id_kh: Int) {
        this.id_kh = id_kh.toLong()
    }

    fun getNamecus(): String? {
        return namecus
    }

    fun setNamecus(namecus: String?) {
        this.namecus = namecus
    }

    fun getAddresscus(): String? {
        return addresscus
    }

    fun setAddresscus(addresscus: String?) {
        this.addresscus = addresscus
    }

    fun getDaycus(): String? {
        return daycus
    }

    fun setDaycus(daycus: String?) {
        this.daycus = daycus
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String?) {
        this.gender = gender
    }

    fun getPhonecus(): String? {
        return phonecus
    }

    fun setPhonecus(phonecus: String?) {
        this.phonecus = phonecus
    }

    fun getNotecus(): String? {
        return notecus
    }

    fun setNotecus(notecus: String?) {
        this.notecus = notecus
    }
}
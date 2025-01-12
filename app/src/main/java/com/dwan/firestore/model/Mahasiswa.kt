package com.dwan.firestore.model

data class Mahasiswa( // Pertama yg dibuat dari semuanya
    val nim: String,
    val nama: String,
    val alamat: String,
    val gender: String,
    val kelas: String,
    val angkatan: String
) {
    constructor() : this("", "", "", "", "", "") // Harus dibangun dengan konstruktor
}

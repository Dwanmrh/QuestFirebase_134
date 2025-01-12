package com.dwan.firestore.ui.navigation

interface DestinasiNavigasi {
    val route: String // String untuk identifikasi rute navigasi
    val titleRes: String // String untuk judul halaman/screen
}

object DestinasiHome: DestinasiNavigasi {
    override val route: String = "home" // Rute untuk halaman home
    override val titleRes: String = "Home" // Judul untuk halaman home
}

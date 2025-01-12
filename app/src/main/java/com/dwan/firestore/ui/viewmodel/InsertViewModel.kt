package com.dwan.firestore.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dwan.firestore.model.Mahasiswa
import com.dwan.firestore.repository.RepositoryMhs

class InsertViewModel(
    private val mhs: RepositoryMhs
) : ViewModel() {
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    // State untuk menyimpan status form (Idle/Loading/Success/Error)
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }
}
// Data class untuk menyimpan state keseluruhan form input mahasiswa
data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),  // Menyimpan data input form saat ini
    val isEntryValid: FormErrorState = FormErrorState(),  // Menyimpan status validasi form (error atau valid)
)

// Data class untuk menyimpan pesan error pada setiap field form
data class FormErrorState(
    val nim: String? = null, // Pesan error untuk field NIM
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    // Fungsi untuk mengecek apakah form valid (tidak ada error)
    fun isValid(): Boolean {
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

// Data class Variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

// Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    gender = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)
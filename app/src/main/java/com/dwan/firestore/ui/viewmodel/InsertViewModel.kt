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

    // Validasi data input pengguna
    fun validateFields(): Boolean {
        val event = uiEvent.insertUiEvent // Mengambil data event dari UI

        // Membuat object FormErrorState yang berisi pesan error untuk setiap field
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState) // Update status validasi pada UI event
        return errorState.isValid() // Mengembalikan hasil validasi
    }

}
// Sealed class untuk menentukan status/keadaan form
sealed class FormState {
    object Idle : FormState() // Status awal (form belum disubmit)
    object Loading : FormState() // Status loading (sedang memproses)
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
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
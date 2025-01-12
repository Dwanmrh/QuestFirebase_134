package com.dwan.firestore.ui.viewmodel

import androidx.lifecycle.ViewModel
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
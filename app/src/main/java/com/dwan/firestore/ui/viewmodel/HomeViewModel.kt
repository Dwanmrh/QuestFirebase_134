package com.dwan.firestore.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwan.firestore.model.Mahasiswa
import com.dwan.firestore.repository.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel( // ke-9 dibuat
    private val repoMhs: RepositoryMhs
) : ViewModel() {

    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            repoMhs.getAllMhs()
                .onStart {
                mhsUIState = HomeUiState.Loading
            }
                .catch {
                    mhsUIState = HomeUiState.Error(e = it) // it = list mahasiswa
                }
                .collect {
                    mhsUIState = if (it.isEmpty()) {
                        HomeUiState.Error(Exception("Belum ada data mahasiswa"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
            }
        }
    }


sealed class HomeUiState{ // Kedelapan dibuat, 6&7 di manifest
    object Loading: HomeUiState() // Hanya mengubah state tidak ada data jadi pake object
    // Sukses
    data class Success(val data: List<Mahasiswa>) : HomeUiState()
    // Error
    data class Error(val e: Throwable) : HomeUiState()
}
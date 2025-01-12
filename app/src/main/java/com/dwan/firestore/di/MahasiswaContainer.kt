package com.dwan.firestore.di

import android.content.Context
import com.dwan.firestore.repository.NetworkRepository
import com.dwan.firestore.repository.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface InterfaceContainerApp { // Keempat dibuat
    val repositoryMhs: RepositoryMhs // Mengelola data mahasiswa
}

class MahasiswaContainer(private val context: Context) : InterfaceContainerApp {
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance() // Base function yg harus digunakan setara base url
    override val repositoryMhs: RepositoryMhs by lazy {
     NetworkRepository(firestore) // Memanggil firestore
    }
}
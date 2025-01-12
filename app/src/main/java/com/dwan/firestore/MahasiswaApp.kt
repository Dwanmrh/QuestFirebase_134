package com.dwan.firestore

import android.app.Application
import com.dwan.firestore.di.MahasiswaContainer

class MahasiswaApp : Application() { // Kelima dibuat

    // Fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: MahasiswaContainer

    override fun onCreate() {
        super.onCreate()
        // Membuat instance ContainerApp
        containerApp = MahasiswaContainer(this)
        // instance adalah object yang dibuat dari class
    }
}
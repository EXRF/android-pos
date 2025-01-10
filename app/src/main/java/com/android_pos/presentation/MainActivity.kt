package com.android_pos.presentation

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.android_pos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            val readPermission = android.Manifest.permission.READ_EXTERNAL_STORAGE
            val cameraPermission = android.Manifest.permission.CAMERA

            val permissionsToRequest = mutableListOf<String>()

            // CAMERA iznini kontrol et
            if (checkSelfPermission(cameraPermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(cameraPermission)
            }

            // WRITE_EXTERNAL_STORAGE iznini kontrol et
            if (checkSelfPermission(writePermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(writePermission)
            }

            // READ_EXTERNAL_STORAGE iznini kontrol et
            if (checkSelfPermission(readPermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(readPermission)
            }

            // İzinleri talep et
            if (permissionsToRequest.isNotEmpty()) {
                val permissionArray = permissionsToRequest.toTypedArray()
                requestPermissions(permissionArray, 1)
            }
        }

    }
}
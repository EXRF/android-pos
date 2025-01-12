package com.android_pos.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.android_pos.R
import com.android_pos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

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

            if (checkSelfPermission(cameraPermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(cameraPermission)
            }

            if (checkSelfPermission(writePermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(writePermission)
            }

            if (checkSelfPermission(readPermission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(readPermission)
            }

            if (permissionsToRequest.isNotEmpty()) {
                val permissionArray = permissionsToRequest.toTypedArray()
                requestPermissions(permissionArray, 1)
            }
        }

        initNavigation()
    }

    private fun initNavigation() {
        // handle user if already login

        var screen: Class<*> = AuthActivity::class.java

        Handler(Looper.getMainLooper()).postDelayed({
            navigateAndClear(screen)
        }, ONE_SECOND)
    }

    private fun navigateAndClear(clazz: Class<*>) {
        val intent = Intent(this, clazz).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        startActivity(intent)
    }

    companion object {
        private const val ONE_SECOND = 2 * 1000L
    }
}
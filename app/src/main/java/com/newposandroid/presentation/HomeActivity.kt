package com.newposandroid.presentation

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.newposandroid.R
import com.newposandroid.databinding.ActivityHomeBinding
import com.newposandroid.domain.model.Product
import com.newposandroid.presentation.main.home.HomeFragment
import com.newposandroid.presentation.main.home.HomeViewModel
import com.newposandroid.presentation.main.order.OrderFragment
import com.newposandroid.presentation.main.profile.ProfileFragment
import com.newposandroid.presentation.main.report.ReportFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    private lateinit var viewModel: HomeViewModel

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getParcelableArrayListExtra<Product>("PRODUCT").let { products ->
                viewModel.addProduct(products ?: mutableListOf())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

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

        disableDarkTheme()

        openMainFragment()
        supportActionBar?.hide()

        binding.btnDraw.ivIcon.setImageResource(R.drawable.ic_draw)
        binding.tvShopName.text = "Abdul Shop"

        binding.bottomNavigation.setItemSelected(R.id.home_menu, true)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it) {
                R.id.home_menu -> {
                    openMainFragment()
                }

                R.id.report_menu -> {
                    val reportFragment = ReportFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_container_nav, reportFragment).commit()

                    // set settings icons
                    binding.btnSettings.ivIcon.setImageResource(R.drawable.ic_settings)
                }

                R.id.orders_menu -> {
                    val orderFragment = OrderFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_container_nav, orderFragment).commit()

                    // set settings icons
                    binding.btnSettings.ivIcon.setImageResource(R.drawable.ic_settings)
                }

                R.id.profile_menu -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_container_nav, profileFragment).commit()

                    // set settings icons
                    binding.btnSettings.ivIcon.setImageResource(R.drawable.ic_settings)
                }
            }
        }
    }

    private fun openMainFragment() {
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frag_container_nav, fragment)
        transaction.commit()

        // set settings icon into add product icons
        binding.btnSettings.ivIcon.setImageResource(R.drawable.ic_add)

        // navigate to add product activity
        binding.btnSettings.ivIcon.setOnClickListener {
            resultLauncher.launch(Intent(applicationContext, AddProductActivity::class.java))
        }
    }

    private fun disableDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
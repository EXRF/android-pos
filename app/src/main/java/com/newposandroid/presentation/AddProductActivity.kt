package com.newposandroid.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.newposandroid.R
import com.newposandroid.databinding.ActivityAddProductBinding
import com.newposandroid.domain.model.Product
import com.newposandroid.presentation.main.add_product.form.FormProductFragment
import com.newposandroid.presentation.main.add_product.photo.PhotoProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddProductBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        disableDarkTheme()
        renderContent()

        // initially load FormProductFragment
        loadFragment(FormProductFragment())

        // handle next button
        binding.btnNext.btn.setOnClickListener {
            if (getCurrentFragment() is FormProductFragment) {
                loadFragment(PhotoProductFragment(), true)
                setProgressBar(100)
            } else {
                val intent = Intent().putParcelableArrayListExtra("PRODUCT",
                    ArrayList(mutableListOf(
                        Product(
                            name = "Becak Kayu",
                            desc = "Becak kayu lurr",
                            image = "https://images.squarespace-cdn.com/content/v1/5fbad9a57c058206994f27d7/1628332328915-7U7L7FFTV2YNR2JK2LBQ/01.jpg?format=2500w",
                            price = 1f,
                        )
                    ))
                )
                setResult(RESULT_OK, intent)
                // finish the activity
                finish()
            }
        }

        // handle back button
        binding.btnBack.ivIcon.setOnClickListener {
            when (getCurrentFragment()) {
                is PhotoProductFragment -> {
                    // Navigate back to FormProductFragment
                    supportFragmentManager.popBackStack()
                    setProgressBar(50)
                }

                else -> {
                    // close the activity
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        when (getCurrentFragment()) {
            is PhotoProductFragment -> {
                // back to FormProductFragment
                supportFragmentManager.popBackStack()
            }

            else -> super.onBackPressed()
        }
    }

    // Function to load a fragment
    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container_product, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null) // Add to back stack
        }

        transaction.commit()
    }

    // Function to get the current fragment
    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.frag_container_product)
    }

    private fun renderContent() {
        binding.btnBack.ivIcon.setImageResource(R.drawable.ic_arrow_back)
        binding.btnNext.tvTitle.text = "Selanjutnya"
        binding.progressBar.max = 100
        binding.progressBar.min = 0

        setProgressBar(50)
    }

    private fun setProgressBar(value: Int) {
        binding.progressBar.setProgress(value, true)
    }

    private fun disableDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
package com.example.simpleuserprofilefetcher



import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.simpleuserprofilefetcher.databinding.ActivityMainBinding // Update with your actual package name

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.fetchUserProfile()

        userViewModel.user.observe(this, Observer { user ->
            binding.userName.text = user.name
            binding.userUsername.text = user.username
            binding.userEmail.text = user.email
            binding.userPhone.text = user.phone
            binding.userAddress.text = "${user.address.street}, ${user.address.city}"
            binding.userWebsite.text = user.website
            binding.userCompany.text = user.company.name
        })

        userViewModel.error.observe(this, Observer { error ->
            binding.errorMessage.text = error
            binding.errorMessage.visibility = android.view.View.VISIBLE
        })
    }
}
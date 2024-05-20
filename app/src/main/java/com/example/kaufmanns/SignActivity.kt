package com.example.kaufmanns

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kaufmanns.databinding.ActivitySignBinding
import com.example.kaufmanns.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var database: DatabaseReference
    private val TAG = "SignActivity"
    private val binding: ActivitySignBinding by lazy {
        ActivitySignBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        binding.hesabinVarmi.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnekle.setOnClickListener {
            userName = binding.name.text.toString().trim()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()
            if (userName.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Lütfen Boş Bırakmayın", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Hesap Başarıyla Oluşturuldu", Toast.LENGTH_SHORT).show()
                    saveUserData()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Hesap Oluşturma Başarısız", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Hesap Oluşturma Hatası", task.exception)
                }
            }

    }
    private fun saveUserData() {

        userName = binding.name.text.toString().trim()
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user =UserModel(userName,email,password)
        val userId= FirebaseAuth.getInstance().currentUser!!.uid
        database.child("Kullanıcı").child(userId).setValue(user)

    }
}

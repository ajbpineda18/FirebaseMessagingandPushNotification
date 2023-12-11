package ph.edu.auf.firebaselesson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.edu.auf.firebaselesson.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnLogin.setOnClickListener{
                if(edtEmail.text.isNullOrEmpty()){
                    edtEmail.error = "Required"
                    return@setOnClickListener
                }

                if(edtPassword.text.isNullOrEmpty()){
                    edtPassword.error = "Required"
                    return@setOnClickListener
                }

                progressIndicator.visibility = View.VISIBLE

                it.isEnabled = false
                login(edtEmail.text.toString(),edtPassword.text.toString())
            }

            txtRegister.setOnClickListener{
                val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun login(email: String, password: String){

    }
}
package ph.edu.auf.firebaselesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.edu.auf.firebaselesson.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnRegister.setOnClickListener{
                if(edtEmail.text.isNullOrEmpty()){
                    edtEmail.error = "Required"
                    return@setOnClickListener
                }

                if(edtPassword.text.length < 6){
                    edtPassword.error = "Must be greater than or equal to 6 characters."
                    return@setOnClickListener
                }

                if(edtPassword.text.toString() != edtRepeatPassword.text.toString()){
                    edtPassword.error = "Password does not match"
                    edtRepeatPassword.error = "Password does not match"
                    return@setOnClickListener
                }

                progressIndicator.visibility = View.VISIBLE
                it.isEnabled = false
                register(edtEmail.text.toString(),edtPassword.text.toString())
            }
        }

    }

    private fun register(email: String, password: String){

    }
}
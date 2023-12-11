package ph.edu.auf.firebaselesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ph.edu.auf.firebaselesson.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

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
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Successfully registered", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener { exception ->
                binding.btnRegister.isEnabled = true
                binding.progressIndicator.visibility = View.GONE
                Toast.makeText(this,"Error: " + exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }
}
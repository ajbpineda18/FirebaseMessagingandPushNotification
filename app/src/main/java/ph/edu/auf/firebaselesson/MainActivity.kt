package ph.edu.auf.firebaselesson

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import ph.edu.auf.firebaselesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        binding.txtUuid.text = currentUser?.email

        binding.btnLogout.setOnClickListener{
            auth.signOut()
            finish()
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(!task.isSuccessful){
                Toast.makeText(this,"Fetching FCM registration token failed: " + task.exception,
                    Toast.LENGTH_LONG).show()
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d(MainActivity::class.java.simpleName,"Token: $token")
            Toast.makeText(this,"Token Received", Toast.LENGTH_SHORT).show()
            //SEND TOKEN TO SERVER
        }

        createChannel()
        subscribeToTopic()
    }

    private fun createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                "main_channel",
                "Main",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(false)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Main Channel"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("main")
            .addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    Toast.makeText(this,"Unable to subscribe to topic: ${task.exception?.localizedMessage}",Toast.LENGTH_SHORT).show()
                    return@addOnCompleteListener
                }
                Toast.makeText(this,"Subscribed to topic",Toast.LENGTH_SHORT).show()
            }
    }
}
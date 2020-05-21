package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val APP_PREFERENCES = "my_settings"
    val APP_PREFERENCES_ACTIVE_EMAIL = "active_email"
    val APP_PREFERENCES_ACTIVE_PASSWORD = "active_password"
    val emailRegex = Regex(pattern = "^([a-z0-9-]+)*[a-z0-9\\-]+@[a-z0-9-]+([.a-z0-9\\-]+)\\.[a-z]{2,6}\$")
    val passwordRegex = Regex(pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        val database = Database()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref: SharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_ACTIVE_EMAIL)) {
            val emailFromDoc = pref.getString(APP_PREFERENCES_ACTIVE_EMAIL, "0");
            if (!emailFromDoc.equals("0")) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
        main_button.setOnClickListener {
            val mail = main_mail.text.toString()
            val password = main_password.text.toString()
            if (emailRegex.containsMatchIn(mail) && passwordRegex.containsMatchIn(password)) {
                if (database.checkUser(mail, password)) {
                    val i = Intent(this, SecondActivity::class.java)
                    val editor = pref.edit()
                    editor.putString(APP_PREFERENCES_ACTIVE_EMAIL, mail)
                    editor.putString(APP_PREFERENCES_ACTIVE_PASSWORD, password)
                    editor.apply()
                    startActivity(i)
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "данного пользователя не существует",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
            else if (!mail.matches(emailRegex)) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Неверный формат email",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else {
                val toast = Toast.makeText(
                    applicationContext,
                    "Неверный формат пароля",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }
}

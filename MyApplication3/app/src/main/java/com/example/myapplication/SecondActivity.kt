package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : Activity() {

    val APP_PREFERENCES = "my_settings"
    val APP_PREFERENCES_ACTIVE_EMAIL = "active_email"
    val APP_PREFERENCES_ACTIVE_PASSWORD = "active_password"
    var database = Database()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val pref: SharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_ACTIVE_EMAIL)) {
            val activeEmail = pref.getString(APP_PREFERENCES_ACTIVE_EMAIL, "0");
            val activePassword = pref.getString(APP_PREFERENCES_ACTIVE_PASSWORD,"0")
            if (database.checkUser(activeEmail, activePassword)) {
                val user = database.getUser(activeEmail, activePassword)
                if (user != null) {
                    second_name.text = user.name
                    second_surname.text = user.Surname
                    second_mail.text = user.mail
                }
            }
        }
        second_button.setOnClickListener {
            val editor = pref.edit()
            editor.putString(APP_PREFERENCES_ACTIVE_EMAIL, "0")
            editor.putString(APP_PREFERENCES_ACTIVE_PASSWORD, "0")
            editor.apply()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}

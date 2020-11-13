package com.example.networking_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val id = intent.getStringExtra("ID")
        Log.i("intent", "working ")

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){Client.api.getUserbyId(id)}
            Log.i("server response2", "working ")
            if (response.isSuccessful){
                Log.i("successful_response2", "working ")
                response.body()?.let {
                    textView.text = it.name
                    textView2.text = it.login
                    Picasso.get().load(it.avatarUrl).into(imageView)
                }
            }
        }
    }
}
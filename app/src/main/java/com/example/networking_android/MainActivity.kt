package com.example.networking_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder().url("https://api.github.com/users/Doomsday008").build()
//        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        adapter.onItemClick={
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)

        }
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){Client.api.getUsers()}
            if (response.isSuccessful){
                response.body()?.let {
                    adapter.swapData(it)
                }
            }
        }



    }
}
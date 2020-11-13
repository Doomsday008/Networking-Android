package com.example.networking_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val originalList = arrayListOf<User>()

    private val adapter = UserAdapter()

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
            Log.i("Adapter", "working ")
        }
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchUsers(it) }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchUsers(it) }
                return true
            }
        })
        searchView.setOnCloseListener {
            adapter.swapData(originalList)
            true
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){Client.api.getUsers()}
            Log.i("server_response", "working ")

            if (response.isSuccessful){
                Log.i("successful_Response", "working ")
                response.body()?.let {
                    originalList.addAll(it)
                    adapter.swapData(it)

                }

            }
        }


    }
    fun searchUsers(query:String){
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){Client.api.searchUsers(query)}
            Log.i("server_response", "working ")

            if (response.isSuccessful){
//                    Log.i("successful_Response", "working ")
                response.body()?.let {
                    it.items?.let { it1 -> adapter.swapData(it1) }

                }

            }
        }
    }
}
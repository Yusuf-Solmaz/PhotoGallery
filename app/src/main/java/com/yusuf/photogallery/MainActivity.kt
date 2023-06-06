package com.yusuf.photogallery

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.photogallery.adapter.PhotoAdapter
import com.yusuf.photogallery.databinding.ActivityMainBinding
import java.io.ByteArrayInputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var photoList: ArrayList<PhotoDao>
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        photoList= ArrayList<PhotoDao>()

        photoAdapter = PhotoAdapter(photoList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter = photoAdapter
        try {
            val myDb = this.openOrCreateDatabase("Photos", MODE_PRIVATE,null)
            val cursor = myDb.rawQuery("Select * from photos",null)
            val titleIx = cursor.getColumnIndex("title")
            val idIx = cursor.getColumnIndex("id")
            val imageIx = cursor.getColumnIndex("image")

            while(cursor.moveToNext()){
                val title = cursor.getString(titleIx)
                val id = cursor.getInt(idIx)
                val imageBlob  = cursor.getBlob(imageIx)

                val imageByteArray = imageBlob ?: ByteArray(0)
                val inputStream = ByteArrayInputStream(imageByteArray)
                val bitmapImage: Bitmap? = BitmapFactory.decodeStream(inputStream)

                val photo = PhotoDao(title,id, bitmapImage!!)
                photoList.add(photo)
            }

            photoAdapter.notifyDataSetChanged()
            cursor.close()
        }
        catch (e: Exception){

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuItem){
            val intent = Intent(this@MainActivity,UploadImages::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
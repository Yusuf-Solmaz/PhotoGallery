package com.yusuf.photogallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.yusuf.photogallery.databinding.ActivityUploadImagesBinding
import java.io.ByteArrayOutputStream
import java.lang.Exception

class UploadImages : AppCompatActivity() {
    lateinit var binding : ActivityUploadImagesBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    var selectedBitmap : Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImagesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerLauncher()
        intent = intent

        if (intent.getStringExtra("info")=="new"){

        }
        else{
            binding.dateEditText.isEnabled = false
            binding.titleEditText.isEnabled = false
            binding.imageView4.isEnabled = false
            binding.saveButton.visibility = Button.GONE
        }
    }



    fun saveImage(view: View){

        val date = binding.titleEditText.text.toString()
        val title=binding.titleEditText.text.toString()

        if (selectedBitmap !=null){
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!,300)

            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()


            try {
                val myDb = this@UploadImages.openOrCreateDatabase("Photos", MODE_PRIVATE,null)
                myDb.execSQL("create table if  not exists photos (id Integer Primary Key,title Varchar, date Varchar,image Blob)")

                val insertDatas = "insert into photos (title,date,image) values (?,?,?)"
                val statement = myDb.compileStatement(insertDatas)
                statement.bindString(1,title)
                statement.bindString(2,date)
                statement.bindBlob(3,byteArray)

                Toast.makeText(this@UploadImages,"Saved!",Toast.LENGTH_LONG).show()
                statement.execute()
            }
            catch (e : Exception){
                e.printStackTrace()
            }

        }



        val intent = Intent (this@UploadImages,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }

    fun chooseImage(view: View){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            //Android>33
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                //permission denied
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES)){

                    Snackbar.make(view,"Permission needed for gallery!",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        View.OnClickListener {
                            //request permission
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }).show()
                }
                else{
                    //request permission
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }

            }
            else{
                //permission granted
                val intentTogallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentTogallery)

            }
        }
        else{
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //permission denied
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

                    Snackbar.make(view,"Permission needed for gallery!",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        View.OnClickListener {
                            //request permission
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }).show()
                }
                else{
                    //request permission
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }

            }
            else{
                //permission granted
                val intentTogallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentTogallery)

            }
        }

    }

    private fun registerLauncher(){


        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->

            if (result.resultCode == RESULT_OK){
                val intentResult = result.data
                if(intentResult != null){
                    val imageData = intentResult.data
                    if (imageData != null){
                        try {
                            if(Build.VERSION.SDK_INT>=28){
                                val source = ImageDecoder.createSource(this@UploadImages.contentResolver,imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView4.setImageBitmap(selectedBitmap)
                            }
                            else{
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                                binding.imageView4.setImageBitmap(selectedBitmap)
                            }

                        }
                        catch (e: Exception ){
                            e.printStackTrace()
                        }
                    }

                }
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            result->

            if (result){
                //permission granted
                val intentTogallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentTogallery)
            }
            else{
                //permission denied
                Toast.makeText(this@UploadImages,"Permission Neded!",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun makeSmallerBitmap(image: Bitmap,maxSize:Int) : Bitmap{
        var width= image.width
        var height = image.height

        val bitmapRatio: Double = width.toDouble()/height.toDouble()

        if (bitmapRatio >1 ){
            //Image is landscape

            width = maxSize
            val scaledHeight = width/bitmapRatio
            height = scaledHeight.toInt()
        }
        else{
            //Image is portrait
            height = maxSize
            val scaledWidth = height*bitmapRatio
            width = scaledWidth.toInt()
        }

        return Bitmap.createScaledBitmap(image,width,height,true)
    }
}
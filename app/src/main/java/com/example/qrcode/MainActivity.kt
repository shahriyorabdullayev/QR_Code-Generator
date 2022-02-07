package com.example.qrcode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        val qrCodeImage = findViewById<ImageView>(R.id.iv_qr_code)
        var text = findViewById<EditText>(R.id.et_qr_text)
        var btnGenerate = findViewById<Button>(R.id.btn_qr_generate)
        var btnShare = findViewById<Button>(R.id.btn_qr_share)

        btnGenerate.setOnClickListener {
            try {
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.encodeBitmap(text.text.toString(), BarcodeFormat.QR_CODE, 300, 300)
                qrCodeImage.setImageBitmap(bitmap)
            }catch(e: Exception){

            }
        }



        btnShare.setOnClickListener {
            
        }

    }


    fun shareMultiImage(uri: Uri) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/*"
        startActivity(Intent.createChooser(intent, "Share"))
    }



    fun shareTextWith(text: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share"))
    }

}
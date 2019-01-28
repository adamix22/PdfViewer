package com.adams.pdfviewer

import android.support.v7.app.AppCompatActivity
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
companion object {
    private val PICK_CODE=1000
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : BaseMultiplePermissionsListener() {})

        button.setOnClickListener {
            val intent= Intent(Intent.ACTION_GET_CONTENT)
           intent.type="application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(intent,"Select Pdf"), PICK_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CODE && resultCode== Activity.RESULT_OK && data!= null){
            val pickedPdf=data.data
            val intent= Intent(this@MainActivity,PdfViewer::class.java)
            intent.putExtra("viewType","storage")
            intent.putExtra("fileUri",pickedPdf.toString())
            startActivity(intent)
        }

    }
}

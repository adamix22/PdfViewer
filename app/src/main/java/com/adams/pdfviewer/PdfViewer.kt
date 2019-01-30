package com.adams.pdfviewer

import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.android.synthetic.main.activity_pdf_viewer.*
import org.xml.sax.helpers.DefaultHandler

class PdfViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)


        if (intent!= null){
            val viewType = intent.getStringExtra("viewType")
            if (!TextUtils.isEmpty(viewType) || viewType!=null) {
                if (viewType.equals("storage")) {
                    val pickedPdf = Uri.parse(intent.getStringExtra("fileUri"))
                    pdf.fromUri(pickedPdf)

                        .defaultPage(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .enableAntialiasing(true)
                        .enableAnnotationRendering(false)
                        .spacing(0)
                        .nightMode(false)
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .scrollHandle(DefaultScrollHandle(this))
                        .onPageChange { page, pageCount -> }
                        .onPageError { page, t ->
                            Toast.makeText(this@PdfViewer, "error while opening page" + page, Toast.LENGTH_SHORT).show()

                        }

                        .load()
                 pdf.useBestQuality(true)

                }

            }
        }


            }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater =menuInflater
        inflater.inflate(R.menu.menu_list,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item!!.isChecked){
            screenOff()
            item.setChecked(false)
        }
        else{
            screenOn()
            item.setChecked(true)
        }



        return true
    }

    private fun screenOn() {
        Toast.makeText(this,"activated Screen on always",Toast.LENGTH_SHORT).show()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    }
    private fun screenOff(){
        Toast.makeText(this,"deactivated Screen on always",Toast.LENGTH_SHORT).show()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}




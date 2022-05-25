package com.example.taitiandroid.ui.qr

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

//Contiene la lógica pa crear el QR

class QRViewModel : ViewModel() {

    val writer = QRCodeWriter()
    val bitMatrix = writer.encode("jorgekulant@gmail.com", BarcodeFormat.QR_CODE, 512, 512)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

    init {
        try {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }


}

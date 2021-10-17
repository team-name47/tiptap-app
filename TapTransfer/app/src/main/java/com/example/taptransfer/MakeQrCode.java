package com.example.taptransfer;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




public class MakeQrCode extends AppCompatActivity {
    public static Bitmap generateQRcode(String data, int h, int w) {
        try {
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, w, h);
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;

            //return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (WriterException e) {
        } catch (IOException e) {}
        return null;
    }

    ImageView imageView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    String intentData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_qr_code);
        initViews();
    }

    private void initViews() {
        final String merchantName =  "Merchant XYZ";
        final String accountNumber = "1234567890123";

        Bundle extras = getIntent().getExtras();
        String amount = "4000"; //extras.getString("Price");

        String networkInfo = "sdkghlfba"; // ILIYA CHANGE THIS!

        String str = accountNumber + "," + amount + "," + merchantName + "," + networkInfo;

        Bitmap img = generateQRcode(str, 300, 300);

        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        imageView = findViewById(R.id.imageView);
        //Drawable d = new BitmapDrawable(getResources(), img);  
        //surfaceView.set
        if (imageView != null)
           imageView.setImageBitmap(img);

        // ILIYA IMPLEMENT SCANNING FOR INCOMING CONNECTIONS HERE


        /*
        Run when you find the endpoint
        =========================================================================
        startActivity(new Intent(MakeQrCode.this, TransferSuccess.class));
        ========================================================================
         */
    }
}
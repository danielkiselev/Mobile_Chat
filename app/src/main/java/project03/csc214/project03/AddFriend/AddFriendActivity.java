package project03.csc214.project03.AddFriend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.R;
import project03.csc214.project03.Sound.SoundManager;

public class AddFriendActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{


    ImageView addFriendIV;
    DatabaseReference mDatabase;
    String UID;
    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        addFriendIV = (ImageView) findViewById(R.id.addFriendIV);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("create qr UID", UID);
        setQR(UID);
        new SoundManager(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addFriendBut(View view){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void handleResult(Result result){
        String friendCode = result.getText();
        Log.d("friend code received:", friendCode);
        zXingScannerView.stopCamera();
        //

        Log.d("AddFriend Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(UID).child(friendCode);
        mDatabase.setValue(friendCode);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(friendCode).child(UID);
        mDatabase.setValue(UID);
        SoundManager.getInstance.playSound(SoundManager.sound_add);
        Toast.makeText(this, "You Are Both Now Friends", Toast.LENGTH_SHORT).show();
        Log.d("AddFriend Data", "Pass");

        Intent i = new Intent(this, AddFriendActivity.class);
        startActivity(i);
    }

    public void setQR(String UID){
        MultiFormatWriter mFWriter = new MultiFormatWriter();
        try {
            Log.d("setQR UID ", UID);
            BitMatrix bitMatrix = mFWriter.encode(UID, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barEncoder = new BarcodeEncoder();
            Bitmap qrIMG = barEncoder.createBitmap(bitMatrix);
            addFriendIV.setImageBitmap(qrIMG);
        }
        catch (WriterException e){
            e.printStackTrace();
            Log.d("error", "setQR: ");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(this, HomeActivity.class);//logout
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
    }
}

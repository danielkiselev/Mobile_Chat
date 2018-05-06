package project03.csc214.project03.Registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project03.csc214.project03.Chat.ChatMasterDetailActivity;
import project03.csc214.project03.Main.MainActivity;
import project03.csc214.project03.R;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentMain = new Intent(this, MainActivity.class);//logout
        intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentMain);
    }
}

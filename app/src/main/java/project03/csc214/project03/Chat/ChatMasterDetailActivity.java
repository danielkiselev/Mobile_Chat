package project03.csc214.project03.Chat;


import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.R;

public class ChatMasterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MasterChatFragment fragment = new MasterChatFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .add(R.id.frame_layout_master, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(this, HomeActivity.class);//logout
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
    }
}

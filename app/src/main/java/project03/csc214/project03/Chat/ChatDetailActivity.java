package project03.csc214.project03.Chat;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.R;

public class ChatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        Intent intent = getIntent();
        String friendCode = intent.getStringExtra("friendCode");
        String fName = intent.getStringExtra("fName");
        String lName = intent.getStringExtra("lName");
        Log.d("chat detail activity",friendCode );
        Log.d("chat detail activity",fName );
        Log.d("chat detail activity",lName );
        //got info
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = new Bundle();
        bundle.putString("friendCode",friendCode );
        bundle.putString("fName", fName);
        bundle.putString("lName", lName);
        DetailChatFragment fragment = new DetailChatFragment();
        fragment.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .add(R.id.frame_layout_detail, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentChat = new Intent(this, ChatMasterDetailActivity.class);//logout
        intentChat.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentChat);
    }

}

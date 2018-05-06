package project03.csc214.project03.Main;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project03.csc214.project03.R;
import project03.csc214.project03.Registration.RegActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    String emailS;
    String passS;
    Button regB;
    Button loginB;
    EditText emailET;
    EditText passET;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        regB = (Button) view.findViewById(R.id.regB);
        loginB = (Button) view.findViewById(R.id.loginB);
        emailET = (EditText) view.findViewById(R.id.emailET);
        passET = (EditText) view.findViewById(R.id.passET);

        Log.d("MainFrag onCreateView: ", "pass");

        regB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(),"Welcome to Kek Chat",Toast.LENGTH_LONG).show();
                Intent intentReg = new Intent(getActivity(), RegActivity.class);
                startActivity(intentReg);
            }

        });

        loginB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                emailS = emailET.getText().toString();
                passS = passET.getText().toString();
                new Login(emailS,passS,getActivity(),getContext());
            }

        });


        return view;
    }

}

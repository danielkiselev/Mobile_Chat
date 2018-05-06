package project03.csc214.project03.Registration;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import project03.csc214.project03.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegFragment extends Fragment {

//n denotes new
    EditText nfnameET;
    EditText nlnameET;
    EditText nemailET;
    EditText npassET;
    EditText npassCET;//check
    Button nregB;
    String fNameS;
    String lNameS;
    String emailS;
    String passS;
    String passCheckS;



    public RegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reg, container, false);

        nregB = (Button) view.findViewById(R.id.newUserRegister);
        nfnameET = (EditText) view.findViewById(R.id.newFNameET);
        nlnameET = (EditText) view.findViewById(R.id.newLNameET);
        nemailET = (EditText) view.findViewById(R.id.newEmailET);
        npassET = (EditText) view.findViewById(R.id.newPassET);
        npassCET = (EditText) view.findViewById(R.id.checkPassET);

        nregB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                fNameS = nfnameET.getText().toString();
                lNameS = nlnameET.getText().toString();
                emailS = nemailET.getText().toString();
                passS = npassET.getText().toString();
                passCheckS = npassCET.getText().toString();
                Log.d("RegFrag onClick fname:", fNameS);
                Log.d("RegFrag onClick lname:", lNameS);
                Log.d("RegFrag onClick email:", emailS);
                Log.d("RegFrag onClick pass:", passS);
                Log.d("RegFrag onClick passC:", passCheckS);

                new CreateUser(fNameS,lNameS,emailS,passS,passCheckS,getActivity(),getContext());
            }

        });


        return view;
    }
}

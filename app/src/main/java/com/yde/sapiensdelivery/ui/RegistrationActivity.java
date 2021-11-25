package com.yde.sapiensdelivery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.yde.sapiensdelivery.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RadioGroup userType = findViewById(R.id.user_type);
        LinearLayout[] optionalLLs = {findViewById(R.id.rate_ll), findViewById(R.id.sin_ll), findViewById(R.id.transport_ll)};

        userType.setOnCheckedChangeListener((radioGroup, i) -> {
            if(i == R.id.customer_sign_up){
                for(LinearLayout optionLL : optionalLLs){
                    optionLL.setVisibility(View.GONE);
                }
            }
            else{
                for(LinearLayout optionLL : optionalLLs){
                    optionLL.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
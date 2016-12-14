package com.example.jaehunkim.conqueror;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.jaehunkim.conqueror.R.id.Conqueror;

public class AuthActivity extends AppCompatActivity {
    private ImageView mGreetingLogo;
    private ImageView mFingerPrint;
    private EditText mEnterPass;
    private TextView mReadFinger;
    private TextView mThemeText;
    private Typeface englishFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mGreetingLogo = (ImageView) findViewById(R.id.greetingLogo);
        mFingerPrint = (ImageView) findViewById(R.id.fingerPrint);
        mEnterPass = (EditText) findViewById(R.id.enterPass);
        mReadFinger = (TextView) findViewById(R.id.readFinger);
        mThemeText = (TextView) findViewById(R.id.Conqueror);

        englishFont = Typeface.createFromAsset(getAssets(), "poppins_regular.ttf");

        mThemeText.setTypeface(englishFont);
        mEnterPass.setTypeface(englishFont);


    }

}

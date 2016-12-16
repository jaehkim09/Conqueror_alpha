package com.example.jaehunkim.conqueror;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jaehunkim.conqueror.R.id.Conqueror;
import static com.example.jaehunkim.conqueror.R.styleable.View;

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

        //import external font
        englishFont = Typeface.createFromAsset(getAssets(), "poppins_regular.ttf");
        //set font for each textViews
        mThemeText.setTypeface(englishFont);
        mEnterPass.setTypeface(englishFont);

        mEnterPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String password = mEnterPass.getText().toString().trim();
                    //to progress to next screen
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    //using myPreferences determine if the program is ruf for the first time
                    boolean isFirstTime = myPreferences.isFirst(AuthActivity.this);

                    if(isFirstTime) {
                        savePasswordUseYn(getApplicationContext(), "Y");
                        savePassword(getApplicationContext(), password);

                        Toast.makeText(getApplicationContext(),
                                "비밀번호가 설정되었습니다",
                                Toast.LENGTH_LONG).show();
                        //to next screen
                        startActivity(intent);
                    }else {
                        String previousPassword = loadPassword(getApplicationContext());
                        if (password.equals(previousPassword)) {
                            Toast.makeText(getApplicationContext(),
                                    "비밀번호가 확인되었습니다",
                                    Toast.LENGTH_LONG).show();
                            //to next screen
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "비밀번호가 맞지 않습니다.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    return true;
                }
                return false;
            }
        });




    }

    public void savePassword (Context context, String password) {
        SharedPreferences preferences = context.getSharedPreferences("environ", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", password);
        editor.commit();
    }

    public String loadPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("environ", 0);
        return preferences.getString("password", "");
    }

    public void savePasswordUseYn(Context context, String useYn) {
        SharedPreferences preferences = context.getSharedPreferences("environ", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passwordYn", useYn);
        editor.commit();
    }

    public static class myPreferences {

        private static final String MY_PREFERENCES = "my_preferences";

        public static boolean isFirst(Context context){
            final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
            final boolean first = reader.getBoolean("is_first", true);
            if(first){
                final SharedPreferences.Editor editor = reader.edit();
                editor.putBoolean("is_first", false);
                editor.commit();
            }
            return first;
        }

    }

}

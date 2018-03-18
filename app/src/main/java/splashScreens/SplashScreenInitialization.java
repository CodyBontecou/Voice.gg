package splashScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codybontecou.voice.MainActivity;
import com.example.codybontecou.voice.R;

import globalVal.Prefs;

public class SplashScreenInitialization extends AppCompatActivity {

    private Button mSave;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_init);


        //if (!Prefs.getPrefs(SplashScreenInitialization.this).getString("owner", "").isEmpty())
            //moveToMain();

        mSave = findViewById(R.id.save);
        mEditText = findViewById(R.id.name);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText.getText().length() == 0) {
                    Toast.makeText(SplashScreenInitialization.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Prefs.getPrefs(SplashScreenInitialization.this).edit().putString("owner", mEditText.getText().toString()).commit();
                    moveToMain();
                }
            }
        });
    }
    private void moveToMain(){
        Intent intent = new Intent(SplashScreenInitialization.this, MainActivity.class);
        SplashScreenInitialization.this.startActivity(intent);
        //SplashScreenInitialization.this.finish();
    }
}

package adam.notebook.example.com.kpproject6.module.introsplash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceKey;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.TablayoutActivity;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    protected boolean active = true;
    protected int splashTime = 3000;
    private PreferenceUtils pref = MyApplication.pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme_green);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread() {
            public void run() {
                try {
                    int waited = 300;

                    while (active && (waited < splashTime)) {
                        sleep(300);
                        if (active) {
                            waited += 300;
                        }
                    }
                } catch (Exception e) {
                    e.toString();
                } finally {
                    if (pref.getBoolean(PreferenceKey.IsLoggedIn)) {
                        startActivity(new Intent(getApplicationContext(), TablayoutActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
//                    startActivity(new Intent(getApplicationContext(), TablayoutActivity.class));
                    finish();
                }
            }
        };

        splashThread.start();
    }
}


//    TextView tvSplash;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //menghilangkan ActionBar
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_splash_screen);
//
//        tvSplash = (TextView) findViewById(R.id.tvSplash);
//
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//            }
//        }, 3000L); //3000 L = 3 detik
//    }
//}
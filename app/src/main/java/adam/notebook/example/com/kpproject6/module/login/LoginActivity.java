package adam.notebook.example.com.kpproject6.module.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.TablayoutActivity;
import adam.notebook.example.com.kpproject6.service.login.LoginPresenter;
import adam.notebook.example.com.kpproject6.service.login.ServiceCallback;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.module.register.RegisterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ServiceCallback {

    private String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.edit_emaillogin)
    EditText editEmail;
    @BindView(R.id.edit_passwordlogin)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.im_hidelogin)
    ImageView imHide;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private boolean isPasswordVisible = false;
    PreferenceUtils sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        // Code berikut berfungsi untuk mengecek session, Jika session true ( sudah login )
//        // maka langsung memulai MainActivity.
//        if (sharedPrefManager.isLoggedIn()){
//            startActivity(new Intent(LoginActivity.this, TablayoutActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }

        imHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imHide.setImageResource(R.drawable.eye);
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imHide.setImageResource(R.drawable.hide);
                }
                editPassword.setSelection(editPassword.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        final String useremail = editEmail.getText().toString();
        final String password = editPassword.getText().toString();
        if (useremail.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Masukkan email dan password", Toast.LENGTH_SHORT).show();
            return;

        }
        if (!Utils.isValidEmail(useremail)) {
            Toast.makeText(this, "Email yang anda masukkan tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = "";
        if (useremail.contains("@")){
            email = useremail;
        }

        LoginPresenter.login(email, password, this);

        Intent intent_log = new Intent(LoginActivity.this, TablayoutActivity.class);
        startActivity(intent_log);
        finish();

    }

    @OnClick(R.id.tv_register)
    public void register(View view) {
        Intent intent_regs = new Intent(LoginActivity.this, RegisterActivity.class);
        intent_regs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_regs);
        finish();
    }

    @Override
    public void onSuccess(User data) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onFailed(String message) {
        LogUtility.logging(TAG, LogUtility.errorLog, "initListeners", "btn_Login.setOnClickListener", message);
        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            btnLogin.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
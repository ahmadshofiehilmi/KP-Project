package adam.notebook.example.com.kpproject6.module.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.service.register.RegisterPresenter;
import adam.notebook.example.com.kpproject6.service.register.ServiceCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements ServiceCallback {

    @BindView(R.id.edit_nameregister)
    EditText edit_Namereg;
    @BindView(R.id.edit_emailregister)
    EditText edit_Emailreg;
    @BindView(R.id.edit_passwordregister)
    EditText edit_Passwordreg;
    @BindView(R.id.edit_addressregister)
    EditText edit_Addressreg;
    @BindView(R.id.edit_cityregister)
    EditText edit_Cityreg;
    @BindView(R.id.edit_postalcoderegister)
    EditText edit_Postalcodereg;
    @BindView(R.id.btn_register)
    Button btn_Register;
    @BindView(R.id.tv_login)
    TextView tv_Login;
    @BindView(R.id.im_eyeregister)
    ImageView im_Eyereg;
    @BindView(R.id.progress_register)
    ProgressBar proRegister;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        im_Eyereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    edit_Passwordreg.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    im_Eyereg.setImageResource(R.drawable.eye);
                } else {
                    edit_Passwordreg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    im_Eyereg.setImageResource(R.drawable.hide);
                }
                edit_Passwordreg.setSelection(edit_Passwordreg.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }
    @OnClick(R.id.btn_register)
    public void register(View view) {
        String email = edit_Emailreg.getText().toString();
        String password = edit_Passwordreg.getText().toString();
        String fullname = edit_Namereg.getText().toString();
        String address = edit_Addressreg.getText().toString();
        String city = edit_Cityreg.getText().toString();
        String postal_code = edit_Postalcodereg.getText().toString();

        if ("".equals(email) || "".equals(password) || "".equals(fullname) || "".equals(address) || "".equals(city) || "".equals(postal_code)) {
            Toast.makeText(this, "Maaf, semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.isValidEmail(email)) {
            Toast.makeText(this, "Email yang anda masukkan tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterPresenter.register(email, password, fullname, address, city, postal_code, this);


    }

    @OnClick(R.id.tv_login)
    public void login(View view) {
        backToLogin();
    }

    private void backToLogin() {
        Intent intent_login = new Intent(RegisterActivity.this, LoginActivity.class);
        intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_login);
        finish();
    }

    private void showSuccesDialog(){
        new AlertDialog.Builder(this)
                .setMessage("Register berhasil. Silahkan cek email anda untuk verifikasi akun")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onSuccess() {
        showSuccesDialog();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            btn_Register.setVisibility(View.GONE);
            proRegister.setVisibility(View.VISIBLE);
        } else {
            btn_Register.setVisibility(View.VISIBLE);
            proRegister.setVisibility(View.GONE);
        }
    }
}

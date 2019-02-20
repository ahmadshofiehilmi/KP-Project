package adam.notebook.example.com.kpproject6.module.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.service.profile.ProfilePresenter;
import adam.notebook.example.com.kpproject6.service.profile.ServiceCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity implements ServiceCallback {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    @BindView(R.id.name_profile)
    EditText namaProfile;
    @BindView(R.id.address_profile)
    EditText addressProfile;
    @BindView(R.id.email_profile)
    EditText emailProfile;
    @BindView(R.id.pc_profile)
    EditText pcProfile;
    @BindView(R.id.city_profile)
    EditText cityProfile;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    @BindView(R.id.image_profile)
    ImageView imageProfil;

    private Boolean isEditMode = false;
    private String email, fullname, address, city, postal_code, profile_image;
    private PreferenceUtils pref = MyApplication.pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setupProfile();
    }

    private void setupProfile() {
        User user = pref.getUserSession();
        emailProfile.setText(user.getEmailUser());
        namaProfile.setText(user.getNameUser());
        addressProfile.setText(user.getAddressUser());
        pcProfile.setText(user.getPostal_codeUser());
        cityProfile.setText(user.getCityUser());

    }

    @OnClick(R.id.btn_edit)
    public void editProfile(View view) {
        if (!isEditMode) {
            emailProfile.setEnabled(true);
            namaProfile.setEnabled(true);
            btnEdit.setText(R.string.simpan);
            isEditMode = true;
            return;
        }

        email = emailProfile.getText().toString();
        fullname = namaProfile.getText().toString();

        if ("".equals(email) || "".equals(fullname)) {
            Toast.makeText(this, "Maaf, semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Simpan profil")
                .setMessage("Anda yakin akan mengubah profil?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProfilePresenter.editProfile(email, fullname, address, city, postal_code, profile_image, ProfileActivity.this);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onSuccess() {
        User user = pref.getUserSession();
        user.setEmailUser(email);
        user.setNameUser(fullname);
        user.setAddressUser(address);
        user.setCityUser(city);
        user.setPostal_codeUser(postal_code);
        user.setProfile_imageUser(profile_image);
        pref.setUserSession(user);
        Toast.makeText(ProfileActivity.this, "Ubah profil berhasil", Toast.LENGTH_SHORT).show();
        Intent intent_profile = new Intent(ProfileActivity.this, ProfileActivity.class);
        intent_profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_profile);
        finish();

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAccountDeleted() {

    }

    @Override
    public void showAlamatUtama(String alamat) {

    }

}

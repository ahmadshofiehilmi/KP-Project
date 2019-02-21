package adam.notebook.example.com.kpproject6.module.profile;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

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
    public static final int REQUEST_IMAGE = 100;

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

        ProfilePresenter.getProfile(this);

        loadProfileDefault();
    }
//camera image
    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        Glide.with(this).load(url)
                .into(imageProfil);
        imageProfil.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }
    private void loadProfileDefault() {
        Glide.with(this).load(R.drawable.ic_account_circle_black_24dp)
                .into(imageProfil);
        imageProfil.setColorFilter(ContextCompat.getColor(this, R.color.profile_default_tint));
    }
    @OnClick({R.id.image_profile2, R.id.image_profile})
    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }
    private void launchCameraIntent() {
        Intent intent = new Intent(ProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }
    private void launchGalleryIntent() {
        Intent intent = new Intent(ProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
//camera image

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
            addressProfile.setEnabled(true);
            pcProfile.setEnabled(true);
            cityProfile.setEnabled(true);
            btnEdit.setText(R.string.simpan);
            isEditMode = true;
            return;
        }

        email = emailProfile.getText().toString();
        fullname = namaProfile.getText().toString();
        address = addressProfile.getText().toString();
        city = cityProfile.getText().toString();
        postal_code = pcProfile.getText().toString();

        if ("".equals(email) || "".equals(fullname)|| "".equals(address)|| "".equals(city)|| "".equals(postal_code)) {
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

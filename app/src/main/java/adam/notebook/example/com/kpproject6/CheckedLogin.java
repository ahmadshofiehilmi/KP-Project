package adam.notebook.example.com.kpproject6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;

public class CheckedLogin {
    public static Boolean hasLogin(Context context) {
        PreferenceUtils pref = new PreferenceUtils(context);

        if (pref.isLoggedIn())
            return pref.isLoggedIn();

        showDialog(context);
        return pref.isLoggedIn();
    }

    private static void showDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setMessage("Anda harus login untuk melanjutkan transaksi")
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }
}

package adam.notebook.example.com.kpproject6.GeneralUtility;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String toJson(Object any) {
        return "" + new Gson().toJsonTree(any);
    }
    public static String toFormatCurrency(String s) {
        NumberFormat format = NumberFormat.getInstance();
        try {
            Long money = Long.parseLong(s);
            return format.format(money).replace(",", ".");
        } catch (Exception e) {
            return s;
        }
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void hideSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = ((Activity)context).getCurrentFocus();
        if (null != imm && null != view) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String toCurrency(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(number).replace(",", ".");
    }
}
package pro.exec.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import pro.exec.R;

/**
 * Created by gupta on 6/16/2018.
 */

public class Utility {
    //Constructing progress dialog object for displaying
    public static ProgressDialog getDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(R.string.dialog_message));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    /*Method to check weather network is available or not*/
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}

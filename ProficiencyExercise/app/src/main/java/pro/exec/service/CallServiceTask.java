package pro.exec.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pro.exec.R;
import pro.exec.ui.model.RootModel;
import pro.exec.util.AppConstant;
import pro.exec.util.ParseJsonData;
import pro.exec.util.Utility;

/**
 * Created by gupta on /16/2018.
 * <p>
 * This class will start new background thread to get data from network
 * this thread will not touch the ui thread
 */

public class CallServiceTask extends AsyncTask<String, Integer, String> {
    private static final String TAG = CallServiceTask.class.getName();
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private ResponseListener mResponseListener;

    public CallServiceTask(Context context, ResponseListener responseListener) {
        this.mContext = context;
        this.mResponseListener = responseListener;
        if (mProgressDialog == null) {
            mProgressDialog = Utility.getDialog(context);
        }
    }

    /*This method will execute on UI thread*/
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /*This method will execute in seperate thread to get data from network*/
    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(AppConstant.URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return readResponseData(inputStream).toString();
        } catch (MalformedURLException ex) {
            Log.d(TAG, ex.toString());
            AppConstant.SERVER_ERROR = ex.getMessage();
        } catch (IOException ex) {
            Log.d(TAG, ex.toString());
            AppConstant.SERVER_ERROR = ex.getMessage();
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
            AppConstant.SERVER_ERROR = ex.getMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    private StringBuilder readResponseData(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            assert reader != null;
            Log.d(TAG, "readResponseData: ");
            String readData = null;
            try {
                readData = reader.readLine();
                while (readData != null) {
                    stringBuilder.append(readData);
                    readData = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder;
    }

    /*Here response will come and doing parsing using GSON*/
    @Override
    protected void onPostExecute(String s) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mResponseListener.GetResponse(ParseJsonData.getJsonResponse(s));
    }

    public interface ResponseListener {
        void GetResponse(RootModel baseResponse);
    }

}

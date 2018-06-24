package pro.exec.util;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import pro.exec.service.BaseResponse;
import pro.exec.ui.model.RootModel;

/**
 * Created by gupta on 6/16/2018.
 */

public class ParseJsonData {
    //Tag to monitor data in logcat
    private static final String TAG = ParseJsonData.class.getName();
    //Gson object which will parse json data to model object
    private static final Gson gson = new Gson();

    public static RootModel getJsonResponse(String jsonResponse) {
        //Local variable should be initialize
        RootModel rootModel = null;
        try {
            rootModel = gson.fromJson(jsonResponse,RootModel.class);
        } catch (Exception e) {
            Log.d(TAG, "getJsonResponse: ");
        }
        return rootModel;
    }

}

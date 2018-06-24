package pro.exec.ui.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import pro.exec.R;
import pro.exec.service.BaseResponse;
import pro.exec.service.CallServiceTask;
import pro.exec.ui.model.RootModel;
import pro.exec.ui.view.MainActivityView;
import pro.exec.util.AppConstant;
import pro.exec.util.Utility;

/**
 * Created by gupta on 6/17/2018
 * In this class you can implement all the business logic and validation.
 */

public class ListPresenter implements CallServiceTask.ResponseListener {
    private Context mContext;
    private CallServiceTask mCallServiceTask;
    private MainActivityView mActivityView;

    public ListPresenter(Context context, MainActivityView activityView) {
        this.mContext = context;
        this.mActivityView = activityView;
    }

    /*This method will start new back ground thread to get data from network location*/
    public void callService() {
        if (Utility.isNetWorkAvailable(mContext)) {
            mCallServiceTask = new CallServiceTask(mContext, this);
            mCallServiceTask.execute();
        }else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.network_msg), Toast.LENGTH_SHORT).show();
        }
    }

    /*after success fully get data presenter will send all the data to UI*/
    @Override
    public void GetResponse(RootModel rootModel) {
        if (rootModel != null) {
            mActivityView.getTitle(rootModel.getTitle());
            mActivityView.getView(rootModel);
        } else {
            Toast.makeText(mContext, AppConstant.SERVER_ERROR, Toast.LENGTH_LONG).show();
        }
    }
}


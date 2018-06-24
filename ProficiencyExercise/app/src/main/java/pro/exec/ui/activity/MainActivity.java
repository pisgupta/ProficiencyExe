package pro.exec.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import pro.exec.R;
import pro.exec.ui.adapter.ListRowAdapter;
import pro.exec.ui.model.RootModel;
import pro.exec.ui.presenter.ListPresenter;
import pro.exec.ui.view.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView, View.OnClickListener {
    //This TAG will be used for log print
    private static final String TAG = MainActivity.class.getName();
    //Widgets object
    private ListView mListView;
    private ListPresenter mListPresenter;
    private ImageView mImageView;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.myList);
        mImageView = (ImageView) findViewById(R.id.imgRefresh);
        mTitle = (TextView) findViewById(R.id.tvTitle);
        mImageView.setOnClickListener(this);
    }

    private void initPresenter() {
        mListPresenter = new ListPresenter(this, this);
        mListPresenter.callService();
    }

    @Override
    public void getView(RootModel rootModel) {
        ListRowAdapter listRowAdapter = new ListRowAdapter(this, rootModel);
        mListView.setAdapter(listRowAdapter);
    }

    @Override
    public void getTitle(String rootModel) {
        if (rootModel != null) {
            mTitle.setText(rootModel);
        }
    }

    @Override
    public void onClick(View view) {
        mListPresenter.callService();
    }
}

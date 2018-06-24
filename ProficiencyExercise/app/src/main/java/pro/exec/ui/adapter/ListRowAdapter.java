package pro.exec.ui.adapter;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pro.exec.R;
import pro.exec.ui.model.RootModel;
import pro.exec.ui.model.Rows;

/**
 * Created by gupta on 6/16/2018.
 * This class will use to bind the object to the list
 */

public class ListRowAdapter extends BaseAdapter {
    private Context mContext;
    private RootModel mRootModel;
    private LayoutInflater mLayoutInflater;
    private Rows[] allRow;


    public ListRowAdapter(Context context, RootModel rootModel) {
        this.mContext = context;
        this.mRootModel = rootModel;
        mLayoutInflater = LayoutInflater.from(context);
        allRow = rootModel.getRows();
    }

    @Override
    public int getCount() {
        if (mRootModel != null)
            return mRootModel.getRows().length;
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view == null) {
            mViewHolder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.row_view, viewGroup, false);
            mViewHolder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            mViewHolder.txtDesc = (TextView) view.findViewById(R.id.txtDesc);
            mViewHolder.imgRowImage = (ImageView) view.findViewById(R.id.rowImg);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        setTextViewData(mViewHolder, allRow[i]);
        loadImage(mViewHolder.imgRowImage, allRow[i]);

        return view;
    }

    private void setTextViewData(ViewHolder viewData, Rows rows) {
        viewData.txtTitle.setText(rows.getTitle());
        viewData.txtDesc.setText(rows.getDescription());
    }

    /*this method will load the image once the all data get from network
    * here i am using picso library to load image from given link.
    *references taken from :http://square.github.io/picasso/
    * */
    private void loadImage(ImageView imageView, Rows rows) {
        try {
            String url = rows.getImageHref();
            Log.d("TAG", "loadImage: "+url);
            Picasso.with(mContext).load(url).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
        ImageView imgRowImage, imgNext;

    }
}

package hcmue.congvu.drlstudent.Model.HomeModel;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 05/10/2018.
 */
public class HomeAdapter extends BaseAdapter {
    private HomeActivity mContext;
    private final String[] mNameIcon;
    private final int[] mImgIcon;

    public HomeAdapter(HomeActivity mContext, String[] mNameIcon, int[] mImgIcon) {
        this.mContext = mContext;
        this.mNameIcon = mNameIcon;
        this.mImgIcon = mImgIcon;
    }

    @Override
    public int getCount() {
        return mNameIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = new View(mContext);
        if(convertView == null){
            view = layoutInflater.inflate(R.layout.home_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgIconHome);
            TextView textView = (TextView) view.findViewById(R.id.tvIconHome);
            imageView.setImageResource(mImgIcon[position]);
            textView.setText(mNameIcon[position]);
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/windsorb.ttf");
            textView.setTypeface(typeface);
        }
        else{
            view = convertView;
        }

        return view;
    }
}

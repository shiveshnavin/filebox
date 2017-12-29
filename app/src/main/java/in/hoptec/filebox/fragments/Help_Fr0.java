package in.hoptec.filebox.fragments;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.hoptec.filebox.R;

/**
 * Created by shivesh on 29/12/17.
 */

public class Help_Fr0 extends Fragment {

    @DrawableRes public int image;

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static final Help_Fr0 newInstance(@ColorRes int clr, @DrawableRes int img, boolean isLast, String message)
    {
        Help_Fr0 f = new Help_Fr0();

        Bundle bdl = new Bundle(1);

        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putInt("img",img);
        bdl.putInt("clr",clr);
        bdl.putBoolean("islast",isLast);

        f.setArguments(bdl);

        return f;
    }


    Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        int image = getArguments().getInt("img");
        int clr = getArguments().getInt("clr");
        boolean islast = getArguments().getBoolean("islast");

        mActivity =getActivity();


        View v = inflater.inflate(R.layout.fragment_help, container, false);

        TextView messageTextView = (TextView)v.findViewById(R.id.desc);
        AppCompatButton btn=(AppCompatButton)v.findViewById(R.id.next);

        btn.setSupportBackgroundTintList(ContextCompat.getColorStateList(mActivity, clr));

        ImageView img = (ImageView)v.findViewById(R.id.img);
        img.setImageResource(image);
        Drawable drawable = img.getDrawable();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        messageTextView.setText(message);
        return v;
    }
}
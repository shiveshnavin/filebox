package in.hoptec.filebox.fragments;

import android.app.Activity;
import android.content.Intent;
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

import in.hoptec.filebox.ui.Home;
import in.hoptec.filebox.R;
import in.hoptec.filebox.ui.Splash;
import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 29/12/17.
 */

public class Help_Fr0 extends Fragment {


    public int pos=0;
    public boolean islast=false;
    public int clr;
    public int image;
    public String message;

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



        mActivity =getActivity();


       final  View v = inflater.inflate(R.layout.fragment_help, container, false);


        TextView messageTextView = (TextView)v.findViewById(R.id.desc);

        AppCompatButton btn=(AppCompatButton)v.findViewById(R.id.next);

        try {
               messageTextView.setTextColor((ContextCompat.getColor(getContext(),clr)));

               btn.setSupportBackgroundTintList(ContextCompat.getColorStateList(mActivity, clr));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(islast)
        {
            btn.setText("LAUNCH");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!islast&&Splash.curItem<Splash.pageAdapter.getCount()-1)
                {
                    Splash.pager.setCurrentItem(Splash.curItem+1,true);
                }

                if(islast)
                {
                   // Splash.pager.setCurrentItem(0,true);
                    Intent intent=new Intent(getContext(), Home.class);
                    startActivity(intent);

                }

            }
        });


        ImageView img = (ImageView)v.findViewById(R.id.img);
        img.setImageResource(image);
        utl.changeColorDrawable(img,clr);
        Drawable drawable = img.getDrawable();

        if (drawable instanceof Animatable) {
           // ((Animatable) drawable).start();
        }

        messageTextView.setText(message);
        return v;
    }
}
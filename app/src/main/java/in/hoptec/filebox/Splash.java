package in.hoptec.filebox;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import in.hoptec.filebox.adapters.HelpPagerAdapter;
import in.hoptec.filebox.fragments.Help_Fr0;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView mIcDownloadAnimator = (ImageView) findViewById(R.id.logo);
        Drawable drawable = mIcDownloadAnimator.getDrawable();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        setUpIntro();

    }

    ArrayList<Integer> colors;

    Integer dur=1000;
    HelpPagerAdapter pageAdapter;
    String curSt="#455ede";
    public void setUpIntro()
    {
       final  View bg=findViewById(R.id.backg);

        colors=new ArrayList<>();

        colors.add(R.color.green_700);
        colors.add(R.color.accent);
        colors.add(R.color.blue_700);

        utl.animateBackGround(bg,curSt,"#0a7e07",false,dur);

        List<Fragment> fragments = getFragments();
        pageAdapter = new HelpPagerAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager =
                (ViewPager)findViewById(R.id.help_pager);
        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                utl.l("Pos : "+position+"\n Offset : "+positionOffset+"\n Pix "+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {

                utl.l("Page Sel : "+position);

                switch (position)
                {
                    case 0:
                        utl.animateBackGround(bg,curSt,"#0a7e07",false,dur);
                        curSt="#0a7e07";

                        break;
                    case 1:
                        utl.animateBackGround(bg,curSt,"#F57F17",false,dur);
                        curSt="#F57F17";

                        break;
                    case 2:
                        utl.animateBackGround(bg,curSt,"#455ede",false,dur);
                        curSt="#455ede";

                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(Help_Fr0.newInstance(colors.get(0),R.drawable.ic_folder_open_48px,false,"No more storming your gallery to find files ," +
                " Organise your documents and Images in buckets to reach easily ."));
        fList.add(Help_Fr0.newInstance(colors.get(1),R.drawable.ic_vpn_key_48px,false,"Hide you private Files and Photos with smart lock from Gallery and Spy Apps."));
        fList.add(Help_Fr0.newInstance(colors.get(2),R.drawable.ic_if_50_cloud_arrow_up_183360,true,"Save important files on Cloud with Easy Sharing !"));

        return fList;
     }


    }


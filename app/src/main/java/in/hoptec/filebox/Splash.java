package in.hoptec.filebox;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    HelpPagerAdapter pageAdapter;
    public void setUpIntro()
    {

        List<Fragment> fragments = getFragments();
        pageAdapter = new HelpPagerAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager =
                (ViewPager)findViewById(R.id.help_pager);
        pager.setAdapter(pageAdapter);
    }


    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(Help_Fr0.newInstance("Fragment 1"));
        fList.add(Help_Fr0.newInstance("Fragment 2"));
        fList.add(Help_Fr0.newInstance("Fragment 3"));
        return fList;
     }


    }


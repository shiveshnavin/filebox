package in.hoptec.filebox;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.hoptec.filebox.adapters.HelpPagerAdapter;
import in.hoptec.filebox.fragments.Help_Fr0;
import me.relex.circleindicator.CircleIndicator;

public class Splash extends AppCompatActivity {
    View pager_container,title_cont;

       View bg;
    public Context ctx;
    public Activity act;
    float init_pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ctx=this;
        act=this;

        ImageView mIcDownloadAnimator = (ImageView) findViewById(R.id.logo);
       final Drawable drawable = mIcDownloadAnimator.getDrawable();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        TextView textView = (TextView) findViewById(R.id.title);
        Spannable word = new SpannableString("File");

        word.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(word);
        Spannable wordTwo = new SpannableString("Box");

        wordTwo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.material_lime_300)), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(wordTwo);
        bg=findViewById(R.id.backg);
        title_cont=findViewById(R.id.title_cont);

        pager_container=findViewById(R.id.pager_container);
        pager_container.setVisibility(View.GONE);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (drawable instanceof Animatable) {

                    ((Animatable) drawable).stop();
                }

                Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.translate);

               // title_cont.startAnimation(myAnim);


                pager_container.setVisibility(View.VISIBLE);


                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slid_up);
                animation.setStartOffset(0);

                pager_container.startAnimation(animation);



            }
        },2000);
        setUpIntro();
        utl.animateBackGround(bg,curSt,"#0a7e07",false,dur);
        curSt="#0a7e07";


            init_pos=title_cont.getY();
      /*  final View activityRootView = findViewById(R.id.backg);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onGlobalLayout() {

                       float pos= Help_Fr0.curv.getY();
                        if(init_pos>pos)
                            title_cont.setY(pos-200);


                    }
                });*/




    }

    ArrayList<Integer> colors;

    public static  ViewPager pager ;
    public static int curItem=0;



    Integer dur=1000;
    public static  HelpPagerAdapter pageAdapter;
    String curSt="#056f00";
    String  colorsS [] ={"#ff0b8043","#ffc53929","#ff3367d6"};
    public void setUpIntro()
    {








        colors=new ArrayList<>();

        colors.add(R.color.material_green_700);
        colors.add(R.color.material_deep_orange_700);
        colors.add(R.color.material_blue_700);


        List<Fragment> fragments = getFragments();
        pageAdapter = new HelpPagerAdapter(getSupportFragmentManager(), fragments);

         pager=       (ViewPager)findViewById(R.id.help_pager);
        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                utl.l("Pos : "+position+"\n Offset : "+positionOffset+"\n Pix "+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {

                utl.l("Page Sel : "+position);

                curItem=position;

                switch (position)
                {
                    case 0:
                        utl.animateBackGround(bg,curSt,colorsS[0],false,dur);
                        curSt=colorsS[0];

                        break;
                    case 1:
                        utl.animateBackGround(bg,curSt,colorsS[1],false,dur);
                        curSt=colorsS[1];

                        break;
                    case 2:
                        utl.animateBackGround(bg,curSt,colorsS[2],false,dur);
                        curSt=colorsS[2];

                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
         indicator.setViewPager(pager);


    }


    Help_Fr0.TransCallback cb=new Help_Fr0.TransCallback() {
        @Override
        public void curPos(float pos) {

            if(init_pos>pos)
                title_cont.setY(pos-200);

        }
    };
    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();

        Help_Fr0 frag=new Help_Fr0();

        frag.image=R.drawable.ic_folder_open_48px;
        frag.message="No more storming your gallery to find files ," +
                " Organise your documents and Images in buckets to reach easily .";
        frag.islast=false;
        frag.clr=colors.get(0);
        frag.cb=cb;
        fList.add(frag);

        /***/
        frag=new Help_Fr0();

        frag.image=R.drawable.ic_vpn_key_48px;
        frag.message="Hide you private Files and Photos with smart lock from Gallery and Spy Apps.";
        frag.islast=false;
        frag.clr=colors.get(1);
        frag.cb=cb;
        fList.add(frag);


        /***/
        frag=new Help_Fr0();

        frag.image=R.drawable.ic_folder_open_48px;
        frag.message="Save important files on Cloud with Easy Sharing !";
        frag.islast=true;
        frag.clr=colors.get(2);
        frag.cb=cb;
        fList.add(frag);



        return fList;
     }


    }


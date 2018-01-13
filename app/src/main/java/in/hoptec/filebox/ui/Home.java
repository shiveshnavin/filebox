package in.hoptec.filebox.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.hoptec.filebox.R;
import in.hoptec.filebox.adapters.BoxesAdapterRec;
import in.hoptec.filebox.database.Box;
import in.hoptec.filebox.utils.utl;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class Home extends BaseActivity {

    /******UI VARS******/
    private TextView logTextView;
    private CoordinatorLayout coordinatorLayout;
    private ImageView wifiActionIndicator;
    private LinearLayout disconnectButton;
    private LinearLayout connectButton;
    private LinearLayout refreshButton;
    private RecyclerView boxe_r;


    private AppBarLayout appBarLayout;
    private LinearLayout header;

    private FloatingActionButton fabConnect, fabDisconnect;
    private ImageView sort;


    private int appBarHeight=200;

    private long FAB_ANIM_DUR=400;
    private boolean LOG_UP=false;

    private Handler h;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initBase();

        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        header=(LinearLayout)findViewById(R.id.header);
        fabConnect = (FloatingActionButton) findViewById(R.id.fab);
        fabDisconnect = (FloatingActionButton) findViewById(R.id.fab2);
        logTextView=(TextView)findViewById(R.id.logs);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.cont);
        wifiActionIndicator=(ImageView)findViewById(R.id.wifiAction);
        disconnectButton=(LinearLayout)findViewById(R.id.disconnect);
        connectButton=(LinearLayout)findViewById(R.id.connect);
        refreshButton=(LinearLayout)findViewById(R.id.refresh);
        sort=(ImageView) findViewById(R.id.sort);
        boxe_r = (RecyclerView) findViewById(R.id.rec);
       // appBarHeight=utl.pxFromDp(ctx,170).intValue();
        h=new Handler();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                Integer verticalOffsetNormalized=-1*verticalOffset;

                Float alphaHeader=1.0f-verticalOffsetNormalized.floatValue()/ appBarHeight;
                Float alphaFAB=verticalOffsetNormalized.floatValue()/ appBarHeight;

                fabConnect.animate().setDuration(FAB_ANIM_DUR).alpha(alphaFAB);
                fabDisconnect.animate().setDuration(FAB_ANIM_DUR).alpha(alphaFAB);

                header.setAlpha(alphaHeader);


            }
        });

        fabConnect.animate().alpha(0f);
        fabDisconnect.animate().alpha(0f);

        initOnCLickListeners();



        setUpBoxes(readDB());
    }
    ArrayList<Box> box_list =new ArrayList<>();



    private void initOnCLickListeners()
    {

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToBox();
               // utl.animate_avd((ImageView) connectButton.findViewById(R.id.iop0));

            }
        });
        addPressReleaseAnimation(connectButton);


        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnect();
            }
        });
        addPressReleaseAnimation(disconnectButton);



        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        addPressReleaseAnimation(refreshButton);


        addPressReleaseAnimation(fabConnect);
        addPressReleaseAnimation(fabDisconnect);


        fabConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addToBox();

            }
        });


        fabDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                refresh();


            }
        });



        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                Context wrapper = new ContextThemeWrapper(ctx, R.style.popup);

                PopupMenu popup = new PopupMenu(wrapper,sort);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_sort);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.by_date:
                                //handle menu1 click
                                break;
                            case R.id.by_name:
                                //handle menu2 click
                                break;
                            case R.id.by_use:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();


                utl.animate_avd(sort);

            }



        });

    }


    private void expandToolbar()
    {


        fabConnect.animate().setDuration(FAB_ANIM_DUR).alpha(0f);
        fabDisconnect.animate().setDuration(FAB_ANIM_DUR).alpha(0f);



    }



    private void addToBox()
    {

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent it=new Intent(ctx,AddBox.class);
                startActivity(it);
                overridePendingTransition(R.anim.act_fade_in, R.anim.act_fade_out);

            }
        },20);

    }

    private void refresh()
    {

    }

    private void disconnect()
    {

    }


    private void setUpBoxes(ArrayList<Box> box_list)
    {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false);
        boxe_r.setLayoutManager(mLayoutManager);



        BoxesAdapterRec boxAdapter = new BoxesAdapterRec(ctx, box_list){

            @Override
            public void click(final int pos,final Box cat) {
                super.click(pos,cat);






            }


        };

        LandingAnimator animator = new LandingAnimator(new OvershootInterpolator(1f));
        boxe_r.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(boxAdapter);
        alphaAdapter.setDuration(1000);
        boxe_r.setNestedScrollingEnabled(false);
        boxe_r.setAdapter(alphaAdapter);




    }


}

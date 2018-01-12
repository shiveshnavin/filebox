package in.hoptec.filebox.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.hoptec.filebox.R;
import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 12/1/18.
 */

public class BaseActivity  extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public Toolbar toolbar;

    public Context ctx;
    public Activity act;

    public void initBase()
    {
        ctx=this;
        act=this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initNavigationDrawer();


    }

    public void initNavigationDrawer() {

        final NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.postDelayed(new Runnable() {
            @Override
            public void run() {

                ImageView mIcDownloadAnimator = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.logo);
                final Drawable drawable = mIcDownloadAnimator.getDrawable();

                if (drawable instanceof Animatable && utl.ANIM_LV1_ENABLED) {
                    ((Animatable) drawable).start();
                }

            }
        },400);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"AddBox",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.box:
                        Toast.makeText(getApplicationContext(),"Boxes",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cloud:
                        Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("v1.1");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.post(new Runnable()
                           {

                               @Override
                               public void run() {

                                   Resources r = getResources();

                                   DisplayMetrics metrics = new DisplayMetrics();

                                   if(r.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                                       getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                       int height = metrics.heightPixels;

                                       float screenWidth = height / r.getDisplayMetrics().density;
                                       float navWidth = (screenWidth - 56);

                                       navWidth = Math.min(navWidth, 320);

                                       int newWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, navWidth, r.getDisplayMetrics());

                                       DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
                                       params.width = newWidth;
                                       navigationView.setLayoutParams(params);


                                   }

                                   if(r.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                                       getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                       int width = metrics.widthPixels;

                                       float screenWidth = width / r.getDisplayMetrics().density;
                                       float navWidth = screenWidth - 56;

                                       navWidth = Math.min(navWidth, 320);

                                       int newWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, navWidth, r.getDisplayMetrics());

                                       DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
                                       params.width = newWidth;
                                       navigationView.setLayoutParams(params);


                                   }
                               }

                           }
        );
    }


    public void addPressReleaseAnimation(final View base)
    {

        final Animation press= AnimationUtils.loadAnimation(ctx,R.anim.rec_zoom_in);
        final Animation release= AnimationUtils.loadAnimation(ctx,R.anim.rec_zoom_nomal);

        base.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        base.startAnimation(press);
                        break;
                    case MotionEvent.ACTION_UP:

                        base.startAnimation(release);
                        break;
                    case MotionEvent.ACTION_CANCEL:

                        base.startAnimation(release);
                        break;
                    default:
                        break;
                }



                return false;
            }
        });


    }

}

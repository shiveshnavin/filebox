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

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

import in.hoptec.filebox.App;
import in.hoptec.filebox.R;
import in.hoptec.filebox.database.Box;
import in.hoptec.filebox.database.BoxFile;
import in.hoptec.filebox.database.BoxMeta;
import in.hoptec.filebox.database.Constants;
import in.hoptec.filebox.database.HeadLessDB;
import in.hoptec.filebox.utils.FileOperations;
import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 12/1/18.
 *
 */

public class BaseActivity  extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public Toolbar toolbar;

    public Context ctx;
    public Activity act;

    public HeadLessDB db;

    public void initBase()
    {
        ctx=this;
        act=this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new HeadLessDB();
        db.init();

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

/*

    public ArrayList<Box> generate()
    {
        utl.e("Creating DB on Device ");
        ArrayList<Box>
                box_list =new ArrayList<>();

        Box bx=new Box();
        bx.boxData=new BoxMeta();
        bx.files=new ArrayList<>();
        bx.boxData.name="Sem5 assigns";
        bx.boxData.countUse=16;
        bx.boxData.dateTime="12 Jan 18";

        BoxFile file=new BoxFile();
        file.name="DCS 1.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="DCS 3.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="MPMC 1.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="CS 1-6.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="CTS 4.pdf";
        file.pathThumb="-";
        bx.files.add(file);
        box_list.add(bx);



        file=new BoxFile();
        file.name="CTS 1.pdf";
        file.pathThumb="-";
        bx.files.add(file);
        box_list.add(bx);



        file=new BoxFile();
        file.name="control 3.pdf";
        file.pathThumb="-";
        bx.files.add(file);
        box_list.add(bx);

        */
/************//*

        bx=new Box();
        bx.boxData=new BoxMeta();
        bx.files=new ArrayList<>();
        bx.boxData.name="Bhavya sem5";
        bx.boxData.countUse=45;
        bx.boxData.dateTime="13 Jan 18";

        file=new BoxFile();
        file.name="mpc 2015 end.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="DCS all trms.pdf";
        file.pathThumb="-";
        bx.files.add(file);

        file=new BoxFile();
        file.name="DOC_2017129.pdf";
        file.pathThumb="-";
        bx.files.add(file);

        file=new BoxFile();
        file.name="cts 15,16.pdf";
        file.pathThumb="-";
        bx.files.add(file);



        */
/************//*

        bx=new Box();
        bx.boxData=new BoxMeta();
        bx.files=new ArrayList<>();
        bx.boxData.name="Practical files";
        bx.boxData.countUse=21;
        bx.boxData.dateTime="13 Jan 18";

        file=new BoxFile();
        file.name="mpc.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="dcs.pdf";
        file.pathThumb="-";
        bx.files.add(file);

        file=new BoxFile();
        file.name="DOC_2017129.pdf";
        file.pathThumb="-";
        bx.files.add(file);

        file=new BoxFile();
        file.name="DS.zip";
        file.pathThumb="ZIP";
        bx.files.add(file);



        */
/************//*

        */
/************//*

        bx=new Box();
        bx.boxData=new BoxMeta();
        bx.files=new ArrayList<>();
        bx.boxData.name="Bhavya pdfs";
        bx.boxData.countUse=12;
        bx.boxData.dateTime="13 Jan 18";

        file=new BoxFile();
        file.name="DCS End terms.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="DCS 3.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="MPMC 1.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="Mpc aaa.pdf";
        file.pathThumb="-";
        bx.files.add(file);


        file=new BoxFile();
        file.name="bhavya dcs.pdf";
        file.pathThumb="-";
        bx.files.add(file);
        box_list.add(bx);
        */
/************//*


        FileOperations fop=new FileOperations();
        fop.write(Constants.getDBFile(),utl.js.toJson(box_list));

        return  (box_list);


    }

*/




}

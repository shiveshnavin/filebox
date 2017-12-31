package in.hoptec.filebox;

import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.hoptec.filebox.fragments.AddToBox;
import in.hoptec.filebox.fragments.HomeFragment;

public class Home extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    public int CUR_STATE=States.HOME;

    public static class States{

        public static final int HOME=1;
        public static final int ADD_BUCKET = 348;
    }




    Fragment curFragment,pFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CUR_STATE==States.HOME)
                {
                    CUR_STATE=States.ADD_BUCKET;
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.material_green_500)));

                    fab.setImageResource(R.drawable.ic_check_white_48dp);


                    transactToState(States.ADD_BUCKET);


                }
                else {
                    CUR_STATE=States.HOME;
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                    fab.setImageResource(R.drawable.ic_add_white_48dp);
                    transactToState(States.HOME);


                }








            }
        });



        transactToState(States.HOME);

        initNavigationDrawer();


    }

    public void initNavigationDrawer() {

       final NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.postDelayed(new Runnable() {
            @Override
            public void run() {

                ImageView mIcDownloadAnimator = (ImageView) findViewById(R.id.logo);
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
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
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
    }



    FragmentTransaction transaction;
    FragmentManager manager;
    public void transactToState(int state)
    {

        manager=getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        switch (state)
        {
            case States.ADD_BUCKET :


                pFragment= getSupportFragmentManager().findFragmentById(R.id.fragment);

                curFragment =new AddToBox();

                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fr_fade_in, R.anim.fr_fade_out);
                transaction.replace(R.id.fragment, curFragment);
                transaction.addToBackStack(null);
                transaction.commit();


                break;
            case States.HOME :

                pFragment= getSupportFragmentManager().findFragmentById(R.id.fragment);


                curFragment =new HomeFragment();

                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fr_fade_in, R.anim.fr_fade_out);
                transaction.replace(R.id.fragment, curFragment);
                transaction.addToBackStack(null);
                transaction.remove(pFragment);
                transaction.commit();



                break;
            default:

        }



    }



















}

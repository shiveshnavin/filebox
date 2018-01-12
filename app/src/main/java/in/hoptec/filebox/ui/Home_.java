package in.hoptec.filebox.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.hoptec.filebox.R;
import in.hoptec.filebox.fragments.AddToBox;
import in.hoptec.filebox.fragments.HomeFragment;
import in.hoptec.filebox.utils.GenricCallback;
import in.hoptec.filebox.utils.Transact;
import in.hoptec.filebox.utils.utl;

public class Home_ extends BaseActivity implements Transact{

    public int CUR_STATE=States.HOME;

    public static class States{

        public static final int HOME=1;
        public static final int ADD_BUCKET = 348;
    }

    public Context ctx;
    public Activity act;

    FloatingActionButton fab;

    Fragment curFragment,pFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx=this;
        act=this;


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CUR_STATE==States.HOME)
                {

                    transactToState(States.ADD_BUCKET);


                }
                else {

                    utl.snack(Home_.this,"Added !");
                   transactToState(States.HOME);


                }








            }
        });



        transactToState(States.HOME);

        initNavigationDrawer();


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
                CUR_STATE=States.ADD_BUCKET;
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.material_green_500)));

                fab.setImageResource(R.drawable.ic_check_white_48dp);



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

                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                CUR_STATE=States.HOME;

                fab.setImageResource(R.drawable.ic_add_white_48dp);


                break;
            default:

        }



    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(CUR_STATE==States.HOME)
        {
            finish();
        }
        else if(CUR_STATE==States.ADD_BUCKET)
        {
            utl.snack(act, "Discard changes and exit ?", "EXIT", new GenricCallback() {
                @Override
                public void onStart() {


                    transactToState(States.HOME);
                }

                @Override
                public void onDo(Object obj) {

                }

                @Override
                public void onDo(Object obj, Object obj2) {

                }

                @Override
                public void onDone(Object obj) {

                }
            });

        }


    }
}

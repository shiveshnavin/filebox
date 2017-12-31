package in.hoptec.filebox.fragments;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;

import in.hoptec.filebox.R;
import in.hoptec.filebox.adapters.BoxesAdapter;
import in.hoptec.filebox.utils.GenricCallback;
import in.hoptec.filebox.utl;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddToBox extends Fragment {

    public AddToBox() {
    }


    private RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    public Context ctx;
    public Activity act;

    ImageView del;

    public void transactToState(int state)
    {




    }
    View view;
    ArrayList<BoxesAdapter.Dummy> dummies;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_add, container, false);

        ctx=getContext();
        act=getActivity();
        del=(ImageView)view.findViewById(R.id.del);
        recyclerView =(RecyclerView)view.findViewById(R.id.boxes);
        mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);

        dummies=new ArrayList<>();
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());
        dummies.add(new BoxesAdapter.Dummy());


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utl.animate_avd(del);
                utl.snack(act, "Discard changes and exit ?", "EXIT", new GenricCallback() {
                    @Override
                    public void onStart() {

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
        });
        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new BoxesAdapter(ctx,dummies){

            @Override
            public void click(int pos) {
                super.click(pos);

                if(pos>=dummies.size())
                    return;
                dummies.remove(pos);
                notifyItemRemoved(pos);


            }


        };



        LandingAnimator animator = new LandingAnimator(new OvershootInterpolator(1f));
        recyclerView.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        alphaAdapter.setDuration(1000);



        recyclerView.setAdapter(alphaAdapter);


        return view;
    }
}

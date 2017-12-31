package in.hoptec.filebox.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;

import in.hoptec.filebox.R;
import in.hoptec.filebox.adapters.BoxesAdapter;
import in.hoptec.filebox.utils.Transact;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements Transact{

    Transact cb;


    public HomeFragment()
    {}


    private RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public void transactToState(int state)
    {




    }

    public Context ctx;
    public Activity act;

    View view;
    ArrayList<BoxesAdapter.Dummy> box_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);

        ctx=getContext();
        act=getActivity();

        recyclerView =(RecyclerView)view.findViewById(R.id.boxes);
        mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);

        box_list =new ArrayList<>();
        int i=0;
        do {
            box_list.add(new BoxesAdapter.Dummy(i));
        } while (i++<10);

        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new BoxesAdapter(ctx, box_list){


            @Override
            public void click(int pos, BoxesAdapter.Dummy cat) {
                super.click(pos,cat);

                if(pos>= box_list.size())
                    return;
                box_list.remove(pos);
                notifyItemRemoved(pos);


            }

            @Override
            public void clickLong(int pos) {
                super.clickLong(pos);

              //  box_list.add(new BoxesAdapter.Dummy());

              //  notifyItemInserted(pos);
                //notifyDataSetChanged();


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

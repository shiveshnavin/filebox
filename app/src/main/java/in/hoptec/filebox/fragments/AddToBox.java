package in.hoptec.filebox.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.hoptec.filebox.ui.AddBox;
import in.hoptec.filebox.R;
import in.hoptec.filebox.adapters.BoxesAdapter;
import in.hoptec.filebox.adapters.BoxesAdapterH;
import in.hoptec.filebox.database.BoxMeta;
import in.hoptec.filebox.utils.GenricCallback;
import in.hoptec.filebox.utils.Transact;
import in.hoptec.filebox.utils.utl;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddToBox extends Fragment {

    public AddToBox() {
    }


    private RecyclerView boxesRec,boxesAddedRec;

    private RecyclerView.Adapter boxAdapter,boxAddAdapter;
    private RecyclerView.LayoutManager mLayoutManager,mLayoutManager2;



    public Context ctx;
    public Activity act;

    ImageView del,add;
    EditText box_name;

    Transact activityCommunicator;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
         activityCommunicator =(Transact)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return new View(getContext());
    }















}

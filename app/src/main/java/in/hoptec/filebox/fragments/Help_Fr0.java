package in.hoptec.filebox.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.hoptec.filebox.R;

/**
 * Created by shivesh on 29/12/17.
 */

public class Help_Fr0 extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final Help_Fr0 newInstance(String message)
    {
        Help_Fr0 f = new Help_Fr0();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.fragment_help, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.desc);
        messageTextView.setText(message);
        return v;
    }
}
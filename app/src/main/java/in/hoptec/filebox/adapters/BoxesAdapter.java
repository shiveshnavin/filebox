package in.hoptec.filebox.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.hoptec.filebox.R;


public class BoxesAdapter extends RecyclerView.Adapter<BoxesAdapter.CustomViewHolder> {
    private List<Dummy> feedItemList;
    private Context mContext;

    public BoxesAdapter(Context context, List<Dummy> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_box, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {

        final int pos=customViewHolder.getAdapterPosition();
        final Dummy item=feedItemList.get(pos);
        customViewHolder.textView.setText(Html.fromHtml(item.getData(pos)));
        customViewHolder.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(pos);
            }
        });


        customViewHolder.base.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                clickLong(pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        View base;


        public CustomViewHolder(View v) {
            super(v);
            
            base=v;
            textView=(TextView) base.findViewById(R.id.name);

            
            

        }
    }

    public void click(int pos)
    {




    }


    public void clickLong(int pos)
    {




    }

    public static class Dummy
    {
        String data="TEST";
        
        public String getData(int i)
        {
            return "Data : "+i;
        }
        
    }






}


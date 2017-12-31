package in.hoptec.filebox.adapters;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import in.hoptec.filebox.R;
import in.hoptec.filebox.database.Box;
import in.hoptec.filebox.utl;


public class BoxesAdapter extends RecyclerView.Adapter<BoxesAdapter.CustomViewHolder> {
    private List<Dummy> feedItemList;
    private Context mContext;

    public BoxesAdapter(Context context, List<Dummy> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_box,viewGroup, false);

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
        final Animation press= AnimationUtils.loadAnimation(mContext,R.anim.rec_zoom_in);
        final Animation release= AnimationUtils.loadAnimation(mContext,R.anim.rec_zoom_nomal);

        customViewHolder.base.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        customViewHolder.base.startAnimation(press);
                        break;
                    case MotionEvent.ACTION_UP:

                        customViewHolder.base.startAnimation(release);

                    case MotionEvent.ACTION_CANCEL:

                        customViewHolder.base.startAnimation(release);



                        break;
                    default:
                        break;
                }



                return false;
            }
        });

       // customViewHolder.root.getLayoutParams().height = utl.getRandomIntInRange(250,75);


        customViewHolder.base.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {



                final Drawable drawable = customViewHolder.del.getDrawable();

                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }


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
        ImageView del,icon;
        View base;
        LinearLayout root;


        public CustomViewHolder(View v) {
            super(v);
            
            base=v;
            textView=(TextView) base.findViewById(R.id.name);
            root=(LinearLayout) base.findViewById(R.id.root);
            del=(ImageView) base.findViewById(R.id.del);
            icon=(ImageView) base.findViewById(R.id.icon);
            
            

        }
    }

    public void click(int pos)
    {





    }


    public void clickLong(int pos)
    {


    }

    public static class Dummy extends Box
    {
        String data="TEST";
        
        public String getData(int i)
        {
            return "Bucket No "+i;
        }
        
    }






}


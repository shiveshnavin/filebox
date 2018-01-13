package in.hoptec.filebox.adapters;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.hoptec.filebox.R;
import in.hoptec.filebox.database.Box;
import in.hoptec.filebox.database.BoxFile;
import in.hoptec.filebox.database.BoxMeta;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


public class BoxesAdapterRec extends RecyclerView.Adapter<BoxesAdapterRec.CustomViewHolder> {
    private List<Box> feedItemList;
    private Context mContext;

    public BoxesAdapterRec(Context context, List<Box> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_box_rec,viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {

        final int pos=customViewHolder.getAdapterPosition();
        final Box item=feedItemList.get(pos);
        customViewHolder.textView.setText(Html.fromHtml(item.boxData.name));
        customViewHolder.views.setText(Html.fromHtml(""+item.boxData.countUse+" Views"));
        customViewHolder.date.setText(Html.fromHtml(item.boxData.dateTime));

        customViewHolder.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(pos,item);
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


        customViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final Drawable drawable = customViewHolder.del.getDrawable();

                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }


                delClick(pos,item);



            }
        });

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        customViewHolder.files.setLayoutManager(mLayoutManager);



        BoxFilesAdapterH boxAdapter = new BoxFilesAdapterH(mContext, feedItemList.get(pos).files){

            @Override
            public void click(final int pos,final BoxFile cat) {
                super.click(pos,cat);






            }


        };

        LandingAnimator animator = new LandingAnimator(new OvershootInterpolator(1f));
        customViewHolder.files.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(boxAdapter);
        alphaAdapter.setDuration(1000);
        customViewHolder.files.setNestedScrollingEnabled(false);
        customViewHolder.files.setAdapter(alphaAdapter);

    }



    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView,views,date;
        ImageView del,icon;
        View base;
        LinearLayout root;
        RecyclerView files;


        public CustomViewHolder(View v) {
            super(v);

            base=v;
            textView=(TextView) base.findViewById(R.id.name);
            views=(TextView) base.findViewById(R.id.btm_t1);
            date=(TextView) base.findViewById(R.id.btm_t2);
            root=(LinearLayout) base.findViewById(R.id.root);
            del=(ImageView) base.findViewById(R.id.del);
            icon=(ImageView) base.findViewById(R.id.icon);
            files=(RecyclerView) base.findViewById(R.id.files);



        }
    }

    public void delClick(int pos, Box cat)
    {





    }


    public void click(int pos, Box cat)
    {





    }


    public void clickLong(int pos)
    {


    }

    public static class Dummy extends Box
    {
        String data="TEST";
        public Dummy(int i)
        {

            boxData=new BoxMeta();
            boxData.id="Box No is "+i;

        }

        public Dummy( )
        {}

        public String getData(int i)
        {
            return "Bucket No "+i;
        }
        
    }






}


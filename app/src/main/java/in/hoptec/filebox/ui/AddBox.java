package in.hoptec.filebox.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.hoptec.filebox.R;
import in.hoptec.filebox.adapters.BoxFilesAdapterH;
import in.hoptec.filebox.adapters.BoxesAdapter;
import in.hoptec.filebox.adapters.BoxesAdapterH;
import in.hoptec.filebox.database.Box;
import in.hoptec.filebox.database.BoxFile;
import in.hoptec.filebox.database.BoxMeta;
import in.hoptec.filebox.utils.GenricCallback;
import in.hoptec.filebox.utils.utl;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class AddBox extends BaseActivity {


    private RecyclerView boxesRec, boxesAddedRec, files;
    private RecyclerView.Adapter boxAdapter, boxAddAdapter, boxFilesAdapter;
    private RecyclerView.LayoutManager mLayoutManager, mLayoutManager2;


    ImageView del, add;
    EditText box_name;
    TextView no_files;

    View view;
    ArrayList<Box> box_list, box_added;
    ArrayList<BoxFile> box_files;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initBase();
        setTitle("Add Files");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.material_green_500)));

        fab.setImageResource(R.drawable.ic_check_white_48dp);

        initBase();
        initView();
        handleIntent();


    }

    public void initView() {

        view = findViewById(R.id.content_home);


        no_files = (TextView) findViewById(R.id.no_files);
        box_name = (EditText) view.findViewById(R.id.box_name);
        add = (ImageView) view.findViewById(R.id.add);
        del = (ImageView) view.findViewById(R.id.del);
        files = (RecyclerView) view.findViewById(R.id.files);
        boxesRec = (RecyclerView) view.findViewById(R.id.boxes);
        boxesAddedRec = (RecyclerView) view.findViewById(R.id.boxes_added);
        mLayoutManager = new LinearLayoutManager(ctx);
        mLayoutManager2 = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);


        box_name.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    //do here your stuff f

                    // add.setImageResource(R.drawable.avd_plus_to_chk);
                    utl.animate_avd(add);

                    String bx = box_name.getText().toString();
                    BoxesAdapter.Dummy dm = new BoxesAdapter.Dummy();
                    dm.boxData = new BoxMeta();
                    dm.boxData.id = bx;
                    box_added.add(dm);

                    boxAddAdapter.notifyItemInserted(boxAddAdapter.getItemCount() - 1);
                    // boxAddAdapter.notifyDataSetChanged();

                    box_name.setText("");

                    return true;
                }
                return false;

            }

        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                utl.animate_avd(add);

                String bx = box_name.getText().toString();
                BoxesAdapter.Dummy cat = new BoxesAdapter.Dummy();
                cat.boxData = new BoxMeta();
                cat.boxData.id = bx;


                box_added.add(cat);
                boxAddAdapter.notifyItemInserted(box_added.size() - 1);
                boxesAddedRec.smoothScrollToPosition(box_added.size() - 1);
                boxAdapter.notifyDataSetChanged();


                box_name.setText("");

            }
        });


        box_list = new ArrayList<>();
        box_list = readDB();
       /* int i=0;
        do {
            box_list.add(new BoxesAdapter.Dummy(i));
        } while (i++<10);
*/

        box_added = new ArrayList<>();


        // Initialize a new instance of RecyclerView Adapter instance
        boxAdapter = new BoxesAdapter(ctx, box_list) {

            @Override
            public void click(final int pos, final Box cat) {
                super.click(pos, cat);


                utl.l("REMOVING : " + utl.js.toJson(box_list.get(pos)));

                box_added.add(cat);
                boxAddAdapter.notifyItemInserted(box_added.size() - 1);
                boxesAddedRec.smoothScrollToPosition(box_added.size() - 1);
                boxAdapter.notifyDataSetChanged();


                box_list.remove(cat);
                notifyDataSetChanged();
                notifyItemRemoved(pos);


            }


        };


        boxAddAdapter = new BoxesAdapterH(ctx, box_added) {

            @Override
            public void click(final int pos, final Box cat) {
                super.click(pos, cat);


                box_list.add(cat);
                boxAdapter.notifyItemInserted(box_list.size() - 1);
                boxesRec.smoothScrollToPosition(box_list.size() - 1);
                boxAdapter.notifyDataSetChanged();


                box_added.remove(cat);
                notifyDataSetChanged();
                notifyItemRemoved(pos);


            }


        };
        boxesAddedRec.setLayoutManager(mLayoutManager2);
        boxesRec.setLayoutManager(mLayoutManager);


        LandingAnimator animator = new LandingAnimator(new OvershootInterpolator(1f));
        boxesRec.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(boxAdapter);
        alphaAdapter.setDuration(1000);
        boxesRec.setNestedScrollingEnabled(false);
        boxesRec.setAdapter(alphaAdapter);

        boxesAddedRec.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter2 = new SlideInBottomAnimationAdapter(boxAddAdapter);
        alphaAdapter2.setDuration(1000);
        boxesAddedRec.setNestedScrollingEnabled(false);
        boxesAddedRec.setAdapter(alphaAdapter2);


        setUpFileList(readDB().get(0).files);


    }

    public void setUpFileList(ArrayList<BoxFile> fileList) {

        int size = fileList.size() ;
        no_files.setText(("" + size) + " Files");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
        files.setLayoutManager(mLayoutManager);


        boxFilesAdapter = new BoxFilesAdapterH(ctx, fileList) {

            @Override
            public void click(final int pos, final BoxFile cat) {
                super.click(pos, cat);


            }


        };

        LandingAnimator animator = new LandingAnimator(new OvershootInterpolator(1f));
        files.setItemAnimator(animator);
        SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(boxFilesAdapter);
        alphaAdapter.setDuration(1000);
        files.setNestedScrollingEnabled(false);
        files.setAdapter(alphaAdapter);


    }


    public void handleIntent()
    {

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        box_files=new ArrayList<>();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            } else if (type.startsWith("image/")) {
                handleSendImage(intent);
            } else  {
                handleSendImage(intent);
            }



            utl.e("Handling Single File type : "+type);



        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent);
            }  else {
                handleSendMultipleImages(intent);
            }



            utl.e("Handling Multiple Files type : "+type);



        } else {



        }


    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {

            utl.e("Text is : "+sharedText);


        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {

            utl.e("URI is : "+imageUri.toString()+" \nPath is : "+utl.pathFromURI(act,imageUri));
            BoxFile file=new BoxFile(utl.pathFromURI(act,imageUri));
            box_files.add(file);

            setUpFileList(box_files);
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {

            for (Uri u:imageUris) {

                utl.e("URI is : "+u.toString()+" \nPath is : "+utl.pathFromURI(act,u));
                BoxFile file=new BoxFile(utl.pathFromURI(act,u));

                 utl.l(utl.js.toJson(file));
                 box_files.add(file);

            }

            setUpFileList(box_files);




        }
    }
}

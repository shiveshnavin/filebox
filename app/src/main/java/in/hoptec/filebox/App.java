package in.hoptec.filebox;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

import in.hoptec.filebox.database.Constants;
import in.hoptec.filebox.utils.FileOperations;
import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 26/12/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Constants.init(this);
        initDB();
        FirebaseApp.initializeApp(this);

    }

    FileOperations fop=new FileOperations();

    public void initDB()
    {

        File db=new File(Constants.getFolder()+"/"+Constants.FILE_DB_INDEX);

        if(db.exists())
        {


            String jsonIndexStr=fop.read(Constants.getFolder()+"/"+Constants.FILE_DB_INDEX);

            try{

                JSONObject jsonObject=new JSONObject(jsonIndexStr);

                Iterator<String> i=jsonObject.keys();


                int countKeys=0;
                do{
                    String k = i.next().toString();
                    utl.l("DB_META","AT "+i+" is Key = "+k);

                    countKeys++;

                }while(i.hasNext());


                if(countKeys==0)
                {
                    utl.l("DATABASE EMPTY");
                }






            }catch (Exception e)
            {
                e.printStackTrace();
            }




        }
        else {


            fop.write(Constants.getFolder()+"/"+Constants.FILE_DB_INDEX,"{}");



        }




    }

}


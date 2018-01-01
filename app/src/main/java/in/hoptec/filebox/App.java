package in.hoptec.filebox;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import java.io.File;

/**
 * Created by shivesh on 26/12/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Constants.init(this);
        FirebaseApp.initializeApp(this);

    }

    FileOperations fop=new FileOperations();

    public void initDB()
    {

        File db=new File(Constants.getFolder()+"/"+Constants.FILE_DB);

        if(db.exists())
        {

        }
        else {

        }




    }

}


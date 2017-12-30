package in.hoptec.filebox;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by shivesh on 26/12/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);

    }

}


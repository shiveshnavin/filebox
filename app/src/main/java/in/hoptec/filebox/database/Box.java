package in.hoptec.filebox.database;

import java.util.ArrayList;

/**
 * Created by shivesh on 31/12/17.
 */

public class Box {

    public BoxMeta boxData;
    public ArrayList<BoxFile> files;



    public void add(BoxFile f)
    {

        boolean isUniq=true;
        for (BoxFile fi:files
             ) {

            if(fi.path.equals(f.path))
            {
                isUniq=false;
            }


        }

        if(isUniq)
        {
            files.add(f);
        }

    }


}

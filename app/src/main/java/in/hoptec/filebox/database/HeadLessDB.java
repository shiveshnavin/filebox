package in.hoptec.filebox.database;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

import in.hoptec.filebox.utils.FileOperations;
import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 13/1/18.
 */

public class HeadLessDB {

    public void init()
    {

        boxes=readDB();


    }

    public ArrayList<Box> getBoxes()
    {

        return readDB();



    }

    public ArrayList<Box> boxes;

    private ArrayList<Box> readDB()
    {

        ArrayList<Box> box_list;
        box_list=new ArrayList<>();

        File db=new File(Constants.getDBFile());
        if(db.exists()){
            FileOperations f=new FileOperations();
            try {

                utl.e("Reading DB from Device ");
                JSONArray jar=new JSONArray(f.read(Constants.getDBFile()));
                for(int i=0;i<jar.length();i++){

                    Box bx=utl.js.fromJson(jar.get(i).toString(),Box.class);
                    box_list.add(bx);
                    utl.e("Got "+bx.boxData.name);



                }


             } catch (Exception e) {


                e.printStackTrace();

            }
        }else {
            FileOperations fop=new FileOperations();
            fop.write(Constants.getDBFile(),utl.js.toJson(box_list));


        }
        return  (box_list);


    }

    public void saveDB()
    {

        utl.e("DB Updated !");
        FileOperations fop=new FileOperations();
        fop.write(Constants.getDBFile(),utl.js.toJson(boxes));




    }

    public void saveDB(ArrayList<Box> xx)
    {

        utl.e("DB Updated !");
        FileOperations fop=new FileOperations();
        fop.write(Constants.getDBFile(),utl.js.toJson(xx));




    }
    public Box addFilesToBox(ArrayList<BoxFile> files,Box box)
    {

        for (Box bxx:boxes
             ) {

            if(box.boxData.id.equals(bxx.boxData.id))
            {
                if(bxx.files==null){
                    bxx.files=new ArrayList<>();
                }

                    for (BoxFile f:files) {

                         bxx.add(f);

                    }
                utl.l("File appended to existing box "+bxx.boxData.name);
                saveDB();
                return bxx;
            }

        }


        if(box.files==null){
            box.files=new ArrayList<>();
        }

        for (BoxFile f:files) {

            box.add(f);

        }
        utl.l("File appended to new box "+box.boxData.name);
        boxes.add(box);
        saveDB();
        return box;


    }


    public void removeBox(Box box)
    {

        ArrayList<Box> xx=readDB();

        for(int i=0;i<xx.size();i++)
        {
            if(xx.get(i).boxData.id.equals(box.boxData.id))
            {
                xx.remove(i);
            }
        }

        saveDB(xx);

    }










}

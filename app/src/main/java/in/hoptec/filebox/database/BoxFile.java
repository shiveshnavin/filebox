package in.hoptec.filebox.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import in.hoptec.filebox.utils.utl;

/**
 * Created by shivesh on 1/1/18.
 */

public class BoxFile {


        public String path;
        public String id;
        public String name;
        public String dateTime;
        public String type;
        public String pathThumb;
        public Integer countUse;


        public BoxFile() {

            if(path!=null){

                if(name==null){
                    generateMeta();
                }

                if(pathThumb==null)
                {
                    makeThumb();
                }

            }
        }
        public BoxFile(String pat)
        {


            path=pat;
            id=""+ System.currentTimeMillis();
            File f=new File(path);
            if(f.exists()){

                try {
                    name=utl.getFileName(path);
                    File file=new File(path);
                    dateTime=utl.dateFromLong(file.lastModified());
                    type=utl.getFileExt(path);

                    makeThumb();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            else {

                name=utl.getFileName(path);


            }



        }
        public void generateMeta()
        {



            id=""+ System.currentTimeMillis();
            if(path!=null){

                try {
                    name=utl.getFileName(path);
                    File file=new File(path);
                    dateTime=utl.dateFromLong(file.lastModified());
                    type=utl.getFileExt(path);
                    makeThumb();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }



        public void makeThumb()
        {

            if(type.contains("jpg")||type.contains("png")||type.contains("jpeg")||type.contains("bmp")||type.contains("ttif")){

            }
            else {
                pathThumb="---"+type;
                return;
            }

            File file =new File(path); // the image file
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bitmapOptions.inJustDecodeBounds = true; // obtain the size of the image, without loading it in memory
            BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions);

            // find the best scaling factor for the desired dimensions
            int desiredWidth = 400;
            int desiredHeight = 300;
            float widthScale = (float)bitmapOptions.outWidth/desiredWidth;
            float heightScale = (float)bitmapOptions.outHeight/desiredHeight;
            float scale = Math.min(widthScale, heightScale);

            int sampleSize = 1;
            while (sampleSize < scale) {
                sampleSize *= 2;
            }
            bitmapOptions.inSampleSize = sampleSize; // this value must be a power of 2,
            // this is why you can not have an image scaled as you would like
            bitmapOptions.inJustDecodeBounds = false; // now we want to load the image

            Bitmap thumbnail = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions);

            pathThumb=Constants.getFolder()+"/thumbs/"+ utl.refineString(name,"_").replace("_"+type,"."+type);
            File thumbnailFile =new File(pathThumb);
            try {
                FileOutputStream fos = new FileOutputStream(thumbnailFile);
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            thumbnail.recycle();



        }



    }

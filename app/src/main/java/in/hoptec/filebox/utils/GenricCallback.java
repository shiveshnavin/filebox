package in.hoptec.filebox.utils;

/**
 * Created by shivesh on 14/7/17.
 */

public interface GenricCallback {


    public void onStart();
    public void onDo(Object obj);
    public void onDo(Object obj, Object obj2);
    public void onDone(Object obj);


}

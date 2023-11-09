package edu.wcu.cs.ascott.cs467.activityforresult1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  implements View.OnClickListener {

    /**An array of strings holds the data**/
    private String[] mDataset;

    private ItemWasClicked observer;

    //==============================================================================================
    /**Provide a suitable constructor (depends on the kind of dataset)
     * @param myDataset For us the data set is just a String. */
    //==============================================================================================
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }//end constructor==============================================================================


    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ViewGroup v = null;
        if (viewType == 0)
        // create a new view in this case a simple text view
            v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        else
            v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item1, parent, false);

        //This is important
        MyViewHolder vh = new MyViewHolder(v);
        vh.setOnItemClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
        holder.letter.setText(mDataset[position].charAt(0)+"");
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    //==============================================================================================
    public void onClick(View v){
        if(observer!= null) {
            boolean show_name = v.getTag().equals("name");
            observer.itemWasClicked(v,show_name);
        }
    }//=============================================================================================

    public void setOnItemWasClicked(ItemWasClicked obs){
        this.observer = obs;
    }

    public interface ItemWasClicked{
        public void itemWasClicked(View v,boolean show_name);
    }
    //----------------------------------------------------------------------------------------------
    /**An inner class to provide a reference to the views for each data item
     Complex data items may need more than one view per item, and
     you provide access to all the views for a data item in a view holder**/
    //----------------------------------------------------------------------------------------------
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /**each data item is just a string in this case**/
        public TextView textView;

        public TextView letter;

        /**A simple constructor with one view.
         * @param v A textView object to hold a string.
         */
        public MyViewHolder(ViewGroup v) {
            super(v);
            textView = v.findViewById(R.id.name);
            letter = v.findViewById(R.id.letter);
            letter.setTag("Letter");
            textView.setTag("Name");
        }//End mthod

        public void setOnItemClickListener( View.OnClickListener v){
            textView.setOnClickListener(v);
            letter.setOnClickListener(v);
        }
    }//end
}

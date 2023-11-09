package edu.wcu.cs.ascott.cs467.activityforresult1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SelectPkm extends AppCompatActivity {

    /**A reference for the Recyclerview**/
    private RecyclerView recyclerView;
    /**The adapter to hold the data**/
    private MyAdapter myAdapter;
    /**A holder for each visible view**/
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pkm);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this,10, LinearLayoutManager.HORIZONTAL,false);
        //layoutManager = new StaggeredGridLayoutManager(10,LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        String[] myDataset = this.getResources().getStringArray(R.array.Pokemon);
        myAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemWasClicked(new MyAdapter.ItemWasClicked(){
            @Override
            public void itemWasClicked(View v, boolean ShowName) {
                if(v instanceof TextView) {
                    TextView t = (TextView) v;
                    itemClicked(t.getText().toString());
                }
            }
        });
    }

    public void itemClicked(String text) {
        Intent intent = new Intent();
        intent.putExtra("name", text);
        setResult(RESULT_OK, intent);
        finish();
    }
}
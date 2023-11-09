package edu.wcu.cs.ascott.cs467.activityforresult1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button selectPoke;

    private Button selectOpp;

    private ActivityResultLauncher<Intent> choosePkm;
    private ActivityResultLauncher<Intent> chooseOpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choosePkm = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");

                        TextView selectedPoke = findViewById(R.id.txt_selected_pokemon);
                        String text = getString(R.string.SelectedPokemon);
                        text += " " + name;
                        selectedPoke.setText(text);
                    }
        });
        chooseOpp = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");

                        TextView selectedOpp = findViewById(R.id.txt_selected_opponent);
                        String text = getString(R.string.SelectedOpponent);
                        text += " " + name;
                        selectedOpp.setText(text);
                    }
                });
        selectPoke = findViewById(R.id.select_pokemon);
        selectOpp = findViewById(R.id.but_select_opponent);

        selectPoke.setOnClickListener(this);
        selectOpp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.equals(selectPoke)) {
            intent = new Intent(this, SelectPkm.class);
            choosePkm.launch(intent);
        }
        else if (view.equals(selectOpp)) {
            intent = new Intent(this, SelectPkm.class);
            chooseOpp.launch(intent);
        }
    }
}
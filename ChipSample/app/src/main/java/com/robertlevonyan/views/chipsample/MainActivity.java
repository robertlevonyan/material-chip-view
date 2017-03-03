package com.robertlevonyan.views.chipsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;
import com.robertlevonyan.views.chip.OnCloseClickListener;
import com.robertlevonyan.views.chip.OnIconClickListener;
import com.robertlevonyan.views.chip.OnSelectClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chip chip = (Chip) findViewById(R.id.football_chip);

        chip.setOnChipClickListener(new OnChipClickListener() {
            @Override
            public void onChipClick(View v) {
                //Your action here...
            }
        });

        chip.setOnCloseClickListener(new OnCloseClickListener() {
            @Override
            public void onCloseClick(View v) {
                //Your action here...
            }
        });

        chip.setOnIconClickListener(new OnIconClickListener() {
            @Override
            public void onIconClick(View v) {
                //Your action here...
            }
        });

        chip.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                //Your action here...
            }
        });
    }
}

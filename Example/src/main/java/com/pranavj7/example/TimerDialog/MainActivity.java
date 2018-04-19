package com.pranavj7.example.TimerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.pranavj7.TimerDialog.TimerDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showTextDialog(View view) {
        TimerDialog dialog = new TimerDialog(this);
        dialog.setColor("#FF0000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("Content Title");
        dialog.setDuration(10000);
        dialog.setContentText(getString(R.string.content_text));
        dialog.setPositiveListener("Ok", new TimerDialog.OnPositiveListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        })
                .setNegativeListener("Cancel", new TimerDialog.OnNegativeListener() {
                    @Override
                    public void onClick(TimerDialog dialog) {
                        Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        dialog.setColor("#");
                    }
                }).show();
    }

    public void showPicDialog(View v) {
        TimerDialog dialog = new TimerDialog(this);
        dialog.setTitle("Content Title");
        dialog.setAnimationEnable(true);
        dialog.setDuration(10000);
        dialog.setContentImage(getResources().getDrawable(R.mipmap.p));
        dialog.setPositiveListener("Ok", new TimerDialog.OnPositiveListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        })
        .setNegativeListener(getString(R.string.cancel), new TimerDialog.OnNegativeListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).show();
    }
    public void showAllModeDialog(View view) {
        TimerDialog dialog = new TimerDialog(this);
        dialog.setTitle("Rewarded Video");
        dialog.setContentText("Watch a video to earn a life");
        dialog.setContentImage(getResources().getDrawable(R.mipmap.p));
        dialog.setAnimationEnable(true);
        dialog.setDuration(10000);
        dialog.setPositiveListener("watch video", new TimerDialog.OnPositiveListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).show();
    }

}

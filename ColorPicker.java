package com.dnoble.software.colorblender;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ColorPicker extends AppCompatActivity {

    private static final String COLOR_RETRIEVED = "com.dnoble.software.colorblender.color_retrieved";

    private SurfaceHolder colorHolder;
    private int redValue;
    private int greenValue;
    private int blueValue;

    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private Button finishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        final SurfaceView sView = (SurfaceView) findViewById(R.id.surfaceView);
        colorHolder = sView.getHolder();
        redValue = 0;
        greenValue = 0;
        blueValue = 0;

        red = (SeekBar) findViewById(R.id.redSlider);
        red.setMax(255);
        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar s) {
            }

            public void onProgressChanged(SeekBar s, int v, boolean u) {
                redValue = red.getProgress();
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }

            public void onStopTrackingTouch(SeekBar s) {
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }
        });

        green = (SeekBar) findViewById(R.id.greenSlider);
        green.setMax(255);
        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar s) {
            }

            public void onProgressChanged(SeekBar s, int v, boolean u) {
                greenValue = green.getProgress();
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }

            public void onStopTrackingTouch(SeekBar s) {
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }
        });

        blue = (SeekBar) findViewById(R.id.blueSlider);
        blue.setMax(255);
        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar s) {
            }

            public void onProgressChanged(SeekBar s, int v, boolean u) {
                blueValue = blue.getProgress();
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }

            public void onStopTrackingTouch(SeekBar s) {
                final Canvas colorBox = colorHolder.lockCanvas();
                colorBox.drawRGB(redValue, greenValue, blueValue);
                colorHolder.unlockCanvasAndPost(colorBox);
            }
        });

        finishedButton = (Button) findViewById(R.id.finishedButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int [] color = new int [] {redValue, greenValue, blueValue};
                Intent data = new Intent();
                data.putExtra(COLOR_RETRIEVED, color);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

    public static int[] colorSelected(Intent result) {
        return result.getIntArrayExtra(COLOR_RETRIEVED);
    }
}

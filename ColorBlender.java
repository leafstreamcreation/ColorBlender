package com.dnoble.software.colorblender;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ColorBlender extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final int REQUEST_CODE_FIRSTCOLOR = 0;
    private static final int REQUEST_CODE_SECONDCOLOR = 1;

    private int[] firstColor;
    private int[] secondColor;

    private SurfaceView firstColorBox;
    private SurfaceHolder firstHolder;
    private Button firstButton;
    private SurfaceView secondColorBox;
    private SurfaceHolder secondHolder;
    private Button secondButton;
    private SurfaceView colorBlendBox;
    private SurfaceHolder blendHolder;
    private SeekBar blendSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blender);

        firstColor = new int[]{0, 0, 0};
        secondColor = new int[]{0, 0, 0};

        firstColorBox = (SurfaceView) findViewById(R.id.color1View);
        firstHolder = firstColorBox.getHolder();
        firstHolder.addCallback(this);
        firstColorBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fetch the color
                Intent i = new Intent(ColorBlender.this, ColorPicker.class);
                startActivityForResult(i, REQUEST_CODE_FIRSTCOLOR);
            }
        });
        firstButton = (Button) findViewById(R.id.firstButton);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fetch the color
                Intent i = new Intent(ColorBlender.this, ColorPicker.class);
                startActivityForResult(i, REQUEST_CODE_FIRSTCOLOR);
                firstButton.setVisibility(View.GONE);
            }
        });
        secondColorBox = (SurfaceView) findViewById(R.id.color2View);
        secondHolder = secondColorBox.getHolder();
        secondHolder.addCallback(this);
        secondColorBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click event", "second color clicked!");
                //fetch the color
                Intent i = new Intent(ColorBlender.this, ColorPicker.class);
                startActivityForResult(i, REQUEST_CODE_SECONDCOLOR);
                //update the colorBox color
                final Canvas canvas = secondHolder.lockCanvas();
                canvas.drawRGB(secondColor[0], secondColor[1], secondColor[2]);
                secondHolder.unlockCanvasAndPost(canvas);
                //update the colorBlend color
                final double blendRatio = blendSlider.getProgress() / 100.0;
                final Canvas colorBox = blendHolder.lockCanvas();
                final int red = (int) ((1.0 - blendRatio) * firstColor[0] + blendRatio * secondColor[0]);
                final int green = (int) ((1.0 - blendRatio) * firstColor[1] + blendRatio * secondColor[1]);
                final int blue = (int) ((1.0 - blendRatio) * firstColor[2] + blendRatio * secondColor[2]);
                colorBox.drawRGB(red, green, blue);
                blendHolder.unlockCanvasAndPost(colorBox);
            }
        });
        secondButton = (Button) findViewById(R.id.secondButton);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //fetch the color
                Intent i = new Intent(ColorBlender.this, ColorPicker.class);
                startActivityForResult(i, REQUEST_CODE_SECONDCOLOR);
                //update the colorBox color
                final Canvas canvas = secondHolder.lockCanvas();
                canvas.drawRGB(secondColor[0], secondColor[1], secondColor[2]);
                secondHolder.unlockCanvasAndPost(canvas);
                //update the colorBlend color
                final double blendRatio = blendSlider.getProgress() / 100.0;
                final Canvas colorBox = blendHolder.lockCanvas();
                final int red = (int) ((1.0 - blendRatio) * firstColor[0] + blendRatio * secondColor[0]);
                final int green = (int) ((1.0 - blendRatio) * firstColor[1] + blendRatio * secondColor[1]);
                final int blue = (int) ((1.0 - blendRatio) * firstColor[2] + blendRatio * secondColor[2]);
                colorBox.drawRGB(red, green, blue);
                blendHolder.unlockCanvasAndPost(colorBox);
                secondButton.setVisibility(View.GONE);
            }
        });

        colorBlendBox = (SurfaceView) findViewById(R.id.blenderView);
        blendHolder = colorBlendBox.getHolder();
        blendHolder.addCallback(this);

        blendSlider = (SeekBar) findViewById(R.id.blenderBar);
        blendSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                final double blendRatio = blendSlider.getProgress() / 100.0;
                final Canvas colorBox = blendHolder.lockCanvas();
                final int red = (int) ((1.0 - blendRatio) * firstColor[0] + blendRatio * secondColor[0]);
                final int green = (int) ((1.0 - blendRatio) * firstColor[1] + blendRatio * secondColor[1]);
                final int blue = (int) ((1.0 - blendRatio) * firstColor[2] + blendRatio * secondColor[2]);

                colorBox.drawRGB(red, green, blue);
                blendHolder.unlockCanvasAndPost(colorBox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                final double blendRatio = blendSlider.getProgress() / 100.0;
                final Canvas colorBox = blendHolder.lockCanvas();
                final int red = (int) ((1.0 - blendRatio) * firstColor[0] + blendRatio * secondColor[0]);
                final int green = (int) ((1.0 - blendRatio) * firstColor[1] + blendRatio * secondColor[1]);
                final int blue = (int) ((1.0 - blendRatio) * firstColor[2] + blendRatio * secondColor[2]);

                colorBox.drawRGB(red, green, blue);
                blendHolder.unlockCanvasAndPost(colorBox);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //do nothing
    }
    public void surfaceCreated(SurfaceHolder holder) {
        if(holder == firstHolder) {
            final Canvas canvas = holder.lockCanvas();
            canvas.drawRGB(firstColor[0], firstColor[1], firstColor[2]);
            holder.unlockCanvasAndPost(canvas);
        }
        else if(holder == secondHolder){
            final Canvas canvas = holder.lockCanvas();
            canvas.drawRGB(secondColor[0], secondColor[1], secondColor[2]);
            holder.unlockCanvasAndPost(canvas);
        }
        else if(holder == blendHolder){
            final double blendRatio = blendSlider.getProgress() / 100.0;
            final Canvas colorBox = holder.lockCanvas();
            final int red = (int) ((1.0 - blendRatio) * firstColor[0] + blendRatio * secondColor[0]);
            final int green = (int) ((1.0 - blendRatio) * firstColor[1] + blendRatio * secondColor[1]);
            final int blue = (int) ((1.0 - blendRatio) * firstColor[2] + blendRatio * secondColor[2]);
            colorBox.drawRGB(red, green, blue);
            blendHolder.unlockCanvasAndPost(colorBox);
        }

    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        //do nothing
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FIRSTCOLOR) {
                firstColor = ColorPicker.colorSelected(data);
            }
            else {
                secondColor = ColorPicker.colorSelected(data);
            }
        }
    }

}

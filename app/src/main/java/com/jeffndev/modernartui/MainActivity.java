package com.jeffndev.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.jeffndev.modernartui.Models.RGB;

public class MainActivity extends AppCompatActivity {

    static final String MOMA_WEB_PAGE = "http://www.moma.org";

    SeekBar mSeekBar;
    View mPanelTopLeft;
    View mPanelBottomLeft;
    View mPanelTopRight;
    View mPanelMiddleRight;
    View mPanelBottomRight;

    final int SLIDER_MAX = 100;

    final RGB topLeftColor0 = new RGB(72, 81, 139);
    final RGB topLeftColor1 = new RGB(85, 107, 47);
    final RGB btmLeftColor0 = new RGB(255, 0, 255);
    final RGB btmLeftColor1 = new RGB(192, 255, 62);
    final RGB topRightColor0 = new RGB(176,23,31);
    final RGB topRightColor1 = new RGB(85, 26, 139);
    final RGB btmRightColor0 = new RGB( 0, 0, 255);
    final RGB btmRightColor1 = new RGB( 238, 201, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPanelTopLeft = findViewById(R.id.panel_top_left);
        mPanelBottomLeft = findViewById(R.id.panel_bottom_left);
        mPanelTopRight = findViewById(R.id.panel_top_right);
        mPanelMiddleRight = findViewById(R.id.panel_middle_right);
        mPanelBottomRight = findViewById(R.id.panel_bottom_right);

        //set original color..
        mPanelTopLeft.setBackgroundColor(Color.rgb(topLeftColor0.r, topLeftColor0.g, topLeftColor0.b));
        mPanelBottomLeft.setBackgroundColor(Color.rgb(btmLeftColor0.r, btmLeftColor0.g, btmLeftColor0.b));

        mPanelTopRight.setBackgroundColor(Color.rgb(topRightColor0.r, topRightColor0.g, topRightColor0.b));
        mPanelMiddleRight.setBackgroundColor(Color.WHITE);
        mPanelBottomRight.setBackgroundColor(Color.rgb(btmRightColor0.r, btmRightColor0.g, btmRightColor0.b));

        mSeekBar = (SeekBar)findViewById(R.id.seek_bar_color_slider);
        mSeekBar.setMax(SLIDER_MAX);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int r = topLeftColor0.r + (int)(progress*(topLeftColor1.r - topLeftColor0.r )/(1.0*SLIDER_MAX));
                int g = topLeftColor0.g + (int)(progress*(topLeftColor1.g - topLeftColor0.g )/(1.0*SLIDER_MAX));
                int b = topLeftColor0.b + (int)(progress*(topLeftColor1.b - topLeftColor0.b )/(1.0*SLIDER_MAX));
                mPanelTopLeft.setBackgroundColor(Color.rgb(r,g,b));
                r = btmLeftColor0.r + (int)(progress*(btmLeftColor1.r - btmLeftColor0.r )/(1.0*SLIDER_MAX));
                g = btmLeftColor0.g + (int)(progress*(btmLeftColor1.g - btmLeftColor0.g )/(1.0*SLIDER_MAX));
                b = btmLeftColor0.b + (int)(progress*(btmLeftColor1.b - btmLeftColor0.b )/(1.0*SLIDER_MAX));
                mPanelBottomLeft.setBackgroundColor(Color.rgb(r,g,b));
                r = topRightColor0.r + (int)(progress*(topRightColor1.r - topRightColor0.r )/(1.0*SLIDER_MAX));
                g = topRightColor0.g + (int)(progress*(topRightColor1.g - topRightColor0.g )/(1.0*SLIDER_MAX));
                b = topRightColor0.b + (int)(progress*(topRightColor1.b - topRightColor0.b )/(1.0*SLIDER_MAX));
                mPanelTopRight.setBackgroundColor(Color.rgb(r,g,b));
                r = btmRightColor0.r + (int)(progress*(btmRightColor1.r - btmRightColor0.r )/(1.0*SLIDER_MAX));
                g = btmRightColor0.g + (int)(progress*(btmRightColor1.g - btmRightColor0.g )/(1.0*SLIDER_MAX));
                b = btmRightColor0.b + (int)(progress*(btmRightColor1.b - btmRightColor0.b )/(1.0*SLIDER_MAX));
                mPanelBottomRight.setBackgroundColor(Color.rgb(r,g,b));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //EMPTY
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //EMPTY
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_more_info:
                AlertDialogFragment.newInstance().show(getFragmentManager(), "Alert");

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(getString(R.string.main_activity_options_menu_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.main_activity_alert_positive_button),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Uri webpage = Uri.parse(MOMA_WEB_PAGE);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                        startActivity(intent);
                                    }

                                }
                            })
                    .setNegativeButton(getString(R.string.main_activity_alert_negative_button),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
        }
    }

}

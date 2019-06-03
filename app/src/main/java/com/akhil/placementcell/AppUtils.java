package com.akhil.placementcell;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;


public class AppUtils {
    private static Dialog dialog1;

    public static int getHeight(Activity context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getWidth(Activity context) {
        return context.getWindowManager().getDefaultDisplay().getWidth();
    }
}
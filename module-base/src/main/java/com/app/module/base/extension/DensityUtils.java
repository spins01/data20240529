package com.app.module.base.extension;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 修改density，densityDpi值-直接更改系统内部对于目标尺寸而言的像素密度
 */
public class DensityUtils {
    private static final float WIDTH = 470;
    private static float appDensity;
    private static float appScaleDensity;

    public static void setDensity(final Application application, Activity activity) {
        //获取当前屏幕信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            //初始化赋值
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            //监听字体变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生更改，重新计算scaleDensity
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        //计算目标density scaledDensity
        //屏幕宽度：displayMetrics.widthPixels
        float targetDensity = displayMetrics.widthPixels / WIDTH;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
//在 Android 中，densityDpi 是屏幕密度的一个标准值。其计算公式是 densityDpi = density * 160。
//160 是 Android 中的一个基准 DPI 值，也称为 mdpi (medium density pixels)，表示每英寸 160 像素的密度。
//当 density = 1 时，densityDpi = 160，这意味着每英寸有 160 个像素。
//不同设备的 density 值会不一样，因此 densityDpi 也会不同。例如，density = 2 的设备，densityDpi = 320 (称为 xhdpi)。
//这个基准值的作用是确保在不同屏幕密度的设备上可以通过统一的单位来计算像素，从而实现屏幕适配。上述代码通过调整 density 和 densityDpi 值，使得应用在不同设备上的显示效果更为一致。
        int targetDensityDpi = (int) (targetDensity * 160);
        //替换Activity的值
        //px = dp * (dpi / 160)
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }
}


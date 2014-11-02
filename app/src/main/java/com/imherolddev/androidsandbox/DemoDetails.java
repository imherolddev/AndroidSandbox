package com.imherolddev.androidsandbox;

import android.app.Activity;

/**
 * Created by imherolddev on 8/23/2014.
 */
public class DemoDetails {

    public final int titleId;
    public final int descriptionId;
    public final Class<? extends Activity> activityClass;

    public DemoDetails (int titleId, int descriptionId, Class<? extends Activity> activityClass) {

        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;

    }

}

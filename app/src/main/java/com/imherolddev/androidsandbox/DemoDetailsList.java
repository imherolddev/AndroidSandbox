package com.imherolddev.androidsandbox;

import com.imherolddev.androidsandbox.demo_activities.device_motion.DeviceMotion;
import com.imherolddev.androidsandbox.fragment_example.FragmentActivity;

/**
 * Created by imherolddev on 8/23/2014.
 */
public final class DemoDetailsList {

    private DemoDetailsList() {}

    public static final DemoDetails[] DEMOS = {

            //add all demo classes here separated by comma

            new DemoDetails(R.string.motion_activity, R.string.motion_activity_description, DeviceMotion.class),
            new DemoDetails(R.string.fragment_activity, R.string.fragment_activity_description, FragmentActivity.class)

    };

}

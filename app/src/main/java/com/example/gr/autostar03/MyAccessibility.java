package com.example.gr.autostar03;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

@SuppressLint("NewApi")
public class MyAccessibility extends AccessibilityService {
    private static final String TAG = "MyAccessibility";
    String[] PACKAGES = {"com.android.settings", "cn.myhug.baobao"};

    @Override
    protected void onServiceConnected() {
        Log.i(TAG, "config success!");
//        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
//        accessibilityServiceInfo.packageNames = PACKAGES;
//        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
//        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
//        accessibilityServiceInfo.notificationTimeout = 1000;
//        setServiceInfo(accessibilityServiceInfo);
    }

    @SuppressLint("NewApi")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // TODO Auto-generated method stub
        int eventType = event.getEventType();
        String eventText = "";
        Log.i(TAG, "==============Start====================");
        AccessibilityNodeInfo nodeInfo = event.getSource();
        android.os.Debug.waitForDebugger();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> list = nodeInfo
                    .findAccessibilityNodeInfosByText("拆红包");
            for (AccessibilityNodeInfo n : list) {
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }

        eventText = eventText + ":" + eventType;
        Log.i(TAG, String.valueOf(eventType));
        Log.i(TAG, "=============END=====================");
    }

    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub

    }

}
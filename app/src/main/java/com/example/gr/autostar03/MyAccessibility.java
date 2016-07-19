package com.example.gr.autostar03;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.lang.reflect.Field;
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
        int eventType = event.getEventType();
        // Log.i(TAG, String.valueOf(eventType));


        String eventText = "";
        AccessibilityNodeInfo nodeInfo = event.getSource();

        if(eventType==AccessibilityEvent.TYPE_VIEW_SCROLLED){
//            Log.i(TAG, eventType+"==============Start====================");
            Log.i(TAG,"TYPE_VIEW_SCROLLED");
            getLastNode();
//            Log.i(TAG, eventType+"==============End====================");
        }

        if (nodeInfo != null&&false) {
            // Log.i(TAG, "==============Start====================");

            List<AccessibilityNodeInfo> list = nodeInfo
                    .findAccessibilityNodeInfosByText("流星雨");
            for (AccessibilityNodeInfo n : list) {
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            String tempText = String.valueOf(nodeInfo.getText());
            eventText += tempText;
            eventText = eventText + ":" + eventType;
            if (tempText != null && !"null".equals(tempText)) {
                //getMessage(nodeInfo);
                AccessibilityNodeInfo pani = nodeInfo.getParent();
                Log.i(TAG, "Parent---" + String.valueOf(pani.getText()));
                int childCount = pani.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    AccessibilityNodeInfo childNodeView = pani.getChild(i);
                    Log.i(TAG, "Child" + i + "---" + String.valueOf(childNodeView.getText()));
                }

                Log.i(TAG, eventText);
                Log.i(TAG, "=============END=====================");
            }
            //Log.i(TAG, "=============END=====================");
        }


    }

    public void getLastNode(){
        AccessibilityNodeInfo rowNode = getRootInActiveWindow();
        int ccount=rowNode.getChildCount();
        AccessibilityNodeInfo lastNode=null;
        if(ccount>0){

            for (int i=0;i<ccount;i++){
                AccessibilityNodeInfo tempNode=rowNode.getChild(i);
                String tempText=String.valueOf(tempNode.getText());
                if(tempText!=null&&!"null".equals(tempText)){
                    lastNode=tempNode;
                    Log.i(TAG,String.valueOf(lastNode.getText()));
                }
            }
        }
       if( lastNode!=null){
           Log.i(TAG,"!!!!!"+String.valueOf(lastNode.getText()));
       }
    }
    public void getAllNode() {
        AccessibilityNodeInfo rowNode = getRootInActiveWindow();
        if (rowNode == null) {
            Log.i(TAG, "noteInfo is　null");
            return;
        } else {
            recycle(rowNode);
        }
    }

    public void recycle(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            Log.i(TAG, "child widget----------------------------" + info.getClassName());
            Log.i(TAG, "showDialog:" + info.canOpenPopup());
            Log.i(TAG, "Text：" + info.getText());
            Log.i(TAG, "windowId:" + info.getWindowId());
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if (info.getChild(i) != null) {
                    recycle(info.getChild(i));
                }
            }
        }
    }

    public void getMessage(Object obj) {
        Log.i(TAG, "---------------------------------------Start--------------------------------");
        try {
            //此处可以通过Message.class来反射 也可以
            //Class.forName("包名+类名");得到对象
            //getFields();获取到Message类的所有属性
            Field[] keys = obj.getClass().getDeclaredFields();
            for (Field f : keys) {
                f.setAccessible(true);
            }
            for (Field f : keys) {
                String field = f.toString().substring(f.toString().lastIndexOf(".") + 1);
                //取出属性名称
                String str = "obj." + field + " --> " + f.get(obj);
                System.out.println(str);
                Log.i(TAG, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        Log.i(TAG, "---------------------------------------End--------------------------------");
    }

    public void shijianInfo(AccessibilityEvent event) {
        String eventText = "";
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "TYPE_VIEW_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "TYPE_VIEW_SELECTED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TYPE_VIEW_TEXT_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                eventText = "TYPE_ANNOUNCEMENT";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                eventText = "TYPE_VIEW_HOVER_ENTER";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                eventText = "TYPE_VIEW_HOVER_EXIT";
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                eventText = "TYPE_VIEW_SCROLLED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";
                break;
        }
        eventText = eventText + ":" + eventType;
        Log.i(TAG, eventText);
    }

    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub

    }

}
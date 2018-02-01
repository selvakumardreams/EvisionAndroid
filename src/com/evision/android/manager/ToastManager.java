package com.evision.android.manager;

import java.util.LinkedList;
import java.util.Queue;

import com.evision.customview.CustomToastMessage;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ToastManager extends Handler {

    private static final int MESSAGE_DISPLAY = 0xc2007;
    private static final int MESSAGE_ADD_VIEW = 0xc20074dd;
    private static final int MESSAGE_REMOVE = 0xc2007de1;

    private static ToastManager mInstance;

    private Queue<CustomToastMessage> msgQueue;
    private Animation inAnimation, outAnimation;

    private ToastManager() {
        msgQueue = new LinkedList<CustomToastMessage>();
    }

    /**
     * @return The currently used instance of the {@link ToastManager}.
     */
    public static synchronized ToastManager getInstance() {
        if (mInstance == null) {
            mInstance = new ToastManager();
        }
        return mInstance;
    }

    public void add(CustomToastMessage toastMsg) {
        msgQueue.add(toastMsg);
        if (inAnimation == null) {
            inAnimation = AnimationUtils.loadAnimation(toastMsg.getActivity(),
                    android.R.anim.fade_in);
        }
        if (outAnimation == null) {
            outAnimation = AnimationUtils.loadAnimation(toastMsg.getActivity(),
                    android.R.anim.fade_out);
        }
        displayMsg();
    }
    
    public void clearMsg(CustomToastMessage toastMsg) {
        if(msgQueue.contains(toastMsg)){
            // Avoid the message from being removed twice.
            removeMessages(MESSAGE_REMOVE);
            msgQueue.remove(toastMsg);
            removeMsg(toastMsg);
        }
    }

    /**
     * Removes all {@link AppMsg} from the queue.
     */
    public void clearAllMsg() {
        if (msgQueue != null) {
            msgQueue.clear();
        }
        removeMessages(MESSAGE_DISPLAY);
        removeMessages(MESSAGE_ADD_VIEW);
        removeMessages(MESSAGE_REMOVE);
    }

    /**
     * Displays the next {@link AppMsg} within the queue.
     */
    private void displayMsg() {
        if (msgQueue.isEmpty()) {
            return;
        }
        // First peek whether the AppMsg is being displayed.
        final CustomToastMessage toastMsg = msgQueue.peek();
        // If the activity is null we throw away the AppMsg.
        if (toastMsg.getActivity() == null) {
            msgQueue.poll();
        }
        final Message msg;
        if (!toastMsg.isShowing()) {
            // Display the AppMsg
            msg = obtainMessage(MESSAGE_ADD_VIEW);
            msg.obj = toastMsg;
            sendMessage(msg);
        } else {
            msg = obtainMessage(MESSAGE_DISPLAY);
            sendMessageDelayed(msg, toastMsg.getDuration()
                    + inAnimation.getDuration() + outAnimation.getDuration());
        }
    }

    private void removeMsg(final CustomToastMessage toastMsg) {
        ViewGroup parent = ((ViewGroup) toastMsg.getView().getParent());
        if (parent != null) {
            outAnimation.setAnimationListener(new OutAnimationListener(toastMsg));
            toastMsg.getView().startAnimation(outAnimation);
            // Remove the AppMsg from the queue.
            msgQueue.poll();
            if (toastMsg.isFloating()) {
                // Remove the AppMsg from the view's parent.
                parent.removeView(toastMsg.getView());
            } else {
                toastMsg.getView().setVisibility(View.INVISIBLE);
            }

            Message msg = obtainMessage(MESSAGE_DISPLAY);
            sendMessage(msg);
        }
    }

    private void addMsgToView(CustomToastMessage toastMsg) {
        View view = toastMsg.getView();
        if (view.getParent() == null) {
            toastMsg.getActivity().addContentView(
                    view,
                    toastMsg.getLayoutParams());
        }
        view.startAnimation(inAnimation);
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        final Message msg = obtainMessage(MESSAGE_REMOVE);
        msg.obj = toastMsg;
        sendMessageDelayed(msg, toastMsg.getDuration());
    }

    @Override
    public void handleMessage(Message msg) {
        final CustomToastMessage toastMsg;
        switch (msg.what) {
            case MESSAGE_DISPLAY:
                displayMsg();
                break;
            case MESSAGE_ADD_VIEW:
                toastMsg = (CustomToastMessage) msg.obj;
                addMsgToView(toastMsg);
                break;
            case MESSAGE_REMOVE:
                toastMsg = (CustomToastMessage) msg.obj;
                removeMsg(toastMsg);
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }

    private static class OutAnimationListener implements Animation.AnimationListener {

        private CustomToastMessage toastMsg;

        private OutAnimationListener(CustomToastMessage appMsg) {
            this.toastMsg = appMsg;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (!toastMsg.isFloating()) {
                toastMsg.getView().setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
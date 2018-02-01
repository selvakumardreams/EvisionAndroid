
package com.evision.customview;


import com.evision.android.R;
import com.evision.android.manager.ToastManager;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CustomToastMessage {

    /**
     * Show the view or text notification for a short period of time. This time
     * could be user-definable. This is the default.
     *
     * @see #setDuration
     */
    public static final int LENGTH_SHORT = 3000;

    /**
     * Show the view or text notification for a long period of time. This time
     * could be user-definable.
     *
     * @see #setDuration
     */
    public static final int LENGTH_LONG = 5000;

    /**
     * Show the text notification for a long period of time with a negative style.
     */
    public static final Style STYLE_ALERT = new Style(LENGTH_LONG, R.color.alert);

    /**
     * Show the text notification for a short period of time with a positive style.
     */
    public static final Style STYLE_CONFIRM = new Style(LENGTH_SHORT, R.color.confirm);

    /**
     * Show the text notification for a short period of time with a neutral style.
     */
    public static final Style STYLE_INFO = new Style(LENGTH_SHORT, R.color.info);

    private final Activity mContext;
    private int mDuration = LENGTH_SHORT;
    private View mView;
    private LayoutParams mLayoutParams;
    private boolean mFloating;

    public CustomToastMessage(Activity context) {
        mContext = context;
    }

    public static CustomToastMessage makeText(Activity context, CharSequence text, Style style) {
        return makeText(context, text, style, R.layout.custom_toast);
    }
    
    public static CustomToastMessage makeText(Activity context, CharSequence text, Style style, float textSize) {
        return makeText(context, text, style, R.layout.custom_toast, textSize);
    }

    public static CustomToastMessage makeText(Activity context, CharSequence text, Style style, int layoutId) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true);
    }
    
    public static CustomToastMessage makeText(Activity context, CharSequence text, Style style, int layoutId, float textSize) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true, textSize);
    }

    public static CustomToastMessage makeText(Activity context, CharSequence text, Style style, View customView) {
       return makeText(context, text, style, customView, false);
    }

    private static CustomToastMessage makeText(Activity context, CharSequence text, Style style, View view, boolean floating) {
        return makeText(context, text, style, view, floating, 0);
    }
    
    private static CustomToastMessage makeText(Activity context, CharSequence text, Style style, View view, boolean floating, float textSize) {
    	CustomToastMessage result = new CustomToastMessage(context);

        view.setBackgroundResource(style.background);

        TextView tv = (TextView) view.findViewById(android.R.id.message);
        if(textSize > 0) tv.setTextSize(textSize);
        tv.setText(text);

        result.mView = view;
        result.mDuration = style.duration;
        result.mFloating = floating;

        return result;
    }

    public static CustomToastMessage makeText(Activity context, int resId, Style style, View customView, boolean floating) {
        return makeText(context, context.getResources().getText(resId), style, customView, floating);
    }

    public static CustomToastMessage makeText(Activity context, int resId, Style style)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), style);
    }

    
    public static CustomToastMessage makeText(Activity context, int resId, Style style, int layoutId)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), style, layoutId);
    }

    /**
     * Show the view for the specified duration.
     */
    public void show() {
        ToastManager manager = ToastManager.getInstance();
        manager.add(this);
    }

    /**
     * @return <code>true</code> if the {@link AppMsg} is being displayed, else <code>false</code>.
     */
    public boolean isShowing() {
        if (mFloating) {
            return mView != null && mView.getParent() != null;
        } else {
            return mView.getVisibility() == View.VISIBLE;
        }
    }

    /**
     * Close the view if it's showing, or don't show it if it isn't showing yet.
     * You do not normally have to call this.  Normally view will disappear on its own
     * after the appropriate duration.
     */
    public void cancel() {
        ToastManager.getInstance().clearMsg(this);

    }

    /**
     * Cancels all queued {@link AppMsg}s. If there is a {@link AppMsg}
     * displayed currently, it will be the last one displayed.
     */
    public static void cancelAll() {
        ToastManager.getInstance().clearAllMsg();
    }

    /**
     * Return the activity.
     */
    public Activity getActivity() {
        return mContext;
    }

    /**
     * Set the view to show.
     *
     * @see #getView
     */
    public void setView(View view) {
        mView = view;
    }

    /**
     * Return the view.
     *
     * @see #setView
     */
    public View getView() {
        return mView;
    }

    /**
     * Set how long to show the view for.
     *
     * @see #LENGTH_SHORT
     * @see #LENGTH_LONG
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    /**
     * Return the duration.
     *
     * @see #setDuration
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * Update the text in a AppMsg that was previously created using one of the makeText() methods.
     *
     * @param resId The new text for the AppMsg.
     */
    public void setText(int resId) {
        setText(mContext.getText(resId));
    }

    /**
     * Update the text in a AppMsg that was previously created using one of the makeText() methods.
     *
     * @param s The new text for the AppMsg.
     */
    public void setText(CharSequence s) {
        if (mView == null) {
            throw new RuntimeException("This AppMsg was not created with AppMsg.makeText()");
        }
        TextView tv = (TextView) mView.findViewById(android.R.id.message);
        if (tv == null) {
            throw new RuntimeException("This AppMsg was not created with AppMsg.makeText()");
        }
        tv.setText(s);
    }

    /**
     * Gets the crouton's layout parameters, constructing a default if necessary.
     *
     * @return the layout parameters
     */
    public LayoutParams getLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        return mLayoutParams;
    }

    /**
     * Sets the layout parameters which will be used to display the crouton.
     *
     * @param layoutParams The layout parameters to use.
     * @return <code>this</code>, for chaining.
     */
    public CustomToastMessage setLayoutParams(LayoutParams layoutParams) {
        mLayoutParams = layoutParams;
        return this;
    }

    /**
     * Constructs and sets the layout parameters to have some gravity.
     *
     * @param gravity the gravity of the Crouton
     * @return <code>this</code>, for chaining.
     * @see android.view.Gravity
     */
    public CustomToastMessage setLayoutGravity(int gravity) {
        mLayoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, gravity);
        return this;
    }

    /**
     * Return the value of floating.
     *
     * @see #setFloating(boolean)
     */
    public boolean isFloating() {
        return mFloating;
    }

    /**
     * Sets the value of floating.
     *
     * @param mFloating
     */
    public void setFloating(boolean mFloating) {
        this.mFloating = mFloating;
    }

    /**
     * The style for a {@link AppMsg}.
     *
     * @author e.shishkin
     */
    public static class Style {

        private final int duration;
        private final int background;

        /**
         * Construct an {@link AppMsg.Style} object.
         *
         * @param duration How long to display the message. Either
         *                 {@link #LENGTH_SHORT} or {@link #LENGTH_LONG}
         * @param resId    resource for AppMsg background
         */
        public Style(int duration, int resId) {
            this.duration = duration;
            this.background = resId;
        }

        /**
         * Return the duration in milliseconds.
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Return the resource id of background.
         */
        public int getBackground() {
            return background;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof CustomToastMessage.Style)) {
                return false;
            }
            Style style = (Style) o;
            return style.duration == duration
                    && style.background == background;
        }

    }

}

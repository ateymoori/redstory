package teymoori.red.story.utils.customViews;

import android.content.Context;
import android.graphics.Canvas;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;

import com.lid.lib.LabelViewHelper;

public class LabelViewConstraint extends ConstraintLayout {
    LabelViewHelper utils;
    public LabelViewConstraint(Context context) {
        this(context, null);
    }
    public LabelViewConstraint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LabelViewConstraint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        utils = new LabelViewHelper(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        utils.onDraw(canvas, getMeasuredWidth(), getMeasuredHeight());
    }
    public void setLabelHeight(int height) {
        utils.setLabelHeight(this, height);
    }
    public int getLabelHeight() {
        return utils.getLabelHeight();
    }
    public void setLabelDistance(int distance) {
        utils.setLabelDistance(this, distance);
    }
    public int getLabelDistance() {
        return utils.getLabelDistance();
    }
    public boolean isLabelVisual() {
        return utils.isLabelVisual();
    }
    public void setLabelVisual(boolean enable) {
        utils.setLabelVisual(this, enable);
    }
    public int getLabelOrientation() {
        return utils.getLabelOrientation();
    }
    public void setLabelOrientation(int orientation) {
        utils.setLabelOrientation(this, orientation);
    }
    public int getLabelTextColor() {
        return utils.getLabelTextColor();
    }
    public void setLabelTextColor(int textColor) {
        utils.setLabelTextColor(this, textColor);
    }
    public int getLabelBackgroundColor() {
        return utils.getLabelBackgroundColor();
    }
    public void setLabelBackgroundColor(int backgroundColor) {
        utils.setLabelBackgroundColor(this, backgroundColor);
    }
    public String getLabelText() {
        return utils.getLabelText();
    }
    public void setLabelText(String text) {
        utils.setLabelText(this, text);
    }
    public int getLabelTextSize() {
        return utils.getLabelTextSize();
    }
    public void setLabelTextSize(int textSize) {
        utils.setLabelTextSize(this, textSize);
    }
}
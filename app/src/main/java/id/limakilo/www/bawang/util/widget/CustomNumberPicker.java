package id.limakilo.www.bawang.util.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import id.limakilo.www.bawang.R;

/**
 * Created by walesadanto on 13/9/15.
 */
public class CustomNumberPicker extends NumberPicker{
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)//For backward-compability

        public CustomNumberPicker(Context context) {
            super(context);
        }

        public CustomNumberPicker(Context context, AttributeSet attrs) {
            super(context, attrs);
            processAttributeSet(attrs);
        }

        public CustomNumberPicker(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            processAttributeSet(attrs);
        }
        private void processAttributeSet(AttributeSet attrs) {
            //This method reads the parameters given in the xml file and sets the properties according to it
            this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
            this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
//            setDividerColor(this, getResources().getColor(attrs.getAttributeIntValue(null, "dividerColor", 0)));
            setDividerColor(this, getResources().getColor(R.color.color_accent));
        }

    private void setDividerColor(NumberPicker picker, int color) {
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}

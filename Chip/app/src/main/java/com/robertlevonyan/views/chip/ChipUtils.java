package com.robertlevonyan.views.chip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by robert on 3/1/17.
 */

class ChipUtils {
    public static final int IMAGE_ID = 0x00910518;
    public static final int TEXT_ID = 0x00059118;

    public static Bitmap getScaledBitmap(Context context, Bitmap bitmap) {
        int width = (int) context.getResources().getDimension(R.dimen.chip_height);
        return Bitmap.createScaledBitmap(bitmap, width, width, false);
    }

    public static Bitmap getSquareBitmap(Bitmap bitmap) {
        final Bitmap output;

        if (bitmap.getWidth() >= bitmap.getHeight()) {
            output = Bitmap.createBitmap(
                    bitmap,
                    bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                    0,
                    bitmap.getHeight(),
                    bitmap.getHeight()
            );
        } else {
            output = Bitmap.createBitmap(
                    bitmap,
                    0,
                    bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                    bitmap.getWidth(),
                    bitmap.getWidth()
            );
        }
        return output;
    }

    public static Bitmap getCircleBitmap(Context context, Bitmap bitmap) {
        int width = (int) context.getResources().getDimension(R.dimen.chip_height);
        final Bitmap output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, width);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static void setIconColor(ImageView icon, int color) {
        Drawable iconDrawable = icon.getDrawable();
        icon.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        icon.setImageDrawable(iconDrawable);
    }
}

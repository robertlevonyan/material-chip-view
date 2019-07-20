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
 * Created by robert on 2/27/2017.
 */

public class ChipUtils {

    static final int IMAGE_ID = 0x00910518;
    static final int TEXT_ID = 0x00059118;

    private static int[] colors = {0xd32f2f, 0xC2185B, 0x7B1FA2, 0x512DA8, 0x303F9F, 0x1976D2, 0x0288D1, 0x0097A7, 0x00796B, 0x388E3C, 0x689F38,
            0xAFB42B, 0xFBC02D, 0xFFA000, 0xF57C00, 0xE64A19, 0x5D4037, 0x616161, 0x455A64};

    public static Bitmap getScaledBitmap(Context context, Bitmap bitmap) {
        int size = context.getResources().getDimensionPixelSize(R.dimen.chip_height);
        return Bitmap.createScaledBitmap(bitmap, size, size, false);
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

    public static Bitmap getCircleBitmap(Context context, Bitmap bitmap, float radius) {
        int size = context.getResources().getDimensionPixelSize(R.dimen.chip_height);
        final Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, size, size);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getCircleBitmapWithText(Context context, String text, int textColor, int bgColor, float radius) {
        int size = context.getResources().getDimensionPixelSize(R.dimen.chip_height);
        final Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Paint textPaint = new Paint();
        final Rect rect = new Rect(0, 0, size, size);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(bgColor);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        textPaint.setColor(textColor);
        textPaint.setStrokeWidth(30);
        textPaint.setTextSize(45);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        int xPos;
        int yPos;
        if (text.length() == 1) {
            xPos = (int) ((canvas.getWidth() / 2) + ((textPaint.descent() + textPaint.ascent()) / 2));
            yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        } else {
            xPos = (int) ((canvas.getWidth() / 3) + ((textPaint.descent() + textPaint.ascent()) / 2));
            yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        }

        canvas.drawBitmap(output, rect, rect, paint);
        canvas.drawText(text, xPos, yPos, textPaint);

        return output;
    }

    public static String generateText(String iconText) {
        if (iconText.isEmpty()) {
            throw new IllegalStateException("Icon text must have at least one symbol");
        }
        if (iconText.length() == 1 || iconText.length() == 2) {
            return iconText;
        }

        String[] parts = iconText.split(" ");
        if (parts.length == 1) {
            String text = parts[0];
            text = text.substring(0, 2);

            String f = text.substring(0, 1);
            String s = text.substring(1, 2);

            f = f.toUpperCase();
            s = s.toLowerCase();

            text = f.concat(s);

            return text;
        }
        String first = parts[0];
        String second = parts[1];

        first = first.substring(0, 1);
        first = first.toUpperCase();
        second = second.substring(0, 1);
        second = second.toUpperCase();

        return first.concat(second);

    }

    public static void setIconColor(ImageView icon, int color) {
        Drawable iconDrawable = icon.getDrawable();
        icon.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        icon.setImageDrawable(iconDrawable);
    }
}


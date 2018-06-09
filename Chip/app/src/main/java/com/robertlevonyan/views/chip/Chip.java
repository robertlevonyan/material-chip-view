package com.robertlevonyan.views.chip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.robertlevonyan.views.chip.ChipUtils.IMAGE_ID;
import static com.robertlevonyan.views.chip.ChipUtils.TEXT_ID;
import static com.robertlevonyan.views.chip.ChipUtils.generateText;
import static com.robertlevonyan.views.chip.ChipUtils.getCircleBitmap;
import static com.robertlevonyan.views.chip.ChipUtils.getCircleBitmapWithText;
import static com.robertlevonyan.views.chip.ChipUtils.getScaledBitmap;
import static com.robertlevonyan.views.chip.ChipUtils.getSquareBitmap;
import static com.robertlevonyan.views.chip.ChipUtils.setIconColor;

/**
 * Created by robert on 3/1/17.
 */

public class Chip extends RelativeLayout {
    private String chipText;
    private boolean hasIcon;
    private Drawable chipIcon;
    private Bitmap chipIconBitmap;
    private boolean closable;
    private boolean selectable;
    private int backgroundColor;
    private int selectedBackgroundColor;
    private int textColor;
    private int selectedTextColor;
    private int closeColor;
    private int selectedCloseColor;
    private int cornerRadius;
    private int strokeSize;
    private int strokeColor;
    private String iconText;
    private int iconTextColor;
    private int iconTextBackgroundColor;

    private ImageView closeIcon;
    private ImageView selectIcon;
    private TextView chipTextView;

    private boolean selected = false;

    private OnCloseClickListener onCloseClickListener;
    private OnSelectClickListener onSelectClickListener;
    private OnChipClickListener onChipClickListener;
    private OnIconClickListener onIconClickListener;

    public Chip(Context context) {
        this(context, null, 0);
    }

    public Chip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initTypedArray(attrs);

        initChipClick();
    }

    private void initChipClick() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChipClickListener != null) {
                    onChipClickListener.onChipClick(v);
                }
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        buildView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams thisParams = getLayoutParams();

        thisParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        thisParams.height = (int) getResources().getDimension(R.dimen.chip_height);

        this.setLayoutParams(thisParams);
    }

    private void buildView() {
        initCloseIcon();
        initSelectIcon();
        initBackgroundColor();
        initTextView();
        initImageIcon();
    }

    private void initSelectClick() {
        selectIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectChip(v);
            }
        });
    }

    private void selectChip(View v) {
        if (!selectable) {
            return;
        }

        selected = !selected;
        initBackgroundColor();
        initTextView();
        selectIcon.setImageResource(R.drawable.ic_select);
        setIconColor(selectIcon, selected ? selectedCloseColor : closeColor);
        if (onSelectClickListener != null) {
            onSelectClickListener.onSelectClick(v, selected);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initCloseClick() {
        closeIcon.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        closeChip(v, true);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        closeChip(v, false);
                        return true;
                }
                return false;
            }
        });
    }

    private void closeChip(View v, boolean clicked) {
        selected = !selected;

        initBackgroundColor();
        initTextView();
        closeIcon.setImageResource(R.drawable.ic_close);
        setIconColor(closeIcon, selected ? selectedCloseColor : closeColor);
        if (onCloseClickListener != null && clicked) {
            onCloseClickListener.onCloseClick(v);
        }
    }

    private void initSelectIcon() {
        if (!selectable) {
            return;
        }

        selectIcon = new ImageView(getContext());

        LayoutParams selectIconParams = new LayoutParams((int) getResources().getDimension(R.dimen.chip_close_icon_size2), (int) getResources().getDimension(R.dimen.chip_close_icon_size2));
        selectIconParams.addRule(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? END_OF : RIGHT_OF, TEXT_ID);
        selectIconParams.addRule(CENTER_VERTICAL);
        selectIconParams.setMargins(
                (int) getResources().getDimension(R.dimen.chip_close_horizontal_margin),
                0,
                (int) getResources().getDimension(R.dimen.chip_close_horizontal_margin),
                0
        );

        selectIcon.setLayoutParams(selectIconParams);
        selectIcon.setScaleType(ImageView.ScaleType.CENTER);
        selectIcon.setImageResource(R.drawable.ic_select);
        setIconColor(selectIcon, closeColor);

        initSelectClick();

        addView(selectIcon);
    }

    private void initCloseIcon() {
        if (!closable) {
            return;
        }

        if (closeIcon == null) {
            closeIcon = new ImageView(getContext());
        }

        LayoutParams closeIconParams = new LayoutParams((int) getResources().getDimension(R.dimen.chip_close_icon_size2), (int) getResources().getDimension(R.dimen.chip_close_icon_size2));
        closeIconParams.addRule(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? END_OF : RIGHT_OF, TEXT_ID);
        closeIconParams.addRule(CENTER_VERTICAL);
        closeIconParams.setMargins(
                (int) getResources().getDimension(R.dimen.chip_close_horizontal_margin),
                0,
                (int) getResources().getDimension(R.dimen.chip_close_horizontal_margin),
                0
        );

        closeIcon.setLayoutParams(closeIconParams);
        closeIcon.setScaleType(ImageView.ScaleType.CENTER);
        closeIcon.setImageResource(R.drawable.ic_close);
        setIconColor(closeIcon, closeColor);

        initCloseClick();

        addView(closeIcon);
    }

    private void initImageIcon() {
        if (!hasIcon) {
            return;
        }

        ImageView icon = new ImageView(getContext());
        LayoutParams iconParams = new LayoutParams((int) getResources().getDimension(R.dimen.chip_height), (int) getResources().getDimension(R.dimen.chip_height));
        iconParams.addRule(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? ALIGN_PARENT_START : ALIGN_PARENT_LEFT);
        icon.setLayoutParams(iconParams);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setId(IMAGE_ID);

        if (chipIcon != null && ((BitmapDrawable) chipIcon).getBitmap() != null) {
            Bitmap bitmap = ((BitmapDrawable) chipIcon).getBitmap();
            bitmap = getSquareBitmap(bitmap);
            bitmap = getScaledBitmap(getContext(), bitmap);
            icon.setImageBitmap(getCircleBitmap(getContext(), bitmap));
        }
        if (chipIconBitmap != null) {
            chipIconBitmap = getSquareBitmap(chipIconBitmap);
            icon.setImageBitmap(getCircleBitmap(getContext(), chipIconBitmap));
            icon.bringToFront();
        }
        if (iconText != null && !iconText.equals("")) {
            Bitmap textBitmap = getCircleBitmapWithText(getContext(), iconText, iconTextColor, iconTextBackgroundColor);
            icon.setImageBitmap(textBitmap);
        }

        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIconClickListener != null) {
                    onIconClickListener.onIconClick(v);
                }
            }
        });

        addView(icon);
    }

    private void initTextView() {
        if (!ViewCompat.isAttachedToWindow(this)) {
            return;
        }

        if (chipTextView == null) {
            chipTextView = new TextView(getContext());
        }

        LayoutParams chipTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (hasIcon || closable || selectable) {
            chipTextParams.addRule(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? END_OF : RIGHT_OF, IMAGE_ID);
            chipTextParams.addRule(CENTER_VERTICAL);
        } else {
            chipTextParams.addRule(CENTER_IN_PARENT);
        }

        @Px int startMargin = hasIcon ? (int) getResources().getDimension(R.dimen.chip_icon_horizontal_margin) : (int) getResources().getDimension(R.dimen.chip_horizontal_padding);
        @Px int endMargin = closable || selectable ? 0 : (int) getResources().getDimension(R.dimen.chip_horizontal_padding);
        chipTextParams.setMargins(
                startMargin,
                0,
                endMargin,
                0
        );

        chipTextView.setLayoutParams(chipTextParams);
        chipTextView.setTextColor(selected ? selectedTextColor : textColor);
        chipTextView.setText(chipText);
        chipTextView.setId(TEXT_ID);

        this.removeView(chipTextView);
        this.addView(chipTextView);
    }

    private void initBackgroundColor() {
        GradientDrawable bgDrawable = new GradientDrawable();
        bgDrawable.setShape(GradientDrawable.RECTANGLE);
        bgDrawable.setCornerRadii(new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius,
                cornerRadius, cornerRadius, cornerRadius, cornerRadius});
        bgDrawable.setColor(selected ? selectedBackgroundColor : backgroundColor);
        bgDrawable.setStroke(strokeSize, strokeColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(bgDrawable);
        } else {
            setBackgroundDrawable(bgDrawable);
        }
    }

    private void initTypedArray(AttributeSet attrs) {
        TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Chip, 0, 0);

        chipText = ta.getString(R.styleable.Chip_mcv_chipText);
        hasIcon = ta.getBoolean(R.styleable.Chip_mcv_hasIcon, false);
        chipIcon = ta.getDrawable(R.styleable.Chip_mcv_chipIcon);
        closable = ta.getBoolean(R.styleable.Chip_mcv_closable, false);
        selectable = ta.getBoolean(R.styleable.Chip_mcv_selectable, false);
        backgroundColor = ta.getColor(R.styleable.Chip_mcv_backgroundColor, ContextCompat.getColor(getContext(), R.color.colorChipBackground));
        selectedBackgroundColor = ta.getColor(R.styleable.Chip_mcv_selectedBackgroundColor, ContextCompat.getColor(getContext(), R.color.colorChipBackgroundClicked));
        textColor = ta.getColor(R.styleable.Chip_mcv_textColor, ContextCompat.getColor(getContext(), R.color.colorChipText));
        selectedTextColor = ta.getColor(R.styleable.Chip_mcv_selectedTextColor, ContextCompat.getColor(getContext(), R.color.colorChipTextClicked));
        closeColor = ta.getColor(R.styleable.Chip_mcv_closeColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseInactive));
        selectedCloseColor = ta.getColor(R.styleable.Chip_mcv_selectedCloseColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseClicked));
        cornerRadius = (int) ta.getDimension(R.styleable.Chip_mcv_cornerRadius, getResources().getDimension(R.dimen.chip_height) / 2);
        strokeSize = (int) ta.getDimension(R.styleable.Chip_mcv_strokeSize, 0);
        strokeColor = ta.getColor(R.styleable.Chip_mcv_strokeColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseClicked));
        iconText = ta.getString(R.styleable.Chip_mcv_iconText);
        iconTextColor = ta.getColor(R.styleable.Chip_mcv_iconTextColor, ContextCompat.getColor(getContext(), R.color.colorChipBackgroundClicked));
        iconTextBackgroundColor = ta.getColor(R.styleable.Chip_mcv_iconTextColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseClicked));

        ta.recycle();
    }

    public String getChipText() {
        return chipText;
    }

    public void setChipText(String chipText) {
        this.chipText = chipText;
        requestLayout();
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
        requestLayout();
    }

    public Drawable getChipIcon() {
        return chipIcon;
    }

    public void setChipIcon(Drawable chipIcon) {
        this.chipIcon = chipIcon;
        requestLayout();
    }

    public void setChipIcon(Bitmap chipIcon) {
        this.chipIconBitmap = chipIcon;
        requestLayout();
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
        this.selectable = false;
        this.selected = false;
        requestLayout();
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        throw new RuntimeException("Use changeBackgroundColor instead of setBackgroundColor");
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void changeBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        requestLayout();
    }

    public int getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }

    public void changeSelectedBackgroundColor(int selectedBackgroundColor) {
        this.selectedBackgroundColor = selectedBackgroundColor;
        requestLayout();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        requestLayout();
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        requestLayout();
    }

    public int getCloseColor() {
        return closeColor;
    }

    public void setCloseColor(int closeColor) {
        this.closeColor = closeColor;
        requestLayout();
    }

    public int getSelectedCloseColor() {
        return selectedCloseColor;
    }

    public void setSelectedCloseColor(int selectedCloseColor) {
        this.selectedCloseColor = selectedCloseColor;
        requestLayout();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        requestLayout();
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        this.closable = false;
        this.selected = false;
        requestLayout();
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
        requestLayout();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        requestLayout();
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText, int iconTextColor, int iconTextBackgroundColor) {
        this.iconText = generateText(iconText);
        this.iconTextColor = iconTextColor == 0 ? ContextCompat.getColor(getContext(), R.color.colorChipBackgroundClicked) : iconTextColor;
        this.iconTextBackgroundColor = iconTextBackgroundColor == 0 ? ContextCompat.getColor(getContext(), R.color.colorChipCloseClicked) : iconTextBackgroundColor;
        requestLayout();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if (!selectable) {
            return;
        }

        this.selected = selected;
        requestLayout();
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        this.onCloseClickListener = onCloseClickListener;
    }

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    public void setOnChipClickListener(OnChipClickListener onChipClickListener) {
        this.onChipClickListener = onChipClickListener;
    }

    public void setOnIconClickListener(OnIconClickListener onIconClickListener) {
        this.onIconClickListener = onIconClickListener;
    }
}

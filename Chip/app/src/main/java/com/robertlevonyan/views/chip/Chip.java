package com.robertlevonyan.views.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.robertlevonyan.views.chip.ChipUtils.IMAGE_ID;
import static com.robertlevonyan.views.chip.ChipUtils.TEXT_ID;
import static com.robertlevonyan.views.chip.ChipUtils.getCircleBitmap;
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
    private boolean closable;
    private boolean selectable;
    private int backgroundColor;
    private int selectedBackgroundColor;
    private int textColor;
    private int selectedTextColor;
    private int closeColor;
    private int selectedCloseColor;

    private ImageView closeIcon;
    private ImageView selectIcon;

    private boolean clicked;
    private boolean selected;

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

        buildView();

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            buildView();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams thisParams = getLayoutParams();

        thisParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        thisParams.height = (int) getResources().getDimension(R.dimen.chip_height);

        this.setLayoutParams(thisParams);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        buildView();
    }

    private void buildView() {
        initBackgroundColor();
        initTextView();
        initImageIcon();
        initCloseIcon();
        initSelectIcon();
    }

    private void initSelectClick() {
        selectIcon.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        onSelectTouchDown();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        onSelectTouchUp(v);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void onSelectTouchDown() {
        clicked = true;
        initBackgroundColor();
        initTextView();
        selectIcon.setImageResource(R.drawable.ic_select);
        setIconColor(selectIcon, selectedCloseColor);
    }

    private void onSelectTouchUp(View v) {
        if (selected) {
            clicked = false;
            initBackgroundColor();
            initTextView();
            selectIcon.setImageResource(R.drawable.ic_select);
            setIconColor(selectIcon, closeColor);
        }
        selected = !selected;
        if (onSelectClickListener != null) {
            onSelectClickListener.onSelectClick(v, selected);
        }
    }

    private void initCloseClick() {
        closeIcon.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        onCloseTouchDown();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        onCloseTouchUp(v);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void onCloseTouchDown() {
        clicked = true;
        initBackgroundColor();
        initTextView();
        closeIcon.setImageResource(R.drawable.ic_close);
        setIconColor(closeIcon, selectedCloseColor);
    }

    private void onCloseTouchUp(View v) {
        clicked = false;
        initBackgroundColor();
        initTextView();
        closeIcon.setImageResource(R.drawable.ic_close);
        setIconColor(closeIcon, closeColor);

        if (onCloseClickListener != null) {
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

        closeIcon = new ImageView(getContext());

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
        TextView chipTextView = new TextView(getContext());

        LayoutParams chipTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (hasIcon || closable || selectable) {
            chipTextParams.addRule(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? END_OF : RIGHT_OF, IMAGE_ID);
            chipTextParams.addRule(CENTER_VERTICAL);
        } else {
            chipTextParams.addRule(CENTER_IN_PARENT);
        }

        int startMargin = hasIcon ? (int) getResources().getDimension(R.dimen.chip_icon_horizontal_margin) : (int) getResources().getDimension(R.dimen.chip_horizontal_padding);
        int endMargin = closable || selectable ? 0 : (int) getResources().getDimension(R.dimen.chip_horizontal_padding);
        chipTextParams.setMargins(
                startMargin,
                0,
                endMargin,
                0
        );

        chipTextView.setLayoutParams(chipTextParams);
        chipTextView.setTextColor(clicked ? selectedTextColor : textColor);
        chipTextView.setText(chipText);
        chipTextView.setId(TEXT_ID);

        this.addView(chipTextView);
    }

    private void initBackgroundColor() {
        GradientDrawable bgDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
                clicked ? selectedBackgroundColor : backgroundColor,
                clicked ? selectedBackgroundColor : backgroundColor}
        );
        bgDrawable.setCornerRadius(getResources().getDimension(R.dimen.chip_height) / 2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(bgDrawable);
        } else {
            setBackgroundDrawable(bgDrawable);
        }
    }

    private void initTypedArray(AttributeSet attrs) {
        TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Chip, 0, 0);

        chipText = ta.getString(R.styleable.Chip_chipText);
        hasIcon = ta.getBoolean(R.styleable.Chip_hasIcon, false);
        chipIcon = ta.getDrawable(R.styleable.Chip_chipIcon);
        closable = ta.getBoolean(R.styleable.Chip_closable, false);
        selectable = ta.getBoolean(R.styleable.Chip_selectable, false);
        backgroundColor = ta.getColor(R.styleable.Chip_backgroundColor, ContextCompat.getColor(getContext(), R.color.colorChipBackground));
        selectedBackgroundColor = ta.getColor(R.styleable.Chip_selectedBackgroundColor, ContextCompat.getColor(getContext(), R.color.colorChipBackgroundClicked));
        textColor = ta.getColor(R.styleable.Chip_textColor, ContextCompat.getColor(getContext(), R.color.colorChipText));
        selectedTextColor = ta.getColor(R.styleable.Chip_selectedTextColor, ContextCompat.getColor(getContext(), R.color.colorChipTextClicked));
        closeColor = ta.getColor(R.styleable.Chip_closeColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseInactive));
        selectedCloseColor = ta.getColor(R.styleable.Chip_selectedCloseColor, ContextCompat.getColor(getContext(), R.color.colorChipCloseClicked));

        ta.recycle();
    }

    public String getChipText() {
        return chipText;
    }

    public void setChipText(String chipText) {
        this.chipText = chipText;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public Drawable getChipIcon() {
        return chipIcon;
    }

    public void setChipIcon(Drawable chipIcon) {
        this.chipIcon = chipIcon;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
        this.selectable = false;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void changeBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }

    public void changeSelectedBackgroundColor(int selectedBackgroundColor) {
        this.selectedBackgroundColor = selectedBackgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public int getCloseColor() {
        return closeColor;
    }

    public void setCloseColor(int closeColor) {
        this.closeColor = closeColor;
    }

    public int getSelectedCloseColor() {
        return selectedCloseColor;
    }

    public void setSelectedCloseColor(int selectedCloseColor) {
        this.selectedCloseColor = selectedCloseColor;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        this.closable = false;
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

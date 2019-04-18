package com.wayne.sunflower.data;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.text.HtmlCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wayne.sunflower.R;

public class BindingAdapters {

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter("isGone")
    public static void bindIsGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("isHide")
    public static void bindIsHide(FloatingActionButton view, boolean isHide) {
        if (isHide) {
            view.hide();
        } else {
            view.show();
        }
    }

    @BindingAdapter("renderHtml")
    public static void bindReaderHtml(TextView view, String description) {
        if (description == null) {
            view.setText("");
        } else {
            view.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @BindingAdapter("wateringText")
    public static void bindWateringText(TextView textView, int wateringInterval) {
        Resources res = textView.getContext().getResources();
        String quantityString = res.getQuantityString(R.plurals.watering_needs_suffix, wateringInterval, wateringInterval);

        SpannableStringBuilder builder = new SpannableStringBuilder().append(res.getString(R.string.watering_needs_prefix));
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, builder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        int start = builder.length();
        builder.append(" " + quantityString);
        builder.setSpan(new StyleSpan(Typeface.ITALIC), start + 1, builder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

}

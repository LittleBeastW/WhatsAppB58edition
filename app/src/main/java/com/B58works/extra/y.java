package com.B58works.extra;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.B58works.LocksC;

/**
 * Created by Bharath.R(58) on 28-12-2017.
 */

public class y implements TextWatcher
{
    private final EditText a;
    private final LocksC b;

    public y(final LocksC b, final EditText a) {
        this.b = b;
        this.a = a;
    }

    public void afterTextChanged(final Editable editable) {
        if (this.a.getText().toString().length() == 4) {
            this.b.callHome();
        }
    }

    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }

    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        final ImageView imageView = (ImageView)this.b.findViewById(LocksC.getID("imageView", "id"));
        final ImageView imageView2 = (ImageView)this.b.findViewById(LocksC.getID("imageView2", "id"));
        final ImageView imageView3 = (ImageView)this.b.findViewById(LocksC.getID("imageView3", "id"));
        final ImageView imageView4 = (ImageView)this.b.findViewById(LocksC.getID("imageView4", "id"));
        final Drawable drawable = LocksC.ctx.getResources().getDrawable(LocksC.getID("pin1", "drawable"));
        final Drawable drawable2 = LocksC.ctx.getResources().getDrawable(LocksC.getID("pinz", "drawable"));
        if (this.a.getText().toString().trim().length() == 1) {
            imageView.setImageDrawable(drawable);
            imageView2.setImageDrawable(drawable2);
            imageView3.setImageDrawable(drawable2);
            imageView4.setImageDrawable(drawable2);
        }
        else if (this.a.getText().toString().length() == 2) {
            imageView.setImageDrawable(drawable);
            imageView2.setImageDrawable(drawable);
            imageView3.setImageDrawable(drawable2);
            imageView4.setImageDrawable(drawable2);
        }
        else if (this.a.getText().toString().length() == 3) {
            imageView.setImageDrawable(drawable);
            imageView2.setImageDrawable(drawable);
            imageView3.setImageDrawable(drawable);
            imageView4.setImageDrawable(drawable2);
        }
        else if (this.a.getText().toString().length() == 4) {
            imageView.setImageDrawable(drawable);
            imageView2.setImageDrawable(drawable);
            imageView3.setImageDrawable(drawable);
            imageView4.setImageDrawable(drawable);
        }
        else if (this.a.getText().toString().length() == 0) {
            imageView.setImageDrawable(drawable2);
            imageView2.setImageDrawable(drawable2);
            imageView3.setImageDrawable(drawable2);
            imageView4.setImageDrawable(drawable2);
        }
    }
}

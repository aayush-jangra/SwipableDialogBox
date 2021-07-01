package com.companyName.zypher_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

public final class DialogBox {
    private static volatile DialogBox INSTANCE = null;
    private DialogBox(){}
    public static DialogBox getInstance(){
        if(INSTANCE==null){
            synchronized (DialogBox.class){
                if(INSTANCE==null)
                {
                    INSTANCE = new DialogBox();
                }
            }
        }

        return INSTANCE;
    }

    public void showDialog(final Context ct, String title, String iurl, final String surl)
    {
        View dialog = LayoutInflater.from(ct).inflate(R.layout.dialog_box_layout, null);

        //Setting Title of Dialog Box
        TextView titleTV = (TextView)dialog.findViewById(R.id.title);
        titleTV.setText(title);

        //Getting Image from URL and adding it to Dialog Boz
        ImageView image = (ImageView)dialog.findViewById(R.id.image);
        Glide.with(ct).load(iurl).into(image);

        //OnClickListener for Success Button.
        Button successBT = (Button)dialog.findViewById(R.id.success_bt);
        successBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(surl)));
            }
        });

        //Building swipable Dialog
        new SwipeDismissDialog.Builder(ct)
                .setView(dialog)
                .build()
                .show();
    }
}

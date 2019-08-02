package com.wyy.qgcloud.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;

public class MakeUploadDirWindow extends PopupWindow{

    private Context context;
    private LinearLayout makeDirLl;
    private LinearLayout uploadDirLl;
    private WindowClickedListener makeDirListener;
    private WindowClickedListener uploadListener;
    private View view;


    public MakeUploadDirWindow(Context context){
        super(context);

        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setTouchable(true);
        setClippingEnabled(false);

        view = LayoutInflater.from(context).inflate(R.layout.window_make_upload_file,null,false);
        setContentView(view);

        makeDirLl = view.findViewById(R.id.ll_new_dir);
        uploadDirLl = view.findViewById(R.id.ll_upload_file);

        makeDirLl.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (makeDirListener != null){
                    makeDirListener.onClick();
                }
            }
        });

        uploadDirLl.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (uploadListener != null){
                    uploadListener.onClick();
                }
            }
        });
    }

    public static int makeDropDownMeasureSpec(int measureSpec){
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT){
            mode = View.MeasureSpec.UNSPECIFIED;
        }else {
            mode = View.MeasureSpec.EXACTLY;
        }

        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec),mode);
    }


    public void setMakeDirListener(WindowClickedListener makeDirListener) {
        this.makeDirListener = makeDirListener;
    }

    public void setUploadListener(WindowClickedListener uploadListener) {
        this.uploadListener = uploadListener;
    }

    public static MakeUploadDirWindow showWindow(Context context,View view){
        MakeUploadDirWindow window = new MakeUploadDirWindow(context);
        View contentView = window.getContentView();
        contentView.measure(MakeUploadDirWindow.makeDropDownMeasureSpec(window.getWidth()),MakeUploadDirWindow.makeDropDownMeasureSpec(window.getHeight()));
        int offsetX = - window.getContentView().getMeasuredWidth();
        int offsetY = 0;
        PopupWindowCompat.showAsDropDown(window,view,offsetX,offsetY, Gravity.START);
        return window;
    }
}

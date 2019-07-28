package com.wyy.qgcloud.ui.register;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.base.BaseActivity;

import java.io.File;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView {

    @BindView(R.id.edt_name)
    EditText nameEdt;
    @BindView(R.id.edt_phone_number)
    EditText phoneNumberEdt;
    @BindView(R.id.edt_register_email)
    EditText registerEmailEdt;
    @BindView(R.id.edt_register_password)
    EditText registerPasswordEdt;
    @BindView(R.id.edit_validate_code)
    EditText editValidateCode;
    @BindView(R.id.imv_validate_code)
    ImageView validateCodeImv;
    @BindView(R.id.btn_register)
    Button registerBtn;
    @BindView(R.id.imv_icon)
    ImageView imvIcon;

    private RegisterContract.RegisterPresent registerPresent;
    private static final int OPEN_ALBUM = 1;
    private static final int CUT_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        nameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = getEdt(nameEdt);
                String regex = "^[\\u4e00-\\u9fa5]{2,4}$";
                boolean format = Pattern.matches(regex, email);
                if (format) {
                    //格式正确则发送网络请求判断是否存在
                } else {
                    //格式不正确则提示用户
                }
            }
        });
        phoneNumberEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = getEdt(phoneNumberEdt);
                String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
                boolean format = Pattern.matches(regex, email);
                if (format) {
                    //格式正确则发送网络请求判断是否存在
                } else {
                    //格式不正确则提示用户
                }
            }
        });
        registerEmailEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = getEdt(registerEmailEdt);
                String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                boolean format = Pattern.matches(regex, email);
                if (format) {
                    //格式正确则发送网络请求判断是否存在
                } else {
                    //格式不正确则提示用户
                }
            }
        });
        registerPasswordEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = getEdt(registerPasswordEdt);
                String regex = "^[^[x00-xff]]{6,15}$";
                boolean format = Pattern.matches(regex, email);
                if (format) {
                    //格式正确则发送网络请求判断是否存在
                } else {
                    //格式不正确则提示用户
                }
            }
        });
    }

    @OnClick({R.id.imv_validate_code, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_validate_code:
                registerPresent.getValidateCodeInfo(this);
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.imv_icon:
                //权限处理
                if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else {
                    openAlbum();
                }
                break;
        }
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    private void register() {
        String email = getEdt(registerEmailEdt);
        String password = getEdt(registerPasswordEdt);
        String userName = getEdt(nameEdt);
        String phone = getEdt(phoneNumberEdt);
        String code = getEdt(editValidateCode);
        //registerPresent.getRegisterInfo(this, email, password, , userName, phone, code);
    }

    @Override
    public void displayIcon(File icon) {
        if(icon != null){
            Glide.with(this)
                    .load(icon)
                    .into(imvIcon);
        }else{
            //提示用户照片不存在
        }
    }

    @Override  //权限回调
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this, "请打开权限。",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case OPEN_ALBUM:
                //根据不同的版本进行不同的操作
                if(Build.VERSION.SDK_INT>=19){
                    handleImageOnKitKat(data);
                }else{
                    handleImageBeforeKitKat(data);
                }
                break;
            case CUT_PHOTO:
                setView(data);
                break;
            default:
                break;
        }
    }

    //打开相册选择照片
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        startActivityForResult(intent, 2);
    }

    //4.4以下系统
    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        photoCut(uri);
    }

    //4.4及以上系统
    private void handleImageOnKitKat(Intent data){
        Uri uri = data.getData();
        //返回Uri为document类型
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);  //取出document id处理
            //uri的authority为media格式时需进行再次解析
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                Uri uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                photoCut(uri1);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri uri2 = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                photoCut(uri2);
            }
        }
        //Uri为content类型
        else if("content".equalsIgnoreCase(uri.getScheme())){
            photoCut(uri);
        }
        //Uri为file类型直接获取
        else if("file".equalsIgnoreCase(uri.getScheme())){
            photoCut(uri);
        }
    }

    //对选择的照片进行裁剪
    private void photoCut(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁剪比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后输出图片大小
        intent.putExtra("outputX", 151);
        intent.putExtra("outputY", 151);
//        //tup格式
//        intent.putExtra("outputFormat", "JPEG");
//        //取消人脸识别
//        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CUT_PHOTO);
    }

    //将裁剪后的图片设置为头像
    private void setView(Intent data){
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            Glide.with(this)
                    .load(bitmap)
                    .into(imvIcon);
        }
    }

}

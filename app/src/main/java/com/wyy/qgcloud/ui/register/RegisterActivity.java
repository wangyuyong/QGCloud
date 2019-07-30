package com.wyy.qgcloud.ui.register;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.base.BaseActivity;
import com.wyy.qgcloud.ui.login.LoginActivity;
import com.wyy.qgcloud.util.MyToast;

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
    @BindView(R.id.imv_register_back)
    ImageView imvRegisterBack;

    private RegisterContract.RegisterPresent registerPresent = new RegisterPresent();
    private static final int OPEN_ALBUM = 1;
    private static final int CUT_PHOTO = 2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static Activity mRegisterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresent.bindView(this);
        mRegisterActivity = this;
        registerPresent.getValidateCodeInfo(RegisterActivity.this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        registerPasswordEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                if (!format) {
                    //格式不正确，底边变色
                    nameEdt.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    nameEdt.setTextColor(getResources().getColor(R.color.colorTextBlack));
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
                String phone = getEdt(phoneNumberEdt);
                String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
                boolean format = Pattern.matches(regex, phone);
                if (!format) {
                    //格式不正确，底边变色
                    phoneNumberEdt.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    phoneNumberEdt.setTextColor(getResources().getColor(R.color.colorTextBlack));
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
                if (!format) {
                    //格式不正确，底边变色
                    registerEmailEdt.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    registerEmailEdt.setTextColor(getResources().getColor(R.color.colorTextBlack));
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
                if (!format) {
                    //格式不正确，底边变色
                    registerPasswordEdt.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    registerPasswordEdt.setTextColor(getResources().getColor(R.color.colorTextBlack));
                }
            }
        });
        registerBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                register();
            }
        });
        validateCodeImv.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                registerPresent.getValidateCodeInfo(RegisterActivity.this);
            }
        });
    }

    @OnClick({R.id.imv_icon, R.id.imv_register_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_icon:
                //权限处理
                if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.imv_register_back:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
    }

    //获取输入内容
    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    //展示验证码
    @Override
    public void displayCode(String icon) {
        byte[] iconByte = Base64.decode(icon.getBytes(), Base64.DEFAULT);
        Glide.with(this)
                .load(iconByte)
                .into(validateCodeImv);

    }

    @Override
    public void showError(String msg, int kind) {
        switch (kind) {
            //爆红提示
            case 1:
                break;
            //弹窗提示
            case 2:
                MyToast.getMyToast().ToastShow(RegisterActivity.this, null, R.drawable.ic_sad, msg);
                break;
            default:
                break;
        }
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(RegisterActivity.this, null, R.drawable.ic_happy, msg);
    }

    //注册
    private void register() {
        String email = getEdt(registerEmailEdt);
        String password = getEdt(registerPasswordEdt);
        String userName = getEdt(nameEdt);
        String phone = getEdt(phoneNumberEdt);
        String code = getEdt(editValidateCode);
        String path = pref.getString("path", "");
        Log.d("wx", path);
        File icon = new File(path);
        registerPresent.getRegisterInfo(this, email, password, icon, userName, phone, code);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "请打开权限。", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case OPEN_ALBUM:
                //根据不同的版本进行不同的操作
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }
                break;
//            case CUT_PHOTO:
//                setView(data);
//                break;
            default:
                break;
        }
    }

    //打开相册选择照片
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        startActivityForResult(intent, 1);
    }

    //4.4以下系统
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagepath(uri, null);
        displayImage(imagePath);
    }

    //4.4及以上系统
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        //返回Uri为document类型
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);  //取出document id处理
            //uri的authority为media格式时需进行再次解析
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //分割字符串得到真正的id
                String id = docId.split(":")[1];
                //构建新的条件语句
                String selection = MediaStore.Images.Media._ID + "=" + id;
                //传入新的Uri和条件语句
                imagePath = getImagepath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagepath(contentUri, null);
            }
        }
        //Uri为content类型
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagepath(uri, null);
        }
        //Uri为file类型直接获取
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        //传入路径来进行展示
        displayImage(imagePath);
    }

//    //对选择的照片进行裁剪
//    private void photoCut(Uri uri){
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        //裁剪比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        //裁剪后输出图片大小
//        intent.putExtra("outputX", 151);
//        intent.putExtra("outputY", 151);
////        //tup格式
////        intent.putExtra("outputFormat", "JPEG");
////        //取消人脸识别
////        intent.putExtra("noFaceDetection", true);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CUT_PHOTO);
//    }

//    //将裁剪后的图片设置为头像
//    private void setView(Intent data){
//        Bundle extras = data.getExtras();
//        if (extras != null) {
//            Bitmap bitmap = extras.getParcelable("data");
//            Glide.with(this)
//                    .load(bitmap)
//                    .into(imvIcon);
//            //String path = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
//            File file = new File("qgcloudicon.jpg");
//            try {
//                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
//                bos.flush();
//                bos.close();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }

    //获取图片真实路径
    private String getImagepath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //将图片进行展示
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            editor = pref.edit();
            editor.putString("path", imagePath);
            Log.d("wx", imagePath);
            editor.apply();
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //设置图片
            Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .into(imvIcon);
        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        registerPresent.unbindView();
        super.onDestroy();
    }

    @OnClick(R.id.imv_register_back)
    public void onViewClicked() {
    }
}

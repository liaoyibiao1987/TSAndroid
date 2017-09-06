package com.dy.crazyandroid;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private Button btn_login;
    private Button btn_logout;
    private Button btn_testintent;
    private Button btn_makecall;
    private String cameraPath;

    public final static int ALBUM_REQUEST_CODE = 1;
    public final static int CROP_REQUEST = 2;
    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath() + "/AppName/camera/";// 拍照路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_testintent = (Button) findViewById(R.id.btn_TestIntent);
        btn_makecall = (Button) findViewById(R.id.btn_makecall);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("请确认所有数据都保存后再推出系统！")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            // @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        Log.i("alertdialog", " 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果API level大于11 大于11的时候能够指定主体
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                    builder = new AlertDialog.Builder(MainActivity.this,
                            AlertDialog.BUTTON_POSITIVE);

                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);

                }
                builder.setItems(new String[]{"拍照", "选择"},
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    /*Intent intent = new Intent(); //调用照相机
                                    intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                            Uri.fromFile(new File(Environment
                                                    .getExternalStorageDirectory(), "camera.jpg")));
                                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                    startActivityForResult(intent, 10);*/
                                    StartCamera(MainActivity.this, 10);
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                                    intent.setType("image/*");
                                    intent.putExtra("crop", "true");
                                    intent.putExtra("aspectX", 1);
                                    intent.putExtra("aspectY", 1);
                                    intent.putExtra("outputX", 80);
                                    intent.putExtra("outputY", 80);
                                    intent.putExtra("return-data", true);
                                    startActivityForResult(intent, 11);
                                }
                            }
                        });
                builder.setTitle("选择照片");
                builder.create().show();
            }
        });
        btn_testintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.TESTINTENT");//方法： android.content.Intent.Intent(String action)
                if (intent != null) {
                    /*Uri uri = Uri.parse("market://search?q=pname:pkg_name");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);*/
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "获取intent错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_makecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }

    public String getAbsolutePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public String StartCamera(Activity activity, int requestCode) {
        // 指定相机拍摄照片保存地址
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent();
            // 指定开启系统相机的Action
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
            // 把文件地址转换成Uri格式
            Uri uri = Uri.fromFile(outFile);
            Toast.makeText(activity, "getAbsolutePath=" + outFile.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 此值在最低质量最小文件尺寸时是0，在最高质量最大文件尺寸时是１
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), "camera.jpg")));
            activity.startActivityForResult(intent, requestCode);
            return outFile.getAbsolutePath();
        } else {
            Toast.makeText(activity, "请确认已经插入SD卡",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* System.out.println(resultCode);
        Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");*/
        super.onActivityResult(requestCode, resultCode, data);


        /*if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                Toast.makeText(getApplicationContext(), "path=" + cameraPath,
                        Toast.LENGTH_LONG).show();
            }else if (requestCode == ALBUM_REQUEST_CODE) {
                try {
                    Uri uri = data.getData();
                    final String absolutePath= getAbsolutePath(MainActivity.this, uri);
                    Toast.makeText(getApplicationContext(), "path=" + absolutePath,
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
        //new AlertDialog.Builder(MainActivity.this).setMessage(cameraBitmap.getHeight()).show();
    }


}

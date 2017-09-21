package com.dy.crazyandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class FormsTestActivity extends AppCompatActivity {

    private int mThemeId = -1; // 皮肤主题ID，默认-1 不处理
    private ToggleButton toggleButton;
    private Button btn_getnetworkstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("theme", -1) != -1) {// 读取皮肤主题ID，-1 不处理
                mThemeId = savedInstanceState.getInt("theme");
                this.setTheme(mThemeId);  //设置主题皮肤
            }
        }
        setContentView(R.layout.activity_forms_test);

        toggleButton = (ToggleButton) findViewById(R.id.tb_swtichTheme);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked() == true) {
                    android.widget.Toast.makeText(getApplicationContext(), "isChecked = true", Toast.LENGTH_SHORT).show();
                    onTheme(R.style.AppTheme);
                } else {
                    android.widget.Toast.makeText(getApplicationContext(), "isChecked = false", Toast.LENGTH_SHORT).show();
                    onTheme(R.style.AppThemeNigth);
                }
            }
        });

        btn_getnetworkstatus = (Button) findViewById(R.id.btn_getnetworkstatus);
        btn_getnetworkstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = getIPAddress(FormsTestActivity.this);
                new AlertDialog.Builder(FormsTestActivity.this).setTitle("IP提示")//设置对话框标题
                        .setMessage("获取到的IP信息！" + x)//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            // @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub

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

    }

    private void onTheme(int iThemeId) {
        mThemeId = iThemeId;
        this.recreate();
    }

    // 保存主题ID，onCreate 时读取主题
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", mThemeId);
    }


    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (inetAddress.isLoopbackAddress() == false && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();

                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}

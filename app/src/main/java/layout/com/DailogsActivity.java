package layout.com;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ppl.ppl.R;

public class DailogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailogs);

        showProcessDadilog();
    }

    private void showProcessDadilog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgress(100);
        dialog.show();
        Toast toast = Toast.makeText(this, "测试信息", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);

        View view = LayoutInflater.from(DailogsActivity.this).inflate(R.layout.activity_clocker, null);
    }
}

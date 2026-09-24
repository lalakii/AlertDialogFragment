package af2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import cn.lalaki.af2.dialog.R;
import cn.lalaki.alert.AlertDialogFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(MainActivity.this);
        setContentView(btn);
        btn.setFitsSystemWindows(true);
        btn.setText(R.string.click);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new AlertDialogFragment.Builder(MainActivity.this).setTitle("hello").setMessage("This is a DialogFragment")
                .setOnDismissListener(dialogInterface -> Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show())
                .setOnCancelListener(dialogInterface -> Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show())
                .setPositiveButton("OK", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                .setNeutralButton("What", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "What", Toast.LENGTH_SHORT).show()).show();
    }
}
package cn.edu.gdmec.android.boxuegutestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity implements View.OnClickListener {

    private TextView tv_test;
    private EditText et_test;
    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initView();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                //TODO implement
                break;
        }
    }

    private void initView() {
        tv_test = (TextView) findViewById(R.id.tv_test);
        et_test = (EditText) findViewById(R.id.et_test);
        btn_test = (Button) findViewById(R.id.btn_test);

        btn_test.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String test = et_test.getText().toString().trim();
        if (TextUtils.isEmpty(test)) {
            Toast.makeText(this, "test不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
    }
}

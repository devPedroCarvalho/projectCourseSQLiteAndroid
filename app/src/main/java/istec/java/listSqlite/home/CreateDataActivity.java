package istec.java.listSqlite.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import istec.java.listSqlite.BaseClass;
import istec.java.listSqlite.R;
import istec.java.listSqlite.model.DataBaseSql;

public class CreateDataActivity extends BaseClass {

    private Button saveData;
    private DataBaseSql dataBaseSql;
    private EditText dataSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        saveData = findViewById(R.id.button_saveData);
        dataSave= findViewById(R.id.editText_dataSave);
        dataBaseSql = new DataBaseSql(this);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = dataSave.getText().toString();

                if (text.equals("")) {
                    toast(CreateDataActivity.this,"Caixa de texto vazia!");
                }else {
                    dataBaseSql.AddData(text);
                    dataSave.setText("");
                    goToFinish(CreateDataActivity.this, HomeActivity.class);
                }

            }
        });



    }
}
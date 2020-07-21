package istec.java.listSqlite;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseClass extends AppCompatActivity {

    public void goToFinish(Context context, Class classs){
        Intent intent = new Intent(context, classs);
        startActivity(intent);
        finish();
    }
    public void goTo(Context context, Class classs){
        Intent intent = new Intent(context, classs);
        startActivity(intent);
    }

    public void toast(Context context, String text ){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();

    }


}

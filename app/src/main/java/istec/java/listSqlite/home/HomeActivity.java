package istec.java.listSqlite.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import istec.java.listSqlite.BaseClass;
import istec.java.listSqlite.R;
import istec.java.listSqlite.model.DataBaseSql;

public class HomeActivity extends BaseClass {

    ListView myListView;
    ArrayAdapter adapter;
    ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myListView = (ListView)findViewById(R.id.myListView);

        listData.clear();
        GetData();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String positionItem = myListView.getItemAtPosition(position).toString();
                deleteTitle(positionItem);
            }
        });

    }

    //deleteData
    public void deleteTitle(String name)
    {
        DataBaseSql dataBase = new DataBaseSql(this);
        dataBase.deleteItem(name);
        goTo(HomeActivity.this,HomeActivity.class);
    }

    //geData
    private void GetData() {
        DataBaseSql dataBase = new DataBaseSql(this);
        Cursor cursor = dataBase.ShowDataSql();

        if (cursor.equals(null)){
            Toast.makeText(HomeActivity.this, "no data!", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listData);
            myListView.setAdapter(adapter);
        }
    }


    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);

        MenuItem searchItem = menu.findItem(R.id.search_id);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> userslists = new ArrayList<>();

                for (String user: listData){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        userslists.add(user);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1, userslists);
                myListView.setAdapter(adapter);
                return true;
            }
        });
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create:
                goTo(HomeActivity.this,CreateDataActivity.class);
            default:
                return super.onContextItemSelected(item);
        }
    }
}
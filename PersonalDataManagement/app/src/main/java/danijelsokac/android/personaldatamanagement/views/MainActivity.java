package danijelsokac.android.personaldatamanagement.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.adapters.PeopleAdapter;
import danijelsokac.android.personaldatamanagement.item_decorators.SpacingDecorator;
import danijelsokac.android.personaldatamanagement.view_models.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rlMainViewContainer) RelativeLayout rlContainer;
    @BindView(R.id.rvAllPeople) RecyclerView rvAllPeople;
    @BindView(R.id.fabAction) FloatingActionButton fabACtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Object obj = this;

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, users -> {
            Toast.makeText(this, "Users arrived", Toast.LENGTH_SHORT).show();
            System.out.println();

            PeopleAdapter adapter = new PeopleAdapter(users, ((MainActivity) obj).getApplicationContext());
            GridLayoutManager manager = new GridLayoutManager((MainActivity)obj, 3);
            rvAllPeople.setLayoutManager(manager);
            rvAllPeople.addItemDecoration(new SpacingDecorator(10));
            rvAllPeople.setAdapter(adapter);

        });
        userViewModel.getUserById(4).observe(this, user -> {
            Toast.makeText(this, "User arrived", Toast.LENGTH_SHORT).show();
            System.out.println();
        });

    }

    public void openPopUp(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.dot_menu, menu.getMenu());
        menu.show();

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.details:
                        //do something
                        return true;
                    case R.id.update:
                        //do something
                        return true;
                    case R.id.delete:
                        //do something
                        return true;
                    default: return false;
                }
            }
        });
    }


}

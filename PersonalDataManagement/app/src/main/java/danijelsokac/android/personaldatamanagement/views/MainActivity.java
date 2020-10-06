package danijelsokac.android.personaldatamanagement.views;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.adapters.PeopleAdapter;
import danijelsokac.android.personaldatamanagement.item_decorators.SpacingDecorator;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.view_models.UserViewModel;
import danijelsokac.android.personaldatamanagement.views.fragments.EditableFormUserDialogFragment;

public class MainActivity extends AppCompatActivity  implements DialogFragmentListener {

    @BindView(R.id.rlMainViewContainer) RelativeLayout rlContainer;
    @BindView(R.id.rvAllPeople) RecyclerView rvAllPeople;
    @BindView(R.id.fabAction) FloatingActionButton fabACtion;

    private UserViewModel userViewModel;
    private PeopleAdapter adapter;
    private List<UserModel> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new PeopleAdapter(userList, this);
        adapter.setFragmentManager(getSupportFragmentManager());
        adapter.setListener(this);
        rvAllPeople.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvAllPeople.setLayoutManager(manager);
        rvAllPeople.addItemDecoration(new SpacingDecorator(10));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, users -> {
            Toast.makeText(this, "Users arrived", Toast.LENGTH_SHORT).show();
            userList.addAll(users);
            rvAllPeople.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyItemInserted(userList.size());
                }
            });
        });
        userViewModel.getUserById(4).observe(this, user -> {
            Toast.makeText(this, "User arrived", Toast.LENGTH_SHORT).show();
            System.out.println();
        });

        fabACtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            rvAllPeople.setAdapter(adapter);
        }
    }

    private void addNewUser() {
        EditableFormUserDialogFragment fragment = EditableFormUserDialogFragment.newInstance(this);
        fragment.show(getSupportFragmentManager().beginTransaction(), "EditableFormUserDialogFragment");
    }

    @Override
    public void onCancel() {
        System.out.println("CANCEL");
    }

    @Override
    public void onSave(UserModel data) {
        System.out.println("OK");
        if(data.getId() != 0) {
            userViewModel.updateUser(data).observe(this, new Observer<UserModel>() {
                @Override
                public void onChanged(UserModel userModel) {
                    System.out.println();
                    rvAllPeople.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.updateItem(userModel);
                        }
                    });
                }
            });
        } else {
            userViewModel.saveUser(data).observe(this, new Observer<UserModel>() {
                @Override
                public void onChanged(UserModel userModel) {
                    rvAllPeople.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addItem(userModel);
                        }
                    });
                }
            });
        }
    }

}

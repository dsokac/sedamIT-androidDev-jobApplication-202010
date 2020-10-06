package danijelsokac.android.personaldatamanagement.adapters.view_holders;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.views.fragments.EditableFormUserDialogFragment;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvNameSurname) TextView tvFullName;
    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.imageButton) ImageButton ibMenu;

    private View view;
    private FragmentManager fragmentManager;
    private DialogFragmentListener listener;

    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        view = itemView;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setListener(DialogFragmentListener listener) {
        this.listener = listener;
    }

    public void bind(UserModel user) {
        tvFullName.setText(user.getName() + " " + user.getSurname());
        ivProfileImage.setImageResource(R.drawable.default_avatar);
        ibMenu.setImageResource(R.drawable.icon_menu_dots_vertical);
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view, user);
            }
        });
    }

    private void openMenu(View v, UserModel user) {
        PopupMenu menu = new PopupMenu(view.getContext(), v);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.dot_menu, menu.getMenu());
        menu.show();

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.details:
                        showUserDetails(user);
                        return true;
                    case R.id.update:
                        updateUser(user);
                        return true;
                    case R.id.delete:
                        deleteUser(user);
                        return true;
                    default: return false;
                }
            }
        });
    }

    private void showUserDetails(UserModel user) {
        Toast.makeText(view.getContext(), "Details of " + user.getName() + " " + user.getSurname() + "( " + user.getId() + ")", Toast.LENGTH_SHORT).show();

    }

    private void updateUser(UserModel user) {
        Toast.makeText(view.getContext(), "Update  " + user.getName() + " " + user.getSurname() + "( " + user.getId() + ")", Toast.LENGTH_SHORT).show();

        EditableFormUserDialogFragment fragment = EditableFormUserDialogFragment.newInstance(user, listener);
        fragment.show(fragmentManager.beginTransaction(), "EditableFormUserDialogFragment");
    }

    private void deleteUser(UserModel user) {
        Toast.makeText(view.getContext(), "Delete " + user.getName() + " " + user.getSurname() + "( " + user.getId() + ")", Toast.LENGTH_SHORT).show();;
    }
}

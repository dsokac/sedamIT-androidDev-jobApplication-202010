package danijelsokac.android.personaldatamanagement.adapters.view_holders;

import android.content.DialogInterface;
import android.text.InputType;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.listeners.DeleteListener;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.views.fragments.EditableFormUserDialogFragment;
import danijelsokac.android.personaldatamanagement.views.fragments.FormUserDialogFragment;

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

    public void bind(UserModel user, DeleteListener deleteListener) {
        tvFullName.setText(user.getName() + " " + user.getSurname());
        ivProfileImage.setImageResource(R.drawable.default_avatar);
        ibMenu.setImageResource(R.drawable.icon_menu_dots_vertical);
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(view, user, deleteListener);
            }
        });
    }

    private void openMenu(View v, UserModel user, DeleteListener deleteListener) {
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
                        deleteUser(user, deleteListener);
                        return true;
                    default: return false;
                }
            }
        });
    }

    private void showUserDetails(UserModel user) {
        FormUserDialogFragment  fragment = FormUserDialogFragment.newInstance(user);
        fragment.show(fragmentManager.beginTransaction(), "FormUserDialogFragment");
    }

    private void updateUser(UserModel user) {
        EditableFormUserDialogFragment fragment = EditableFormUserDialogFragment.newInstance(user, listener);
        fragment.show(fragmentManager.beginTransaction(), "EditableFormUserDialogFragment");
    }

    private void deleteUser(UserModel user, DeleteListener deleteListener) {
        List<UserModel> delete = new ArrayList<>();
        delete.add(user);
        deleteListener.onDeleteAction(delete);
    }
}

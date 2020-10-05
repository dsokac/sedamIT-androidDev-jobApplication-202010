package danijelsokac.android.personaldatamanagement.adapters.view_holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.models.UserModel;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvNameSurname) TextView tvFullName;
    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.imageButton) ImageButton ibMenu;

    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(UserModel user) {
        tvFullName.setText(user.getName() + " " + user.getSurname());
        ivProfileImage.setImageResource(R.drawable.default_avatar);
        ibMenu.setImageResource(R.drawable.icon_menu_dots_vertical);
    }
}

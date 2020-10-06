package danijelsokac.android.personaldatamanagement.views.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.databinding.UpdatePersonLayoutBinding;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.repositories.UserRepository;

public class EditableFormUserDialogFragment extends DialogFragment {

    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;

    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etSurname) EditText etSurname;
    @BindView(R.id.etPhone) EditText etPhone;
    @BindView(R.id.etMobilePhone) EditText etMobilePhone;
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etAddress) EditText etAddress;

    @BindView(R.id.btnSave) Button btnSave;
    @BindView(R.id.btnCancel) Button btnCancel;

    private UserModel user;
    private View view;
    private DialogFragmentListener listener;
    private UpdatePersonLayoutBinding binding;

    public static EditableFormUserDialogFragment newInstance(UserModel user, DialogFragmentListener listener) {
        EditableFormUserDialogFragment fragment = new EditableFormUserDialogFragment(listener);

        Bundle args = new Bundle();
        args.putSerializable("user", new Gson().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    public static EditableFormUserDialogFragment newInstance(DialogFragmentListener listener) {
        EditableFormUserDialogFragment fragment = new EditableFormUserDialogFragment(listener);
        return fragment;
    }

    public EditableFormUserDialogFragment(DialogFragmentListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            Object value = getArguments().getSerializable("user");
            if (value != null) {
                user = new Gson().fromJson(value.toString(), UserModel.class);
            }
        } else {
            user = new UserModel();
            user.setFullName("Novi profil");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.update_person_layout, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();

        ButterKnife.bind(this, view);
        ivProfileImage.setImageResource(R.drawable.default_avatar);
        binding.setUser(user);
        binding.executePendingBindings();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel newUser = new UserModel();
                if(user.getId() != 0) {
                    newUser.setId(user.getId());
                }
                newUser.setName(etName.getText().toString());
                newUser.setSurname(etSurname.getText().toString());
                newUser.setPhoneNumber(etPhone.getText().toString());
                newUser.setMobilePhoneNumber(etMobilePhone.getText().toString());
                newUser.setEmail(etEmail.getText().toString());
                newUser.setAddress(etAddress.getText().toString());
                listener.onSave(newUser);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancel();
                dismiss();
            }
        });

        return view;
    }
}

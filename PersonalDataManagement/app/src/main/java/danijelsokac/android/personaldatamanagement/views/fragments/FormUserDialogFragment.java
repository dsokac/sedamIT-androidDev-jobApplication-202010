package danijelsokac.android.personaldatamanagement.views.fragments;

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

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.databinding.PersonLayoutBinding;
import danijelsokac.android.personaldatamanagement.databinding.UpdatePersonLayoutBinding;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;

public class FormUserDialogFragment extends DialogFragment {

    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;

    @BindView(R.id.btnClose) Button btnClose;

    private UserModel user;
    private View view;
    private PersonLayoutBinding binding;

    public static FormUserDialogFragment newInstance(UserModel user) {
        FormUserDialogFragment fragment = new FormUserDialogFragment();

        Bundle args = new Bundle();
        args.putSerializable("user", new Gson().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            Object value = getArguments().getSerializable("user");
            if (value != null) {
                user = new Gson().fromJson(value.toString(), UserModel.class);
            }
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.person_layout, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();

        ButterKnife.bind(this, view);
        ivProfileImage.setImageResource(R.drawable.default_avatar);
        binding.setUser(user);
        binding.executePendingBindings();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }
}

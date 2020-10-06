package danijelsokac.android.personaldatamanagement.listeners;

import android.view.View;

import danijelsokac.android.personaldatamanagement.models.UserModel;

public interface DialogFragmentListener {
    void onCancel();
    void onSave(UserModel data);
}

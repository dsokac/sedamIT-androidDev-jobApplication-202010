package danijelsokac.android.personaldatamanagement.listeners;

import danijelsokac.android.personaldatamanagement.models.UserModel;

public interface DialogFragmentListener {
    void onCancel();
    void onSave(UserModel data);
}

package danijelsokac.android.personaldatamanagement.listeners;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.UserModel;

public interface DeleteListener {
    void onDeleteAction(List<UserModel> users);
}

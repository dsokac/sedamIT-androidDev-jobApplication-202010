package danijelsokac.android.personaldatamanagement.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.UserModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<UserModel>> users;

    public LiveData<List<UserModel>> getUsers() {
        if(users == null) {
            users = new MutableLiveData<>();
            //load  data
        }
        return users;
    }

}

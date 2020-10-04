package danijelsokac.android.personaldatamanagement.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private UserRepository repository = UserRepository.getInstance();
    private MutableLiveData<List<UserModel>> users;
    private MutableLiveData<UserModel> user;
    private MutableLiveData<UserModel> newUser;
    private MutableLiveData<UserModel> deletedUser;

    public MutableLiveData<List<UserModel>> getUsers() {
        if(users == null) {
            users = new MutableLiveData<>();
            users = repository.getUsers();
        }
        return users;
    }

    public MutableLiveData<UserModel> getUserById(int id) {
        if(user == null) {
            user = new MutableLiveData<>();
            user = repository.getUserById(id);
        }
        return user;
    }

    public MutableLiveData<UserModel> deleteUserById(int id) {
        if(deletedUser == null) {
            deletedUser = new MutableLiveData<>();
            deletedUser = repository.deleteUserById(id);
        }
        return deletedUser;
    }

    public MutableLiveData<UserModel> saveUser(UserModel user) {
        if(newUser == null) {
            newUser = new MutableLiveData<>();
            newUser = repository.saveUser(user);
        }
        return newUser;
    }
}

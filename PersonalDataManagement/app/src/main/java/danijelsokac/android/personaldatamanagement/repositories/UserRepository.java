package danijelsokac.android.personaldatamanagement.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import danijelsokac.android.personaldatamanagement.listeners.ErrorListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;
import danijelsokac.android.personaldatamanagement.services.ServiceGenerator;
import danijelsokac.android.personaldatamanagement.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository INSTANCE = null;
    private UserService userService = null;
    private ErrorListener errorListener = null;

    private UserRepository() {
        userService =  ServiceGenerator.createService(UserService.class);
    }

    public static UserRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public void setErrorListener(ErrorListener errorListener){
        this.errorListener = errorListener;
    }

    public MutableLiveData<List<UserModel>> getUsers() {
        MutableLiveData<List<UserModel>> users = new MutableLiveData<>();
        this.userService.getUsers().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                users.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                if(errorListener != null) {
                    errorListener.onErrorOccured(t.getMessage());
                }
            }
        });
        return users;
    }

}

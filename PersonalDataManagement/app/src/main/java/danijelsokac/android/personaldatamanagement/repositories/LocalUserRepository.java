package danijelsokac.android.personaldatamanagement.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import danijelsokac.android.personaldatamanagement.db.AppRoomDatabase;
import danijelsokac.android.personaldatamanagement.db.DAOs.UserModelDao;
import danijelsokac.android.personaldatamanagement.models.UserModel;

public class LocalUserRepository {

    private UserModelDao userModelDao;

    private LocalUserRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        userModelDao = db.userModelDao();
    }

    public LiveData<List<UserModel>> getAll() {
        return userModelDao.getAll();
    }

    public LiveData<List<UserModel>> getAllOrderByRemoteId() {
        return userModelDao.getAllOrderByRemoteId();
    }

    public LiveData<UserModel> getByLocalId(int id) {
        return userModelDao.getByLocalId(id);
    }

    public LiveData<UserModel> getByRemoteId(int id) {
        return userModelDao.getByRemoteId(id);
    }

    void insert(UserModel user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userModelDao.insert(user);
        });
    }

    void insertAll(UserModel... usera) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userModelDao.insertAll(usera);
        });
    }

    void delete(UserModel user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userModelDao.delete(user);
        });
    }

    void deleteAll(UserModel... users) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userModelDao.deleteAll(users);
        });
    }
}

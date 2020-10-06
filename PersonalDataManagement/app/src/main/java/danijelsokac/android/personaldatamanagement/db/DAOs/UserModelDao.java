package danijelsokac.android.personaldatamanagement.db.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.UserModel;

@Dao
public interface UserModelDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(UserModel user);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(UserModel... users);

    @Delete
    void delete(UserModel user);

    @Delete
    void deleteAll(UserModel... users);

    @Query("SELECT * from app_users")
    List<UserModel> getAll();

    @Query("SELECT * from app_users ORDER BY id ASC")
    List<UserModel> getAllOrderByRemoteId();

    @Query("SELECT * from app_users where local_id = :id")
    UserModel getByLocalId(int id);

    @Query("SELECT * from app_users where id = :id")
    UserModel getByRemoteId(int id);
}

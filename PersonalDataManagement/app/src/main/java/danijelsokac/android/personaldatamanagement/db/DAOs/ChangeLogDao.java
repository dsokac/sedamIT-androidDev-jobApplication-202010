package danijelsokac.android.personaldatamanagement.db.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.ChangeLog;

@Dao
public interface ChangeLogDao {

    @Delete
    void delete(ChangeLog log);

    @Query("DELETE FROM change_logs")
    void deleteAll();

    @Insert
    void insert(ChangeLog log);

    @Query("SELECT * from change_logs")
    List<ChangeLog> getAll();

    @Query("SELECT * from change_logs where id = :id")
    ChangeLog getById(int id);
}

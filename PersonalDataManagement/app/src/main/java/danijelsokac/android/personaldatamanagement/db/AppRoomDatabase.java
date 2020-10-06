package danijelsokac.android.personaldatamanagement.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import danijelsokac.android.personaldatamanagement.db.DAOs.ChangeLogDao;
import danijelsokac.android.personaldatamanagement.db.DAOs.UserModelDao;
import danijelsokac.android.personaldatamanagement.models.ChangeLog;
import danijelsokac.android.personaldatamanagement.models.UserModel;

@Database(entities = {UserModel.class, ChangeLog.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUM_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public abstract UserModelDao userModelDao();
    public abstract ChangeLogDao changeLogDao();

    public static AppRoomDatabase getDatabase(final Context context) {
        if(INSTANCE  == null) {
            synchronized (AppRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "app_db").build();
                }
            }
        }
        return INSTANCE;
    }

}

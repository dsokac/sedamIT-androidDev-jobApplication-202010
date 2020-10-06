package danijelsokac.android.personaldatamanagement.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "change_logs")
@Getter @Setter
public class ChangeLog {

    static final String DELETE = "DELETE";
    static final String UPDATE = "UPDATE";
    static final String CREATE = "CREATE";

    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "local_id")  private int localId;
    @ColumnInfo(name = "remote_id")  private int remoteId;
    private String action; //DELETE, UPDATE, CREATE;

}

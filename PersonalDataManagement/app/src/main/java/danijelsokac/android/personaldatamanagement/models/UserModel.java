package danijelsokac.android.personaldatamanagement.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "app_users")
@Getter @Setter
public class UserModel {
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "local_id")  private int localId;
    @Expose                            private int id;
    @Expose @SerializedName("ime")     private String name;
    @Expose @SerializedName("prezime") private String surname;
    @ColumnInfo(name = "phone_number") @Expose @SerializedName("telefon") private String phoneNumber;
    @ColumnInfo(name = "mobile_number") @Expose @SerializedName("mobitel") private String mobilePhoneNumber;
    @Expose @SerializedName("mail")    private String email;
    @Expose @SerializedName("adresa")  private String address;

    private String fullName;

    public String getFullName() {
        if(fullName != null) {
            return fullName;
        } else {
            return name + " " + surname;
        }
    }
    public void setFullName(String value) {
        fullName = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return getId() == userModel.getId() &&
                Objects.equals(getName(), userModel.getName()) &&
                Objects.equals(getSurname(), userModel.getSurname()) &&
                Objects.equals(getPhoneNumber(), userModel.getPhoneNumber()) &&
                Objects.equals(getMobilePhoneNumber(), userModel.getMobilePhoneNumber()) &&
                Objects.equals(getEmail(), userModel.getEmail()) &&
                Objects.equals(getAddress(), userModel.getAddress()) &&
                Objects.equals(getFullName(), userModel.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPhoneNumber(), getMobilePhoneNumber(), getEmail(), getAddress(), getFullName());
    }
}

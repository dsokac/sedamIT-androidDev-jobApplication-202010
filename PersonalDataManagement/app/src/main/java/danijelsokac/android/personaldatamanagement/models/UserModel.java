package danijelsokac.android.personaldatamanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserModel {
    @Expose                            private int id;
    @Expose @SerializedName("ime")     private String name;
    @Expose @SerializedName("prezime") private String surname;
    @Expose @SerializedName("telefon") private String phoneNumber;
    @Expose @SerializedName("mobitel") private String mobilePhoneNumber;
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
}

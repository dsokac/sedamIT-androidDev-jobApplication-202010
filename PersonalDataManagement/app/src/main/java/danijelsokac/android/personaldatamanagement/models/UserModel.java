package danijelsokac.android.personaldatamanagement.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserModel {
                               private int id;
    @SerializedName("ime")     private String name;
    @SerializedName("prezime") private String prezime;
    @SerializedName("telefon") private String phoneNumber;
    @SerializedName("mobitel") private String mobilePhoneNumber;
    @SerializedName("mail")    private String email;
    @SerializedName("adresa")  private String address;
}

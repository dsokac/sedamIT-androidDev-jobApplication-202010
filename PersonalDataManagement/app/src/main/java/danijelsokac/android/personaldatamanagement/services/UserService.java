package danijelsokac.android.personaldatamanagement.services;

import java.util.List;

import danijelsokac.android.personaldatamanagement.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("/osobe")
    Call<List<UserModel>> getUsers();

    @GET("/osobe/{id}")
    Call<UserModel> getUserById(@Path("id") int id);

    @POST("/osobe")
    Call<UserModel> saveUser(@Body UserModel user);

    @PUT("/osobe/{id}")
    Call<UserModel> updateUser(@Body UserModel user, @Path("id") int id);

    @DELETE("/osobe/{id}")
    Call<UserModel> deleteUser(@Path("id") int id);
}

package com.example.sisagua.network;

import com.example.sisagua.models.Abonado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface InterfaceAPI {
    /*
    @POST("authentication/login/")
    Call<Abonado> loginWithCredentials(@Body Credentials user);
    */
    @GET ("abonados/")
    Call<List<Abonado>> getAbonados(@Header("authorization") String auth);
    /*
    @POST ("checklist/locations")
    Call<LocationModel> postLocation(@Header("authorization") String auth, @Body LocationModel locationModel);

    @GET("checklist/checklist/")
    Call<List<GetCheckListModel>> getCheckList(@Header("authorization") String auth);

    @GET ("checklist/checklist-items/")
    Call<List<ItemCheckListModel>> getCheckListItem(@Header("authorization") String token);

    @POST("checklist/checklist/")
    Call<CheckListModel> postCheckList(@Header("authorization") String auth, @Body CheckListModel checkListModel);

    @GET("checklist/checklist-section-items/{idCheckList}")
    Call<List<ItemCheckListModel>> getCheckListItemByCheckList(@Header("authorization") String auth, @Path("idCheckList") String idCheckList);

    @PUT("checklist/checklist_section_items/{idItem}/")
    Call<PostItemCheckListModel> putItem(@Header("authorization")String auth, @Path("idItem") String idItem, @Body PostItemCheckListModel itemCheckListModel);

    @PATCH("checklist/checklist_section_items/{id}/")
    Call<ResponseLevItemModel> patchItem(@Header("authorization")String auth, @Path("id") String id, @Body PatchLevItemModel patchLevItemModel);

    @PATCH("checklist/checklist/{idItem}/")
    Call<GetPatchValidateModel> patchValidate(@Header("authorization")String auth, @Path("idItem") int idItem, @Body PatchValidarModel patchLevItemModel);
    */
}
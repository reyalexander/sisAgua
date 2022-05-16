package com.example.sisagua.network;

import com.example.sisagua.models.Abonado;
import com.example.sisagua.models.Medidor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceAPI {
    @GET ("api/abonados")
    Call<List<Abonado>> getAbonados(@Header("authorization") String auth);

    @GET ("api/abonados/medidores/{id}")
    Call<List<Medidor>> getMedidores(@Header("authorization") String auth, @Path("id") String id);

}
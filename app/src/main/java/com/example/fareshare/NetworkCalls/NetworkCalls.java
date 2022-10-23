package com.example.fareshare.NetworkCalls;

import com.example.fareshare.end_point.ApiPath;
import com.example.fareshare.model.Admin;
import com.example.fareshare.model.Driver;
import com.example.fareshare.model.Passenger;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkCalls {

    @POST(ApiPath.PASSENGER_LOGIN)
    @FormUrlEncoded
    Call<Passenger> passengerLogin(@Field("p_email") String email, @Field("p_password") String password);

    @POST(ApiPath.DRIVER_LOGIN)
    @FormUrlEncoded
    Call<Driver> driverLogin(@Field("d_email") String email, @Field("d_password") String password);

    @POST(ApiPath.DRIVER_SIGNUP)
    @FormUrlEncoded
    Call<Driver> driverSignup(@Field("d_name") String name,
                              @Field("d_email") String email,
                              @Field("d_password") String password,
                              @Field("d_cnic") String cnic,
                              @Field("d_cellno") String d_cellno,
                              @Field("d_vehicaltype") String vehicaltype,
                              @Field("d_vehical_numplate") String numberplate,
                              @Field("d_licence") String licence,
                              @Field("d_gender") String d_gender);


    @POST(ApiPath.PASSENGER_SIGNUP)
    @FormUrlEncoded
    Call<Passenger> passengerSignup(@Field("p_name") String name,
                                    @Field("p_email") String email,
                                    @Field("p_password") String password,
                                    @Field("p_cellno") String p_cellno,
                                    @Field("p_gender") String p_gender
    );

    @POST(ApiPath.ADMIN_LOGIN)
    @FormUrlEncoded
    Call<Admin> adminLogin(@Field("a_name") String a_name,
                           @Field("a_password") String a_password
    );

    @POST(ApiPath.ADD_FARE)
    @FormUrlEncoded
    Call<Admin> addFare(@Field("base_fare")  int base_fare,
                           @Field("km_fare")  int km_fare

    );
    @GET(ApiPath.GET_ALLPASSENGERS)
    Call<JsonObject> getAllPassengers();

    @GET(ApiPath.GET_ALLDRIVERS)
    Call<JsonObject> getAllDrivers();

    @POST(ApiPath.UPDATEPASSENGER_STATUS)
    @FormUrlEncoded
    Call<JsonObject> updatepassengerstatus(@Field("p_id") int id, @Field("p_status") String status);

    @POST(ApiPath.UPDATEDRIVER_STATUS)
    @FormUrlEncoded
    Call<JsonObject> updatedriverstatus(@Field("d_id") int id, @Field("d_status") String status);
    //upate passenger
    @POST(ApiPath.UPDATEDRIVER_RECORD)
    @FormUrlEncoded
    Call<Driver>updatedriverrecord(@Field("d_id") int id,
                                    @Field("d_name") String name,
                                    @Field("d_email") String email,
                                    @Field("d_cellno") String d_cellno,
                                    @Field("d_vehicaltype") String vehicaltype,
                                    @Field("d_vehical_numplate") String numberplate,
                                    @Field("d_licence") String licence);

   //updatepassenger
    @POST(ApiPath.UPDATEDRIVER_STATUS)
    @FormUrlEncoded
    Call<Passenger>updatepassengerrecord(@Field("p_id") int id,
                                         @Field("p_name") String name,
                                         @Field("p_email") String email,
                                         @Field("p_cellno") String p_cellno

    );


}

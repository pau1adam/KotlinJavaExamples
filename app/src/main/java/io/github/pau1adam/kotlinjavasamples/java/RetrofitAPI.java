package io.github.pau1adam.kotlinjavasamples.java;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class RetrofitAPI {

    public RetrofitAPI() {
    }

    private static final String BASE_URL = "http://food2fork.com/api/";

    public static final String API_KEY = "add api key";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    public static Food2ForkApi api = retrofit.create(Food2ForkApi.class);
}

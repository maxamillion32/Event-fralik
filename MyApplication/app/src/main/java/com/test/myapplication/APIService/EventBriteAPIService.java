package com.test.myapplication.APIService;

import com.test.myapplication.Models.CategoriesModel.CategoriesObject;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by NehaRege on 8/18/16.
 */
public interface EventBriteAPIService {
    /**
     *  Free Events
     *
     *  URL: https://www.eventbriteapi.com/v3/events/?token=AMDMMKWPWFPOCAUYVIW2&price=free
     *
     *
     *
     */

//    headers = {
//        "Authorization": "Bearer SESXYS4X3FJ5LHZRWGKQ",
//    }

    @GET("v3/events/")
    Call<FreeEventsObject> getAllFreeEvents(
            @Query("price") String free,
            @Header("Authorization") String token);

    @GET("v3/events/")
    Call<FreeEventsObject> getAllEvents(
            @Header("Authorization") String token);

//    https://www.eventbriteapi.com/v3/events/?token=AMDMMKWPWFPOCAUYVIW2&price=free&q=popular

//

    /**
     *
     *    https://www.eventbriteapi.com/v3/events/?token=AMDMMKWPWFPOCAUYVIW2&price=free&q=popular&venue.country=America
     *
     *
     * @param free
     * @param popular
     * @param America
     * @param token
     * @return
     */

    @GET("v3/events/")
    Call<FreeEventsObject> getAllFreePopularEvents(
            @Query("price") String free,
            @Query("q") String popular,
            @Query("venue.country") String America,
            @Header("Authorization") String token
    );

    /**
     *
     *   https://www.eventbriteapi.com/v3/categories/?token=AMDMMKWPWFPOCAUYVIW2
     *
     * @param token
     * @return
     */
    @GET("/categories")
    Call<CategoriesObject> getCategories(
            @Header("Authorization") String token
    );


//    //For trending news
//    @GET("trendingtopics")
//    Call<TrendingTopicsObject> getTrendingTopics(@Header("Ocp-Apim-Subscription-Key") String apiKey);
//
//
//    @GET("news")
//    Call<CategoryNewsObject> getSpecificTopicArticles(
//            @Query("category") String categoryName, @Header("Ocp-Apim-Subscription-Key") String apiKey);
//
//
//    @GET("search?")
//    Call<SearchNewsObject> getArticlesBasedOnSearchQuery(
//            @Query("q") String searchQuery, @Header("Ocp-Apim-Subscription-Key") String apiKey);
//



}

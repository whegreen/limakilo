package id.limakilo.www.bawang.util.api.campaign;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by walesadanto on 11/11/15.
 */
public interface CampaignService {


    @GET("/microfunds/{id}")
    void getCampaignDetail(@Header("authentification") String authentification,
                        @Path("id") String orderId,
                        Callback<GetCampaignDetailResponseModel> callback);

    @GET("/microfunds")
    void getCampaign(@Header("authentification") String authentification,
                   Callback<GetCampaignResponseModel> callback);

}

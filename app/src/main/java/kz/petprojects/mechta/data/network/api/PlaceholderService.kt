package kz.petprojects.mechta.data.network.api
import kz.petprojects.mechta.data.network.response.getSmartphones.GetSmartphonesResponse
import kz.petprojects.mechta.data.network.response.getSmartphoneDetails.GetSmartphoneDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceholderService {
    @GET("catalog")
    suspend fun getSmartphones(
        @Query("page") page: Int = 1,
        @Query("pageLimit") pageLimit: Int = 20,
        @Query("section") section: String = "smartfony"
    ): GetSmartphonesResponse

    @GET("product/{code}")
    suspend fun getSmartphoneDetails(
        @Path("code") code: String
    ): GetSmartphoneDetailsResponse

}
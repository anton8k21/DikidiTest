import com.example.dikiditest.data.modelDto.HomeInfoModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("info")
    suspend fun getHomeInfo(@Header("Authorization") auth: String): Response<HomeInfoModelDto>


}
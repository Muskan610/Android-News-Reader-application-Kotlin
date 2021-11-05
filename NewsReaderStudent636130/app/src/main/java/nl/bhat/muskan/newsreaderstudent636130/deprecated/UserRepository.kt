package nl.bhat.muskan.newsreaderstudent636130.deprecated
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.GetResultsService
import nl.bhat.muskan.newsreaderstudent636130.AuthToken
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userApi: GetResultsService,
    private val userManager: UserManager
)  {

    // save authtoken to dataStore
    suspend fun saveAuthToken(token: AuthToken) {
        userManager.saveAuthToken(token)
    }

    suspend fun logout(){
        userManager.deleteAuthToken()
    }
}

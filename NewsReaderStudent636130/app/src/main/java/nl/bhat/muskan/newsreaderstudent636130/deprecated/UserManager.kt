package nl.bhat.muskan.newsreaderstudent636130.deprecated

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.bhat.muskan.newsreaderstudent636130.AuthToken
import javax.inject.Inject

private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(name = "key_store")

class UserManager @Inject constructor(@ApplicationContext context: Context){

    private val dataStore : DataStore<Preferences> by lazy {
        context._dataStore
    }

    companion object {
      private  val TOKEN = stringPreferencesKey("AUTH_TOKEN")
    }

    // get saved authentication token
    val getAuthToken: Flow<AuthToken?>
    get() = dataStore.data.map { preferences ->
        preferences[TOKEN]?.let { AuthToken(it) }
    }

    // save login authentication token
    suspend fun saveAuthToken(authToken: AuthToken) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = authToken.toString()
        }
    }

    suspend fun deleteAuthToken() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}


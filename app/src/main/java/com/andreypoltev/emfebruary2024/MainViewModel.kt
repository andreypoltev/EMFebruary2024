package com.andreypoltev.emfebruary2024

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreypoltev.emfebruary2024.model.APIResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getApiResponse()
        }
    }

    suspend fun getApiResponse(): APIResponse {
        try {
            val client = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }

            val link = "https://run.mocky.io/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010"
            val response = client.get(link)
            client.close()

            Log.d("MyResponse", response.body())
            Log.d("MyResponse", response.status.toString())

            return response.body()
        } catch (e: Exception) {
//            _toastMessage.value = e.message.toString()
            // Handle exception, log, or rethrow as needed
            Log.e("MyResponse", "Error fetching API response: ${e.message}")
            throw e
        } finally {
//            _toastMessage.value = "Info loaded successfully"
            // Any cleanup or finalization code
        }
    }

}
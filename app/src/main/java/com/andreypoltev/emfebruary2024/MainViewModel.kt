package com.andreypoltev.emfebruary2024

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreypoltev.emfebruary2024.data.APIResponse
import com.andreypoltev.emfebruary2024.data.model.Item
import com.andreypoltev.emfebruary2024.domain.ItemDao
import com.andreypoltev.emfebruary2024.domain.User
import com.andreypoltev.emfebruary2024.domain.UserDao
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(val itemDao: ItemDao, val userDao: UserDao) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    private val _sortType = MutableStateFlow(SortType.byDefault)
    private val _filterTag = MutableStateFlow(Tags.showAll.tag)

    private fun sort(items: List<Item>, sortType: String): List<Item> {
        return when (sortType) {
            SortType.byDefault -> {
                items
            }

            SortType.byRating -> {
                items.sortedByDescending { it.feedback?.rating }
            }

            SortType.byPriceDesc -> {
                items.sortedByDescending { it.price.priceWithDiscount.toInt() }
            }

            SortType.byPriceAsc -> {
                items.sortedBy {
                    it.price.priceWithDiscount.toInt()
                }
            }

            else -> {
                items // or throw an exception, or handle this case differently
            }
        }
    }

    private fun filter(items: List<Item>, tag: String): List<Item> {

        return if (tag == Tags.showAll.tag) {

            items

        } else {

            items.filter { it.tags.any { it == tag } }

        }


    }

    fun items(): Flow<List<Item>> {
        return combine(items, _filterTag, _sortType) { items, filterTag, sortType ->
            sort(filter(items, filterTag), sortType)
        }.flowOn(Dispatchers.IO)
    }

    fun setSortType(sortType: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _sortType.value = sortType
        }


    }


    fun setTag(tag: String) {
        viewModelScope.launch {
            _filterTag.value = tag
        }

    }


    fun flowFavorites() = itemDao.flowAllItems()

    fun flowUsers() = userDao.flowAllUsers()

    fun clearDb() = viewModelScope.launch(Dispatchers.IO) {
        userDao.clearTable()
        itemDao.clearTable()
    }

    fun flowRowCount() = itemDao.flowRowCount()

    fun flowUserCount() = userDao.flowUserCount()

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

//    suspend fun addItem(item: Item) = withContext(Dispatchers.IO) {
//        itemDao.insertItem(item)
//
//    }
//
//    suspend fun removeItem(item: Item) = withContext(Dispatchers.IO) {
//        itemDao.removeItem(item)
//
//    }

    fun addOrRemoveItem(item: Item) {

        viewModelScope.launch {

            val items = itemDao.getAllItems()

            if (item in items) {
                itemDao.removeItem(item)

            } else {
                itemDao.insertItem(item)
            }

        }


    }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = getApiResponse()
//            val response = getApiResponse().items
        }
    }

    suspend fun getApiResponse(): List<Item> {
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
//            Log.d("MyResponse", response.status.toString())

//            val r: APIResponse = response.body<APIResponse>()

            return response.body<APIResponse>().items
        } catch (e: Exception) {

//            _toastMessage.value = e.message.toString()
            // Handle exception, log, or rethrow as needed
            Log.e("MyResponse", "Error fetching API response: ${e.message}")
            return emptyList()
        } finally {
//            _toastMessage.value = "Info loaded successfully"
            // Any cleanup or finalization code
        }
    }

}
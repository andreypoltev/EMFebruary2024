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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val itemDao: ItemDao, val userDao: UserDao) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    private val _itemsFilteredByTag = _items
    val itemsFilteredByTag = _itemsFilteredByTag.asStateFlow()

    private val _sortType = MutableStateFlow(SortType.byDefault)

    private val _itemsFilteredAndSorted = _items
    val itemsFilteredAndSorted = _itemsFilteredAndSorted.asStateFlow()


    fun sortAndFilter() {

    }

    fun setSortType(sortType: String) {

        when (sortType) {

            SortType.byDefault -> {
                _itemsFilteredAndSorted.value =
                    _items.value
            }

            SortType.byRating -> {
                _itemsFilteredAndSorted.value =
                    _itemsFilteredAndSorted.value.sortedByDescending { it.feedback?.rating }
            }

            SortType.byPriceDesc -> {
                _itemsFilteredAndSorted.value =
                    _itemsFilteredAndSorted.value.sortedByDescending { it.price.priceWithDiscount.toInt() }
            }

            SortType.byPriceAsc -> {
                _itemsFilteredAndSorted.value =
                    _itemsFilteredAndSorted.value.sortedBy {
                        Log.d("", it.price.priceWithDiscount)
                        it.price.priceWithDiscount.toInt()
                    }
            }
        }


    }


    fun setTag(tag: String) {
        viewModelScope.launch {

            if (tag == "show_all") {
                _itemsFilteredByTag.value = _items.value
            } else {
                _itemsFilteredByTag.value = _items.value.filter {
                    Log.d("", "Items tags: ${it.tags}")
                    it.tags.any { it == tag }
                }
            }


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

//            Log.d("MyResponse", response.body())
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
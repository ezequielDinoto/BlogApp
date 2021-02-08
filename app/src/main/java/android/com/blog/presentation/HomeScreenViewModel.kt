package android.com.blog.presentation

import android.com.blog.core.Resource
import android.com.blog.domain.HomeScreenRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeScreenViewModel (private val  repo: HomeScreenRepo):ViewModel() {

    fun fetchLatestPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getLatestPosts())

        }catch (e:Exception){

            Resource.Failure(e)

        }
    }
}

class HomeScreenViewFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }

}
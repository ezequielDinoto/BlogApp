package android.com.blog.domain

import android.com.blog.core.Resource
import android.com.blog.data.model.Post
import android.com.blog.data.model.remote.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatestPosts()



}
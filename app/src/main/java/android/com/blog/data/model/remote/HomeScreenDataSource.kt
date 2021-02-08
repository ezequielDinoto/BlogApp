package android.com.blog.data.model.remote

import android.com.blog.core.Resource
import android.com.blog.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

//trae la info Firebase
class HomeScreenDataSource {

    suspend fun getLatestPosts(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let { fbPost ->
                postList.add(fbPost)

            }
        }
        return Resource.Success(postList)
    }
}
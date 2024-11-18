package com.example.videogamedbapp.data.repositories

import android.content.Context
import com.example.videogamedbapp.R
import com.example.videogamedbapp.core.Resource
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository(private val context: Context) {
    suspend fun <T> handleApiCall(execute: suspend () -> T): Resource<T> {
        return try {
            val result = execute()
            Resource.Success(result)
        } catch (e: HttpException) {
            Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        } catch (e: IOException) {
            Resource.Error(context.getString(R.string.no_internet_error))
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.unexpected_error))
        }
    }
}
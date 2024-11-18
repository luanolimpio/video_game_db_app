package com.example.videogamedbapp.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.usecases.GetGenresUseCase
import com.example.videogamedbapp.domain.usecases.GetPlatformsUseCase
import com.example.videogamedbapp.domain.usecases.GetPublishersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    getPlatformsUseCase: GetPlatformsUseCase,
    getGenresUseCase: GetGenresUseCase,
    getPublishersUseCase: GetPublishersUseCase
) :
    ViewModel() {
    val platforms = getPlatformsUseCase().cachedIn(viewModelScope)

    val genres = getGenresUseCase().cachedIn(viewModelScope)

    val publishers = getPublishersUseCase().cachedIn(viewModelScope)
}
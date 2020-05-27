package com.tiredbones.photobrowser.feature.photos.recent

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.feature.photos.PhotosRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetRecentPhotosUseCaseTest {

  @Mock lateinit var photosRepository: PhotosRepository

  private lateinit var sut: GetRecentPhotosUseCase

  @Before
  fun setUp() {
    sut = GetRecentPhotosUseCase(photosRepository)
  }

  @Test
  fun `invoke should returns list with photo items from repository`() {
    val page = 1
    val perPage = 1
    val result = listOf(PhotoData(id = "1", owner = "2", secret = "3", server = "4", farm = 5, title = "6"))
    given(photosRepository.getRecentPhotos(page, perPage)).willReturn(
        Single.just(result)
    )

    sut.invoke(page, perPage).test().assertValue(result)
  }

  @Test
  fun `invoke should return an error if repository returns error`() {
    val page = 1
    val perPage = 1
    val error = mock<Exception>()
    given(photosRepository.getRecentPhotos(page, perPage)).willReturn(
        Single.error(error)
    )

    sut.invoke(page, perPage).test().assertError(error)
  }
}

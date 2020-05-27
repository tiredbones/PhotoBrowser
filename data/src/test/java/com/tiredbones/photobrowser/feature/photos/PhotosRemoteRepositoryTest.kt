package com.tiredbones.photobrowser.feature.photos

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.tiredbones.photobrowser.mapper.map
import com.tiredbones.photobrowser.network.response.PhotoItemResponse
import com.tiredbones.photobrowser.network.response.PhotosPagerResponse
import com.tiredbones.photobrowser.network.response.RecentPhotosResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PhotosRemoteRepositoryTest {
  @Mock lateinit var photosApi: PhotosApi

  private lateinit var sut: PhotosRemoteRepository

  @Before
  fun setUp() {
    sut = PhotosRemoteRepository(photosApi)
  }

  @Test
  fun `getRecentPhotos should returns list with photo items if api returns success result`() {
    val page = 1
    val perPage = 1
    val photoItem = PhotoItemResponse(id = "1", owner = "2", secret = "3", server = "4", farm = 5, title = "6")
    val response = RecentPhotosResponse(
        photos = PhotosPagerResponse(
            page = 1,
            photo = listOf(photoItem)
        ),
        stat = "ok"
    )
    given(photosApi.getRecentPhotos(page, perPage)).willReturn(
        Single.just(response)
    )
    val expectedResult = response.map()

    sut.getRecentPhotos(page, perPage).test().assertValue(expectedResult)
  }

  @Test
  fun `getRecentPhotos should return an error if api returns failure`() {
    val page = 1
    val perPage = 1
    val error = mock<Exception>()
    given(photosApi.getRecentPhotos(page, perPage)).willReturn(
        Single.error(error)
    )

    sut.getRecentPhotos(page, perPage).test().assertError(error)
  }
}

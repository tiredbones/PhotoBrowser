package com.tiredbones.photobrowser.feature.photos.recent

import com.tiredbones.photobrowser.BuildConfig
import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotoItem
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PhotosMapperTest {

  private val sut = PhotosMapper()

  @Test
  fun `map should returns mapped list`() {
    val id = "id"
    val owner = "owner"
    val secret = "secret"
    val server = "server"
    val farm = 1
    val title = "title"
    val input = listOf(PhotoData(id, owner, secret, server, farm, title))

    val expectedResult = listOf(PhotoItem(
        id = id,
        imageUrl = BuildConfig.FARM_URL.format(farm, server, id, secret),
        title = title
    ))
    val actualResult = sut.mapToUiItems(input)

    assertThat(actualResult, equalTo(expectedResult))
  }
}

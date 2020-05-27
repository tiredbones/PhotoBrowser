package com.tiredbones.photobrowser.feature.photos.recent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.tiredbones.photobrowser.entities.PhotoData
import io.reactivex.Single
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import util.RxTestRule
import util.testObserver

@RunWith(MockitoJUnitRunner::class)
internal class RecentPhotosViewModelTest {
  @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
  @get:Rule val rxTestRule = RxTestRule()

  @Mock lateinit var getRecentPhotosUseCase: GetRecentPhotosUseCase
  @Mock lateinit var photosMapper: PhotosMapper

  private lateinit var sut: RecentPhotosViewModel

  @Before
  fun setUp() {
    sut = RecentPhotosViewModel(getRecentPhotosUseCase, photosMapper)
  }

  @Test
  fun `getRecentPhotosUseCase should be invoked when view model is created`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.just(list)
    )

    subscribeToList()
    verify(getRecentPhotosUseCase).invoke(any(), any())
  }

  @Test
  fun `viewModel should start with loading when created`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.just(list)
    )

    val testObserver = sut.loading.testObserver()
    subscribeToList()
    assertThat(testObserver.observedValues.first(), equalTo(true))
  }

  @Test
  fun `viewModel should stop loading data is received`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.just(list)
    )

    val testObserver = sut.loading.testObserver()
    subscribeToList()
    assertThat(testObserver.observedValues[1], equalTo(false))
  }

  @Test
  fun `showError should be triggered when getRecentPhotosUseCase returns error`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.error(mock<Throwable>())
    )

    val testObserver = sut.showError.testObserver()
    subscribeToList()
    assertThat(testObserver.observedValues.size, equalTo(1))
  }

  @Test
  fun `loading should be stopped when getRecentPhotosUseCase returns error`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.error(mock<Throwable>())
    )

    val testObserver = sut.loading.testObserver()
    subscribeToList()
    assertThat(testObserver.observedValues[1], equalTo(false))
  }

  @Test
  fun `onRetryClicked should start with loading`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.error(mock<Throwable>())
    )

    val testObserver = sut.loading.testObserver()
    subscribeToList()

    sut.onRetryClicked()
    assertThat(testObserver.observedValues[2], equalTo(true))
  }

  @Test
  fun `onRetryClicked should trigger getRecentPhotosUseCase`() {
    given(getRecentPhotosUseCase.invoke(any(), any())).willReturn(
        Single.error(mock<Throwable>())
    )

    subscribeToList()
    sut.onRetryClicked()
    verify(getRecentPhotosUseCase, times(2)).invoke(any(), any())
  }

  private fun subscribeToList() {
    sut.photos.observeForever { }
  }

  companion object {
    val list = listOf(PhotoData(id = "1", owner = "2", secret = "3", server = "4", farm = 5, title = "6"))
  }
}

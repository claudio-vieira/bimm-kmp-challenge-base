package com.bimm.takehomeassignmnent

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import com.bimm.takehomeassignmnent.sakes.data.SakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
class SakeLocationsSharedViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSakeLocationStateIsLoadedCorrectly() = runTest {
        // Given
        val fakeData = listOf(
            SakeLocation(
                name = "信州スシサカバ 寿しなの",
                description = "Sushi bar with a variety of sake options.",
                picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                rating = 4.0f,
                address = "〒380-0824 長野県長野市南長野南石堂町1421",
                coordinates = arrayListOf(36.644257, 138.18668),
                googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
                website = "https://www.sushinano.com/"
            )
        )

        val fakeDataSource = FakeSakeDataSource(fakeData)
        val repository = SakeRepository(fakeDataSource)

        //When
        val viewModel = SakeLocationsSharedViewModel(repository)

        // Let the coroutine launch inside ViewModel init complete
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(fakeData, viewModel.sakeLocationState.value)
    }

    @Test
    fun testSetSakeSelected() {
        // Given
        val fakeData = listOf(
            SakeLocation(
                name = "信州スシサカバ 寿しなの",
                description = "Sushi bar with a variety of sake options.",
                picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                rating = 4.0f,
                address = "〒380-0824 長野県長野市南長野南石堂町1421",
                coordinates = arrayListOf(36.644257, 138.18668),
                googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
                website = "https://www.sushinano.com/"
            )
        )

        val fakeDataSource = FakeSakeDataSource(fakeData)
        val repository = SakeRepository(fakeDataSource)

        val viewModel = SakeLocationsSharedViewModel(repository)
        val selected = fakeData[0]

        viewModel.setSakeSelected(selected)

        assertEquals(selected, viewModel.sakeSelected)
    }
}
package com.bimm.takehomeassignmnent

import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.sakes.data.ISakeRepository
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class FakeSakeRepository(private val result: ResponseState<List<SakeLocation>>) : ISakeRepository {
    override suspend fun fetchSakeLocations(): ResponseState<List<SakeLocation>> = result
}

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
    fun `fetch success updates state with data`() = runTest {
        // Given
        val sakeList = listOf(
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

        //When
        val fakeRepo = FakeSakeRepository(ResponseState.Success(sakeList))
        val viewModel = SakeLocationsSharedViewModel(fakeRepo)

        advanceUntilIdle()

        val state = viewModel.sakeLocationState.value

        // Then
        assertEquals(sakeList, state.sakeLocations)
        assertFalse(state.loading)
    }

    @Test
    fun `setSakeSelected updates selected sake`() {
        // Given
        val sake = SakeLocation(
            name = "信州スシサカバ 寿しなの",
            description = "Sushi bar with a variety of sake options.",
            picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
            rating = 4.0f,
            address = "〒380-0824 長野県長野市南長野南石堂町1421",
            coordinates = arrayListOf(36.644257, 138.18668),
            googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
            website = "https://www.sushinano.com/"
        )

        val fakeRepo = FakeSakeRepository(ResponseState.Success(emptyList()))
        val viewModel = SakeLocationsSharedViewModel(fakeRepo)

        //When
        viewModel.setSakeSelected(sake)

        // Then
        assertEquals(sake, viewModel.sakeSelected)
    }
}
package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Event2
import com.alif.daftarliga.model.EventResponse2
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.utilities.TestContextProvider
import com.alif.daftarliga.view.viewinterfaces.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testSearchMatchDataFromAPI() {
        // NOTE: to initialize means creating a mock here
        // initialize event
        val event = Event2("610504","","","Arsenal vs Chelsea",
            "Chelsea @ Arsenal","FA Community Shield 2015-08-02 Arsenal vs Chelsea",
            "Soccer","4571","FA Community Shield","2015",
            "2015 FA Community Shield (also known as The FA Community Shield supported by McDonald's for sponsorship reasons) was the 93rd FA Community Shield, an annual English football match played between the winners of the previous season's Premier League and FA Cup. The match was contested by Arsenal, the 2014–15 FA Cup winners, and Chelsea, champions of the 2014–15 Premier League. It was held at Wembley Stadium on 2 August 2015.",
            "Arsenal","Chelsea","1","0","0","85437",
            "","","","","",
            "","","","","",
            "","","","","",
            "","","","","",
            "2015-08-02","","","00:00:00","","",
            "133604","133610",
            "Watched by a crowd of 85,437 and a television audience of over a million, Arsenal won the match 1–0. \r\nThe only goal of the match came near the midway point of the first half; Alex Oxlade-Chamberlain collected a pass from Theo Walcott from the right and scored past Thibaut Courtois. Both managers did not shake hands after the game, which drew media attention. Arsenal's victory was their first against Chelsea in over three years and manager Arsène Wenger's first over José Mourinho in 14 attempts. ",
            "","","","https://www.thesportsdb.com/images/media/event/poster/pjjo191565436756.jpg",
            "","","","","","","","","unlocked")

        // create eventList from event
        val eventList:ArrayList<Event2> = ArrayList()
        eventList.add(event)

        // initialize responseObject
        // responseObject consists of list of events
        val responseObject = EventResponse2(eventList)

        // response is arraylist of responseObject(EventResponse2)
        val response: ArrayList<EventResponse2> = ArrayList()
        response.add(responseObject)

        // initialize search query
        val query = "Liverpool"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestWithCoroutinesAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventResponse2::class.java
                )
            ).thenReturn(responseObject)

            presenter.searchMatchDataFromAPI(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showSearchedMatches(response)
            Mockito.verify(view).hideLoading()
        }
    }
}
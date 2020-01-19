package com.alif.daftarliga.model.webservice

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun testDoRequestWithCoroutinesAsync() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=4356"
        apiRepository.doRequestWithCoroutinesAsync(url)
        verify(apiRepository).doRequestWithCoroutinesAsync(url)
    }

    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=4356"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}
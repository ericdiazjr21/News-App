package ericdiaz.program.newsapp

import ericdiaz.program.newsapp.network.NetworkModule
import org.junit.Test

class NetworkTest {

    private val testSubject = NetworkModule.getNewsService()

    @Test
    fun `test network connection`() {
        testSubject.getNews()
            .test()
            .await()
    }
}
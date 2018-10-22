package com.fabianrguezrguez.appofthrones

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import android.support.test.filters.LargeTest
import com.fabianrguezrguez.appofthrones.R.id.googleBtn
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.rule.BaristaRule
import org.junit.Before
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.junit.WireMockRule






/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.fabianrguezrguez.appofthrones", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    var baristaRule = BaristaRule.create(MainActivity::class.java)

    @Rule
    var wireMockRule = WireMockRule(8089)

    @Before
    fun setup() {
        baristaRule.launchActivity()
    }

    @Test
    fun listGoesOverTheFold() {
        assertDisplayed("Jon Snow")
    }

    @Test
    fun openUrlLinkOnNewWindow() {

        val server = MockWebServer()

        server.enqueue(MockResponse().setBody("hello, world!"))

        server.start()

        clickOn(googleBtn)
        val request1 = server.takeRequest()
        assertEquals("https://www.google.com", request1.path)
        assertNotNull(request1.getHeader("Authorization"))

        server.shutdown()
    }
}

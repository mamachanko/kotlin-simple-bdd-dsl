package io.github.mamachanko.kotlinsimplebdddsl

import com.google.common.truth.Truth.assertThat
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.fluentlenium.adapter.junit.FluentTest
import org.fluentlenium.core.FluentPage
import org.fluentlenium.core.annotation.Page
import org.fluentlenium.core.annotation.PageUrl
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class FeatureTest : FluentTest() {

    @LocalServerPort
    private val serverPort: Int = 0

    @Page
    lateinit var homepage: Homepage

    companion object {

        @BeforeClass @JvmStatic
        fun setupClass() {
            ChromeDriverManager.getInstance().setup()
        }
    }

    @Before
    fun setUp() {
        baseUrl = "http://localhost:$serverPort"
    }

    @Test
    fun `I can click a button to see a message`() {
        `given I am on the homepage` {
            `when I click the button` {
                `then I see`("Thank you for clicking!")
            }
        }
    }

    private fun `given I am on the homepage`(body: () -> Unit) {
        goTo(homepage)
        body()
    }

    private fun `when I click the button`(body: () -> Unit) {
        homepage.clickButton()
        body()
    }

    private fun `then I see`(expectedMessage: String) {
        assertThat(homepage.message).isEqualTo(expectedMessage)
    }
}

@PageUrl("/")
class Homepage : FluentPage() {

    fun clickButton() = `$`("#clickMe").click()

    val message: String
        get() = `$`("#message").text()
}
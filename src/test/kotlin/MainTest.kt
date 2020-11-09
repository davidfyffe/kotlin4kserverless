import com.lm.app
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MainTest {

    @Test
    fun `should return a users list`() {
        val response = app(Request(GET, "/users"))
        assertThat(response, hasStatus(OK))
    }

    @Test
    fun `should add a user to a list`() {
        val response = app(Request(Method.POST, "/users/DAVID"))
        assertThat(response, hasStatus(OK))
    }
}
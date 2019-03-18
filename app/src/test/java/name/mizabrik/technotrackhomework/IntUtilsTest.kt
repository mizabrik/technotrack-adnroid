package name.mizabrik.technotrackhomework

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.allegro.finance.tradukisto.ValueConverters

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class IntUtilsTest {
    @Test
    fun toRussian_small() {
        val converter = ValueConverters.RUSSIAN_INTEGER
        for (i in 1..999) assertEquals(converter.asWords(i), i.toRussian())
    }

    @Test
    fun toRussian_thousand() {
        assertEquals("тысяча", 1000.toRussian())
    }
}
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GildedRoseTest {

    internal val expected = """-------- day 0 --------
name, sellIn, quality
+5 Dexterity Vest / sell in 10 / quality of 20
Aged Brie / sell in 2 / quality of 0
Elixir of the Mongoose / sell in 5 / quality of 7
Sulfuras, Hand of Ragnaros / sell in 0 / quality of 80
Sulfuras, Hand of Ragnaros / sell in -1 / quality of 80
Backstage passes to a TAFKAL80ETC concert / sell in 15 / quality of 20
Backstage passes to a TAFKAL80ETC concert / sell in 10 / quality of 49
Backstage passes to a TAFKAL80ETC concert / sell in 5 / quality of 49
Conjured Mana Cake / sell in 3 / quality of 6

-------- day 1 --------
name, sellIn, quality
+5 Dexterity Vest / sell in 9 / quality of 19
Aged Brie / sell in 1 / quality of 1
Elixir of the Mongoose / sell in 4 / quality of 6
Sulfuras, Hand of Ragnaros / sell in 0 / quality of 80
Sulfuras, Hand of Ragnaros / sell in -1 / quality of 80
Backstage passes to a TAFKAL80ETC concert / sell in 14 / quality of 21
Backstage passes to a TAFKAL80ETC concert / sell in 9 / quality of 50
Backstage passes to a TAFKAL80ETC concert / sell in 4 / quality of 50
Conjured Mana Cake / sell in 2 / quality of 5
    """.trimIndent()

    @Test
    fun firstTest() {

        var output = ByteArrayOutputStream()
        val out = PrintStream(output)

        var items = listOf(Item("+5 Dexterity Vest", 10, 20), //
                Item("Aged Brie", 2, 0), //
                Item("Elixir of the Mongoose", 5, 7), //
                Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                Item("Sulfuras, Hand of Ragnaros", -1, 80), Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), Item("Backstage passes to a TAFKAL80ETC concert", 10, 49), Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                Item("Conjured Mana Cake", 3, 6))

        var app = GildedRose(items)

        val days = 2

        for (i in 0 until days) {
            out.println("-------- day $i --------")
            out.println("name, sellIn, quality")
            for (item in items) {
                out.println(item)
            }
            out.println()
            app.updateQuality()
        }

        println(output.toString())
        assertEquals(expected, output.toString().replace("\r\n".toRegex(), "\n").trim())

    }
}
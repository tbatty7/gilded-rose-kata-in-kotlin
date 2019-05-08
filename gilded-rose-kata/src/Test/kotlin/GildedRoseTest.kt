import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class GildedRoseTest {

    @Test
    internal fun `Test Regular Items`() {
        val quality = 10
        val newRegularItem = Item("potato", 5, quality)
        val expiredRegularItem = Item("strawberries", 0, quality)
        val rottenRegularItem = Item("strawberries", -1, 0)
        val items = Arrays.asList(newRegularItem, expiredRegularItem, rottenRegularItem)
        val gildedRose = GildedRose(items)
        gildedRose.updateQuality()
        assertUpdateOn(newRegularItem, 4, quality - 1)
        assertUpdateOn(expiredRegularItem, -1, quality - 2)
        assertUpdateOn(rottenRegularItem, -2, 0)
    }

    @Test
    internal fun `Test Legendary Items`() {
        val quality = 80
        val newLegendaryItem = Item("Sulfuras, Hand of Ragnaros", 10, quality)
        val expiringLegendaryItem = Item("Sulfuras, Hand of Ragnaros", 1, quality)
        val expiredLegendaryItem = Item("Sulfuras, Hand of Ragnaros", -1, quality)
        val items = Arrays.asList(newLegendaryItem, expiringLegendaryItem, expiredLegendaryItem)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertUpdateOn(newLegendaryItem, 10, quality)
        assertUpdateOn(expiringLegendaryItem, 1, quality)
        assertUpdateOn(expiredLegendaryItem, -1, quality)
    }

    @Test
    internal fun `Test Cheesy Items`() {
        val quality = 10
        val topQuality = 50
        val newCheese = Item("Aged Brie", 10, quality)
        val oldCheese = Item("Aged Brie", 1, quality)
        val expiredCheese = Item("Aged Brie", 0, quality)
        val perfectCheese = Item("Aged Brie", 0, topQuality)
        val items = Arrays.asList(newCheese, expiredCheese, oldCheese, perfectCheese)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertUpdateOn(newCheese, 9, quality + 1)
        assertUpdateOn(oldCheese, 0, quality + 1)
        assertUpdateOn(expiredCheese, -1, quality + 2)
        assertUpdateOn(perfectCheese, -1, topQuality + 0)
    }

    @Test
    internal fun `Test Backstage Passes`() {
        val quality = 10
        val topQuality = 50
        val newPass = Item("Backstage passes to a TAFKAL80ETC concert", 11, quality)
        val oldPass = Item("Backstage passes to a TAFKAL80ETC concert", 10, quality)
        val lastPass = Item("Backstage passes to a TAFKAL80ETC concert", 5, quality)
        val expiredPass = Item("Backstage passes to a TAFKAL80ETC concert", 0, quality)
        val bestPass = Item("Backstage passes to a TAFKAL80ETC concert", 11, topQuality)

        val items = Arrays.asList(newPass, oldPass, lastPass, expiredPass, bestPass)
        val gildedRose = GildedRose(items)
        gildedRose.updateQuality()

        assertUpdateOn(newPass, 10, quality + 1)
        assertUpdateOn(oldPass, 9, quality + 2)
        assertUpdateOn(lastPass, 4, quality + 3)
        assertUpdateOn(expiredPass, -1, 0)
        assertUpdateOn(bestPass, 10, topQuality)
    }

    private fun assertUpdateOn(newLegendaryItem: Item, sellIn: Int, quality: Int) {
        assertEquals(sellIn, newLegendaryItem.sellIn)
        assertEquals(quality, newLegendaryItem.quality)
    }
}
import facade.World
import misc.Tracker

class WorldTest {

    private fun getMockWorld(): World {
        return World(3,1, listOf(), Tracker())
    }
}
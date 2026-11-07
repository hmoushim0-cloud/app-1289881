package sa.factory.rawasiproptechreal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RawasiProptechEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun real_estateDao(): RawasiProptechDao
}

package elnaggar.youmityounes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.provider.SyncStateContract
import elnaggar.youmityounes.Constants
import elnaggar.youmityounes.Product

@Database(entities = [Product::class],version =  Constants.DATABASE_VERSION)
abstract class AppDatabase:RoomDatabase(){
    abstract fun productDao():ProdauctDatabaseAccessObject

}
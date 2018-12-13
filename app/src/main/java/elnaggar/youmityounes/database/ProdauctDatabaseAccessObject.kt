package elnaggar.youmityounes.database

import android.arch.persistence.room.*
import elnaggar.youmityounes.Product

@Dao
interface ProdauctDatabaseAccessObject {
    @get:Query("Select * from product")
    val all: List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product): Long

    @Delete
    fun delete(product: Product)
    @Update
    fun update(product: Product)

}
package elnaggar.youmityounes

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product")
data class Product(@PrimaryKey(autoGenerate = true)
                   var id: Int
                   , var name: String
                   , val price: Double
                   , val quantity: Int)

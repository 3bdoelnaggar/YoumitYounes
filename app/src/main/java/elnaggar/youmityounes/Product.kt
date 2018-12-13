package elnaggar.youmityounes

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product")
data class Product(@PrimaryKey(autoGenerate = true)
                   var id: Int?=null
                   , var name: String?=null
                   , var price: Double?=0.toDouble()
                   , var quantity: Int?=0)

package elnaggar.youmityounes

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import elnaggar.youmityounes.database.AppDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_add_product.view.*
import kotlinx.android.synthetic.main.dialog_sold_product.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            createAddProductDialog()
        }

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = ProductAdapter(getProducts(), ::onUpClicked, ::onDownClicked)
        recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun createAddProductDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null)
        val name: EditText = dialogView.et_name
        val price: EditText = dialogView.et_price
        val quantity: EditText = dialogView.et_quantity
        val add: Button = dialogView.btn_add
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()
        add.setOnClickListener {
            if (name.text.toString().equals("") || price.text.toString().equals("") || quantity.text.toString().equals("")) {
                Toast.makeText(this, "من فضلك املئ كل البياات", Toast.LENGTH_SHORT).show()
            } else {
                val product = Product()
                product.name = name.text.toString()
                product.price = name.text.toString().toDoubleOrNull()
                product.quantity = quantity.text.toString().toIntOrNull()

                addToDataBase(product)
                dialog.dismiss()
            }
        }
        dialog.show()


    }

    private fun addToDataBase(product: Product) {
        val database =
            Room.databaseBuilder(this, AppDatabase::class.java, "products.db").fallbackToDestructiveMigration().build()
        database.productDao().insert(product)


    }

    private fun getProducts(): ArrayList<Product>? {
        val database =
            Room.databaseBuilder(this, AppDatabase::class.java, "products.db").fallbackToDestructiveMigration().build()
        return database.productDao().all as ArrayList<Product>

    }

    private fun onDownClicked(product: Product) {
        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_sold_product, null)
        builder.setView(view)
        val dialog = builder.create()
        val price: EditText = view.et_sellPrice
        val done: Button = view.btn_done
        val name: TextView = view.tv_product
        name.text = product.name
        done.setOnClickListener {
            if (price.text.toString() != "") {
                addToSoldProducts(product)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "ما كتبتش السعر", Toast.LENGTH_SHORT).show()

            }
        }
        dialog.show()


    }

    private fun addToSoldProducts(product: Product) {
        val database =
            Room.databaseBuilder(this, AppDatabase::class.java, "products.db").fallbackToDestructiveMigration().build()
        product.quantity = product.quantity?.minus(1)
        database.productDao().update(product)


    }

    private fun onUpClicked(product: Product) {

        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_products, null)
        builder.setView(view)
        val dialog = builder.create()
        val quantity: EditText = view.et_quantity
        val done: Button = view.btn_done
        val name: TextView = view.tv_product
        name.text = product.name
        done.setOnClickListener {
            if (quantity.text.toString() != "") {
                addProducts(product)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "ما كتبتش العدد", Toast.LENGTH_SHORT).show()

            }
        }
        dialog.show()

    }

    private fun addProducts(product: Product) {
        val database = getDataBase()
        database.productDao().update(product)
    }

    private fun getDataBase(): AppDatabase {
        return Room.databaseBuilder(this, AppDatabase::class.java, "products.db").fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_youmia -> {
                startActivity(Intent(this@MainActivity, YoumiaActivity::class.java))

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

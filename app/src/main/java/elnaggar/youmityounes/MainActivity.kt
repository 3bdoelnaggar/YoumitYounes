package elnaggar.youmityounes

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
                addToDataBase(
                    Product(
                        name.text.toString(),
                        price.text.toString().toDouble(),
                        quantity.text.toString().toInt()
                    )
                )
                dialog.dismiss()
            }
        }
        dialog.show()


    }

    private fun addToDataBase(product: Product) {

    }

    private fun getProducts(): ArrayList<Product>? {
        val array = ArrayList<Product>()

        return array

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
                addToSoldProducts()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "ما كتبتش السعر", Toast.LENGTH_SHORT).show()

            }
        }
        dialog.show()


    }

    private fun addToSoldProducts() {



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
                addToSoldProducts()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "ما كتبتش العدد", Toast.LENGTH_SHORT).show()

            }
        }
        dialog.show()

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

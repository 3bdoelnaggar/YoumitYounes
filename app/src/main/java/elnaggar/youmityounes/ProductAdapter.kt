package elnaggar.youmityounes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(
    val items: ArrayList<Product>?,
    private val onUpClicked: (Product) -> Unit,
    private val onDownClicked: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.product_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        items?.get(p1)?.let {
            p0.name.text = it.name
            p0.price.text = it.price.toString()
            p0.quantity.text = it.quantity.toString()
            p0.up.setOnClickListener { _ ->
                onUpClicked(it)
            }
            p0.down.setOnClickListener { _->
                onDownClicked(it)
            }
        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.tv_name
        val quantity: TextView = view.tv_quantity
        val price: TextView = view.tv_price
        val up: ImageView = view.iv_up
        val down: ImageView = view.iv_down


    }

}
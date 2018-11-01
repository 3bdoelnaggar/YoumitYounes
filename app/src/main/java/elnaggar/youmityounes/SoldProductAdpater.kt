package elnaggar.youmityounes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.product_list_item.view.*

class SoldProductAdpater(val items: ArrayList<Product>?) :
    RecyclerView.Adapter<SoldProductAdpater.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.sold_product_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = items?.get(p1)
        p0.name.text = item?.name
        p0.price.text = item?.price.toString()
        p0.quantity.text = item?.quantity.toString()

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.tv_name
        val quantity: TextView = view.tv_quantity
        val price: TextView = view.tv_price


    }

}
package elnaggar.youmityounes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

class YoumiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youmia)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = SoldProductAdpater(getProducts())
        recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


    }

    private fun getProducts(): ArrayList<Product>? {
        val array = ArrayList<Product>()
        array.add(Product("بيتسايه", 70.0, 1))
        array.add(Product("سماعه بلوتوث ماركة حسونه", 70.0, 1))
        array.add(Product("جراب موبيل S17", 70.0, 1))
        array.add(Product("المجموع", 210.0, 3))
        return array

    }

}

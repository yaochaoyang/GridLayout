package com.chaoyang.gridlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaoyang.gridlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        initData()
    }

    val arrayList = ArrayList<Int>()

    private fun initData() {

        arrayList.add(R.mipmap.imge_01)
        arrayList.add(R.mipmap.imge_02)
        arrayList.add(R.mipmap.imge_03)
        arrayList.add(R.mipmap.imge_04)
        arrayList.add(R.mipmap.imge_05)
        arrayList.add(R.mipmap.imge_06)
        arrayList.add(R.mipmap.imge_01)
        arrayList.add(R.mipmap.imge_02)
        arrayList.add(R.mipmap.imge_03)

//        val gridLayout = findViewById<GridLayout>(R.id.main_gridlayout)
//        gridLayout.setGridLayout(arrayList)

//        val layout = findViewById<Layout>(R.id.main_layout)
//        layout.setGridLayout(arrayList)


//        binding.mainGridlayout.setGridLayout(arrayList)
        binding.recyclerView.adapter = MyAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }


    inner class MyAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflate =
                LayoutInflater.from(context).inflate(R.layout.adapter_gridview, parent, false)
            return MyViewHolder(inflate)
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val myViewHolder = holder as MyViewHolder
            val images = ArrayList<Int>()
            for (i in 0 .. position) {
                val image = arrayList[i]
                images.add(image)
            }
            myViewHolder.gridLayout.setGridLayout(images)
            myViewHolder.gridLayoutForJava.setGridLayout(images)
        }


        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gridLayout = itemView.findViewById<GridLayout>(R.id.item_gridlayout)
            val gridLayoutForJava = itemView.findViewById<GridLayoutForJava>(R.id.item_gridlayoutforjava)
        }
    }
}
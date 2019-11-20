package com.alif.daftarliga

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val list = findViewById<RecyclerView>(R.id.club_list)
        initData()

        league_list.layoutManager = GridLayoutManager(this, 2)
        league_list.adapter = DaftarLigaAdapter(this, items) {
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_image)
        items.clear()

        for (i in name.indices) {
            items.add(Item(name[i], image.getResourceId(i, 0)))
        }

        image.recycle()
    }

//    class MainActivityUI : AnkoComponent<MainActivity> {
//        override fun createView(ui: AnkoContext<MainActivity>): View {
//
//        }
//    }
}

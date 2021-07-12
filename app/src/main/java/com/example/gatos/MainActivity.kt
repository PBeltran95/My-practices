package com.example.gatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var catAdapter: CatAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var cBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(cBinding.root)

        catAdapter = CatAdapter(listOf(
            Cats("Belu", "Carey", "5 meses", "https://mihermosogato.com/wp-content/uploads/2020/07/Gato-Carey.jpg"),
            Cats("Belu", "Carey", "5 meses", "https://mihermosogato.com/wp-content/uploads/2020/07/Gato-Carey.jpg"),
            Cats("Belu", "Carey", "5 meses", "https://mihermosogato.com/wp-content/uploads/2020/07/Gato-Carey.jpg"),
            Cats("Leito", "Colorado", "2 años", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRP6Q5bFOCAxgwpBAHLAcaLKKqZL5X1rSEMzQ&usqp=CAU"),
            Cats("Vaca", "Vovino", "Desconocida", "https://static.vix.com/es/sites/default/files/styles/1x1/public/g/gato-disfrazado-de-vaca.jpg"),
            Cats("Eucli", "Britanico", "5 años", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsCHOEXm8vDDvcZbqd_thP3_XXQREbOfZMhg&usqp=CAU")
        ))

        linearLayoutManager = LinearLayoutManager(this)
        cBinding.rvCats.apply {
            layoutManager = linearLayoutManager
            adapter = catAdapter
        }
    }

}
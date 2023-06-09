package com.example.desafio3.views

import android.content.Context
import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.desafio3.R
import com.example.desafio3.db.HelperDB
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    private var MarcaFragment: MarcaFragment = MarcaFragment()
    private var ColoresFragment: ColoresFragment = ColoresFragment()
    private var TipoAutoFragment: TipoAutoFragment = TipoAutoFragment()
    private var AutosFragment: AutosFragment = AutosFragment()
    private var UsuariosFragment: UsuariosFragment = UsuariosFragment()

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var toolbar: Toolbar? = null
    private lateinit var userLogin: TextView

    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCredentials()

        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase

        toolbar = findViewById(R.id.toolbar_main)
        this.setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawerContainer)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var header: View = navigationView.getHeaderView(0) // Obtener la vista del header del drawer
        userLogin = header.findViewById(R.id.tvName)

        userLogin.text = email


        loadFragment(MarcaFragment, "Marcas")

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navItemMarcas -> {
                loadFragment(MarcaFragment, "Marcas")
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.navItemColores -> {
                loadFragment(ColoresFragment, "Colores")
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.navItemTipoAuto -> {
                loadFragment(TipoAutoFragment, "Tipo de Automoviles")
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.navItemAutomoviles -> {
                loadFragment(AutosFragment, "Automoviles")
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.navItemUsuarios -> {
                loadFragment(UsuariosFragment, "Usuarios")
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.navItemLogout -> {

            }
        }

        return false
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment, title: String)
    {
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment)
        transaction.commit()
        toolbar?.title = title
        this.setSupportActionBar(toolbar)
    }


    private fun getCredentials(){
        val credentials = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val email = credentials.getString("user", "")
        this.email = email.toString()
    }
}
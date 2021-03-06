package com.example.mykotlinstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var textViewEmail:TextView? = null
    private var drawerLayout:DrawerLayout? = null
    private var toolbarView:Toolbar? = null
    private var navigationView:NavigationView? = null
    private var recyclerView:RecyclerView? = null
    private var buttonAddStory:FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")

        connectViews()
        textViewEmail?.text = email
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDrawer()
        try {
            updateEmailInHeader(email!!)
        }catch (e:NullPointerException){

        }
        drawerClicks()
        openAddStoryActivity()
        displayStory()


    }
    private fun updateEmailInHeader(email:String){
        val headerView =navigationView?.getHeaderView(0)
        val textViewEmail = headerView?.findViewById<TextView>(R.id.tvEmail)
        textViewEmail?.text = email
    }
    private fun setupDrawer(){
        val toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)

        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                drawerLayout?.openDrawer(GravityCompat.START)
                true
            }
            else -> true
        }
    }
    private fun connectViews(){
        textViewEmail = findViewById(R.id.tvEmail)
        drawerLayout = findViewById(R.id.drawer)
        toolbarView = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navView)
        recyclerView = findViewById(R.id.storiesRecyclerView)
        buttonAddStory = findViewById(R.id.btAddStory)
    }
    private fun drawerClicks(){
        navigationView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    drawerLayout?.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.logout ->{
                    finish()
                    val i = Intent(this,LoginActivity::class.java)
                    startActivity(i)
                    true
                }
                else -> true
            }
        }
    }
    private fun openAddStoryActivity(){
        buttonAddStory?.setOnClickListener {
            val i = Intent(this,AddStoryActivity::class.java)
            startActivity(i)
        }
    }
    private fun displayStory(){
       val storiesArray = ArrayList<Story>()
        storiesArray.add(
            Story(getString(R.string.story1_title)
            ,getString(R.string.story1_subtitle),getString(R.string.story1_desc)))
        storiesArray.add(Story("This is the second story"
            ,"this is second subtitle","Welcome to my kotlin coding Story"))
        storiesArray.add(Story("This is the third story"
            ,"this is third subtitle","Welcome to my kotlin coding Story"))



        val customAdapter = CustomAdapter(storiesArray,this)
        recyclerView?.adapter = customAdapter

        if(intent.getStringExtra("title") != null){
            val title = intent.getStringExtra("title")
            val subTitle = intent.getStringExtra("subtitle")
            val desc = intent.getStringExtra("desc")

            val newStory = Story(title!!,subTitle!!,desc!!)

            storiesArray.add(newStory)
            customAdapter.notifyDataSetChanged()

        }


    }

}
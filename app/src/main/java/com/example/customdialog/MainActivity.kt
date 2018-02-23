package com.example.customdialog

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.TypedValue
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var alertDialog: AlertDialog.Builder? = null
    private var fragmentDialog: DialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initAlertDialog()
        initFragmentDialog()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout?
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView?
        navigationView!!.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout?
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_custom_dialog) {
            openCustomDialog()
        } else if (id == R.id.nav_alert_dialog) {
            alertDialog!!.show()
        } else if (id == R.id.nav_fragment_dialog) {
            fragmentDialog!!.show(supportFragmentManager, "tag")
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout?
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openCustomDialog() {
        val customDialog = CustomDialog(this, "Do you want to play again?", "Yes", "No")
        customDialog.setImage(R.drawable.ic_menu_camera)
        val nFont = resources.getDimension(R.dimen.font_size_16sp).toInt()
        customDialog.show()

        val btn = customDialog.findViewById(R.id.btn_no) as Button
        val btn1 = customDialog.findViewById(R.id.btn_yes) as Button
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, nFont.toFloat())
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, nFont.toFloat())

        customDialog.setListener(object: CustomDialogCompleteListener {
            override fun onComplete(nSucceeded: Int) {
                if (CustomDialog.BTN_YES == nSucceeded) {
                    //yes
                    Toast.makeText(baseContext, "YES", Toast.LENGTH_SHORT).show()
                } else {
                    //skip or no
                    Toast.makeText(baseContext, "NO", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }


    private fun initAlertDialog() {
        alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog!!.setTitle("Confirm Delete...")
        alertDialog!!.setMessage("Are you sure you want delete your account?")
        alertDialog!!.setIcon(R.drawable.ic_error_black)
        alertDialog!!.setPositiveButton("YES"
        ) { dialog, which ->
            // Write your code here to execute after dialog
            Toast.makeText(applicationContext, "YES, delele it", Toast.LENGTH_SHORT).show()
        }

        alertDialog!!.setNegativeButton("NO"
        ) { dialog, which ->
            // Write your code here to execute after dialog
            Toast.makeText(applicationContext, "NO, don't delete it", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }
    }

    private fun initFragmentDialog() {
        fragmentDialog = MyFragmentDialog()
        val args = Bundle()
        args.putString("title", "Confirm delete")
        args.putString("message", "Are you sure you want to delete your account")
        fragmentDialog!!.arguments = args
    }


}

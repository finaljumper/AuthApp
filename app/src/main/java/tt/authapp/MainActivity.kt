package tt.authapp

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import butterknife.bindView

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ViewPagerAdapter

    val viewPager: ViewPager by bindView(R.id.viewPager)
    val tabLayout: TabLayout by bindView(R.id.tabLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "login")
        adapter.addFragment(RegisterFragment(), "register")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}

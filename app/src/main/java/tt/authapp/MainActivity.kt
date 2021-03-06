package tt.authapp

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import butterknife.bindView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var realm: Realm by Delegates.notNull()

    lateinit var adapter: ViewPagerAdapter

    val viewPager: ViewPager by bindView(R.id.viewPager)
    val tabLayout: TabLayout by bindView(R.id.tabLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getInstance(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build())

        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(realm), "login")
        adapter.addFragment(RegisterFragment(realm), "register")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

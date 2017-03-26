package tt.authapp

import android.app.Application
import io.realm.Realm

/**
 * Created by Renai on 27.03.2017.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
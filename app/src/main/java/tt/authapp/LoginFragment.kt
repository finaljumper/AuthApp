package tt.authapp


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import butterknife.bindView
import io.realm.Realm
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment(var realm: Realm) : Fragment() {

    val usernameField: TextInputEditText by bindView(R.id.username_field)
    val passwordField: TextInputEditText by bindView(R.id.password_field)
    val loginBtn: Button by bindView(R.id.login_button)

    var username: String? = null
    var password: String? = null



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBtn.setOnClickListener {
            username = usernameField.text.toString()
            password = passwordField.text.toString()
            Log.d("USERNAME", username)
            Log.d("PASSWORD", password)
            val user = User(username!!, password!!)
            var found: User? = null
            realm.executeTransaction {
                found = realm
                        .where(User::class.java)
                        .equalTo("username", username)
                        .equalTo("password", password)
                        .findFirst()
            }
            if (found != null)
                Toast.makeText(context, "Login successful.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Incorrect username/password", Toast.LENGTH_SHORT).show()
        }
    }

}// Required empty public constructor

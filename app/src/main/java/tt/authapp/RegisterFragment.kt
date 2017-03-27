package tt.authapp


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
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
import io.realm.exceptions.RealmPrimaryKeyConstraintException


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment(var realm: Realm) : Fragment() {

    val usernameField: TextInputEditText by bindView(R.id.username_field)
    val passwordField: TextInputEditText by bindView(R.id.password_field)
    val registerBtn: Button by bindView(R.id.register_button)
    val usernameBlock: TextInputLayout by bindView(R.id.username_block)
    val passwordBlock: TextInputLayout by bindView(R.id.password_block)

    var username: String? = null
    var password: String? = null

    var usernameChecked = false
    var passwordChecked = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textWatcherUsername = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                usernameChecked = p0!!.length >= 4
            }

            override fun afterTextChanged(p0: Editable?) { }
        }
        usernameField.addTextChangedListener(textWatcherUsername)
        val textWatcherPassword = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                passwordChecked = p0!!.length >= 4
            }

            override fun afterTextChanged(p0: Editable?) { }
        }
        passwordField.addTextChangedListener(textWatcherPassword)

        registerBtn.setOnClickListener {
            if (!usernameChecked) {
                usernameBlock.error = "Wrong username"
            } else {
                usernameBlock.error = null
            }
            if (!passwordChecked) {
                passwordBlock.error = "Wrong password"
            } else {
                passwordBlock.error = null
            }
            if (usernameChecked and passwordChecked) {
                username = usernameField.text.toString()
                password = passwordField.text.toString()
                val user = User(username!!, password!!)
                try {
                    realm.executeTransaction({
                        realm.copyToRealm(user)
                        Toast.makeText(context, "Register complete.", Toast.LENGTH_SHORT).show()
                    })
                } catch (e: RealmPrimaryKeyConstraintException) {
                    Toast.makeText(context, "User already exists!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}// Required empty public constructor

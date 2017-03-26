package tt.authapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
        @PrimaryKey open var username: String = "",
        open var password: String = ""
) : RealmObject()
package com.dd.common.utils.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.dd.utils.Utils
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 *  用继承 :object SettingsRepository : IDataStoreOwner( "settings") {
 *       val counter by intPreference()
 *  }
 *
 *  已经继承父类的用委托
 *  object SettingsRepository : BaseRepository(), IDataStoreOwner by DataStoreOwner("settings") {
 *       val counter by intPreference()
 *  }
 **/
open class DataStoreOwner(name: String) : IDataStoreOwner {
    private val Context.dataStore by preferencesDataStore(name)
    override val dataStore get() = context.dataStore
}

interface IDataStoreOwner {
    val context: Context get() = application

    val dataStore: DataStore<Preferences>

    fun intPreference(default: Int? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Int>> =
        PreferenceProperty(::intPreferencesKey, default)

    fun doublePreference(default: Double? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Double>> =
        PreferenceProperty(::doublePreferencesKey, default)

    fun longPreference(default: Long? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Long>> =
        PreferenceProperty(::longPreferencesKey, default)

    fun floatPreference(default: Float? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Float>> =
        PreferenceProperty(::floatPreferencesKey, default)

    fun booleanPreference(default: Boolean? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Boolean>> =
        PreferenceProperty(::booleanPreferencesKey, default)

    fun stringPreference(default: String? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<String>> =
        PreferenceProperty(::stringPreferencesKey, default)

    fun stringSetPreference(default: Set<String>? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Set<String>>> =
        PreferenceProperty(::stringSetPreferencesKey, default)

    class PreferenceProperty<V>(
        private val key: (String) -> Preferences.Key<V>,
        private val default: V? = null,
    ) : ReadOnlyProperty<IDataStoreOwner, DataStorePreference<V>> {
        private var cache: DataStorePreference<V>? = null

        override fun getValue(
            thisRef: IDataStoreOwner,
            property: KProperty<*>
        ): DataStorePreference<V> =
            cache ?: DataStorePreference(
                thisRef.dataStore,
                key(property.name),
                default
            ).also { cache = it }
    }

    companion object {
        val application by lazy {
            Utils.getApp()
        }
    }
}

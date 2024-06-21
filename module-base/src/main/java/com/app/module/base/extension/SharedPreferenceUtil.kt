package com.app.module.base.extension

import android.os.Parcelable

import com.tencent.mmkv.MMKV

/**
 * @author:陈商
 * @date:08/01/2024 2:54 PM
 * @description:存储键值对的类
 */
object SharedPreferenceUtil {
    private var kv = MMKV.defaultMMKV()
    fun putString(key: String, value: String): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putInteger(key: String, value: Int): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putBoolean(key: String, value: Boolean): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putLong(key: String, value: Long): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putFloat(key: String, value: Float): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putDouble(key: String, value: Double): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putBytes(key: String, value: ByteArray): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putSet(key: String, value: Set<String>): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun putParcelable(key: String, value: Parcelable): SharedPreferenceUtil {
        kv.encode(key, value)
        return this
    }

    fun getString(key: String, value: String = ""): String? {
        return kv.decodeString(key, value)
    }

    fun getInteger(key: String, value: Int = -1): Int {
        return kv.decodeInt(key, value)
    }

    fun getBoolean(key: String, value: Boolean = false): Boolean {
        return kv.decodeBool(key, value)
    }

    fun getLong(key: String, value: Long = 0L): Long {
        return kv.decodeLong(key, value)
    }

    fun getFloat(key: String, value: Float = 0.1F): Float {
        return kv.decodeFloat(key, value)
    }

    fun getDouble(key: String, value: Double = 0.1): Double {
        return kv.decodeDouble(key, value)
    }

    fun getBytes(key: String, value: ByteArray = byteArrayOf('s'.code.toByte(), 'm'.code.toByte())): ByteArray? {
        return kv.decodeBytes(key, value)
    }

    fun getSet(key: String, value: Set<String> = HashSet<String>()): MutableSet<String>? {
        return kv.decodeStringSet(key, value)
    }


    /**
     * 删除指定键值对
     */
    fun deleteValueForKey(key: String) {
        kv.removeValueForKey(key)
    }

    /**
     * 删除多个键值对
     */
    fun deleteValuesForKeys(keyList: Array<out String>) {
        kv.removeValuesForKeys(keyList)
    }

    /**
     * 清空所有数据
     */
    fun deleteAllKeys() {
        kv.clearAll()
        kv.clearMemoryCache()
    }

    /**
     * 判断是否包含指定的键
     */
    fun containsKey(key: String): Boolean {
        return kv.containsKey(key)
    }

    /**
     * 获取存储的数据总数
     */
    fun getKeysCount(): Long {
        return kv.count()
    }

    /**
     * 获取所有存储的键
     */
    fun getAllKeys(): Array<out String>? {
        return kv.allKeys()
    }

}
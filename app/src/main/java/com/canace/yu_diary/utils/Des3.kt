package com.canace.yu_diary.utils

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec

/**
 * <pre>
 *     @desc: des3 encode and decode
 * </pre>
 * @author imtianx
 * @email imtianx@gmail.com
 * @date 2020/3/4 10:41 AM
 */
object Des3 {

    private const val DEFAULT_SECRET_KEY = "yu_diary#20200304@sfyahui#"
    private const val DEFAULT_IV = "01234567"
    private const val DEFAULT_TRANSFORMATION = "desede/CBC/PKCS5Padding"


    fun encode(plainText: String?): String {
        val spec = DESedeKeySpec(DEFAULT_SECRET_KEY.toByteArray())
        val keyFactory = SecretKeyFactory.getInstance("desede")
        val desKey = keyFactory.generateSecret(spec)
        val cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION)
        val ips = IvParameterSpec(DEFAULT_IV.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, desKey, ips)
        val encryptData = cipher.doFinal((plainText ?: "").toByteArray())
        return Base64.encode(encryptData)
    }

    fun decode(encryptText: String?): String {
        val spec = DESedeKeySpec(DEFAULT_SECRET_KEY.toByteArray())
        val keyFactory = SecretKeyFactory.getInstance("desede")
        val desKey = keyFactory.generateSecret(spec)
        val cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION)
        val ips = IvParameterSpec(DEFAULT_IV.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, desKey, ips)
        return cipher.doFinal(Base64.decode(encryptText ?: ""))
            .toString(Charsets.UTF_8)
    }
}
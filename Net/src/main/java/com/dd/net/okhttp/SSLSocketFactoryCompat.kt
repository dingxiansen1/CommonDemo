package com.dd.net.okhttp

import android.os.Build
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.security.GeneralSecurityException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class SSLSocketFactoryCompat(tm: X509TrustManager?) : SSLSocketFactory() {
    private var defaultFactory: SSLSocketFactory? = null
    private fun upgradeTLS(ssl: SSLSocket) {
        // Android 5.0+ (API level21) provides reasonable default settings
        // but it still allows SSLv3
        // https://developer.android.com/about/versions/android-5.0-changes.html#ssl
        if (protocols != null) {
            ssl.enabledProtocols = protocols
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && cipherSuites != null) {
            ssl.enabledCipherSuites = cipherSuites
        }
    }

    override fun getDefaultCipherSuites(): Array<String> {
        return cipherSuites!!
    }

    override fun getSupportedCipherSuites(): Array<String> {
        return cipherSuites!!
    }

    @Throws(IOException::class)
    override fun createSocket(s: Socket, host: String, port: Int, autoClose: Boolean): Socket {
        val ssl = defaultFactory!!.createSocket(s, host, port, autoClose)
        if (ssl is SSLSocket) upgradeTLS(ssl)
        return ssl
    }

    @Throws(IOException::class)
    override fun createSocket(host: String, port: Int): Socket {
        val ssl = defaultFactory!!.createSocket(host, port)
        if (ssl is SSLSocket) upgradeTLS(ssl)
        return ssl
    }

    @Throws(IOException::class)
    override fun createSocket(
        host: String,
        port: Int,
        localHost: InetAddress,
        localPort: Int
    ): Socket {
        val ssl = defaultFactory!!.createSocket(host, port, localHost, localPort)
        if (ssl is SSLSocket) upgradeTLS(ssl)
        return ssl
    }

    @Throws(IOException::class)
    override fun createSocket(host: InetAddress, port: Int): Socket {
        val ssl = defaultFactory!!.createSocket(host, port)
        if (ssl is SSLSocket) upgradeTLS(ssl)
        return ssl
    }

    @Throws(IOException::class)
    override fun createSocket(
        address: InetAddress,
        port: Int,
        localAddress: InetAddress,
        localPort: Int
    ): Socket {
        val ssl = defaultFactory!!.createSocket(address, port, localAddress, localPort)
        if (ssl is SSLSocket) upgradeTLS(ssl)
        return ssl
    }

    init {
        defaultFactory = try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tm?.let { arrayOf(it) }, null)
            sslContext.socketFactory
        } catch (e: GeneralSecurityException) {
            throw AssertionError() // The system has no TLS. Just give up.
        }
    }

    companion object {
        // Android 5.0+ (API level21) provides reasonable default settings
        // but it still allows SSLv3
        // https://developer.android.com/about/versions/android-5.0-changes.html#ssl
        var protocols: Array<String>? = null
        var cipherSuites: Array<String>? = null

        init {
            try {
                val socket = getDefault().createSocket() as SSLSocket
                /* set reasonable protocol versions */
                // - enable all supported protocols (enables TLSv1.1 and TLSv1.2 on Android <5.0)
                // - remove all SSL versions (especially SSLv3) because they're insecure now
                val protocols: MutableList<String> = LinkedList()
                for (protocol in socket.getSupportedProtocols()) if (!protocol.uppercase(
                        Locale.getDefault()
                    ).contains("SSL")
                ) protocols.add(protocol)
                Companion.protocols = protocols.toTypedArray()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        //定义一个信任所有证书的TrustManager
        @JvmField
        val trustAllCert: X509TrustManager = object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}
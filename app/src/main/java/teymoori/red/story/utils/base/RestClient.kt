package teymoori.red.story.utils.base

import android.annotation.SuppressLint

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.security.cert.CertificateException
import okhttp3.OkHttpClient
import teymoori.red.story.BuildConfig
import javax.net.ssl.*


open class RestClient {
    companion object {
        fun service(authenticateRequired: Boolean): RestAPI {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

//            if (authenticateRequired)
//                builder.addInterceptor { chain ->
//                    val request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + Gen.getData(Gen.TOKEN_KEY, "")).build()
//                    chain.proceed(request)
//                }

            val loggerInterceptorBody = HttpLoggingInterceptor()
            loggerInterceptorBody.level = HttpLoggingInterceptor.Level.BODY
            val loggerInterceptorHeader = HttpLoggingInterceptor()
            loggerInterceptorHeader.level = HttpLoggingInterceptor.Level.HEADERS

            var client = builder.readTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG)
                client = builder.addInterceptor(loggerInterceptorBody).addInterceptor(loggerInterceptorHeader).readTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS)


            val clientCreated = client.build()
            val retrofit = retrofit2.Retrofit.Builder().baseUrl(Gen.getServerAddress()).client(clientCreated).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()


            return retrofit.create(RestAPI::class.java)
        }
    }


}
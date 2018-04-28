package io.ktor.client.call

import java.lang.reflect.*
import kotlin.reflect.*

class TypeInfo(val type: KClass<*>, val reifiedType: Type)

@Deprecated("INTERNAL API")
open class TypeBase<T>

inline fun <reified T> typeInfo(): TypeInfo {
    @Suppress("DEPRECATION")
    val base = object : TypeBase<T>() {}
    val superType = base::class.java.genericSuperclass!!

    @Suppress("UNCHECKED_CAST")
    val reifiedType = (superType as ParameterizedType).actualTypeArguments.first()!!
    return TypeInfo(T::class, reifiedType)
}

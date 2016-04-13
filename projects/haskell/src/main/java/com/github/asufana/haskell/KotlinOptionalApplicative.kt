package com.github.asufana.haskell

class KotlinOptionalApplicative {

    companion object {

        //class (Functor F) => Applicative F where
        //  pure :: a -> F a
        fun <A> pure(a: A): A? {
            return a
        }

        //class (Functor F) => Applicative F where
        //  (<*>) :: F (a -> b) -> F a -> F b
        //1引数関数
        fun <A, B> amap(f: ((A) -> B)?): (A?) -> B? {
            if (f == null) {
                return { a -> null }
            }
            return { a ->
                if (a == null) null
                else f.let { it.invoke(a) }
            }
        }

        //2引数関数
        fun <A, B, C> amap2(f: ((A, B) -> C)?): (A?, B?) -> C? {
            if (f == null) {
                return { a, b -> null }
            }
            return { a, b ->
                if (a == null || b == null) null
                else f.let { it.invoke(a, b) }
            }
        }

        //2引数関数のカリー化
        fun <A, B, C> curry(f: ((A, B) -> C)?): (A?) -> ((B?) -> C?) {
            if (f == null) {
                return { a -> { b -> null } }
            }
            return { a ->
                { b ->
                    if (a == null || b == null) null
                    else f.let { it.invoke(a, b) }
                }
            }
        }

    }
}

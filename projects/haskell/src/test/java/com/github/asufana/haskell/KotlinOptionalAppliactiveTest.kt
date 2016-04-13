package com.github.asufana.haskell

import com.github.asufana.haskell.KotlinOptionalApplicative.Companion.amap
import com.github.asufana.haskell.KotlinOptionalApplicative.Companion.amap2
import com.github.asufana.haskell.KotlinOptionalApplicative.Companion.curry
import com.github.asufana.haskell.KotlinOptionalApplicative.Companion.pure
import org.junit.Assert.*
import org.junit.Test

class KotlinOptionalAppliactiveTest {

    @Test
    fun test() {

        //1/2処理
        val half: (Int) -> Int = { i -> i / 2 }
        //足し算処理
        val add: (Int, Int) -> Int = { i1, i2 -> i1 + i2 }
        //引き算処理
        val sub: (Int, Int) -> Int = { i1, i2 -> i1 - i2 }

        //パラメータ
        val n1: Int? = 3
        val n2: Int? = 2

        //1/2処理
        assertEquals(amap(pure(half)).invoke(n2), 1)
        assertEquals(amap(pure(half)).invoke(null), null)
        //足し算処理
        assertEquals(amap2(pure(add)).invoke(n1, n2), 5)
        assertEquals(amap2(pure(add)).invoke(n1, null), null)
        //引き算処理
        assertEquals(amap2(pure(sub)).invoke(n1, n2), 1)
        assertEquals(amap2(pure(sub)).invoke(n1, null), null)

        //amap2をカリー化する
        assertEquals(curry(pure(add)).invoke(n1).invoke(n2), 5)
        assertEquals(curry(pure(add)).invoke(n1).invoke(null), null)

    }

}


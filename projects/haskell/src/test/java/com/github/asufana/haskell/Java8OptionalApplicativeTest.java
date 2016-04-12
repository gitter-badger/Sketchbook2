package com.github.asufana.haskell;

import org.junit.Test;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.asufana.haskell.Java8OptionalApplicative.amap;
import static com.github.asufana.haskell.Java8OptionalApplicative.amap2;
import static com.github.asufana.haskell.Java8OptionalApplicative.curry;
import static com.github.asufana.haskell.Java8OptionalApplicative.pure;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Java8OptionalApplicativeTest {
    
    @Test
    public void test() throws Exception {
        
        //1/2処理
        final Function<Integer, Integer> half = i -> i / 2;
        //足し算処理
        final BiFunction<Integer, Integer, Integer> add = (i1, i2) -> i1 + i2;
        //引き算処理
        final BiFunction<Integer, Integer, Integer> sub = (i1, i2) -> i1 - i2;
        
        //パラメータ
        final Optional<Integer> n1 = Optional.ofNullable(3);
        final Optional<Integer> n2 = Optional.ofNullable(2);
        
        //1/2処理
        assertThat(amap(pure(half)).apply(n2), is(Optional.of(1)));
        assertThat(amap(pure(half)).apply(Optional.empty()),
                   is(Optional.empty()));
        //足し算処理
        assertThat(amap2(pure(add)).apply(n1, n2), is(Optional.of(5)));
        assertThat(amap2(pure(add)).apply(n1, Optional.empty()),
                   is(Optional.empty()));
        //引き算処理
        assertThat(amap2(pure(sub)).apply(n1, n2), is(Optional.of(1)));
        assertThat(amap2(pure(sub)).apply(n1, Optional.empty()),
                   is(Optional.empty()));
        
        //amap2をカリー化する
        assertThat(curry(pure(add)).apply(n1).apply(n2), is(Optional.of(5)));
        assertThat(curry(pure(add)).apply(n1).apply(Optional.empty()),
                   is(Optional.empty()));
        
        //haskellなら...
        //  (+) <$> (Just 3) <*> (Just 2) <*> (Just 1) ...
        
    }
    
}

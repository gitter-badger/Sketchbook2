package com.github.asufana.haskell;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

//Java8 Optional アプリカティブファンクター
public class Java8OptionalApplicative {
    
    //class (Functor F) => Applicative F where
    //  pure :: a -> F a
    public static <A> Optional<A> pure(final A a) {
        return Optional.ofNullable(a);
    }
    
    //class (Functor F) => Applicative F where
    //  (<*>) :: F (a -> b) -> F a -> F b
    //1引数関数
    public static <A, B> Function<Optional<A>, Optional<B>> amap(final Optional<Function<A, B>> f) {
        if (!f.isPresent()) {
            return a -> Optional.empty();
        }
        return a -> !a.isPresent()
                ? Optional.empty()
                : Optional.ofNullable(f.get().apply(a.get()));
    }
    
    //2引数関数
    public static <A, B, C> BiFunction<Optional<A>, Optional<B>, Optional<C>> amap2(final Optional<BiFunction<A, B, C>> f) {
        if (!f.isPresent()) {
            return (a, b) -> Optional.empty();
        }
        return (a, b) -> !a.isPresent() || !b.isPresent()
                ? Optional.empty()
                : Optional.ofNullable(f.get().apply(a.get(), b.get()));
    }
    
    //2引数関数のカリー化
    public static <A, B, C> Function<Optional<A>, Function<Optional<B>, Optional<C>>> curry(final Optional<BiFunction<A, B, C>> f) {
        if (!f.isPresent()) {
            return (a) -> (b) -> Optional.empty();
        }
        return (a) -> (b) -> !a.isPresent() || !b.isPresent()
                ? Optional.empty()
                : Optional.ofNullable(f.get().apply(a.get(), b.get()));
    }
}

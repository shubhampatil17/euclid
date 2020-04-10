package io.github.fosstree.exceptions.core;

public final class Nullifier {

    public interface FuncInterface<T> {
        T eval();
    }

    /**
     * Evaluates a lambda expression or else returns null if the chain is broken
     *
     * @param chain : Example objA.getObjB().getObjC[0].getObjD
     * @param <T>
     * @return
     */
    public static <T> T get(FuncInterface<T> chain) {
        try {
            return chain.eval();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Evaluates a lambda expression or else returns "orElse" param if the chain is broken
     *
     * @param chain  : Example objA.getObjB().getObjC[0].getObjD
     * @param orElse
     * @param <T>
     * @return
     */
    public static <T> T get(FuncInterface<T> chain, T orElse) {
        try {
            return chain.eval();
        } catch (NullPointerException e) {
            return orElse;
        }
    }
}

package com.sic777.utils.container.tuple;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.third = c;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}

package com.sic777.utils.container.tuple;

public class SevenTuple<A, B, C, D, E, F, G> extends SixTuple<A, B, C, D, E, F> {

	private static final long serialVersionUID = 1L;
	public final G seventh;

	public SevenTuple(A first, B second, C third, D fourth, E fifth, F sixth, G seventh) {
		super(first, second, third, fourth, fifth, sixth);
		this.seventh = seventh;
	}
}
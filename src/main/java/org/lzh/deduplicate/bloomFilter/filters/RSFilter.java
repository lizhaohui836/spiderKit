package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class RSFilter extends Filter {

	public RSFilter(long size){
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		int b = 378551;
		int a = 63689;
		int hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash * a + str.charAt(i);
			a = a * b;
		}
		return hash % size;
	}

}

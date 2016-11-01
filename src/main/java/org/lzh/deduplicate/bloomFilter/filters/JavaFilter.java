package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class JavaFilter extends Filter {

	public JavaFilter(long size) {
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = 31 * hash + str.charAt(i);
		}

		if (hash < 0) {
			hash *= -1;
		}

		return hash % size;
	}

}

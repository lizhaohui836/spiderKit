package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class HfFilter extends Filter {

	public HfFilter(long size) throws Exception {
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		int length = str.length();
		long hash = 0;

		for (int i = 0; i < length; i++)
			hash += str.charAt(i) * 3 * i;

		if (hash < 0)
			hash = -hash;

		return hash % size;
	}

}

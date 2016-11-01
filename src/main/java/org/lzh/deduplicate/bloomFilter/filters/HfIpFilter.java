package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class HfIpFilter extends Filter {
	public HfIpFilter(long size){
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		int length = str.length();
		long hash = 0;
		for (int i = 0; i < length; i++) {
			hash += str.charAt(i % 4) ^ str.charAt(i);
		}
		return hash % size;
	}

}

package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class ELFFilter extends Filter {


	protected ELFFilter(long size){
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		long hash = 0;
		long g = 0;
		
		int length = str.length() ;
		for (int i = 0; i < length; i++) {
			hash = (hash << 4) + str.charAt(i);
			g = hash & 0xF0000000;
			if (g > 0) {
				hash ^= g >> 24;
			}
			hash &= ~g;
		}

		return hash % size;
	}

}

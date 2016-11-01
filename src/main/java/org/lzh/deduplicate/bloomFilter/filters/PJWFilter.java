package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class PJWFilter extends Filter {

	public PJWFilter(long size) {
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		int BitsInUnsignedInt = 32;
		int ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
		int OneEighth = BitsInUnsignedInt / 8;
		int HighBits = 0xFFFFFFFF << (BitsInUnsignedInt - OneEighth);
		int hash = 0;
		int test = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash << OneEighth) + str.charAt(i);

			if ((test = hash & HighBits) != 0) {
				hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
			}
		}

		return hash%size;
	}

}

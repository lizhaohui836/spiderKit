package org.lzh.deduplicate.bloomFilter.filters;


import org.lzh.deduplicate.bloomFilter.iface.Filter;

public class TianlFilter extends Filter {

	public TianlFilter(long size) {
		super(size);
	}

	@Override
	public long myHashCode(String str) {
		// TODO Auto-generated method stub
		long hash = 0;

		int iLength = str.length();
		if (iLength == 0)
			return 0;

		if (iLength <= 256)
			hash = 16777216L * (iLength - 1);
		else
			hash = 4278190080L;

		int i;

		char ucChar;

		if (iLength <= 96) {
			for (i = 1; i <= iLength; i++) {
				ucChar = str.charAt(i - 1);
				if (ucChar <= 'Z' && ucChar >= 'A') {
					ucChar = (char) (ucChar + 32);
				}
				hash += (3 * i * ucChar * ucChar + 5 * i * ucChar + 7 * i + 11 * ucChar) % 16777216;
			}
		} else {
			for (i = 1; i <= 96; i++) {
				ucChar = str.charAt(i + iLength - 96 - 1);
				if (ucChar <= 'Z' && ucChar >= 'A')
					ucChar = (char) (ucChar + 32);
				hash += (3 * i * ucChar * ucChar + 5 * i * ucChar + 7 * i + 11 * ucChar) % 16777216;
			}
		}
		if (hash < 0) {
			hash *= -1;
		}
		return hash % size;
	}

}

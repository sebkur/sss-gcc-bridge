package de.mobanisto.sss;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestEncodeDecode
{

	@Test
	public void test()
	{
		int n = 3;
		int k = 2;

		byte[] data = new byte[SSS.MLEN];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) i;
		}

		System.out.println(Shares.toHexString(data));

		List<byte[]> shares = SSS.createShares(data, n, k);

		for (byte[] share : shares) {
			System.out.println(Shares.toHexString(share));
		}

		byte[] recovered = SSS.combineShares(shares);

		System.out.println(Shares.toHexString(recovered));

		Assert.assertEquals(Shares.toHexString(data),
				Shares.toHexString(recovered));
	}

}

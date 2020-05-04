package de.mobanisto.sss;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestEncodeDecodeSecrets
{

	@Test
	public void test()
	{
		int n = 3;
		int k = 2;

		byte[] data = Secrets.create("a secret");

		System.out.println(new String(data));

		List<byte[]> shares = SSS.createShares(data, n, k);

		for (byte[] share : shares) {
			System.out.println(Shares.toHexString(share));
		}

		byte[] recovered = SSS.combineShares(shares);

		System.out.println(new String(recovered));

		Assert.assertEquals(new String(data), new String(recovered));
	}

}

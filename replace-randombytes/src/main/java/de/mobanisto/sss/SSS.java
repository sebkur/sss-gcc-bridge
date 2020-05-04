package de.mobanisto.sss;

import java.util.ArrayList;
import java.util.List;

import org.renjin.gcc.runtime.BytePtr;

public class SSS
{

	public static final int KEYSHARE_LEN = 33;
	public static final int MLEN = 64;
	public static final int CLEN = MLEN + 16;
	public static final int SHARE_LEN = CLEN + KEYSHARE_LEN;

	public static List<byte[]> createShares(byte[] data, int n, int k)
	{
		byte[] flatShares = new byte[n * SHARE_LEN];
		BytePtr rshares = new BytePtr(flatShares);
		BytePtr rdata = new BytePtr(data);
		SssGcc.sss_create_shares(rshares, rdata, (byte) n, (byte) k);

		List<byte[]> shares = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			byte[] share = Shares.getShare(flatShares, i);
			shares.add(share);
		}
		return shares;
	}

	public static byte[] combineShares(List<byte[]> shares)
	{
		byte[] recovered = new byte[MLEN];
		BytePtr rrecovered = new BytePtr(recovered);

		byte[] availableShares = Shares.concat(shares);
		BytePtr rAvailableShares = new BytePtr(availableShares);

		SssGcc.sss_combine_shares(rrecovered, rAvailableShares,
				(byte) shares.size());

		return recovered;
	}

	public static List<byte[]> createKeyshares(byte[] data, int n, int k)
	{
		byte[] flatShares = new byte[n * KEYSHARE_LEN];
		BytePtr rshares = new BytePtr(flatShares);
		BytePtr rdata = new BytePtr(data);
		SssGcc.sss_create_keyshares(rshares, rdata, (byte) n, (byte) k);

		List<byte[]> shares = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			byte[] share = Shares.getKeyshare(flatShares, i);
			shares.add(share);
		}
		return shares;
	}

	public static byte[] combineKeyshares(List<byte[]> shares)
	{
		byte[] recovered = new byte[KEYSHARE_LEN - 1];
		BytePtr rrecovered = new BytePtr(recovered);

		byte[] availableShares = Shares.concat(shares);
		BytePtr rAvailableShares = new BytePtr(availableShares);

		SssGcc.sss_combine_keyshares(rrecovered, rAvailableShares,
				(byte) shares.size());
		return recovered;
	}

}

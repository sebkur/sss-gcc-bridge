package de.mobanisto.sss;

import org.junit.Test;
import org.renjin.gcc.runtime.BytePtr;
import org.renjin.gcc.runtime.UnsatisfiedLinkException;

public class RandomBytesTest {

    @Test(expected = UnsatisfiedLinkException.class)
    public void sssTest() {
        byte[] buf = new byte[100];
        BytePtr rbuf = new BytePtr(buf);
        int num = de.mobanisto.sss.SssGcc.randombytes(rbuf, buf.length);
        // Once randombytes() works, this will be expected to work
        // assertThat(num, 100);
    }

}

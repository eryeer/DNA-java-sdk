package com.github.DNAProject.common;

import com.github.DNAProject.account.Account;
import com.github.DNAProject.crypto.SignatureScheme;
import com.github.DNAProject.sdk.exception.SDKException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {
    Account account;
    @Before
    public void setUp() throws Exception {
        account = new Account(SignatureScheme.SHA256WITHECDSA);
    }

    @Test
    public void compareTo() throws Exception {
        Account account2 = new Account(SignatureScheme.SHA256WITHECDSA);
        int res = account2.getAddressU160().compareTo(account.getAddressU160());
        assertNotNull(res);
    }

    @Test
    public void parse() {
//        Address address = Address.parse(account.getAddressU160().toHexString());
//        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromPubKey() {
        Address address = Address.addressFromPubKey(account.serializePublicKey());
        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromPubKey1() {
        Address address = Address.addressFromPubKey(Helper.toHexString(account.serializePublicKey()));
        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromMultiPubKeys() throws Exception {
        Account account2 = new Account(SignatureScheme.SHA256WITHECDSA);
        Address res = Address.addressFromMultiPubKeys(2,account.serializePublicKey(),account2.serializePublicKey());
        assertNotNull(res);
    }

    @Test
    public void toBase58() throws SDKException {
        String res = account.getAddressU160().toBase58();
        Address addr = Address.decodeBase58(res);
        assertEquals(addr,account.getAddressU160());
    }

    @Test
    public void toScriptHash() {
        Address addr = Address.toScriptHash(Helper.hexToBytes("12a67b"));
        assertNotNull(addr);
    }

}
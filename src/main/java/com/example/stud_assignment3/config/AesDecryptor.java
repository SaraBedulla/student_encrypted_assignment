package com.example.stud_assignment3.config;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.spec.IvParameterSpec;

public class AesDecryptor {

    public static String decrypt(String base64EncryptedData, String base64Key, String base64IV) throws Exception {
        byte[] encryptedData = Base64.decode(base64EncryptedData);
        byte[] key = Base64.decode(base64Key);
        byte[] iv = Base64.decode(base64IV);

        BufferedBlockCipher cipher = new BufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        CipherParameters params = new KeyParameter(key);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(false, new ParametersWithIV(params, ivSpec.getIV()));

        byte[] decryptedBytes = new byte[cipher.getOutputSize(encryptedData.length)];
        int processedBytes = cipher.processBytes(encryptedData, 0, encryptedData.length, decryptedBytes, 0);
        cipher.doFinal(decryptedBytes, processedBytes);

        return new String(decryptedBytes);
    }
}

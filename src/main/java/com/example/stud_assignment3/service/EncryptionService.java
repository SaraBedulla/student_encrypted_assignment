package com.example.stud_assignment3.service;


import com.example.stud_assignment3.entity.Student;
import com.example.stud_assignment3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class EncryptionService {

    @Value("${encryption.key}")
    private String encryptionKey;

    private final StudentRepository studentRepository;

    public EncryptionService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public String decryptHex(String hexEncodedData) {
        try {
            byte[] encryptedBytes = hexStringToByteArray(hexEncodedData);

            byte[] decryptedBytes = decryptData(encryptedBytes, encryptionKey);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    private byte[] decryptData(byte[] encryptedData, String encryptionKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        return cipher.doFinal(encryptedData);
    }


    private byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}

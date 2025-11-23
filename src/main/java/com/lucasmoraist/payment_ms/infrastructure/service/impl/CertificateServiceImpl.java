package com.lucasmoraist.payment_ms.infrastructure.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.payment_ms.domain.exceptions.CertificateException;
import com.lucasmoraist.payment_ms.infrastructure.service.CertificateService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final ObjectMapper objectMapper;

    public CertificateServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void validate(String payloadHash, Object payload) {
        if (payloadHash == null || payloadHash.isEmpty()) {
            throw new CertificateException("PayloadHash is null or empty");
        }

        try {
            String payloadAsString = objectMapper.writeValueAsString(payload);
            byte[] data = payloadAsString.getBytes(StandardCharsets.UTF_8);

            PublicKey publicKey = loadPublicKey();

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(data);

            byte[] signatureBytes = Base64.getDecoder().decode(payloadHash);
            boolean valid = signature.verify(signatureBytes);

            if (!valid) {
                throw new CertificateException("Signature validation failed");
            }
        } catch (Exception e) {
            throw new CertificateException("Error validating payload", e);
        }
    }

    public static RSAPublicKey loadPublicKey() {
        try {
            String pem = Files.readString(Path.of("src/main/resources/certs/certificate-public.key"));
            String pubPem = pem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] der = Base64.getDecoder().decode(pubPem);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(der);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CertificateException("Error loading public key: " + e.getMessage(), e);
        }
    }

}

package com.mobile.mobilehardware.attestation;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

/**
 * @author guxiaonian
 */
class KeyAttestationUtil {

    private static Certificate[] exportKeyAttestation(String alias) {
        try {
            KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
            ks.load(null);
            return ks.getCertificateChain(alias);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static X509Certificate convertToX509Cert(String certificateString) throws CertificateException {
        X509Certificate certificate = null;
        CertificateFactory cf;
        try {
            if (certificateString != null && !certificateString.trim().isEmpty()) {
                certificateString = certificateString
                        .replace("-----BEGIN CERTIFICATE-----\n", "")
                        .replace("-----END CERTIFICATE-----", "");
                byte[] certificateData = Base64.decode(certificateString, Base64.DEFAULT | Base64.NO_PADDING | Base64.NO_WRAP);
                cf = CertificateFactory.getInstance("X509");
                certificate = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certificateData));
            }
        } catch (CertificateException e) {
            throw new CertificateException(e);
        }
        return certificate;
    }

    static boolean generateKeyPair(String keyAlias) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyAlias, KeyProperties.PURPOSE_SIGN)
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1"))
                    .setUserAuthenticationRequired(false);
            builder.setAttestationChallenge(genChallenge());
            generator.initialize(builder.build());
            generator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static byte[] genChallenge() {
        SecureRandom random = new SecureRandom();
        byte[] challenge = new byte[32];
        random.nextBytes(challenge);
        return challenge;
    }

    static X509Certificate[] keyAttestationVerify(String keyAlias) {
        try {
            Certificate[] certificates = KeyAttestationUtil.exportKeyAttestation(keyAlias);
            if (certificates == null) {
                return null;
            }
            X509Certificate[] x509Certificates = new X509Certificate[certificates.length];
            int index = 0;
            for (Certificate cert : certificates) {
                String strPEM = Base64.encodeToString(cert.getEncoded(), Base64.DEFAULT | Base64.NO_WRAP | Base64.NO_PADDING);
                x509Certificates[index] = KeyAttestationUtil.convertToX509Cert(strPEM);
                index++;
            }
            return x509Certificates;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean verifyCertificateChain(X509Certificate[] certs) {
        try {
            for (int i = 1; i < certs.length; ++i) {
                certs[i].checkValidity();
                PublicKey pubKey = certs[i].getPublicKey();
                certs[i - 1].verify(pubKey);
                if (i == certs.length - 1) {
                    certs[i].verify(pubKey);
                }
            }
            X509Certificate secureRoot = (X509Certificate) CertificateFactory
                    .getInstance("X.509")
                    .generateCertificate(new ByteArrayInputStream(Constants.GOOGLE_ROOT_CERTIFICATE.getBytes()));
            return Arrays.equals(secureRoot.getTBSCertificate(), certs[certs.length - 1].getTBSCertificate());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

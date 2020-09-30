package com.mobile.mobilehardware.attestation;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;

import java.io.IOException;
import java.security.cert.X509Certificate;

import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_CHALLENGE_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_SECURITY_LEVEL_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_VERSION_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.KEYMASTER_SECURITY_LEVEL_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.KEYMASTER_VERSION_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.KEY_DESCRIPTION_OID;
import static com.mobile.mobilehardware.attestation.Constants.KM_SECURITY_LEVEL_SOFTWARE;
import static com.mobile.mobilehardware.attestation.Constants.KM_SECURITY_LEVEL_STRONG_BOX;
import static com.mobile.mobilehardware.attestation.Constants.KM_SECURITY_LEVEL_TRUSTED_ENVIRONMENT;
import static com.mobile.mobilehardware.attestation.Constants.SW_ENFORCED_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.TEE_ENFORCED_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.UNIQUE_ID_INDEX;

/**
 * @author guxiaonian
 */
class ParsedAttestationRecord {

    final int attestationVersion;
    final SecurityLevel attestationSecurityLevel;
    final int keymasterVersion;
    final SecurityLevel keymasterSecurityLevel;
    final byte[] attestationChallenge;
    final byte[] uniqueId;
    final AuthorizationList softwareEnforced;
    final AuthorizationList teeEnforced;

    private ParsedAttestationRecord(ASN1Sequence extensionData) {
        this.attestationVersion =
                ASN1Parsing.getIntegerFromAsn1(extensionData.getObjectAt(ATTESTATION_VERSION_INDEX));
        this.attestationSecurityLevel =
                securityLevelToEnum(
                        ASN1Parsing.getIntegerFromAsn1(
                                extensionData.getObjectAt(ATTESTATION_SECURITY_LEVEL_INDEX)));
        this.keymasterVersion =
                ASN1Parsing.getIntegerFromAsn1(extensionData.getObjectAt(KEYMASTER_VERSION_INDEX));
        this.keymasterSecurityLevel =
                securityLevelToEnum(
                        ASN1Parsing.getIntegerFromAsn1(
                                extensionData.getObjectAt(KEYMASTER_SECURITY_LEVEL_INDEX)));
        this.attestationChallenge =
                ((ASN1OctetString) extensionData.getObjectAt(ATTESTATION_CHALLENGE_INDEX)).getOctets();
        this.uniqueId = ((ASN1OctetString) extensionData.getObjectAt(UNIQUE_ID_INDEX)).getOctets();
        this.softwareEnforced =
                AuthorizationList.createAuthorizationList(
                        ((ASN1Sequence) extensionData.getObjectAt(SW_ENFORCED_INDEX)).toArray(),
                        attestationVersion);
        this.teeEnforced =
                AuthorizationList.createAuthorizationList(
                        ((ASN1Sequence) extensionData.getObjectAt(TEE_ENFORCED_INDEX)).toArray(),
                        attestationVersion);
    }

    static ParsedAttestationRecord createParsedAttestationRecord(X509Certificate cert)
            throws IOException {
        ASN1Sequence extensionData = extractAttestationSequence(cert);
        return new ParsedAttestationRecord(extensionData);
    }

    private static SecurityLevel securityLevelToEnum(int securityLevel) {
        switch (securityLevel) {
            case KM_SECURITY_LEVEL_SOFTWARE:
                return SecurityLevel.SOFTWARE;
            case KM_SECURITY_LEVEL_TRUSTED_ENVIRONMENT:
                return SecurityLevel.TRUSTED_ENVIRONMENT;
            case KM_SECURITY_LEVEL_STRONG_BOX:
                return SecurityLevel.STRONG_BOX;
            default:
                throw new IllegalArgumentException("Invalid security level.");
        }
    }

    private static ASN1Sequence extractAttestationSequence(X509Certificate attestationCert)
            throws IOException {
        byte[] attestationExtensionBytes = attestationCert.getExtensionValue(KEY_DESCRIPTION_OID);
        if (attestationExtensionBytes == null || attestationExtensionBytes.length == 0) {
            throw new IllegalArgumentException("Couldn't find the keystore attestation extension data.");
        }

        ASN1Sequence decodedSequence;
        try (ASN1InputStream asn1InputStream = new ASN1InputStream(attestationExtensionBytes)) {
            byte[] derSequenceBytes = ((ASN1OctetString) asn1InputStream.readObject()).getOctets();
            try (ASN1InputStream seqInputStream = new ASN1InputStream(derSequenceBytes)) {
                decodedSequence = (ASN1Sequence) seqInputStream.readObject();
            }
        }
        return decodedSequence;
    }

    public enum SecurityLevel {
        SOFTWARE,
        TRUSTED_ENVIRONMENT,
        STRONG_BOX
    }
}

package com.mobile.mobilehardware.attestation;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;

import static com.mobile.mobilehardware.attestation.Constants.KM_VERIFIED_BOOT_STATE_FAILED;
import static com.mobile.mobilehardware.attestation.Constants.KM_VERIFIED_BOOT_STATE_SELF_SIGNED;
import static com.mobile.mobilehardware.attestation.Constants.KM_VERIFIED_BOOT_STATE_UNVERIFIED;
import static com.mobile.mobilehardware.attestation.Constants.KM_VERIFIED_BOOT_STATE_VERIFIED;
import static com.mobile.mobilehardware.attestation.Constants.ROOT_OF_TRUST_DEVICE_LOCKED_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ROOT_OF_TRUST_VERIFIED_BOOT_HASH_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ROOT_OF_TRUST_VERIFIED_BOOT_KEY_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ROOT_OF_TRUST_VERIFIED_BOOT_STATE_INDEX;

/**
 * @author guxiaonian
 */
class RootOfTrust {

    final byte[] verifiedBootKey;
    final boolean deviceLocked;
    final VerifiedBootState verifiedBootState;
    final byte[] verifiedBootHash;

    private RootOfTrust(ASN1Sequence rootOfTrust, int attestationVersion) {
        this.verifiedBootKey =
                ((ASN1OctetString) rootOfTrust.getObjectAt(ROOT_OF_TRUST_VERIFIED_BOOT_KEY_INDEX))
                        .getOctets();
        this.deviceLocked =
                ASN1Parsing.getBooleanFromAsn1(rootOfTrust.getObjectAt(ROOT_OF_TRUST_DEVICE_LOCKED_INDEX));
        this.verifiedBootState =
                verifiedBootStateToEnum(
                        ASN1Parsing.getIntegerFromAsn1(
                                rootOfTrust.getObjectAt(ROOT_OF_TRUST_VERIFIED_BOOT_STATE_INDEX)));
        if (attestationVersion >= 3) {
            this.verifiedBootHash =
                    ((ASN1OctetString) rootOfTrust.getObjectAt(ROOT_OF_TRUST_VERIFIED_BOOT_HASH_INDEX))
                            .getOctets();
        } else {
            this.verifiedBootHash = null;
        }
    }

    static RootOfTrust createRootOfTrust(ASN1Sequence rootOfTrust, int attestationVersion) {
        if (rootOfTrust == null) {
            return null;
        }
        return new RootOfTrust(rootOfTrust, attestationVersion);
    }

    private static VerifiedBootState verifiedBootStateToEnum(int securityLevel) {
        switch (securityLevel) {
            case KM_VERIFIED_BOOT_STATE_VERIFIED:
                return VerifiedBootState.VERIFIED;
            case KM_VERIFIED_BOOT_STATE_SELF_SIGNED:
                return VerifiedBootState.SELF_SIGNED;
            case KM_VERIFIED_BOOT_STATE_UNVERIFIED:
                return VerifiedBootState.UNVERIFIED;
            case KM_VERIFIED_BOOT_STATE_FAILED:
                return VerifiedBootState.FAILED;
            default:
                throw new IllegalArgumentException("Invalid verified boot state.");
        }
    }

    public enum VerifiedBootState {
        VERIFIED,
        SELF_SIGNED,
        UNVERIFIED,
        FAILED
    }
}

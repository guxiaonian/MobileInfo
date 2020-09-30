package com.mobile.mobilehardware.attestation;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_APPLICATION_ID_PACKAGE_INFOS_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_APPLICATION_ID_SIGNATURE_DIGESTS_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_PACKAGE_INFO_PACKAGE_NAME_INDEX;
import static com.mobile.mobilehardware.attestation.Constants.ATTESTATION_PACKAGE_INFO_VERSION_INDEX;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author guxiaonian
 */
class AttestationApplicationId implements Comparable<AttestationApplicationId> {
    final List<AttestationPackageInfo> packageInfos;
    final List<byte[]> signatureDigests;

    private AttestationApplicationId(DEROctetString attestationApplicationId) throws IOException {
        ASN1Sequence attestationApplicationIdSequence =
                (ASN1Sequence) ASN1Sequence.fromByteArray(attestationApplicationId.getOctets());
        ASN1Set attestationPackageInfos =
                (ASN1Set)
                        attestationApplicationIdSequence.getObjectAt(
                                ATTESTATION_APPLICATION_ID_PACKAGE_INFOS_INDEX);
        this.packageInfos = new ArrayList<>();
        for (ASN1Encodable packageInfo : attestationPackageInfos) {
            this.packageInfos.add(new AttestationPackageInfo((ASN1Sequence) packageInfo));
        }

        ASN1Set digests =
                (ASN1Set)
                        attestationApplicationIdSequence.getObjectAt(
                                ATTESTATION_APPLICATION_ID_SIGNATURE_DIGESTS_INDEX);
        this.signatureDigests = new ArrayList<>();
        for (ASN1Encodable digest : digests) {
            this.signatureDigests.add(((ASN1OctetString) digest).getOctets());
        }
    }

    AttestationApplicationId(
            List<AttestationPackageInfo> packageInfos, List<byte[]> signatureDigests) {
        this.packageInfos = packageInfos;
        this.signatureDigests = signatureDigests;
    }

    static AttestationApplicationId createAttestationApplicationId(
            DEROctetString attestationApplicationId) {
        if (attestationApplicationId == null) {
            return null;
        }
        try {
            return new AttestationApplicationId(attestationApplicationId);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public int compareTo(AttestationApplicationId other) {
        int res = Integer.compare(packageInfos.size(), other.packageInfos.size());
        if (res != 0) {
            return res;
        }
        for (int i = 0; i < packageInfos.size(); ++i) {
            res = packageInfos.get(i).compareTo(other.packageInfos.get(i));
            if (res != 0) {
                return res;
            }
        }
        res = Integer.compare(signatureDigests.size(), other.signatureDigests.size());
        if (res != 0) {
            return res;
        }
        ByteArrayComparator cmp = new ByteArrayComparator();
        for (int i = 0; i < signatureDigests.size(); ++i) {
            res = cmp.compare(signatureDigests.get(i), other.signatureDigests.get(i));
            if (res != 0) {
                return res;
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AttestationApplicationId)
                && (compareTo((AttestationApplicationId) o) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageInfos, Arrays.deepHashCode(signatureDigests.toArray()));
    }

    public static class AttestationPackageInfo implements Comparable<AttestationPackageInfo> {
        final String packageName;
        final long version;

        private AttestationPackageInfo(ASN1Sequence packageInfo) {
            this.packageName =
                    new String(
                            ((ASN1OctetString)
                                    packageInfo.getObjectAt(ATTESTATION_PACKAGE_INFO_PACKAGE_NAME_INDEX))
                                    .getOctets(),
                            UTF_8);
            this.version =
                    ((ASN1Integer) packageInfo.getObjectAt(ATTESTATION_PACKAGE_INFO_VERSION_INDEX))
                            .getValue()
                            .longValue();
        }

        AttestationPackageInfo(String packageName, long version) {
            this.packageName = packageName;
            this.version = version;
        }

        @Override
        public int compareTo(AttestationPackageInfo other) {
            int res = packageName.compareTo(other.packageName);
            if (res != 0) {
                return res;
            }
            res = Long.compare(version, other.version);
            if (res != 0) {
                return res;
            }
            return res;
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof AttestationPackageInfo) && (compareTo((AttestationPackageInfo) o) == 0);
        }

        @Override
        public int hashCode() {
            return Objects.hash(packageName, version);
        }
    }

    private static class ByteArrayComparator implements java.util.Comparator<byte[]> {
        @Override
        public int compare(byte[] a, byte[] b) {
            int res = Integer.compare(a.length, b.length);
            if (res != 0) {
                return res;
            }
            for (int i = 0; i < a.length; ++i) {
                res = Byte.compare(a[i], b[i]);
                if (res != 0) {
                    return res;
                }
            }
            return res;
        }
    }
}

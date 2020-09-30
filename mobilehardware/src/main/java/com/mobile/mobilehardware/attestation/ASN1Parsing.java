package com.mobile.mobilehardware.attestation;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Integer;

/**
 * @author guxiaonian
 */
class ASN1Parsing {

    static boolean getBooleanFromAsn1(ASN1Encodable asn1Value) {
        if (asn1Value instanceof ASN1Boolean) {
            return ((ASN1Boolean) asn1Value).isTrue();
        } else {
            throw new RuntimeException(
                    "Boolean value expected; found " + asn1Value.getClass().getName() + " instead.");
        }
    }

    static int getIntegerFromAsn1(ASN1Encodable asn1Value) {
        if (asn1Value instanceof ASN1Integer) {
            return ((ASN1Integer) asn1Value).getValue().intValue();
        } else if (asn1Value instanceof ASN1Enumerated) {
            return ((ASN1Enumerated) asn1Value).getValue().intValue();
        } else {
            throw new IllegalArgumentException(
                    "Integer value expected; found " + asn1Value.getClass().getName() + " instead.");
        }
    }
}

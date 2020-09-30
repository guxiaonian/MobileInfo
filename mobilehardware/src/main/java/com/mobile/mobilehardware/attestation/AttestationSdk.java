package com.mobile.mobilehardware.attestation;

import android.os.Build;
import android.util.Log;

import org.bouncycastle.util.encoders.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author guxiaonian
 */
public class AttestationSdk {

    private static final String TAG = "AttestationSdk";

    public static JSONObject getKeyAttestation() {
        JSONObject jsonObject = new JSONObject();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "The Build Version " + Build.VERSION.SDK_INT + " < 24");
            return jsonObject;
        }
        //keyAlias
        String keyAlias = UUID.randomUUID().toString();
        try {
            //keyPairGenerator
            jsonObject.put("keyPairGenerator", KeyAttestationUtil.generateKeyPair(keyAlias));
            //X509Certificate
            X509Certificate[] certificates = KeyAttestationUtil.keyAttestationVerify(keyAlias);
            if (certificates == null || certificates.length == 0) {
                Log.e(TAG, "The X509Certificate is null");
                return jsonObject;
            }
            //verifyCertificateChain
            jsonObject.put("verifyCertificateChain", KeyAttestationUtil.verifyCertificateChain(certificates));
            getAttestationRecord(jsonObject, certificates[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static void getAttestationRecord(JSONObject jsonObject, X509Certificate certificate) throws Exception {
        ParsedAttestationRecord parsedAttestationRecord = ParsedAttestationRecord.createParsedAttestationRecord(certificate);
        //Attestation version
        jsonObject.put("attestationVersion", parsedAttestationRecord.attestationVersion);
        //Attestation Security Level
        jsonObject.put("attestationSecurityLevel", parsedAttestationRecord.attestationSecurityLevel.name());
        //Keymaster Version
        jsonObject.put("keymasterVersion", parsedAttestationRecord.keymasterVersion);
        //Keymaster Security Level
        jsonObject.put("keymasterSecurityLevel", parsedAttestationRecord.keymasterSecurityLevel.name());
        //Attestation Challenge
        jsonObject.put("attestationChallenge", new String(parsedAttestationRecord.attestationChallenge, UTF_8));
        //Unique ID
        jsonObject.put("uniqueId", Arrays.toString(parsedAttestationRecord.uniqueId));
        //Software Enforced Authorization List
        JSONObject softwareEnforced = new JSONObject();
        getAuthorizationList(parsedAttestationRecord.softwareEnforced, softwareEnforced);
        jsonObject.put("softwareEnforced", softwareEnforced);
        //TEE Enforced Authorization List
        JSONObject teeEnforced = new JSONObject();
        getAuthorizationList(parsedAttestationRecord.teeEnforced, teeEnforced);
        jsonObject.put("teeEnforced", teeEnforced);
    }

    private static void getAuthorizationList(AuthorizationList authorizationList, JSONObject jsonObject) throws Exception {
        //Purpose
        getOptional(authorizationList.purpose, "purpose", jsonObject);
        //Algorithm
        getOptional(authorizationList.algorithm, "algorithm", jsonObject);
        //KeySize
        getOptional(authorizationList.keySize, "keySize", jsonObject);
        //Digest
        getOptional(authorizationList.digest, "digest", jsonObject);
        //Padding
        getOptional(authorizationList.padding, "padding", jsonObject);
        //EC Curve
        getOptional(authorizationList.ecCurve, "ecCurve", jsonObject);
        //RSA Public Exponent
        getOptional(authorizationList.rsaPublicExponent, "rsaPublicExponent", jsonObject);
        //Rollback Resistance
        jsonObject.put("rollbackResistance", authorizationList.rollbackResistance);
        //Active DateTime
        getOptional(authorizationList.activeDateTime, "activeDateTime", jsonObject);
        //Origination Expire DateTime
        getOptional(authorizationList.originationExpireDateTime, "originationExpireDateTime", jsonObject);
        //Usage Expire DateTime
        getOptional(authorizationList.usageExpireDateTime, "usageExpireDateTime", jsonObject);
        //No Auth Required
        jsonObject.put("noAuthRequired", authorizationList.noAuthRequired);
        //User Auth Type
        getOptional(authorizationList.userAuthType, "userAuthType", jsonObject);
        //Auth Timeout
        getOptional(authorizationList.authTimeout, "authTimeout", jsonObject);
        //Allow While On Body
        jsonObject.put("allowWhileOnBody", authorizationList.allowWhileOnBody);
        //Trusted User Presence Required
        jsonObject.put("trustedUserPresenceRequired", authorizationList.trustedUserPresenceRequired);
        //Trusted Confirmation Required
        jsonObject.put("trustedConfirmationRequired", authorizationList.trustedConfirmationRequired);
        //Unlocked Device Required
        jsonObject.put("unlockedDeviceRequired", authorizationList.unlockedDeviceRequired);
        //All Applications
        jsonObject.put("allApplications", authorizationList.allApplications);
        //Application ID
        getOptional(authorizationList.applicationId, "applicationId", jsonObject);
        //Creation DateTime
        getOptional(authorizationList.creationDateTime, "creationDateTime", jsonObject);
        //Origin
        getOptional(authorizationList.origin, "origin", jsonObject);
        //Rollback Resistant
        jsonObject.put("rollbackResistant", authorizationList.rollbackResistant);
        //Root Of Trust
        JSONObject rootOfTrust = new JSONObject();
        getRootOfTrust(authorizationList.rootOfTrust, rootOfTrust);
        jsonObject.put("rootOfTrust", rootOfTrust);
        //OS Version
        getOptional(authorizationList.osVersion, "osVersion", jsonObject);
        //OS Patch Level
        getOptional(authorizationList.osPatchLevel, "osPatchLevel", jsonObject);
        // Attestation Application ID
        JSONObject applicationId = new JSONObject();
        getAttestationApplicationId(authorizationList.attestationApplicationId, applicationId);
        jsonObject.put("attestationApplicationId", applicationId);
        //Attestation Application ID Bytes
        getOptional(authorizationList.attestationApplicationIdBytes, "attestationApplicationIdBytes", jsonObject);
        //Attestation ID Brand
        getOptional(authorizationList.attestationIdBrand, "attestationIdBrand", jsonObject);
        //Attestation ID Device
        getOptional(authorizationList.attestationIdDevice, "attestationIdDevice", jsonObject);
        //Attestation ID Product
        getOptional(authorizationList.attestationIdProduct, "attestationIdProduct", jsonObject);
        //Attestation ID Serial
        getOptional(authorizationList.attestationIdSerial, "attestationIdSerial", jsonObject);
        //Attestation ID IMEI
        getOptional(authorizationList.attestationIdImei, "attestationIdImei", jsonObject);
        //Attestation ID MEID
        getOptional(authorizationList.attestationIdMeid, "attestationIdMeid", jsonObject);
        //Attestation ID Manufacturer
        getOptional(authorizationList.attestationIdManufacturer, "attestationIdManufacturer", jsonObject);
        //Attestation ID Model
        getOptional(authorizationList.attestationIdModel, "attestationIdModel", jsonObject);
        //Vendor Patch Level
        getOptional(authorizationList.vendorPatchLevel, "vendorPatchLevel", jsonObject);
        //Boot Patch Level
        getOptional(authorizationList.bootPatchLevel, "bootPatchLevel", jsonObject);
    }

    private static void getRootOfTrust(Optional<RootOfTrust> rootOfTrust, JSONObject jsonObject) throws Exception {
        if (rootOfTrust.isPresent()) {
            jsonObject.put("verifiedBootKey", Base64.toBase64String(rootOfTrust.get().verifiedBootKey));
            jsonObject.put("deviceLocked", rootOfTrust.get().deviceLocked);
            jsonObject.put("verifiedBootState", rootOfTrust.get().verifiedBootState.name());
            jsonObject.put("verifiedBootHash", Base64.toBase64String(rootOfTrust.get().verifiedBootHash));
        }
    }

    private static void getAttestationApplicationId(
            Optional<AttestationApplicationId> attestationApplicationId, JSONObject jsonObject) throws Exception {
        if (attestationApplicationId.isPresent()) {
            JSONArray packageInfo = new JSONArray();
            for (AttestationApplicationId.AttestationPackageInfo info : attestationApplicationId.get().packageInfos) {
                JSONObject object = new JSONObject();
                object.put("packageName", info.packageName);
                object.put("version", info.version);
                packageInfo.put(object);
            }
            jsonObject.put("packageInfo", packageInfo);
            JSONArray signatureDigest = new JSONArray();
            for (byte[] digest : attestationApplicationId.get().signatureDigests) {
                JSONObject object = new JSONObject();
                object.put("digest", Base64.toBase64String(digest));
                signatureDigest.put(object);
            }
            jsonObject.put("signatureDigest", signatureDigest);
        }
    }

    private static <T> void getOptional(Optional<T> optional, String caption, JSONObject jsonObject) throws Exception {
        if (optional.isPresent()) {
            if (optional.get() instanceof byte[]) {
                jsonObject.put(caption, Base64.toBase64String((byte[]) optional.get()));
            } else {
                jsonObject.put(caption, optional.get());
            }
        }
    }

}

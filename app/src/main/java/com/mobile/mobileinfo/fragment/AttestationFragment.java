package com.mobile.mobileinfo.fragment;

import android.os.Bundle;

import com.mobile.mobilehardware.attestation.AttestationSdk;
import com.mobile.mobilehardware.complete.CompleteHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;

public class AttestationFragment extends BaseFragment {

    public static AttestationFragment newInstance() {
        Bundle args = new Bundle();
        AttestationFragment fragment = new AttestationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(AttestationSdk.getKeyAttestation());
    }

    @Override
    public String getDescription() {
        return "Safety information of key attestation";
    }


}

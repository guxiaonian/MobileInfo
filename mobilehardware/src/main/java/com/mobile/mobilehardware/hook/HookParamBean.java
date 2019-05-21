package com.mobile.mobilehardware.hook;

/**
 *
 * @author 谷闹年
 * @date 2018/8/14
 */
public class HookParamBean {
    private boolean chargeXposedPackage = false;
    private boolean chargeXposedHookMethod = false;
    private boolean chargeXposedJars = false;
    private boolean chargeSubstratePackage = false;
    private boolean chargeSubstrateHookMethod = false;
    private boolean chargeSubstrateJars = false;
    private boolean chargeFrida = false;

    public boolean isChargeXposedPackage() {
        return chargeXposedPackage;
    }

    public void setChargeXposedPackage(boolean chargeXposedPackage) {
        this.chargeXposedPackage = chargeXposedPackage;
    }

    public boolean isChargeXposedHookMethod() {
        return chargeXposedHookMethod;
    }

    public void setChargeXposedHookMethod(boolean chargeXposedHookMethod) {
        this.chargeXposedHookMethod = chargeXposedHookMethod;
    }

    public boolean isChargeXposedJars() {
        return chargeXposedJars;
    }

    public void setChargeXposedJars(boolean chargeXposedJars) {
        this.chargeXposedJars = chargeXposedJars;
    }

    public boolean isChargeSubstratePackage() {
        return chargeSubstratePackage;
    }

    public void setChargeSubstratePackage(boolean chargeSubstratePackage) {
        this.chargeSubstratePackage = chargeSubstratePackage;
    }

    public boolean isChargeSubstrateHookMethod() {
        return chargeSubstrateHookMethod;
    }

    public void setChargeSubstrateHookMethod(boolean chargeSubstrateHookMethod) {
        this.chargeSubstrateHookMethod = chargeSubstrateHookMethod;
    }

    public boolean isChargeSubstrateJars() {
        return chargeSubstrateJars;
    }

    public void setChargeSubstrateJars(boolean chargeSubstrateJars) {
        this.chargeSubstrateJars = chargeSubstrateJars;
    }

    public boolean isChargeFrida() {
        return chargeFrida;
    }

    public void setChargeFrida(boolean chargeFrida) {
        this.chargeFrida = chargeFrida;
    }

    public HookParamBean() {
    }
}

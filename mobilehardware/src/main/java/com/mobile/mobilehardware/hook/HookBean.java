package com.mobile.mobilehardware.hook;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class HookBean extends BaseBean {
    private static final String TAG = HookBean.class.getSimpleName();

    /**
     * Xposed详细信息
     */
    private JSONObject isHaveXposed;

    /**
     * Substrate详细信息
     */
    private JSONObject isHaveSubstrate;

    /**
     * Frida详细信息
     */
    private JSONObject isHaveFrida;

    public JSONObject getIsHaveXposed() {
        return isHaveXposed;
    }

    public void setIsHaveXposed(JSONObject isHaveXposed) {
        this.isHaveXposed = isHaveXposed;
    }

    public JSONObject getIsHaveSubstrate() {
        return isHaveSubstrate;
    }

    public void setIsHaveSubstrate(JSONObject isHaveSubstrate) {
        this.isHaveSubstrate = isHaveSubstrate;
    }

    public JSONObject getIsHaveFrida() {
        return isHaveFrida;
    }

    public void setIsHaveFrida(JSONObject isHaveFrida) {
        this.isHaveFrida = isHaveFrida;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Hook.IS_HAVE_XPOSED, isHaveXposed);
            jsonObject.put(BaseData.Hook.IS_HAVE_SUBSTRATE, isHaveSubstrate);
            jsonObject.put(BaseData.Hook.IS_HAVE_FRIDA, isHaveFrida);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }

    public static class XposedBean extends BaseBean {

        /**
         * 包名检测
         */
        private boolean checkXposedPackage;

        /**
         * 检测调用栈中的可疑方法
         */
        private boolean checkXposedHookMethod;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkXposedJars;

        /**
         * 检测载入Xposed工具类
         */
        private boolean checkClassLoader;

        /**
         * 新增判断系统方法调用钩子
         */
        private boolean checkNativeMethod;

        /**
         * 虚拟检测Xposed环境
         */
        private boolean checkSystem;

        /**
         * 寻找Xposed运行库文件
         */
        private boolean checkExecLib;

        /**
         * 内核查找Xposed链接库
         */
        private boolean checkCheckman;

        /**
         * 环境变量特征字判断
         */
        private boolean checkXposedBridge;

        public boolean isCheckXposedPackage() {
            return checkXposedPackage;
        }

        public void setCheckXposedPackage(boolean checkXposedPackage) {
            this.checkXposedPackage = checkXposedPackage;
        }

        public boolean isCheckXposedHookMethod() {
            return checkXposedHookMethod;
        }

        public void setCheckXposedHookMethod(boolean checkXposedHookMethod) {
            this.checkXposedHookMethod = checkXposedHookMethod;
        }

        public boolean isCheckXposedJars() {
            return checkXposedJars;
        }

        public void setCheckXposedJars(boolean checkXposedJars) {
            this.checkXposedJars = checkXposedJars;
        }

        public boolean isCheckClassLoader() {
            return checkClassLoader;
        }

        public void setCheckClassLoader(boolean checkClassLoader) {
            this.checkClassLoader = checkClassLoader;
        }

        public boolean isCheckNativeMethod() {
            return checkNativeMethod;
        }

        public void setCheckNativeMethod(boolean checkNativeMethod) {
            this.checkNativeMethod = checkNativeMethod;
        }

        public boolean isCheckSystem() {
            return checkSystem;
        }

        public void setCheckSystem(boolean checkSystem) {
            this.checkSystem = checkSystem;
        }

        public boolean isCheckExecLib() {
            return checkExecLib;
        }

        public void setCheckExecLib(boolean checkExecLib) {
            this.checkExecLib = checkExecLib;
        }

        public boolean isCheckCheckman() {
            return checkCheckman;
        }

        public void setCheckCheckman(boolean checkCheckman) {
            this.checkCheckman = checkCheckman;
        }

        public boolean isCheckXposedBridge() {
            return checkXposedBridge;
        }

        public void setCheckXposedBridge(boolean checkXposedBridge) {
            this.checkXposedBridge = checkXposedBridge;
        }

        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put(BaseData.Hook.Xposed.CHECK_XPOSED_PACKAGE, checkXposedPackage);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_XPOSED_HOOK_METHOD, checkXposedHookMethod);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_XPOSED_JARS, checkXposedJars);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_CLASSLOADER, checkClassLoader);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_NATIVE_METHOD, checkNativeMethod);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_SYSTEM, checkSystem);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_EXEC_LIB, checkExecLib);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_CHECKMAN, checkCheckman);
                jsonObject.put(BaseData.Hook.Xposed.CHECK_XPOSED_BRIDGE, checkXposedBridge);

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return super.toJSONObject();
        }
    }


    public static class SubstrateBean extends BaseBean {

        /**
         * 包名检测
         */
        private boolean checkSubstratePackage;

        /**
         * 检测调用栈中的可疑方法
         */
        private boolean checkSubstrateHookMethod;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkSubstrateJars;

        public boolean isCheckSubstratePackage() {
            return checkSubstratePackage;
        }

        public void setCheckSubstratePackage(boolean checkSubstratePackage) {
            this.checkSubstratePackage = checkSubstratePackage;
        }

        public boolean isCheckSubstrateHookMethod() {
            return checkSubstrateHookMethod;
        }

        public void setCheckSubstrateHookMethod(boolean checkSubstrateHookMethod) {
            this.checkSubstrateHookMethod = checkSubstrateHookMethod;
        }

        public boolean isCheckSubstrateJars() {
            return checkSubstrateJars;
        }

        public void setCheckSubstrateJars(boolean checkSubstrateJars) {
            this.checkSubstrateJars = checkSubstrateJars;
        }

        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put(BaseData.Hook.Substrate.CHECK_SUBSTRATE_PACKAGE, checkSubstratePackage);
                jsonObject.put(BaseData.Hook.Substrate.CHECK_SUBSTRATE_HOOK_METHOD, checkSubstrateHookMethod);
                jsonObject.put(BaseData.Hook.Substrate.CHECK_SUBSTRATE_JARS, checkSubstrateJars);


            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return super.toJSONObject();
        }
    }

    public static class FridaBean extends BaseBean {

        /**
         * 检测进程信息
         */
        private boolean checkRunningProcesses;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkFridaJars;

        public boolean isCheckRunningProcesses() {
            return checkRunningProcesses;
        }

        public void setCheckRunningProcesses(boolean checkRunningProcesses) {
            this.checkRunningProcesses = checkRunningProcesses;
        }

        public boolean isCheckFridaJars() {
            return checkFridaJars;
        }

        public void setCheckFridaJars(boolean checkFridaJars) {
            this.checkFridaJars = checkFridaJars;
        }

        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put(BaseData.Hook.Frida.CHECK_RUNNING_PROCESSES, checkRunningProcesses);
                jsonObject.put(BaseData.Hook.Frida.CHECK_FRIDA_JARS, checkFridaJars);


            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return super.toJSONObject();
        }
    }
}

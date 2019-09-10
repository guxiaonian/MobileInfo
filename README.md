<div align="center">
  
## MobileInfo

**获取`Android`手机硬件的全部信息**

**请自觉遵循《信息安全技术移动互联网应用(App)收集个人信息基本规范(草案)》**

[![Download](https://api.bintray.com/packages/guxiaonian/maven/mobile/images/download.svg) ](https://bintray.com/guxiaonian/maven/mobile/_latestVersion)
[![GitHub issues](https://img.shields.io/github/issues/guxiaonian/MobileInfo.svg)](https://github.com/guxiaonian/MobileInfo/issues)
[![GitHub forks](https://img.shields.io/github/forks/guxiaonian/MobileInfo.svg)](https://github.com/guxiaonian/MobileInfo/network)
[![GitHub stars](https://img.shields.io/github/stars/guxiaonian/MobileInfo.svg)](https://github.com/guxiaonian/MobileInfo/stargazers)
[![GitHub license](https://img.shields.io/github/license/guxiaonian/MobileInfo.svg)](http://www.apache.org/licenses/LICENSE-2.0)

</div>
<br>

[APP体验](./download.png)

Table of Contents
=================

   * [数据信息](#数据信息)
      * [App信息获取](#app信息获取)
      * [App安装列表获取](#app安装列表获取)
      * [音量数据获取](#音量数据获取)
      * [版本数据获取](#版本数据获取)
      * [电池数据获取](#电池数据获取)
      * [Cpu实时数据获取](#cpu实时数据获取)
      * [蓝牙数据获取](#蓝牙数据获取)
      * [系统Build数据获取](#系统build数据获取)
      * [摄像头数据获取](#摄像头数据获取)
      * [Cpu数据获取](#cpu数据获取)
      * [调试数据获取](#调试数据获取)
      * [host数据获取](#host数据获取)
      * [模拟器数据获取](#模拟器数据获取)
      * [hook数据获取](#hook数据获取)
      * [本地数据获取](#本地数据获取)
      * [内存数据获取](#内存数据获取)
      * [多开数据获取](#多开数据获取)
      * [网络数据获取](#网络数据获取)
      * [root数据获取](#root数据获取)
      * [屏幕数据获取](#屏幕数据获取)
      * [SDCard数据获取](#SDCard数据获取)
      * [设置数据获取](#设置数据获取)
      * [信号数据获取](#信号数据获取)
      * [手机卡数据获取](#手机卡数据获取)
      * [堆栈数据获取](#堆栈数据获取)
      * [唯一ID数据获取](#唯一id数据获取)
      * [UA数据获取](#ua数据获取)
      * [Xposed数据获取](#xposed数据获取)

# 依赖

```gradle
implementation 'fairy.easy:mobilehardware:{latest-version}'

```

# 数据信息

### [App信息获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/app)

###### 代码示例

```java
 PackageHelper.getPackageInfo(getApplicationContext())
```

###### 数据格式

```json
{
    "appName":"MobileInfo",
    "packageName":"com.mobile.mobileinfo",
    "packageSign":"521cec5eb6984bc70f5b2a27e437b979",
    "appVersionCode":"1",
    "appVersionName":"1.0",
    "targetSdkVersion":"27",
    "minSdkVersion":"14"

}
```

数据键|数据类型|数据解释
---|-----|---
appName|String|app名字
packageName |String|app包名
packageSign |String|app包签名
appVersionCode |String|app版本号
appVersionName |String|app版本名
targetSdkVersion |String|目标系统版本号
minSdkVersion |String|最低系统版本号

### [App安装列表获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/applist)

###### 代码示例

```java
 ListAppHelper.mobListApp(getApplicationContext())
```

###### 数据格式

```json
[
    {
        "packageName":"com.huawei.android.tips",
        "versionName":"1.0",
        "versionCode":"1",
        "isSystem":"false"
    }
]
```

数据键|数据类型|数据解释
---|-----|---
packageName |String|app包名
appVersionCode |String|app版本号
appVersionName |String|app版本名
isSystem |String|是否是系统app

### [音量数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/audio)

###### 代码示例

```java
 AudioHelper.mobGetMobAudio(getApplicationContext())
```

###### 数据格式

```json
{
    "maxVoiceCall":"15",
    "currentVoiceCall":"3",
    "maxSystem":"15",
    "currentSystem":"0",
    "maxRing":"15",
    "currentRing":"0",
    "maxMusic":"15",
    "currentMusic":"0",
    "maxAlarm":"15",
    "currentAlarm":"0"
}
```

数据键|数据类型|数据解释
---|-----|---
maxVoiceCall |String|最大通话音量
currentVoiceCall |String|当前通话音量
maxSystem |String|最大系统音量
currentSystem |String|当前系统音量
maxRing |String|最大铃声音量
currentRing |String|当前铃声音量
maxMusic |String|最大音乐音量
currentMusic |String|当前音乐音量
maxAlarm |String|最大提示声音音量
currentAlarm |String|当前提示声音音量

### [版本数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/band)

###### 代码示例

```java
 BandHelper.mobGetBandInfo()
```

###### 数据格式

```json
{
    "baseBand":"21C30B323S006C000,21C30B323S006C000",
    "innerBand":"STF-AL00 8.0.0.360(C00GT)",
    "linuxBand":"$unknown"
}
```

数据键|数据类型|数据解释
---|-----|---
baseBand |String|基带版本
innerBand |String|内部版本
linuxBand |String|linux内核版本

### [电池数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/battery)

###### 代码示例

```java
BatteryHelper.mobGetBattery(getApplicationContext())
```

###### 数据格式

```json
{
    "br":"74.0%",
    "status":"charging",
    "plugState":"usb",
    "health":"good",
    "present":"true",
    "technology":"Li-poly",
    "temperature":"33℃",
    "voltage":"4.068V",
    "power":"3100.0mAh"
}
```

数据键|数据类型|数据解释
---|-----|---
br |String|电量百分比
status |String|电池状态
plugState |String|电池充电状态
health |String|电池健康状况
present |String|是否有电池
technology |String|电池的技术制造
temperature |String|电池温度
voltage |String|电池电压
power |String|电池总电量

### [Cpu实时数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/block)

###### 代码示例

```java
CpuInternals.getInstance().getCpuSampler().start();
CpuInternals.getInstance().getCpuSampler().getCpuList();
CpuInternals.getInstance().getCpuSampler().stop();

```

### [蓝牙数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/bluetooth)

###### 代码示例

```java
BluetoothHelper.mobGetMobBluetooth(getApplicationContext())
```

###### 数据格式

```json
{
    "bluetoothAddress":"$unknown",
    "isEnabled":"true",
    "device":[
        {
            "name":"iPhone",
            "address":"E4:9A:79:89:C7:24",
        }
    ],
    "phoneName":"Honor"
}
```

数据键|数据类型|数据解释
---|-----|---
bluetoothAddress |String|蓝牙地址
isEnabled |String|蓝牙是否打开
device |JSONArray|连接的手机的信息
name |String|连接手机的蓝牙地址
address |String|连接手机的蓝牙名字
phoneName |String|手机设置的名字

### [系统Build数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/build)

###### 代码示例

```java
BuildHelper.mobGetBuildInfo()
```

###### 数据格式

```json
{
    "board":"STF",
    "bootloader":"unknown",
    "brand":"HONOR",
    "device":"HWSTF",
    "display":"STF-AL00 8.0.0.360(C00GT)",
    "fingerprint":"HONOR/STF-AL00/HWSTF:8.0.0/HUAWEISTF-AL00/360(C00GT):user/release-keys",
    "hardware":"hi3660",
    "host":"WUH1000129106",
    "id":"HUAWEISTF-AL00",
    "manufacturer":"HUAWEI",
    "model":"STF-AL00",
    "product":"STF-AL00",
    "radio":"21C30B323S006C000,21C30B323S006C000",
    "serial":"8BN0217901012695",
    "tags":"release-keys",
    "time":"1553851883000",
    "type":"user",
    "user":"test",
    "osVersion":"HONOR/STF-AL00/HWSTF:8.0.0/HUAWEISTF-AL00/358(C00GT):user/release-keys",
    "releaseVersion":"8.0.0",
    "codeName":"$unknown",
    "incremental":"360(C00GT)",
    "sdkInt":"26",
    "previewSdkInt":"0",
    "securityPatch":"2019-04-05"
}
```

数据键|数据类型|数据解释
---|-----|---
board |String|主板名称
bootloader |String|系统引导程序版本号
brand |String|系统定制商
device |String|设备参数
display |String|显示屏参数
fingerprint |String|硬件名
hardware |String|内核命令行中的硬件名
host |String|host
id |String|标签
manufacturer |String|硬件厂商
model |String|版本
product |String|手机厂商
radio |String|无线电固件的版本字符串
serial |String|获取硬件序列号
tags |String|描述Build的标签
time |String|time
type |String|type
user |String|user
osVersion |String|os版本
releaseVersion |String|版本
codeName |String|当前开发代码名称
incremental |String|基础源代码控件用于表示此构建的内部值
sdkInt |String|SDK的版本
previewSdkInt |String|SDK的预览版本
securityPatch |String|用户可见的安全补丁程序级别

### [摄像头数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/camera)

###### 代码示例

```java
CameraHelper.getCameraInfo(getApplicationContext())
```

###### 数据格式

```json
{
    "cameraInfo":[
        {
            "cameraFacing":"back",
            "cameraLevel":"limited",
            "cameraFlashInfo":"true",
            "outputFormats":[
                "raw_sensor",
                "jpeg",
                "private",
                "yuv_420_888",
                "depth16"
            ]
        }
    ]
}
```

数据键|数据类型|数据解释
---|-----|---
cameraInfo |JSONArray|摄像头信息
cameraFacing |String|摄像头的位置
cameraLevel |String|摄像头支持水平
cameraFlashInfo |String|是否有闪光灯
outputFormats |JSONArray|摄像头支持的格式

### [Cpu数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/cpu)

###### 代码示例

```java
CpuHelper.mobGetCpuInfo()
```

###### 数据格式

```json
{
    "cpuName":"0",
    "cpuFreq":"1844000KHZ",
    "cpuMaxFreq":"1844000KHZ",
    "cpuMinFreq":"533000KHZ",
    "cpuHardware":"hi3660",
    "cpuCores":"8",
    "cpuTemp":"36℃",
    "cpuAbi":"arm64-v8a,armeabi-v7a,armeabi"
}
```

数据键|数据类型|数据解释
---|-----|---
cpuName |String|CPU名字
cpuFreq |String|CPU频率
cpuMaxFreq |String|CPU最大频率
cpuMinFreq |String|CPU最小频率
cpuHardware |String|CPU硬件名
cpuCores |String|CPU核数
cpuTemp |String|CPU温度
cpuAbi |String|CPU架构

### [调试数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/debug)

###### 代码示例

```java
DebugHelper.getDebuggingData(getApplicationContext())
```

###### 数据格式

```json
{
    "isOpenDebug":"true",
    "isDebugVersion":"true",
    "isDebugging":"false",
    "isReadProcStatus":"false",
    "isAllowMockLocation":"false"
}
```

数据键|数据类型|数据解释
---|-----|---
isOpenDebug |String|是否开启了调试模式
isDebugVersion |String|是否是Debug版本
isDebugging |String|是否正在调试
isReadProcStatus |String|读取id判断是否在调试
isAllowMockLocation |String|是否打开位置模拟

### [host数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/dns)

###### 代码示例

```java
DnsHelper.mobDNS("ip")
```

### [模拟器数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/emulator)

###### 代码示例

```java
EmulatorHelper.mobCheckEmulator(getApplicationContext())
```

###### 数据格式

```json
{
    "checkBuild":"false",
    "checkPkg":"false",
    "checkPipes":"false",
    "checkQEmuDriverFile":"false",
    "checkHasLightSensorManager":"false",
    "checkCpuInfo":"false"
}
```

数据键|数据类型|数据解释
---|-----|---
checkBuild |String|build
checkPkg |String|包名修改
checkPipes |String|管道检测
checkQEmuDriverFile |String|驱动程序检测
checkHasLightSensorManager |String|光传感器检测
checkCpuInfo |String|cpu架构检测

### [hook数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/hook)

###### 代码示例

```java
HookHelper.isXposedHook(getApplicationContext())
```

###### 数据格式

```json
{
    "xposedInfo":{
        "checkXposedPackage":"false",
        "checkXposedHookMethod":"false",
        "checkXposedJars":"false",
        "checkClassLoader":"false",
        "checkNativeMethod":"false",
        "checkSystem":"false",
        "checkExecLib":"false",
        "checkCheckman":"false",
        "checkXposedBridge":"false"
    },
    "substrateInfo":{
        "checkSubstratePackage":"false",
        "checkSubstrateHookMethod":"false",
        "checkSubstrateJars":"false"
    },
    "fridaInfo":{
        "checkRunningProcesses":"false",
        "checkFridaJars":"false"
    }
}
```

数据键|数据类型|数据解释
---|-----|---
xposedInfo |JSONObject|Xposed信息
checkXposedPackage |String|包名检测
checkXposedHookMethod |String|检测调用栈中的可疑方法
checkXposedJars |String|检测内存中可疑的jars
checkClassLoader |String|检测载入Xposed工具类
checkNativeMethod |String|判断系统方法调用钩子
checkSystem |String|虚拟检测Xposed环境
checkExecLib |String|寻找Xposed运行库文件
checkCheckman |String|内核查找Xposed链接库
checkXposedBridge |String|环境变量特征字判断
substrateInfo |JSONObject|Substrate信息
checkSubstratePackage |String|包名检测
checkSubstrateHookMethod |String|检测调用栈中的可疑方法
checkSubstrateJars |String|检测内存中可疑的jars
fridaInfo |JSONObject|Frida信息
checkRunningProcesses |String|检测进程信息
checkFridaJars |String|检测内存中可疑的jars

### [本地数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/local)

###### 代码示例

```java
LocalHelper.mobGetMobLocal()
```

###### 数据格式

```json
{
    "country":"CN",
    "language":"zh"
}
```

数据键|数据类型|数据解释
---|-----|---
country |String|当前国家
language |String|当前语言

### [内存数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/memory)

###### 代码示例

```java
MemoryHelper.getMemoryInfo(getApplicationContext())
```

###### 数据格式

```json
{
    "ramMemoryTotal":"3.90 GB",
    "ramMemoryAvailable":"1.56 GB",
    "romMemoryAvailable":"40.22 GB",
    "romMemoryTotal":"56.30 GB",
    "sdCardMemoryAvailable":"40.20 GB",
    "sdCardMemoryTotal":"56.28 GB"
}
```

数据键|数据类型|数据解释
---|-----|---
ramMemoryTotal |String|RAM全部内存
ramMemoryAvailable |String|RAM可用内存
romMemoryAvailable |String|ROM可用内存
romMemoryTotal |String|ROM全部内存
sdCardMemoryAvailable |String|内存卡可用内存
sdCardMemoryTotal |String|内存卡全部内存

### [多开数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/moreopen)

###### 代码示例

```java
MoreOpenHelper.checkVirtual(getApplicationContext())
```

###### 数据格式

```json
{
    "checkByPrivateFilePath":"false",
    "checkByMultiApkPackageName":"false",
    "checkByHasSameUid":"false"
}
```

数据键|数据类型|数据解释
---|-----|---
checkByPrivateFilePath |String|检测私有路径
checkByMultiApkPackageName |String|maps检测
checkByHasSameUid |String|ps检测

### [网络数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/network)

###### 代码示例

```java
NetWorkHelper.mobGetMobNetWork(getApplicationContext())
```

###### 数据格式

```json
{
    "type":"WIFI",
    "networkAvailable":"true",
    "haveIntent":"false",
    "isFlightMode":"false",
    "isNFCEnabled":"true",
    "isHotspotEnabled":"false",
    "hotspotSSID":"$unknown",
    "hotspotPwd":"$unknown",
    "encryptionType":"$unknown"
}
```

数据键|数据类型|数据解释
---|-----|---
type |String|网络类型
networkAvailable |String|网络是否可用
haveIntent |String|是否开启数据流量
isFlightMode |String|是否是飞行模式
isNFCEnabled |String|NFC功能是否开启
isHotspotEnabled |String|是否开启热点
hotspotSSID |String|热点账号
hotspotPwd |String|热点密码
encryptionType |String|热点加密类型

### [root数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/root)

###### 代码示例

```java
RootHelper.mobileRoot(getApplicationContext())
```

### [屏幕数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/screen)

###### 代码示例

```java
ScreenHelper.mobGetMobScreen(getApplicationContext())
```

###### 数据格式

```json
{
    "densityScale":"3.0",
    "densityDpi":"480",
    "width":"1080",
    "height":"1920",
    "isScreenAuto":"false",
    "isScreenAutoChange":"false",
    "screenBrightness":"114",
    "checkHideStatusBar":"false",
    "checkHasNavigationBar":"false",
    "getStatusBarHeight":"72",
    "getNavigationBarHeight":"126"
}
```

数据键|数据类型|数据解释
---|-----|---
densityScale |String|当前屏幕密度与标准屏幕密度的比值
densityDpi |String|屏幕密度
width |String|屏幕宽度
height |String|屏幕高度
isScreenAuto |String|亮度是否为自动调节
isScreenAutoChange |String|屏幕亮度
screenBrightness |String|屏幕是否开启自动旋转
checkHideStatusBar |String|是否隐藏状态栏
checkHasNavigationBar |String|是否显示底部导航栏
getStatusBarHeight |String|获取状态栏高度
getNavigationBarHeight |String|获取底部导航栏的高度

### [SDCard数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/sdcard)

###### 代码示例

```java
SDCardHelper.mobGetSdCard()
```

###### 数据格式

```json
{
    "isSDCardEnable":"true",
    "getSDCardPath":"/storage/emulated/0"
}
```

数据键|数据类型|数据解释
---|-----|---
isSDCardEnable |String|SDCard是否可用
getSDCardPath |String|SDCard的路径


### [设置数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/setting)

###### 代码示例

```java
SettingsHelper.mobGetMobSettings(getApplicationContext())
```

###### 数据格式

```json
{
    "androidId":"f573c0f6ca5178a2"
}
```

数据键|数据类型|数据解释
---|-----|---
androidId |String|androidId

### [信号数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/signal)

###### 代码示例

```java
SignalHelper.mobGetNetRssi(getApplicationContext())
```

###### 数据格式

```json
{
    "type":"WIFI",
    "bssid":"82:2a:a8:1b:58:de",
    "ssid":"GPNL",
    "ipAddress":"192.168.1.73",
    "ipAddressIpv6":"$unknown",
    "macAddress":"D8:C7:71:E4:48:B9",
    "networkId":"93",
    "linkSpeed":"86Mbps",
    "rssi":"-53",
    "level":"4",
    "supplicantState":"COMPLETED",
    "proxy":"false",
    "proxyAddress":"$unknown",
    "proxyPort":"$unknown"
}
```

数据键|数据类型|数据解释
---|-----|---
type |String|网络类型
bssid |String|bssid
ssid |String|ssid
ipAddress |String|ipv4
ipAddressIpv6 |String|ipv6
macAddress |String|mac地址
networkId |String|网络id
linkSpeed |String|网络速度
rssi |String|信号强度
level |String|信号等级
supplicantState |String|连接状态
proxy |String|是否开启代理
proxyAddress |String|代理地址
proxyPort |String|代理端口号

### [手机卡数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/simcard)

###### 代码示例

```java
SimCardHelper.mobileSimInfo(getApplicationContext())
```

###### 数据格式

```json
{
    "sim1Imei":"$unknown",
    "sim2Imei":"866957032955878",
    "sim1Imsi":"460037121038284",
    "sim2Imsi":"$unknown",
    "simSlotIndex":"0",
    "meid":"A000007ADE42E2",
    "sim1ImsiOperator":"CT",
    "sim2ImsiOperator":"$unknown",
    "sim1Ready":"true",
    "sim2Ready":"false",
    "isTwoCard":"false",
    "isHaveCard":"true",
    "operator":"CT"
}
```

数据键|数据类型|数据解释
---|-----|---
sim1Imei |String|卡1IMEI
sim2Imei |String|卡2IMEI
sim1Imsi |String|卡1IMSI
sim2Imsi |String|卡2IMSI
simSlotIndex |String|有流量的卡的卡槽id
meid |String|MEID
sim1ImsiOperator |String|卡1运营商
sim2ImsiOperator |String|卡2运营商
sim1Ready |String|卡1是否激活
sim2Ready |String|卡2是否激活
isTwoCard |String|是否有两张卡
isHaveCard |String|是否有卡
operator |String|流量卡运营商

### [堆栈数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/stack)

###### 代码示例

```java
StackSampler.getStackInfo(Thread.currentThread())
```

### [唯一ID数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/uniqueid)

###### 代码示例

```java
PhoneIdHelper.getPsuedoUniqueID()
```

### [UA数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/useragent)

###### 代码示例

```java
UserAgentHelper.getDefaultUserAgent(getApplicationContext())
```

### [Xposed数据获取](https://github.com/guxiaonian/MobileInfo/tree/master/mobilehardware/src/main/java/com/mobile/mobilehardware/xposed)

###### 代码示例

```java
XposedHookHelper.checkXposedInjet(getApplicationContext())
```

###### 数据格式

```json
{
    "xposedApp":"false",
    "xposedImei":"false",
    "xposedImsi":"false",
    "xposedSerial":"false",
    "xposedSsid":"false",
    "xposedMac":"false",
    "xposedAddress":"false",
    "xposedAndroidId":"false",
    "xposedLatitude":"false",
    "xposedLongitude":"false"
}
```

数据键|数据类型|数据解释
---|-----|---
xposedApp |String|是否hook了本APP
xposedImei |String|是否hook了IMEI
xposedImsi |String|是否hook了IMSI
xposedSerial |String|是否hook了序列号
xposedSsid |String|是否hook了SSID
xposedMac |String|是否hook了MAC地址
xposedAddress |String|是否hook了蓝牙地址
xposedAndroidId |String|是否hook了AndroidId
xposedLatitude |String|否hook了纬度
xposedLongitude |String|是否hook了经度

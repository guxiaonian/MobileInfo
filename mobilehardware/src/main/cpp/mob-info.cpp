#include "mob-info.h"


using std::string;

string getBootId() {
    string bootId = readFile("/proc/sys/kernel/random/boot_id");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/boot_id");
    }
    return bootId;
}

string getEntropyAvail() {
    string bootId = readFile("/proc/sys/kernel/random/entropy_avail");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/entropy_avail");
    }
    return bootId;
}

string getPoolSize() {
    string bootId = readFile("/proc/sys/kernel/random/poolsize");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/poolsize");
    }
    return bootId;
}

string getReadWakeupThreshold() {
    string bootId = readFile("/proc/sys/kernel/random/read_wakeup_threshold");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/read_wakeup_threshold");
    }
    return bootId;
}

string getWriteWakeupThreshold() {
    string bootId = readFile("/proc/sys/kernel/random/write_wakeup_threshold");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/write_wakeup_threshold");
    }
    return bootId;
}

string getUuid() {
    string bootId = readFile("/proc/sys/kernel/random/uuid");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/uuid");
    }
    return bootId;
}

string getURandomMinReseedSecs() {
    string bootId = readFile("/proc/sys/kernel/random/urandom_min_reseed_secs");
    if (strlen(bootId.c_str()) == 0) {
        bootId = shellExecute("cat /proc/sys/kernel/random/urandom_min_reseed_secs");
    }
    return bootId;
}

string getKennel() {

    string kennel;
    kennel = readFile("/proc/version");
    if (strlen(kennel.c_str()) == 0) {
        string r = shellExecute("uname -r");
        string v = shellExecute("uname -v");
        if (strlen(r.c_str()) != 0) {
            if (strlen(v.c_str()) != 0) {
                return r + " " + v;
            } else {
                return r;
            }
        }
    } else {
        return kennel;
    }
    return "";

}

string getBuildInfo64(const char *__name) {
    char data[BUF_SIZE_64];
    __system_property_get(__name, data);
    return data;
}

string getBuildInfo256(const char *__name) {
    char data[BUF_SIZE_256];
    __system_property_get(__name, data);
    return data;
}

string readFile(const string &path) {

    if (path.empty()) {
        return "";
    }
    char buf[BUF_SIZE];
    FILE *pf = NULL;
    if ((pf = fopen(path.c_str(), "r")) == NULL) {
        return "";
    }
    string resultStr = "";
    while (fgets(buf, sizeof(buf), pf)) {
        resultStr += buf;
    }
    fclose(pf);
    unsigned long size = resultStr.size();
    if (size > 0 && resultStr[size - 1] == '\n') {
        resultStr = resultStr.substr(0, size - 1);
    }
    return resultStr;
}

string shellExecute(const string &cmdStr) {
    char buf[128];
    FILE *pf = NULL;
    if ((pf = popen(cmdStr.c_str(), "r")) == NULL) {
        return "";
    }
    string resultStr = "";
    while (fgets(buf, sizeof(buf), pf)) {
        resultStr += buf;
    }
    pclose(pf);
    unsigned long size = resultStr.size();
    if (size > 0 && resultStr[size - 1] == '\n') {
        resultStr = resultStr.substr(0, size - 1);
    }
    return resultStr;
}

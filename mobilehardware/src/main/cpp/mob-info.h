#include <jni.h>
#include <string>
#include <sys/system_properties.h>
#include <stdio.h>
#include <malloc.h>
#include <string.h>
#define BUF_SIZE 512
#define BUF_SIZE_256 256
#define BUF_SIZE_64 64
using std::string;

string getBootId();

string getEntropyAvail();

string getPoolSize();

string getReadWakeupThreshold();

string getWriteWakeupThreshold();

string getUuid();

string getURandomMinReseedSecs();

string getKennel();

string readFile(const string &path);

string getBuildInfo64(const char *__name);

string getBuildInfo256(const char *__name);

string shellExecute(const string &cmdStr);
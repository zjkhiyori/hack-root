# HackRoot
HackRoot是一个可以让系统免root，App仍能获取高级权限的一个demo，比如静默卸载安装，获取adb调试日志，篡改hosts，杀掉某个服务、进程等等adb shell能做到的一切事情

![screenshot](https://github.com/zjkhiyori/hack-root/blob/master/example/screenshot.png)

## 开始使用
* 开始前请确保你的Android设备已经开启调试模式
* Clone工程
* 将`${rootProject}/libs/server.dex`文件push至你的android设备目录的`/data/local/tmp/`
    ```
    adb push ${rootProject}/libs/server.dex /data/local/tmp
    ```
* 安装最新release的apk
* 执行启动脚本
    * 前台执行（拔掉数据线服务会关闭）
        ```
        bash scripts/launch_fore.sh 
        ```
    * 后台执行（除非重启设备或者kill该服务，否则会一直运行，任务名称为`club.syachiku.hackrootservice`）
        ```
        bash scripts/launch_silence.sh 
        ```
现在你可以进入HackRoot输入包名随意静默卸载App，或者任何adb能做的事情

若有有趣的想法欢迎fork与pr

## 技术参考
[app_process-shell-use](https://github.com/gtf35/app_process-shell-use)

[android-common](https://github.com/Trinea/android-common)

## License
```
MIT License

Copyright (c) 2019 Yori Zhao

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

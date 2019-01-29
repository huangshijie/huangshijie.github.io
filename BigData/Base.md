大数据是通过传统数据库技术和数据处理工具不能处理的庞大而复杂的**数据集合**

```
CentOS报错：Could not retrieve mirrorlist http://mirrorlist.centos.org/?release=7&arch=x86_64&repo=os&infra=stock32 error was 14: curl#6 - "Could not resolve host: mirrorlist.centos.org; Unknown error"

应该是因为刚装好虚拟机，网卡没自动连上

通过命令 - nmcli d
发现有个网关disconnected

step 1. 通过命令 -nmtui，打开网络管理页面
step 2. 删除那个disconnected的网关，新建一个，确保“Automatically connect”被选中
step 3. 重启搞定
```

```
查看主机名 hostnamectl

修改主机名 hostnamectl set-hostname xxx
```

下载
CentOS: http://linux.cc.lehigh.edu/centos/7.6.1810/isos/x86_64/CentOS-7-x86_64-Minimal-1810.iso


# Reference
https://www.cnblogs.com/zhangyinhua/p/7647334.html
# Docker 体系
## 1 Docker 入门
### 1.1 Docker 安装简单使用
* uname -a  查看内核版本，docker要求3.8以上，最好是3.10以上
* 安装 yum install docker
> 如果出现 Error downloading packages: 说明可能需要换源，下面是切换阿里云
```shell
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
yum makecache
```
* 完成可查看版本，设置启动及自启动
```shell
docker -v
systemctl enable docker
systemctl start docker
```
### 1.2 Docker 镜像
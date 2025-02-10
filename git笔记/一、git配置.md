## 一、git安装

[Git - 安装 Git](https://git-scm.com/book/zh/v2/起步-安装-Git)

## 二、git配置

每台计算机上只需要配置一次，程序升级会保留配置信息

git config命令控制git外观和行为的配置变量，这些变量存储在三个不同的位置

1、/etc/gitconfig 文件:针对系统上所有用户的配置 带上--system表示读写该文件，由于是系统配置文件修改时需要管理员或超级用户权限

2、`~/.gitconfig` 或 `~/.config/git/config` 文件:针对当前用户，带上--global表示读写该文件，会对当前用户的所有仓库生效

3、当前仓库的.git/config文件:针对当前仓库，带上--local表示读写该文件，默认是这个，不带选项也可以

每个级别会覆盖上一级别的配置

```console
git config --list --show-origin //查询所有配置及所在文件
```

```
git config --list //列出所有配置，可能会有重复的变量名，因为有三个配置文件，这种时候git使用的是最后找到的变量，即最后一级别的配置
```

```
git config <key> //检查某一项的配置，显示的是生效的属性，也就是最后找到的
```

```console
git config --show-origin <key>//显示的是最后设置该属性的文件及值
```

## 三、配置用户名和邮件信息

Git每次提交都会写入这些信息，提交后不可更改

```console
git config --global user.name "用户名"
git config --global user.email 邮箱
```

如果想针对某个特定仓库指定不同的用户名和邮箱可以在该仓库目录下运行不带--global选项的命令

## 四、指定文本编辑器

不指定的话当git需要输入信息时会使用操作系统默认的文本编辑器，一般没有需求的话可以默认，可查看core.editor属性看看指定哪个编辑器。如果要指定，可看[Git - 设置与配置](https://git-scm.com/book/zh/v2/附录-C:-Git-命令-设置与配置#ch_core_editor)

## 五、git综合手册

3种获取git综合手册命令效果一样

```console
git help <verb>//如git help config是获得 git config 命令的手册
```

```console
git <verb> --help
```

```console
man git-<verb>
```

简明的输出：使用git <verb>  -h
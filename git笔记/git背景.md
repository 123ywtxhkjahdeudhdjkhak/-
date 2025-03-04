## 一、版本控制

版本控制是用来记录一个或若干个文件内容变化以便将来查阅某个特定版本修订情况的系统。

本地版本控制系统->集中化的版本控制系统->分布式版本控制系统

本地版本控制，早期习惯复制整个项目目录的方式保存不同的版本，或许还会加上备份时间来进行区别，好处是简单，坏处是容易混淆所在的工作目录，项目大的话还耗时，耗空间。后面开发了许多本地版本控制系统。最流行的事RCS，工作原理是在硬盘上保存补丁集，通过补丁集，可以计算出不同版本的文件内容。

<img src="image\Snipaste_2024-11-13_11-48-18.png" style="zoom:55%;" />

集中化的版本控制系统，解决不同主机的开发者协同工作问题。这类系统由单一的集中管理服务器，协同工作的人通过客户端访问，取出和提交更新。缺点：服务器出问题了，工作就进行不下去了，无法提交，并且由于项目保存在单一位置，如果没做好备份，服务器出问题可能会导致丢失数据，如文件的变更历史，本地版本控制也有该问题。

<img src="image\Snipaste_2024-11-13_11-56-09.png" style="zoom:75%;" />

分布式版本控制，这类系统不只是提取最新的文件，而是将整个代码仓库完整镜像下来，保留文件变更历史。如果服务器出问题，可以使用本地的仓库恢复。可以用固定的服务器，也可以使用开发的机器也可以充当服务器

<img src="image\Snipaste_2024-11-13_12-01-42.png" style="zoom: 50%;" />

## 二、Git工作原理

### 对待数据：直接记录快照，而非差异比较

其他系统大都保存文件变更内容，这类系统将存储的信息看做一组基本文件和每个文件随时间逐步累计的差异，存储着每个文件和初始版本的差异，也叫基于差异的版本控制。

<img src="image\Snipaste_2024-11-13_12-10-32.png" style="zoom:75%;" />

Git保存的是在每次提交或保存项目状态时会对当前的全部文件创建一个快照并创建指向该快照的索引，存储着随时间变化的快照。为了效率，对没有修改的文件不会重新存储该文件，而是保留一个链接指向之前存储的文件。

<img src="image\Snipaste_2024-11-13_12-10-48.png" style="zoom:75%;" />

Git中绝大部分操作都在本地进行，只需要访问本地文件和资源，一般不需要来自网络上其他计算机的资源。本地拥有完整的项目，有完整的提交历史，即使没有网络工作也能进行，等到有网络时在上传提交。

### Git保证完整性。

Git存储文件前都会计算校验和，计算校验和的算法是SHA-1散列（hash），由40个16进制字符组成的字符串，基于Git中文件内容和目录结构计算出来。之后通过校验和引用。这也意味着不会存在Git不知情的情况下修改文件，每次修改提交都会生成新的校验和，这个功能构建在Git，是Git必有的功能。当传送或某些操作导致的文件丢失，Git就会发现。Git中保存的信息都是以文件内容的哈希值来索引而不是文件名。

Git目前几乎所有的Git操作只会往Git仓库中添加数据（无论是删除、新增、修改文件），很难使用Git从仓库中删除数据，也就是Git几乎不会执行让数据无法恢复的操作，可以随意修改，但如果没提交的数据（工作区、暂存区没提交到仓库的数据）要注意。

### 三种状态

Git中被跟踪的文件有三种状态

已提交：数据已保存到本地数据库里

已暂存：数据已被标记，将之保存到下次提交的快照中

已修改：数据提交被修改但还没保存到数据库中也没被标记

由Git三种状态可将项目分为三个阶段：

工作区：对项目的某个版本独立提取出来的内容，即磁盘中.git同级目录下的文件，切换版本磁盘文件就会改变，但未跟踪的文件不受控制

暂存区（索引）：Git术语是索引，保存了下次将要提交的文件列表信息，一般在Git仓库中。执行add操作后文件会生成快照，在该文件列表中索引，但还未提交到本地Git仓。

Git目录（Git仓库）：保存项目元数据和对象数据库的地方。Git的核心，从其他计算机克隆仓库即复制该目录的数据。当执行commit操作后，就会根据暂存区的文件列表信息找到暂存区的快照文件并永久存储到Git目录。

<img src="image\Snipaste_2024-11-15_11-32-14.png" style="zoom:75%;" />

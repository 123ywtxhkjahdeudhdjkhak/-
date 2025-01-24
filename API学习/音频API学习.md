#### 1.SoundPool 

[SoundPool](https://developer.android.google.cn/reference/android/media/SoundPool)

SoundPool 类管理并播放应用程序的音频资源。

SoundPool 是一组声音样本的集合，可以从 APK 内部资源或文件系统中的文件加载到内存中。SoundPool 库使用 MediaCodec 服务将音频解码为原始 16 位 PCM 格式。这使得应用程序能够包含压缩流，而无需在播放期间承受解压缩带来的 CPU 负载和延迟。

音池中的声音应较短，因为它们会被预解码到内存中。每个解码后的声音在内部都被限制为 1 兆字节的存储空间，这大约相当于 44.1kHz 立体声下 5.6 秒（在较低的采样率或单声道通道掩码下，持续时间会相应更长）。如果解码后的音频声音超过每个声音 1 兆字节的存储空间，将会被截断。

除了低延迟播放外，SoundPool 还能管理同一时间渲染的音频流数量。在创建 SoundPool 对象时，maxStreams 参数会设置从单个 SoundPool 同时播放的最大音频流数量。SoundPool 会跟踪活动流的数量。如果超过最大流数量，SoundPool 将自动停止先前播放的流，首先基于优先级，然后在相同优先级内基于播放时间先后。限制最大流数量有助于控制 CPU 负载，并降低音频混音影响视觉或用户界面性能的可能性。

通过设置非零循环值可以循环播放声音。值为 -1 时，声音将无限循环。在这种情况下，应用程序必须显式调用 stop() 函数来停止声音。任何其他非零值都会使声音重复指定的次数，例如值为 3 时，声音总共播放 4 次。

播放速率也可以更改。播放速率的范围是 0.5 到 2.0。

优先级从低到高排列，即数值越大优先级越高。当调用 play() 时，如果活动流的数量会超过创建 SoundPool 时由 maxStreams 参数设定的值，就会用到优先级。在这种情况下，流分配器会停止优先级最低的流。如果存在多个优先级相同的流，它会选择最旧的流来停止。如果新流的优先级低于所有活动流的优先级，新声音将不会播放，play() 函数将返回一个为零的 streamID。

让我们来看一个典型的用例：一款游戏包含多个关卡。每个关卡都有其独有的音效，且这些音效仅在该关卡中使用。在这种情况下，游戏逻辑应在加载第一个关卡时创建一个新的 SoundPool 对象。关卡数据本身可能包含该关卡要使用的音效列表。加载逻辑会遍历音效列表，调用相应的 SoundPool.load() 函数。通常应在过程早期完成此操作，以便在需要播放之前有时间将音频解压缩为原始 PCM 格式。

一旦声音加载完毕且播放开始，应用程序可以通过调用 SoundPool.play() 来触发声音。正在播放的流可以暂停或恢复，应用程序还可以通过实时调整播放速率来改变音高，以实现多普勒效应或合成效果。

请注意，由于资源限制，流可能会被停止，因此 streamID 是对流的特定实例的引用。如果流被停止以让更高优先级的流播放，该流将不再有效。但是，应用程序可以调用 streamID 的方法而不报错。这可能有助于简化程序逻辑，因为应用程序无需关注流的生命周期。

在我们的示例中，当玩家完成关卡时，游戏逻辑应调用 SoundPool.release() 来释放所有正在使用的原生资源，然后将 SoundPool 引用设置为 null。如果玩家开始另一关卡，将创建一个新的 SoundPool，加载声音，然后继续播放。

嵌套类和接口：SoundPool.Builder、SoundPool.OnLoadCompleteListener

Public构造方法：

```
//该构造方法已弃用，推荐使用SoundPool.Builder代替
SoundPool(int maxStreams,int streamType,int srcQuality) 
```

Public方法：

```
//暂停所有活动流
final void autoPause() 
//恢复所有以前的活动流
final void autoResume() 
//从指定的APK资源中加载声音
int load(Context context,int resId,int Priority)
//从指定路径中加载
int load(String path,int priority) 
//从资源描述符中加载声音
int load(AssetFileDescriptor afd, int priority)
//从文件描述符中加载声音
int load(FileDescriptor fd, long offset, long length, int priority)
//暂停播放流
final void pause(int streamID)
//播放从sound ID的声音
final int play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
//释放SoundPool的资源
final void release()
//恢复播放流
final void resume(int streamID)
//设置环路模式
final void setLoop(int streamID, int loop)
//为onloadcompletellistener设置回调钩子
void setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener listener)
//更改流优先级
final void setPriority(int streamID, int priority)
//改变播放速率
final void setRate(int streamID, float rate)
//设置流音量
final void setVolume(int streamID, float leftVolume, float rightVolume)
//停止播放流
final void stop(int streamID)
//卸载sound ID的声音
final boolean unload(int soundID)
```



#### 2、SoundPool.Builder

[SoundPool.Builder](https://developer.android.google.cn/reference/android/media/SoundPool.Builder)

SoundPool对象的构建器类。

Public构造方法：

```
Builder()
```

Public方法：

```
//构造SoundPool对象
SoundPool build()
//设置AudioAttributes
SoundPool.Builder setAudioAttributes(AudioAttributes attributes)
//设置session ID给将要附加的SoundPool
SoundPool.Builder setAudioSessionId(int sessionId)
//设置SoundPool所属的上下文
SoundPool.Builder setContext(Context context)
//设置可以同时播放的最大同时流的数量
SoundPool.Builder setMaxStreams(int maxStreams)
```



#### 3、SoundPool.OnLoadCompleteListener

[SoundPool.OnLoadCompleteListener](https://developer.android.google.cn/reference/android/media/SoundPool.OnLoadCompleteListener)



#### 4、AssetFileDescriptor

[AssetFileDescriptor](https://developer.android.google.cn/reference/android/content/res/AssetFileDescriptor)

AssetManager中条目的文件描述符，提供了打开的FileDescriptor，可用于读取数据，以及文件中该条目数据的偏移量和长度。

嵌套类：AssetFileDescriptor.AutoCloseInputStream、AssetFileDescriptor.AutoCloseOutputStream

常量：long UNKNOWN_LENGTH：当未声明长度时，被使用的长度和AssetFileDescriptor(android.os.ParcelFileDescriptor, long, long)和getDeclaredLength()

公共字段：

```
static final Creator<AssetFileDescriptor> CREATOR
```

公共构造方法：

```
//从给定的值创建一个新的AssetFileDescriptor
AssetFileDescriptor(ParcelFileDescriptor fd, long startOffset, long length)
//从给定的值创建一个新的AssetFileDescriptor
AssetFileDescriptor(ParcelFileDescriptor fd, long startOffset, long length, Bundle extras)
```

公共方法：

```
//执行getParcelFileDescriptor().close()
void close()
//为这个asset资源创建并返回一个新的自动关闭输入流
FileInputStream	createInputStream()
//为这个asset资源创建并返回一个新的自动关闭输出流
FileOutputStream createOutputStream()
//一个位掩码，指示由Parcelable对象实例封送的特殊对象类型集。取值为0或CONTENTS_FILE_DESCRIPTOR。描述此Parcelable实例的封送表示中包含的特殊对象的种类。例如，如果对象将在包含文件描述符的输出中执行writeToParcel(android.os.Parcel, int),当前方法的返回值必须包含CONTENTS_FILE_DESCRIPTOR bit
int	describeContents()
//返回在构造AssetFileDescriptor时声明的实际字节数
long getDeclaredLength()
//返回可用于解释底层文件描述符的任何其他详细信息
Bundle getExtras()
//返回可用于读取文件中的数据的文件描述符
FileDescriptor getFileDescriptor()
//返回此asset资源条目数据的总字节数
long getLength()
//返回AssetFileDescriptor实例包含的ParcelFileDescriptor
ParcelFileDescriptor getParcelFileDescriptor()
//返回该资源项数据开始的字节偏移量
long getStartOffset()
//返回对象的字符串表示形式
String toString()
//把这个东西压成一个包裹
void writeToParcel(Parcel out, int flags)
```

继承的方法：

```
//实现java.io.Closeable接口的方法
//关闭此流并释放与之关联的所有系统资源
abstract void close()
//实现android.os.Parcelable接口的方法
abstract int describeContents()
abstract void writeToParcel(Parcel dest, int flags)
//实现java.lang.AutoCloseable接口的方法
//关闭此资源，放弃所有底层资源
abstract void close()
```

#### 5、FileDescriptor

[FileDescriptor](https://developer.android.google.cn/reference/java/io/FileDescriptor)



#### 6、AudioAttributes

[AudioAttributes](https://developer.android.google.cn/reference/android/media/AudioAttributes)



#### 7、AssetFileDescriptor.AutoCloseInputStream

[AssetFileDescriptor.AutoCloseInputStream](https://developer.android.google.cn/reference/android/content/res/AssetFileDescriptor.AutoCloseInputStream)



#### 8、AssetFileDescriptor.AutoCloseOutputStream

[AssetFileDescriptor.AutoCloseOutputStream](https://developer.android.google.cn/reference/android/content/res/AssetFileDescriptor.AutoCloseOutputStream)



#### 9、ParcelFileDescriptor

[ParcelFileDescriptor](https://developer.android.google.cn/reference/android/os/ParcelFileDescriptor)

被Parcel.readFileDescriptor()方法返回的文件修饰符

嵌套类：ParcelFileDescriptor.AutoCloseInputStream、ParcelFileDescriptor.AutoCloseOutputStream、ParcelFileDescriptor.FileDescriptorDetachedException

嵌套接口：ParcelFileDescriptor.OnCloseListener

常量：

```
//与open(File, int)一起使用，表示写入时追加到文件末尾
int	MODE_APPEND
//与open(File, int)一起使用，表示如果该文件不存在，则创建该文件
int MODE_CREATE
//与open(File, int)一起使用，表示以只读访问权限打开文件
int MODE_READ_ONLY
//与open(File, int)一起使用，表示以读写访问权限打开文件
int	MODE_READ_WRITE
//与open(File, int)一起使用，表示打开文件时擦除文件内容
int	MODE_TRUNCATE
//此常量在API级别19中已弃用。创建全局可读的文件是非常危险的，并且可能在应用程序中造成安全漏洞。这是强烈不鼓励的；相反，应用程序应该使用更正式的交互机制，例如ContentProvider, BroadcastReceiver, and Service。无法保证这种访问模式会一直保留在文件上，比如当文件经历备份与恢复操作时。
int MODE_WORLD_READABLE
//同上，API 19弃用，创建全局可写的文件危险
int	MODE_WORLD_WRITEABLE
//与open(File, int)一起使用，表示以只写访问打开文件
int	MODE_WRITE_ONLY
```

继承的接口常量：

```
//android.os.Parcelable
//和describeContents()一起使用的描述符位，表示Parcelable对象的平面化表示包含一个文件描述符。
int	CONTENTS_FILE_DESCRIPTOR
//和writeToParcel(Parcel, int)一起使用的标志，表示被写入的对象是一个返回值，这是一个函数的结果，比如Parcelable someFunction()、void someFunction(out Parcelable)或void someFunction(inout Parcelable)
int	PARCELABLE_WRITE_RETURN_VALUE
```

公共字段：

```
static final Creator<ParcelFileDescriptor> CREATOR
```

公共构造方法

```
//创建一个新的ParcelFileDescriptor来封装另一个描述符，该修饰符的获取：被Parcel.readFileDescriptor()方法返回的文件修饰符、或静态方法获取
ParcelFileDescriptor(ParcelFileDescriptor wrapped)
```

公共方法：

```
//将原始原生fd的所有权转移到新的ParcelFileDescriptor中
static ParcelFileDescriptor adoptFd(int fd)
//指示此ParcelFileDescriptor是否能够进行通信并检测远程错误/崩溃情况
boolean	canDetectErrors()
//如果管道或套接字对的另一端遇到错误或崩溃，则检测并抛出
void checkError()
//关闭ParcelFileDescriptor
void close()
//关闭ParcelFileDescriptor，通知所有peer在处理过程中发生错误
void closeWithError(String msg)
//创建两个结构为数据管道的ParcelFileDescriptor
static ParcelFileDescriptor[] createPipe()
//创建两个结构为数据管道的ParcelFileDescriptor
static ParcelFileDescriptor[] createReliablePipe()
//创建两个ParcelFileDescriptor，结构为一对相互连接的套接字
static ParcelFileDescriptor[] createReliableSocketPair()
//创建两个ParcelFileDescriptor，结构为一对相互连接的套接字
static ParcelFileDescriptor[] createSocketPair()
//描述此Parcelable实例的封送表示中包含的特殊对象的种类。
int	describeContents()
//返回这个ParcelFileDescriptor的原生fd int，并将其与这里的对象分离
int	detachFd()
//创建一个新的ParcelFileDescriptor，它是一个现有的FileDescriptor的副本
static ParcelFileDescriptor	dup(FileDescriptor orig)
//创建一个新的ParcelFileDescriptor，它是现有的FileDescriptor的副本
ParcelFileDescriptor dup()
//从指定的DatagramSocket创建一个新的ParcelFileDescriptor
static ParcelFileDescriptor	fromDatagramSocket(DatagramSocket datagramSocket)
//从原始的本地fd创建一个新的ParcelFileDescriptor
static ParcelFileDescriptor	fromFd(int fd)
//从指定的套接字创建一个新的ParcelFileDescriptor
static ParcelFileDescriptor	fromSocket(Socket socket)
//返回这个ParcelFileDescriptor的原生fd int
int	getFd()
//检索与该对象关联的实际FileDescriptor
FileDescriptor getFileDescriptor()
//返回表示该fd的文件的总大小，由stat()确定
long getStatSize()
//创建一个新的ParcelFileDescriptor来访问给定的文件
static ParcelFileDescriptor	open(File file, int mode)
//创建一个新的ParcelFileDescriptor来访问给定的文件
static ParcelFileDescriptor	open(File file, int mode, Handler handler, ParcelFileDescriptor.OnCloseListener listener)
//将表示文件模式的字符串（如“rw”）转换为适合open（file, int）使用的位掩码
static int	parseMode(String mode)
//返回对象的字符串表示形式
String toString()
//创建一个新的ParcelFileDescriptor包装一个已经打开的文件
static ParcelFileDescriptor wrap(ParcelFileDescriptor pfd, Handler handler, ParcelFileDescriptor.OnCloseListener listener)
//把这个东西压成一个包裹。如果Parcelable.PARCELABLE_WRITE_RETURN_VALUE在flags中设置，文件描述符将在副本被写入到Parcel后关闭。
void writeToParcel(Parcel out, int flags)

```



#### 10、Parce

[Parcel](https://developer.android.google.cn/reference/android/os/Parcel)



#### 11、Parcelable

[android.os.Parcelable](https://developer.android.google.cn/reference/android/os/Parcelable)



#### 12、ParcelFileDescriptor.AutoCloseInputStream

[ParcelFileDescriptor.AutoCloseInputStream](https://developer.android.google.cn/reference/android/os/ParcelFileDescriptor.AutoCloseInputStream)



#### 13、ParcelFileDescriptor.AutoCloseOutputStream

[ParcelFileDescriptor.AutoCloseOutputStream](https://developer.android.google.cn/reference/android/os/ParcelFileDescriptor.AutoCloseOutputStream)



#### 14、ParcelFileDescriptor.FileDescriptorDetachedException

[ParcelFileDescriptor.FileDescriptorDetachedException](https://developer.android.google.cn/reference/android/os/ParcelFileDescriptor.FileDescriptorDetachedException)



#### 15、ParcelFileDescriptor.OnCloseListener

[ParcelFileDescriptor.OnCloseListener](https://developer.android.google.cn/reference/android/os/ParcelFileDescriptor.OnCloseListener)



#### 16、DatagramSocket

[DatagramSocket](https://developer.android.google.cn/reference/java/net/DatagramSocket)

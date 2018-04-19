# Timer-Dialog  [![](https://jitpack.io/v/pranavj7Z/Timer-Dialog.svg)](https://jitpack.io/#pranavj7Z/Timer-Dialog)
Android Library that provides you a dialog box with an integrated timer along with a circular progress indicator.</br>
The circular progress bar indicates the countdown.

### Screenshots:

![](https://github.com/pranavj7Z/Timer-Dialog/blob/master/s.png?raw=true )
![](https://github.com/pranavj7Z/Timer-Dialog/blob/master/p.png?raw=true )
![](https://github.com/pranavj7Z/Timer-Dialog/blob/master/j.png?raw=true )
### Usage

#### Gradle
##### Step 1. Add the JitPack repository to your build file
~~~ xml
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
~~~

##### Step 2. Add the dependency
~~~ xml
dependencies {
	        compile 'com.github.pranavj7Z:Timer-Dialog:1.04'
}
~~~

#### Maven
##### Step 1. Add the JitPack repository to your build file
~~~ xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
~~~

##### Step 2. Add the dependency
~~~ xml
<dependency>
	    <groupId>com.github.pranavj7Z</groupId>
	    <artifactId>Timer-Dialog</artifactId>
	    <version>1.04</version>
	</dependency>
~~~

##### Java Code:

<pre><code>
 TimerDialog dialog = new TimerDialog(this);
        dialog.setTitle("Content Title");
        dialog.setAnimationEnable(true);
        dialog.setDuration(10000);
        dialog.setContentImage(getResources().getDrawable(R.mipmap.p));
        dialog.setPositiveListener("Ok", new TimerDialog.OnPositiveListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        })
        .setNegativeListener(getString(R.string.cancel), new TimerDialog.OnNegativeListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).show();
</code></pre>

<pre><code>
TimerDialog dialog = new TimerDialog(this);
        dialog.setTitle("Rewarded Video");
        dialog.setContentText("Watch a video to earn a life");
        dialog.setContentImage(getResources().getDrawable(R.mipmap.p));
        dialog.setAnimationEnable(true);
        dialog.setDuration(10000);
        dialog.setPositiveListener("watch video", new TimerDialog.OnPositiveListener() {
            @Override
            public void onClick(TimerDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).show();
    }
</code></pre>

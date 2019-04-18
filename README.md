# GridEditHelper

验证码格/密码格 输入帮助工具类，用于EditText自动移动焦点  


![](https://github.com/Edward-yfbx/GridEditHelper/blob/master/test.jpg)


#### Dependency
```
dependencies {
	implementation 'com.github.Edward-yfbx:GridEditHelper:1.0.0'
}
```
#### How to use
```

val list = arrayOf(editTxt1, editTxt2, editTxt3, editTxt4, editTxt5, editTxt6)

GridEditHelper().addViews(list).onInputResult {

   Toast.makeText(this, it, Toast.LENGTH_LONG).show()
}

```

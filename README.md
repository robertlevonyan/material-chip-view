# Material Chip View

Material Chip view. Can be used as tags for categories, contacts or creating text clouds.

## Setup

#### Gradle:

Add following line of code to your module(app) level gradle file

```java
    compile 'com.robertlevonyan.view:MaterialChipView:1.0.0'
```

#### Maven:

```xml
  <dependency>
    <groupId>com.robertlevonyan.view</groupId>
    <artifactId>MaterialChipView</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
  </dependency>
```

## Usage:

```xml
  <com.robertlevonyan.views.chip.Chip
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:chipText="@string/customTitle" />
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/sample.png) ![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/closable.png)



```
    app:backgroundColor="@color/customChipBackgroundColor"
    app:chipIcon="@drawable/customIcon"
    app:chipText="@string/customTitle"
    app:closeColor="@color/customCloseIconColor"
    app:hasIcon="true"
    app:selectable="true"
    app:selectedBackgroundColor="@color/customSelectedChipColor"
    app:textColor="@color/customTitleColor"
```

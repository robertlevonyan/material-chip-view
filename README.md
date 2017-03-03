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

## Usage

```xml
  <com.robertlevonyan.views.chip.Chip
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:chipText="@string/customTitle" />
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/sample.png) 

### Cutomizing Chip

```xml
    app:closable="true"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/closable.png)

```xml
    app:selectable="true"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/closable.png)

```xml
    app:hasIcon="true"
    app:chipIcon="@drawable/customIcon"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/hasIcon.png)

```xml
    app:hasIcon="true"
    app:chipIcon="@drawable/customIcon"
    app:closable="true"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/hasIconClosable.png)

```
    app:backgroundColor="@color/customChipBackgroundColor"
    app:closeColor="@color/customCloseIconColor"
    app:selectedBackgroundColor="@color/customSelectedChipColor"
    app:textColor="@color/customTitleColor"
```

![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/custom1.png), 
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/custom2.png)

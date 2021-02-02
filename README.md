# Material Chip View

|Material Chip view. Can be used as tags for categories, contacts or creating text clouds.|<img src="https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip.png"  width="500" />|
|----------------------------------------------------------------------------------------------|-----------|

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Chip%20View-yellow.svg?style=flat-square)](https://android-arsenal.com/details/1/5396) [![API](https://img.shields.io/badge/API-17%2B-yellow.svg?style=flat-square)](https://android-arsenal.com/api?level=14) [ ![Download](https://api.bintray.com/packages/robertlevonyan/maven/MaterialChipView/images/download.svg) ](https://bintray.com/robertlevonyan/maven/MaterialChipView/_latestVersion)

## Setup

#### Gradle:

Add following line of code to your module(app) level gradle file

```groovy
    implementation 'com.robertlevonyan.view:MaterialChipView:2.0.6'
```

#### Maven:

```xml
  <dependency>
    <groupId>com.robertlevonyan.view</groupId>
    <artifactId>MaterialChipView</artifactId>
    <version>2.0.6</version>
    <type>pom</type>
  </dependency>
```

## Usage

```xml
  <com.robertlevonyan.views.chip.Chip
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/customTitle" />
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip.jpg)

### Cutomizing Chip

```xml
    app:mcv_closable="true"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_closable.jpg)

```xml
    app:mcv_selectable="true"
```
|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_selectable_0.jpg)|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_selectable_1.jpg)|
|----------------------------------------------------------------------------------------------|-----------|

```xml
    app:mcv_chipIcon="@drawable/customIcon"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_icon.jpg)

```xml
    app:mcv_backgroundColor="@color/customChipBackgroundColor"
    app:mcv_closeColor="@color/customCloseIconColor"
    app:mcv_selectedBackgroundColor="@color/customSelectedChipColor"
    app:mcv_textColor="@color/customTitleColor"
```

|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_cloud_0.jpg)|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/Images/chip_cloud_1.jpg)|
|----------------------------------------------------------------------------------------------|-----------|

### Attributes

|Custom Atributes                 |Description                                 |
|---------------------------------|--------------------------------------------|
|`app:mcv_textColor`              |Custom color for text label                 |
|`app:mcv_backgroundColor`        |Custom background color                     |
|`app:mcv_selectedBackgroundColor`|Custom background color when selected       |
|`app:mcv_chipIcon`               |Icon resource for Chip                      |
|`app:mcv_closable`               |Chip with close button                      |
|`app:mcv_closeColor`             |Custom color for close button               |
|`app:mcv_selectable`             |Chip with selection button                  |
|`app:mcv_selectedTextColor`      |Custom color for label when selected        |
|`app:mcv_cornerRadius`           |Change the corner radius of your Chip       |
|`app:mcv_strokeSize`             |Add stroke to your Chip                     |
|`app:mcv_strokeColor`            |Choose a custom stroke color for your Chip  |
|`app:mcv_iconText`               |Instead of an image create icon from String |
|`app:mcv_iconTextColor`          |Select a color for your String icon         |
|`app:mcv_iconTextBackgroundColor`|Select a background color for String icon   |

### Setting Listeners

```kotlin
    val chip = findViewById(R.id.chip);
```
Chip click listener
```kotlin
    chip.setOnClickListener { view ->
         //Your action here...
    }
```

On Close button click listener
```kotlin
    chip.setOnCloseClickListener { view ->
         //Your action here...
    }

```

On Icon click listener
```kotlin
    chip.setOnIconClickListener { view ->
         //Your action here...
    }

```

On Select button click listener
```kotlin
    chip.setOnSelectClickListener { view: View, selected: Boolean ->
         //Your action here...
    }
```
### Customizing Chip from code

```kotlin
        chip.text = "My awesome Chip" // Set Chip label
        chip.chipTextColor = chipTextColor // Set Chip label color
        chip.chipBackgroundColor = chipBackgroundColor //Set custom background color
        chip.chipSelectedBackgroundColor = chipSelectedBackgroundColor //Set custom background color when selected
        chip.chipIcon = myIconDrawable //Set Icon Drawable for Chip
        chip.chipIconBitmap = myIconBitmap //Set Icon Bitmap for Chip
        chip.closable = true //Set Chip has close button
        chip.chipCloseColor = chipCloseColor //Set custom color for close button
        chip.chipSelectedCloseColor = chipSelectedCloseColor //Set custom color for close button on selected state
        chip.selectable = false //Set Chip has selection button
        chip.chipSelected = true // Set Chip selected
        chip.chipSelectedTextColor = chipSelectedTextColor //Set custom color for label when selected
        chip.cornerRadius = 5 // Set corner radius of your Chip
        chip.strokeSize = 2 // Set width of stroke
        chip.strokeColor = strokeColor // Set stroke color for your Chip
        chip.setIconText(text, iconTextColor, iconTextBackgroundColor); // Set Chip icon text, text color and background color (in case of Kotlin iconTextColor and iconTextBackgroundColor have default value of 0)
```

## Versions

#### 2.0.1 - 2.0.4

Minor changes, some missing parts pushed

### 2.0.0

New version of the library. Fully rewritten with Kotlin and AndroidX ready 🤩 

#### 1.2.1, 1.2.2

General bug fixed

#### 1.1.3

Selection option added

#### 1.1.2

Icon text issue fixed

#### 1.1.1

Updated version of Material Chip View:
    - the attribute names are changed to escape conflicts with other libs
    - added new features like customizable corner radius and stroke
    - recreation issue fixed for post Lollipop versions

#### 1.0.1

Recreation issues are fixed

### 1.0.0

First version of library

## Contact

- **Email**: me@robertlevonyan.com
- **Website**: https://robertlevonyan.com/
- **Medium**: https://medium.com/@RobertLevonyan
- **Twitter**: https://twitter.com/@RobertLevonyan
- **Facebook**: https://facebook.com/robert.levonyan
- **Google Play**: https://play.google.com/store/apps/dev?id=5477562049350283357

## Licence

```
    Material Chip View©
    Copyright 2017 Robert Levonyan
    Url: https://github.com/robertlevonyan/materialChipView

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```

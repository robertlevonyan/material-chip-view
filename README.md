# Material Chip View

|Material Chip view. Can be used as tags for categories, contacts or creating text clouds. This library can be an alternative for Material Chip from Jetpack library.|<img src="https://github.com/robertlevonyan/materialChipView/blob/master/images/chip.png"  width="500" />|
|----------------------------------------------------------------------------------------------|-----------|

|Android Arsenal|[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Chip%20View-yellow.svg?style=flat-square)](https://android-arsenal.com/details/1/5396) [![API](https://img.shields.io/badge/API-16%2B-yellow.svg?style=flat-square)](https://android-arsenal.com/api?level=16)|
|--------------------|--------------------|
|MaterialChipVersion|[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.view/MaterialChipView/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.view/MaterialChipView)|
|ComposeChipVersion|[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.compose/materialchip/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.compose/materialchip)|

## Setup

Add following line of code to your project level gradle file

```kotlin
  repositories {
    mavenCentral()
  }
```

Add following line of code to your module(app) level gradle file

#### Groovy:

```groovy
    implementation 'com.robertlevonyan.view:MaterialChipView:<MaterialChipVersion>'
```

#### Kotlin:

```kotlin
    implementation("com.robertlevonyan.view:MaterialChipView:$MaterialChipVersion")
```

#### Maven:

```xml
  <dependency>
    <groupId>com.robertlevonyan.view</groupId>
    <artifactId>MaterialChipView</artifactId>
    <version>MaterialChipVersion</version>
    <type>pom</type>
  </dependency>
```

### For Jetpack Compose version

```kotlin
    implementation("com.robertlevonyan.compose:materialchip:$ComposeChipVersion")
```

## Usage

```xml
  <com.robertlevonyan.views.chip.Chip
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/customTitle" />
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip.jpg)

### Cutomizing Chip

```xml
    app:mcv_closable="true"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_closable.jpg)

```xml
    app:mcv_selectable="true"
```
|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_selectable_0.jpg)|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_selectable_1.jpg)|
|----------------------------------------------------------------------------------------------|-----------|

```xml
    app:mcv_chipIcon="@drawable/customIcon"
```
![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_icon.jpg)

```xml
    app:mcv_backgroundColor="@color/customChipBackgroundColor"
    app:mcv_closeColor="@color/customCloseIconColor"
    app:mcv_selectedBackgroundColor="@color/customSelectedChipColor"
    app:mcv_textColor="@color/customTitleColor"
```

|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_cloud_0.jpg)|![alt text](https://github.com/robertlevonyan/materialChipView/blob/master/images/chip_cloud_1.jpg)|
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
|`app:mcv_selectedStrokeSize`     |Stroke size when selected                   |
|`app:mcv_selectedStrokeColor`    |Custom stroke color when selected  		   |
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
        chip.selectedStrokeSize = 2 // Set width of stroke when selected
        chip.selectedStrokeColor = selectedStrokeColor // Set stroke color for your Chip when selected
        chip.setIconText(text, iconTextColor, iconTextBackgroundColor); // Set Chip icon text, text color and background color (in case of Kotlin iconTextColor and iconTextBackgroundColor have default value of 0)
        chip.chipHorizontalPadding = 10 // Set horizontal padding for chip
```

### For Jetpack Compose version

```kotlin
setContent {
  Box(modifier = Modifier.fillMaxWidth()) {
    MaterialChip(
      text = "Some Text",
      chipIcon = ChipIconRes(icon = R.drawable.android), // ChipIconBitmap or ChipIconDrawable
      interaction = ChipInteraction.Closable, // Selectable, SelectableWithoutIcon or None
      strokeSize = 1.dp,
      selectedStrokeSize = 2.dp,
      strokeColor = Color.Red,
      selectedStrokeColor = Color.Blue,
      onSelectClick = { selected ->
        // your code here
      },
      onIconClick = {
        // your code here
      },
      onChipClick = {
        // your code here
      },
      onCloseClick = {
        // your code here
      }
    )
  }
}
```

Also there is a `MaterialChipGroup` available for Jetpack Compose version
```kotlin
setContent {
  Box(modifier = Modifier.fillMaxWidth()) {
    MaterialChipGroup(
      items = listOf("Chip1", "Chip2", "Chip3"),
      createTitle = { it }, //returns a String for the title
      initialSelectedElementIndex = 1, // default value is -1 (means nothing is selected)
      onItemSelected = { position, element ->
        // do something
      },
      // the rest is for Chip appearance
      interaction = ChipInteraction.Selectable,
      strokeSize = 1.dp,
      selectedStrokeSize = 2.dp,
      strokeColor = Color.Red,
      selectedStrokeColor = Color.Blue,
      backgroundColor = Color.Red,
      selectedBackgroundColor = Color.Blue,
    )
  }
}
```

## Versions

### 3.0.0

Jetpack Compose support added
Chip Group added for JetpackCompose

#### 2.2.1 - 2.2.7

Update to Java 11
SDK 31 ready
Minor updates

### 2.2.0

Migration to mavenCentral

#### 2.1.1 - 2.1.7

Minor changes, some missing parts pushed

#### 2.1.0

New update, now it is possible to make chip selectable without selection icon

#### 2.0.1 - 2.0.7

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

<a href="https://www.buymeacoffee.com/robertlevonyan">
  <img src="https://github.com/robertlevonyan/material-chip-view/blob/master/images/coffee.jpeg"  width="300" />
</a>

## Licence

```
    Material Chip View©
    Copyright 2022 Robert Levonyan
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

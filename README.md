<h1 align="center">مواقيت - عادات ومهام</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.xxx-a97bff"/></a>
  <img alt="Clean Architecture" src="https://img.shields.io/badge/Clean-Architecture-white"/>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

<p align="center">  
📝 Mawaqeet: To-do and Habits app. Is a modern Android app with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on Clean architecture and MVVM architecture. And also apply Modularization.
</p>
</br>

<p align="center">
<img src="https://user-images.githubusercontent.com/63272288/223731766-bc007d9b-b386-4587-9c26-654d850c5d21.png"/>
</p>

## Download
Go to the [Releases](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/releases) to download the latest APK. <br> 
Or simply get it from the play store here 👇

<a href='https://play.google.com/store/apps/details?id=com.moataz.mawaqeet'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="170px"/></a>

## App Images
Habits | Sections  
--- | --- |
![](https://i.ibb.co/ZN0N3sY/photo-2023-08-07-02-51-59.jpg) | ![](https://i.ibb.co/M6zT6n1/photo-2023-08-07-02-51-59-2.jpg) |

| Add habit | Edit habit |
 --- | --- | 
  ![](https://i.ibb.co/kJzZn2S/photo-2023-08-07-gf02-51-59.jpg) | ![](https://i.ibb.co/NNyW5nK/photo-2023-08-0ggg7-02-51-59.jpg) | 
  
 To-do | Add to-do
--- | --- |
![](https://i.ibb.co/crzXSRQ/photo-2023-08-07-02ffff-51-59.jpg) | ![](https://i.ibb.co/vZ2GYBr/photo-2023-08-07-02-fefe52-00.jpg)

## How it works

#### Project Architecture
This project follows the Clean Architecture structure and MVVM. The domain layer contains UseCases that encapsulate a single, specific task that is part of the application's business logic. The data layer implements the repository interface defined in the domain layer, providing a single source of truth for data. The UI layer uses all the components and classes related to the Android framework to get the data from the ViewModel layer and display it on the UI.

<img src="https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png" width="500" />

#### Structure (App Modules)
This project is use modularizing by feature. Every feature has it is one (data - domain - UI)

      + App <- The main module
      + common/ 
          + data <- The common database(Room) between the features
      + habits/
          + data <- implements the repository interface defined in the domain layer
            - local 
            - repositories
          + domain <- contains UseCases that encapsulate the business logic.
            - entities 
            - repository
            - usecases
          + UI <- uses MVVM with ViewModels exposing StateFlow that the UI consumes.
              - view
              - viewmodel
      + to-do <- Have the same things as habits module

#### Data and Dependenciy Flow:
This illustration from the clean architecture book shows the dependencies between the layers in an example app and the way data flows between them. (our app uses the same thing).

<img src="https://user-images.githubusercontent.com/63272288/224540200-813c1fd2-1416-4f2a-b404-ac9dc93b655f.jpg" width="500" />
              

## Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - StateFlow: For reactive style programming (from VM to UI). 
  - DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments
  - [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- Architecture
  - Clean Architecture (Data - Domain - UI)
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Used to handle gradle dependencies and config versions
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.

## TODO
- [X] Habits Screen
- [X] To-do Screen
- [ ] Ktlint or Detekt
- [ ] Use Jetpack Compose

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/MoatazBadawy)__ on GitHub for my next creations! 🤩

## Contributions 🤝
If you'd like to contribute, please take a look at the [PRs Welcome](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/labels) label on the issue tracker. <br> 
For new features, please open an issue to discuss it before beginning implementation.

# License
```xml
Designed and developed by 2023 Moataz Mohamed

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

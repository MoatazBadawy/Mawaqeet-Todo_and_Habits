<h1 align="center">مواقيت - عادات ومهام</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.9.xxx-a97bff"/></a>
  <img alt="Clean Architecture" src="https://img.shields.io/badge/Clean-Architecture-white"/>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

<p align="center">  
📝 Mawaqeet: Your Ultimate To-Do and Habits Companion! Get ready to immerse yourself in a sleek and intuitive user interface, where functionality meets style, built with the latest technologies and architectural paradigms.
</p>
</br>

<p align="center">
<img src="https://user-images.githubusercontent.com/63272288/223731766-bc007d9b-b386-4587-9c26-654d850c5d21.png"/>
</p>

## About the project
🚀 Is a modern Android app, built In different versions (Jetpack Coompose and XML). It's Powered by Hilt, Coroutines, Flow, Jetpack (Room, ViewModel, Navigation), Lottie, and Material Design. The app is structured based on Clean architecture and MVVM architecture, and modulized by feature.

#### 🔗 Download
Go to the [Releases](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/releases) to download the latest APK. Or simply get it from the Play Store 👇

<a href='https://play.google.com/store/apps/details?id=com.moataz.mawaqeet'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="170px"/></a>
## How it works

#### 🌟 Project format
We have built our application in different versions to make sure that everyone can learn from it. Whether you prefer the futuristic feel of Jetpack Compose or the classic reliability of XML, we've got you covered. Dive into a visually appealing design that enhances usability and captivates your senses.
> [!NOTE]
> You can obtain the different versions of the project through the following branches: <br>
> - Native (XML): [bransh/tree/native.xml](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/tree/native/xml)
> - Native (Jetpack Compose): [bransh/tree/native.compose](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/tree/native/jetpack_compose) 
> - KMM (iOS and Android): Coming Soon...

#### 🏗️ Project Architecture
Mawaqeet follows the Clean Architecture structure and MVVM to provide logical simplicity and maintainability. <br> 
- The domain layer contains UseCases that encapsulate a single, specific task that is part of the application's business logic. <br>
- The data layer implements the repository interface defined in the domain layer, providing a single source of truth for data. <br>
- The UI layer cnotance view and viewModel layers, it uses all the components and classes related to the Android framework to get the data from the ViewModel layer and display it on the device.

<img src="https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png" width="500" />

#### 🧩 Structure (App Modules)
With a modular architecture, the app is designed for scalability and maintainability. New features seamlessly integrate, and updates are a breeze, ensuring Mawaqeet evolves with you. 
> [!TIP]
> With modularizing by feature, Every feature has it is one (data - domain - UI).

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
              

## 🔧 Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) is Android's recommended by google modern toolkit for building native UI.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle awareness. Allows data to survive configuration changes such as screen rotations.
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
- [Lottie](https://lottiefiles.com/) Effortlessly bring the smallest, free, ready-to-use motion graphics for the web, app, social, and designs.
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Used to handle gradle dependencies and config versions
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.

## 🤝 Contributions
If you'd like to contribute, please take a look at the [PRs Welcome](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/labels) label on the issue tracker. <br> 
For new features, please open an issue to discuss it before beginning implementation.

## :heart: Find this repository useful?
Support it by joining __[stargazers](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/stargazers)__ for this repository. :star: also, __[follow me](https://github.com/MoatazBadawy)__ on GitHub for my next creations! 🤩

# License
```XML
Designed and developed by 2023 Moataz Mohamed

Licensed under the Apache License, Version 2.0 (the "License");
You may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

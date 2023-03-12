<h1 align="center">مواقيت - عادات ومهام</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.4.xxx-blue"/></a>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

![](https://user-images.githubusercontent.com/63272288/223731766-bc007d9b-b386-4587-9c26-654d850c5d21.png)

## Overview 
Mawaqeet is an Android app designed to help you build healthy and positive habits in an efficient and easy way. With this app, you'll receive daily notifications to remind you of your goals, and you can easily choose from a variety of departments and diverse customs to add to your daily routine.
- A great way to build new habits.
- Many departments and diverse customs.
- Choose your favorite section and add habits easily.
- Daily notifications to remind you of morning and evening habits

The app is available on Google Play, so you can try it out for yourself and start building a healthy and positive life today!.
<a href='https://play.google.com/store/apps/details?id=com.moataz.mawaqeet&hl=ar&gl=US'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="170px"/></a>
<br />

## Project Architecture:
This project follows the Clean Architecture structure and MVVM. The domain layer contains UseCases that encapsulate a single, specific task that is part of the application's business logic. The data layer implements the repository interface defined in the domain layer, providing a single source of truth for data. The UI layer uses all the components and classes related to the Android framework to get the data from the ViewModel layer and display it on the UI.


<img src="https://user-images.githubusercontent.com/63272288/224539374-26ea3e6b-ed81-4700-bbbe-640489aeca38.jpg" width="600" />

## Modules:
* **ui** - This module uses all the components and classes related to the Android framework to display data from the ViewModel layer on the UI.
* **data** - This Kotlin module implements the repository interface defined in the domain layer, providing a single source of truth for data. It can only access the domain module.
* **domain** - This Kotlin module contains UseCases that encapsulate a single, specific task that is part of the application's business logic. It cannot access any other module.
* **local** - This module handles data interaction with the local storage (Room DB).
* **presentation - This Android module uses MVVM with ViewModels exposing StateFlow that the UI consumes. The ViewModel does not know anything about its consumers. It can only access the domain module. (Note: this module is not included in this repo). I Included my viewmodel and it is mapper it is in the ui module.


<img src="https://user-images.githubusercontent.com/63272288/224540081-69478b9d-7b3c-4225-beff-94e9f9ce64bc.jpg" width="600" />

## Data and Dependenciy Flow:
This illustration from the clean architecture book shows the dependencies between the layers in a project and the way data flows between them.


<img src="https://user-images.githubusercontent.com/63272288/224540200-813c1fd2-1416-4f2a-b404-ac9dc93b655f.jpg" width="600" />


## Tech stack - Library:

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) - Flow is used to pass (send) a stream of data that can be computed asynchronously
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for dependency injection.
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Used to handle gradle dependencies and config versions
- JetPack
  - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state-holder,property%20of%20the%20MutableStateFlow%20class.) - For reactive style programming (from VM to UI). 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get lifecyle event of an activity or fragment and performs some action in response to change
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Used to create room db and store the data.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Used to bind UI components in your XML layouts.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.

## TODO
- [X] Habits Screen
- [X] Ktlint or Detekt
- [ ] Todo Screen
- [ ] Use Jetpack Compose

## Find this repository useful?
Support it by joining __[stargazers](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/stargazers)__ for this repository. <br>

## Contributions
If you'd like to contribute, please take a look at the [PRs Welcome](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/labels) label on the issue tracker. For new features, please open an issue to discuss it before beginning implementation.

<h1 align="center">مواقيت - عادات ومهام</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.4.xxx-blue"/></a>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

<img src="https://user-images.githubusercontent.com/63272288/223731766-bc007d9b-b386-4587-9c26-654d850c5d21.png" width="700" />

## Overview 
An app that provides you with the tools and resources to build healthy and positive habits in an efficient and easy way. You will get daily notifications to achieve your goals. Try the app now and embark on the journey of building a healthy and positive life.
- A great way to build new habits.
- Many departments and diverse customs.
- Choose your favorite section and add habits easily.
- Daily notifications to remind you of morning and evening habits

<a href='https://play.google.com/store/apps/details?id=com.moataz.mawaqeet&hl=ar&gl=US'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="170px"/></a>
<br />

## Project Architecture:
This project is Follow Clean Architecture Structure and MVVM. I Found Most of people sayes this is also use MVI because of the existence of the domain and its interaction with the project. So I'll start researching and reading into this topic and will update the repo after that. Anyway, let's get to the reliability of the project.


<img src="https://user-images.githubusercontent.com/63272288/224539374-26ea3e6b-ed81-4700-bbbe-640489aeca38.jpg" width="700" />

## Modules:
* **ui** - It uses all the components and classes releated to Android Framework. It gets the data from presentation layer and shows on UI. (**access all the modules**)
* **data** - The data layer implements the repository interface that the domain layer defines. This layer provide a single source of truth for data. (Kotlin module that **can only access domain module**)
* **local** - Handles data interacting with the local storing (Room DB). (**can only access data module**)
* **domain** - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. (Kotlin module that **cannot access any other module**)
* **presentation** - MVVM with ViewModels exposing LiveData that the UI consume. The ViewModel does not know anything about it's consumers. (Android module that **can only access domain module**)


<img src="https://user-images.githubusercontent.com/63272288/224540081-69478b9d-7b3c-4225-beff-94e9f9ce64bc.jpg" width="700" />

## Data and Dependenciy Flow:
This illustration from the clean architecture book shows the dependencies between the layers in a project and the way data flows between them.


<img src="https://user-images.githubusercontent.com/63272288/224540200-813c1fd2-1416-4f2a-b404-ac9dc93b655f.jpg" width="700" />


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
If you'd like to contribute, please take a look at the PRs Welcome label on the issue tracker. For new features, please open an issue to discuss it before beginning implementation.

# Eduskunta App

Eduskunta App is a simple Android application that demonstrates using navigation, Retrofit, and Room in a Kotlin + Jetpack Compose project. The app shows a list of Finnish Parliament members and allows viewing their details.

## Features
- Two-level browsing: Party → Members list
- Member detail screen with photo, party, constituency, minister status, and Twitter handle
- Notes per member with +/- indicator and text
- Offline support with **Retrofit** and **Room database**
- Smooth navigation between screens using **Jetpack Compose Navigation** 
- Simple and responsive UI

## Tech Stack
-	**Kotlin**
-	**Jetpack Compose** (UI)
-	**Retrofit** (API calls)
-	**Room** (Local database)
-	**ViewModel + StateFlow** (State management)

## Screenshots

<p align="center">
<img src="./res/Screenshot_20260315_184632.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/><img src="./res/Screenshot_20260315_184640.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/>
</p>

<p align="center">
<img src="./res/Screenshot_20260315_184653.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/><img src="./res/Screenshot_20260315_183009.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/>
</p>

<p align="center">
<img src="./res/Screenshot_20260315_183405.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/><img src="./res/Screenshot_20260315_183524.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/>
</p>

<p align="center">
<img src="./res/Screenshot_20260315_185019.png" width="35%" alt="Android Mobile App Screen - Eduskunta Navigations screen"/>
</p>

## Video link



## Android App structure:

```text
EduskuntaApp/
├─ data/
│  ├─ api/        <-- Retrofit service
│  └─ db/         <-- Room database, Entity & DAO
├─ repositories/ <-- OfflineMemberRepository, OfflineNoteRepository <-- Handles data from DB 
├─ ui/
│  ├─ screens/    <-- MainScreen, PartyListScreen, MemberListScreen, MemberDetailScreen
│  └─ viewmodel/  <-- ViewModel + StateFlow
├─ EduskuntaApplication.kt
├─ MainActivity.kt
└─ Navigation.kt
```

### Notes
-	The app fetches member data from a remote API using Retrofit and stores it locally in Room.
-	The UI is fully scrollable and responsive.
-	Navigation is implemented using Jetpack Compose Navigation.
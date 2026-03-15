# Eduskunta App

Eduskunta App is a simple Android application that demonstrates using navigation, Retrofit, and Room in a Kotlin + Jetpack Compose project. The app shows a list of Finnish Parliament members and allows viewing their details.

## Screenshots

## Features
- List of members with basic info (name, party, constituency)
- Member detail screen with photo, party, constituency, minister status, and Twitter handle
- Offline support with Room database
- Smooth navigation between screens using Jetpack Compose Navigation 
- Simple and responsive UI

## Tech Stack
-	**Kotlin**
-	**Jetpack Compose** (UI)
-	**Retrofit** (API calls)
-	**Room** (Local database)
-	**ViewModel + StateFlow** (State management)

Android App structure:

```text
EduskuntaApp/
├─ data/
│  ├─ api/        <-- Retrofit service
│  └─ db/         <-- Room database & DAO
├─ repository/    <-- Handles data from API & DB
├─ ui/
│  ├─ screens/    <-- MainScreen, MemberDetailScreen
│  └─ components/ <-- Reusable UI components
└─ viewmodel/     <-- State management using ViewModel + StateFlow
```

Notes
-	The app fetches member data from a remote API using Retrofit and stores it locally in Room.
-	The UI is fully scrollable and responsive.
-	Navigation is implemented using Jetpack Compose Navigation.
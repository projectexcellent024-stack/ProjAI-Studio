# ProjAI Studio

ProjAI Studio is an Android mobile application built with Kotlin and Jetpack Compose. This README documents the supported toolchain, build commands, and troubleshooting steps for a clean build.

## Quality note
The project uses KSP code generation for Hilt, Room, and Moshi. A full build requires the Android SDK, JDK 17, and the included Gradle wrapper.

## Requirements
- JDK: Java 17. AGP 9.3 requires JDK 17.
- Android SDK:
  - Android SDK Platform 37 (`compileSdk = 37`)
  - Android SDK Build-Tools
  - Android Platform-Tools (`adb`)
  - Emulator system image for API 37, or a compatible physical device
- Gradle: use the included Gradle wrapper. The project uses Gradle 9.5.0.
- Recommended IDE: Android Studio Quail 3 or newer. AGP 9.3 is compatible with current Android Studio releases that support it.

## Environment variables
- `ANDROID_SDK_ROOT` or `ANDROID_HOME` may point to your Android SDK installation when the SDK is not managed automatically by Android Studio.

## Quick setup
1. Install JDK 17 and Android Studio.
2. Install Android SDK Platform 37 and the required build tools using Android Studio SDK Manager.
3. Connect an Android device or create an API 37 emulator.
4. Open the repository root in Android Studio and allow Gradle Sync to complete.

## Build & run
From the repository root:

### Windows
```text
gradlew.bat clean build
gradlew.bat :app:assembleDebug
gradlew.bat :app:installDebug
```

### macOS / Linux
```text
./gradlew clean build
./gradlew :app:assembleDebug
./gradlew :app:installDebug
```

The first build can take longer because KSP generates Hilt, Room, and Moshi sources.

## Run from Android Studio
1. Open the repository root.
2. Confirm Gradle uses JDK 17.
3. Sync the project.
4. Select an emulator or connected device.
5. Run the `app` configuration.

## Troubleshooting
- **JDK mismatch:** verify that Android Studio's Gradle JDK is 17 and that `java -version` reports JDK 17 when using the terminal.
- **KSP errors:** run `gradlew.bat clean assembleDebug` and confirm the KSP plugin version matches Kotlin 2.2.10.
- **Missing SDK:** install Android SDK Platform 37 and Platform-Tools from SDK Manager.
- **adb not found:** install Platform-Tools and ensure `adb` is available from the Android Studio terminal.
- **Slow builds:** Gradle configuration cache is enabled and the daemon heap is capped at 2 GB in `gradle.properties`.

## Testing
Run JVM tests with:

```text
gradlew.bat test
```

Run instrumented tests with a connected device/emulator:

```text
gradlew.bat connectedAndroidTest
```

## Security
Do not commit API keys, passwords, tokens, or other secrets. External API credentials should be injected through a secure runtime mechanism such as Gradle properties for local development or Android Keystore-backed storage where appropriate.

## Project configuration
- Application ID: `com.example.projaistudio`
- `minSdk = 26`
- `targetSdk = 37`
- `compileSdk = 37`
- Java source/target compatibility: 11
- Gradle wrapper: 9.5.0
- Android Gradle Plugin: 9.3.1
- Kotlin: 2.2.10
- KSP: 2.2.10-2.0.2

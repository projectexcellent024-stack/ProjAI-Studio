# ProjAI Studio

ProjAI Studio is an Android mobile application (Kotlin + Jetpack Compose). This README documents how to build, run, and test the app from a fresh clone and lists required SDKs and toolchain versions.

## Quality note
This project uses KSP code generators (Hilt, Room, Moshi). A full build requires the Android SDK, JDK, and Gradle wrapper. Follow these instructions to get a clean build and run the app.

## Requirements
- JDK: Java 11 (JDK 11). Ensure `java -version` reports a 11.x JVM.
- Android SDK:
  - Android SDK Platform 37 (compileSdkVersion = 37)
  - Android SDK Build-Tools (matching latest available for your platform)
  - Android Platform-Tools (adb)
  - Emulator system image for API 37 (x86_64 recommended) or a physical device running a compatible Android version
- Gradle: use the included Gradle wrapper (`./gradlew` / `gradlew.bat`). Do not install a system Gradle unless you know how to match versions.
- Recommended IDE: Android Studio (Arctic Fox / newer). Open the project with Android Studio so it can auto-install required SDK components.

## Environment variables
- ANDROID_SDK_ROOT or ANDROID_HOME should point to your SDK installation if not using Android Studio to manage the SDK.

## Quick setup (Windows / macOS / Linux)
1. Install JDK 11 and Android Studio.
2. Install Android SDK Platform 37 and required build-tools via Android Studio SDK Manager, or via command line:

   sdkmanager "platforms;android-37" "platform-tools" "build-tools;37.0.0"

   (If `sdkmanager` is not on PATH, run it from the SDK `tools/bin` or use Android Studio.)

3. Install an emulator image (x86_64) for API 37 (or nearest available). Example via sdkmanager:

   sdkmanager "system-images;android-37;google_apis;x86_64"

   Then create an AVD (example):

   avdmanager create avd -n ProjAI_API_37 -k "system-images;android-37;google_apis;x86_64" -d pixel

4. Start the emulator via Android Studio AVD Manager or:

   emulator -avd ProjAI_API_37

## Build & run (commands)
From the repository root:

- Unix / macOS:
  ./gradlew clean build
  ./gradlew :app:assembleDebug
  ./gradlew :app:installDebug    # installs to a connected device or running emulator

- Windows (PowerShell / CMD):
  gradlew.bat clean build
  gradlew.bat :app:assembleDebug
  gradlew.bat :app:installDebug

Notes:
- First build may trigger KSP processors (Hilt/Room/Moshi) and will take longer.
- If the install step fails, ensure an emulator or device is connected (`adb devices`).

## Run from Android Studio
1. Open the project (select the repository root) in Android Studio.
2. Let Gradle sync and allow Android Studio to download any missing SDK components.
3. Select a device/emulator and click Run (green ▶). Android Studio handles install and launch.

## Common issues & troubleshooting
- KSP / annotation processor errors: Clean and rebuild (`./gradlew clean assembleDebug`) and verify KSP plugin is applied in `app/build.gradle.kts`.
- Java version mismatch: Ensure Android Studio / command-line Java uses JDK 11. On some systems Android Studio bundles a compatible JDK; command-line `java` may be different.
- Missing SDK or platforms: Use SDK Manager in Android Studio or `sdkmanager` to install `platforms;android-37` and a system image.
- adb not found: Ensure `platform-tools` are installed and `adb` is on PATH or use the Android Studio terminal.
- Slow Gradle/KSP builds: Increase Gradle daemon memory in `gradle.properties` (already set to -Xmx2048m). Use `org.gradle.configuration-cache=true` to speed repeated builds.

## Testing
- Unit tests and Android instrumented tests are configured in the `app` module (see `app/build.gradle.kts`). Run:

  ./gradlew test    # JVM unit tests
  ./gradlew connectedAndroidTest  # instrumentation tests on a device/emulator

## Notes for maintainers
- The project targets compileSdk 37 and minSdk 26 (see app/build.gradle.kts). Verify any new libraries are compatible with these SDK levels.
- Do not commit API keys or secrets to the repo. If a feature requires external API access, inject secrets through Gradle properties or Android keystore at runtime.

---

If you want, I can add a short developer-facing HOWTO section that documents common Gradle tasks, or I can open a PR with the README added — tell me which you prefer.

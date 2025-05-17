# How project was done

- Project was done using KMP.
- It's using Koin as dependency injection.
- It's using SKIE to wrap the ViewModel for IOS.
- It's using Navigation to navigate between screens
- It's using a Serialization plugin to handle data classes (especially data coming from an API in the future).
- It's Coroutines to handle asyncronous work.
- A MVVM pattern was my architectural choice.
- 

# Future impovements
- Consume the data from an API by using Ktor.
- Create a loadind state for both Android and IOS, so we could change the unit tests to handle this states.
- With the API created we could test the Repository as well.
- Handle cache data for images or even use the Database to prevent calling API network multiple times
  
# Here's a video showing the app running on a physical Samsung s20 FE
![Screenshot_20250517_211806_KMP Take Home Assignment](https://github.com/user-attachments/assets/c4af6326-dcf1-47a0-a102-28a3eac56afb)
![Screenshot_20250517_211815_KMP Take Home Assignment](https://github.com/user-attachments/assets/0231b798-0d76-4916-9d6a-f3e218fa4841)
![Screenshot_20250517_211823_Maps](https://github.com/user-attachments/assets/3d69da4c-bfff-469a-85f9-d0e3af7edfa3)
![Screenshot_20250517_211831_Chrome](https://github.com/user-attachments/assets/30ea6f49-ab57-45b5-bd88-ac662cf4e5de)

# Here's a video showing the app running on a physical iPhone 15 pro max
![IMG_0521](https://github.com/user-attachments/assets/3cff0a24-cb61-42de-aacc-ecaa809ecaa2)
![IMG_0522](https://github.com/user-attachments/assets/04341129-e151-4476-b6fa-fa2a525457b2)
![IMG_0523](https://github.com/user-attachments/assets/092d93a4-e311-4644-b222-464d6a0fda80)
![IMG_0524](https://github.com/user-attachments/assets/9674c792-3894-40d9-9d9e-cf378ae9ae58)





# Bimm KMP Take Home Assignment - Base repo

IMPORTANT: Don't create a PR to this repo with your solution.

# Assignment instructions
You should have received a separate description for your assignment. If you haven't, please contact the member of the hiring team you're in touch with.

Also please verify you have received any files (JSON, xml, csv, txt, images, depending on the assignment) or any API addresses you assignment mentions. Those vary with the position you're applying to.


# Assignment deliver instructions
[Fork](https://github.com/reul/bimm-kmp-challenge-base/fork) or clone this repo and add the necessary code to complete your assignment, then email us with the link of the forked repo or to a zip or tarball file we can download and evaluate.

It's very important that we can run the project and see it being executed ourselves, so don't make it dependent on any private repos or environment variables we might not have access to or, if you must, include an APK in the tarball or in a GitHub release in your fork. Don't forget to include or stage any other dependencies (like json files).

## Build instructions
This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


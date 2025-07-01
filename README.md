
# 👤 Android Sign-Up App

An Android application built using **MVVM architecture**, **Jetpack Navigation**, and **DataBinding**, focused on creating a modern and clean sign-up experience. This app includes input validations, camera capture for profile image, and shared ViewModel across fragments.

---

## 📲 Features

- 🔐 Sign-up flow using name, email, and password
- 📸 Capture profile image via camera and set avatar
- ✅ Input validation with error handling
- 📦 MVVM pattern with ViewModel and Repository layers
- 💡 Shared ViewModel across fragments (multi-step sign-up)
- 📍 Runtime permissions handling for camera
- 📌 Custom BindingAdapters for XML reusability
- 🧩 Jetpack Navigation Component for flow management
- 🧠 Dagger 2 for dependency injection

---

## 🧱 Architecture

```

MVVM (Model - View - ViewModel)

```

- `View`: XML layout + Fragment
- `ViewModel`: UI logic, form validation, LiveData
- `Model`: User data model, Repository
- `DI`: Dagger 2 for injecting ViewModelFactory, Repository, etc.

---

## 📂 Project Structure

```

📁 data/
└── model/User.kt
└── repository/AuthRepository.kt

📁 di/
└── AppComponent.kt
└── AppModule.kt
└── ViewModelFactory.kt

📁 ui/
└── signup/
├── SignUpFragment.kt
├── ProfileImageFragment.kt
└── SharedSignUpViewModel.kt

📁 utils/
└── ValidationUtils.kt
└── BindingAdapters.kt
└── PermissionUtils.kt

````

---

## 🛠️ Tech Stack

| Layer         | Tech                        |
|---------------|-----------------------------|
| UI            | XML + DataBinding           |
| Navigation    | Jetpack Navigation          |
| ViewModel     | Lifecycle ViewModel         |
| State         | LiveData                    |
| Validation    | Kotlin + Utility Classes    |
| Permissions   | AndroidX + Utils            |
| DI            | Dagger 2                    |
| Media         | Camera Intent + Glide       |

---

## 🧪 Input Validation

| Field     | Rules                                       |
|-----------|---------------------------------------------|
| Name      | Not empty, minimum 3 characters             |
| Email     | Must match email format                     |
| Password  | At least 6 characters, 1 number & 1 letter  |

Validation logic lives in `ValidationUtils.kt`, and the results are exposed via LiveData from the `SharedSignUpViewModel`.

---

## 🖼️ Profile Picture Capture

- Requests camera permission at runtime
- Opens native camera using `Intent(MediaStore.ACTION_IMAGE_CAPTURE)`
- Saves and sets image via Glide in a custom `BindingAdapter`

```kotlin
@BindingAdapter("profileImage")
fun loadProfileImage(view: ImageView, uri: Uri?) {
    uri?.let {
        Glide.with(view.context).load(uri).into(view)
    }
}
````

---

## 🔄 Shared ViewModel

Used across multiple fragments for a multi-step sign-up flow.

```kotlin
val viewModel: SharedSignUpViewModel by activityViewModels { viewModelFactory }
```

---

## 🔐 Permissions

* Handled with Activity Result APIs
* Runtime permission check for camera
* Shown only if not granted already

---

---

## 🧪 Testing Ideas

* ViewModel unit tests for validation logic
* Fragment UI tests with Espresso
* Permission behavior tests

---


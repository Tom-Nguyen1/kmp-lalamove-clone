<div align="center">

# 🚚 Lalamove Clone

### Kotlin Multiplatform + Compose Multiplatform

**1 Codebase → 3 Platforms: Android • iOS • Desktop**

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.20-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.10.1-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android%20|%20iOS%20|%20Desktop-orange.svg)]()

</div>

---

## 🎯 Giới thiệu

Đây là dự án clone UI ứng dụng **Lalamove** — nền tảng giao hàng hàng đầu Đông Nam Á — được xây dựng hoàn toàn bằng **Kotlin Multiplatform (KMP)** và **Compose Multiplatform**. Công nghệ cho phép viết 1 lần, chạy trên nhiều nền tảng.

> 💡 **Mục đích**: Học tập, nghiên cứu và chia sẻ kiến thức về cross-platform development với Kotlin. Dự án này là sản phẩm của **TechNext Solutions** — đội ngũ chuyên phát triển ứng dụng đa nền tảng với công nghệ hiện đại.

---

## ✨ Tính năng nổi bật

| Feature | Mô tả |
|---------|-------|
| 🎨 **Premium UI** | Giao diện cao cấp với Material Design 3 Expressive |
| 🎠 **Promo Carousel** | Banner slider tự động với page indicators |
| 🗺️ **Route Card** | Quản lý điểm đón/trả hàng với dotted line connectors |
| 🚗 **Vehicle Selector** | Chọn loại xe với animated selection states |
| 🎭 **VIPER Architecture** | Kiến trúc VIPER chuẩn enterprise |
| 🧭 **Custom Navigation** | Animated slide/fade transitions giữa các màn hình |
| 🎨 **Canvas Icons** | Icons tự vẽ bằng code, không cần assets |
| 🌗 **Dark Mode** | Hỗ trợ Light/Dark theme tự động |
| 🧱 **Lalamove Theme** | Hệ thống theme chuẩn brand identity (Orange palette) |

---

## 🛠️ Tech Stack

```
📦 Kotlin Multiplatform (KMP) 2.2.20
├── 🎨 Compose Multiplatform 1.10.1
├── 🎯 Material Design 3 Expressive
├── 🏗️ VIPER Architecture
├── 📦 KotlinX Serialization 1.8.0
├── 🔄 Lifecycle ViewModel Compose
└── 🎭 AnimatedContent Navigation
```

---

## 🚀 Cài đặt & Chạy

### Yêu cầu

- JDK 17+
- Android Studio Ladybug (2024.2.1) trở lên
- Kotlin 2.2+

### Clone project

```bash
git clone https://github.com/salem98/kmp-lalamove-clone.git
cd kmp-lalamove-clone
```

### Chạy Android

```bash
./gradlew :composeApp:installDebug
```

### Chạy Desktop

```bash
./gradlew :composeApp:run
```

### Chạy iOS

> Yêu cầu macOS với Xcode. Mở project trong Android Studio → chọn iOS target → Run.

---

## 📂 Cấu trúc dự án

```
composeApp/
├── src/
│   ├── commonMain/              # 🎯 Code dùng chung cho tất cả platforms
│   │   └── kotlin/
│   │       └── com/lalamove/clone/
│   │           ├── core/
│   │           │   └── viper/         # ViewState sealed class
│   │           ├── feature/
│   │           │   ├── home/          # 🏠 Màn hình chính
│   │           │   │   ├── entity/    # Data models
│   │           │   │   ├── interactor/# Business logic
│   │           │   │   ├── presenter/ # ViewModel
│   │           │   │   └── view/      # Composables
│   │           │   ├── drawer/        # 📋 Side navigation
│   │           │   ├── wallet/        # 💰 Ví điện tử
│   │           │   ├── inbox/         # 📬 Thông báo & Khuyến mãi
│   │           │   ├── orders/        # 📦 Lịch sử đơn hàng
│   │           │   └── settings/      # ⚙️ Cài đặt
│   │           ├── navigation/        # Routes & Navigation
│   │           ├── theme/             # Colors, Typography, Shapes
│   │           └── App.kt            # Root Composable
│   │
│   ├── androidMain/             # 📱 Code riêng Android
│   ├── iosMain/                 # 🍎 Code riêng iOS
│   └── desktopMain/             # 🖥️ Code riêng Desktop
```

---

## 🎨 UI Components

### Đã implement:

- ✅ **PromoCarousel** — Auto-sliding banner với animated page indicators
- ✅ **RouteCard** — Quản lý pickup/drop-off với dotted line connectors
- ✅ **VehicleList** — Chọn loại xe với animated selection & Canvas icons
- ✅ **AdditionalServicesSection** — Dịch vụ bổ sung với pricing
- ✅ **HomeScreen** — Trang chính tích hợp tất cả components
- ✅ **DrawerContent** — Side menu với profile header & region selector
- ✅ **WalletScreen** — Hiển thị số dư, top-up & payment methods
- ✅ **InboxScreen** — Tabs Notifications/Promotions với gradient cards
- ✅ **OrdersScreen** — Lịch sử đơn hàng với status badges
- ✅ **SettingsScreen** — Cài đặt theo nhóm với log-out

### Đang phát triển:

- 🔲 **DeliveryFormScreen** — Form tạo đơn giao hàng
- 🔲 **MyDriversScreen** — Danh sách tài xế yêu thích
- 🔲 **RewardsScreen** — Chương trình tích điểm
- 🔲 **CouponShopScreen** — Cửa hàng coupon
- 🔲 **HelpCenterScreen** — Trung tâm hỗ trợ

---

## 🏗️ Kiến trúc VIPER

Dự án áp dụng kiến trúc **VIPER** được điều chỉnh cho Compose Multiplatform:

```
┌─────────────┐     ┌─────────────┐     ┌──────────────┐
│    View      │────▶│  Presenter  │────▶│  Interactor  │
│ (Composable) │◀────│ (ViewModel) │◀────│ (UseCase)    │
└─────────────┘     └─────────────┘     └──────────────┘
       │                    │                    │
       │              ┌─────────────┐     ┌──────────────┐
       └─────────────▶│   Router    │     │   Entity     │
                      │ (AppRoute)  │     │ (Data Class) │
                      └─────────────┘     └──────────────┘
```

| Layer | Mapping | Mô tả |
|-------|---------|-------|
| **View** | `@Composable` | Hiển thị UI, nhận sự kiện từ người dùng |
| **Presenter** | `ViewModel` | Quản lý state, xử lý logic UI |
| **Interactor** | Business Logic | Chứa use cases, gọi repositories |
| **Entity** | `data class` | Models dữ liệu, serializable |
| **Router** | `AppRoute` sealed interface | Định nghĩa routes, điều hướng |

---

## 📚 Học được gì từ dự án này?

1. 🏗️ **Kotlin Multiplatform Architecture** — Cách tổ chức code shared/platform-specific
2. 🎨 **Compose Multiplatform** — Xây dựng UI declarative cho multi-platform
3. 🎭 **VIPER Pattern** — Áp dụng kiến trúc enterprise cho mobile apps
4. ✨ **Custom Animations** — AnimatedContent, AnimatedVisibility, tween transitions
5. 🎨 **Canvas Drawing** — Vẽ vehicle icons/graphics bằng code
6. 🎨 **Theme System** — Xây dựng Design System hoàn chỉnh với Material 3
7. 🧭 **Custom Navigation** — Xây dựng navigation stack tùy chỉnh cho KMP

---

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Hãy:

1. ⭐ Star repo nếu thấy hữu ích
2. 🍴 Fork và tạo Pull Request
3. 🐛 Report bugs qua Issues
4. 💡 Đề xuất features mới

---

## 📞 Liên hệ

**TechNext Solutions** — Đội ngũ phát triển ứng dụng đa nền tảng

Có thắc mắc gì về KMP/Compose Multiplatform? Liên hệ qua:

- 🌐 GitHub: [@salem98](https://github.com/salem98)

---

## 📄 License

```
MIT License

Copyright (c) 2026 TechNext Solutions

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

<div align="center">

Made with ❤️ and 🚚 by **TechNext Solutions** in Vietnam

*Nếu dự án này giúp ích cho bạn, hãy cho mình một ⭐ nhé!*

</div>

# Flutter Project Architecture Guideline with Code Example

## Project Structure
Organize the project into directories to separate concerns and ensure maintainability:
```lib
|____ui
|    |____core
|    |    |____ui
|    |    |    |____<shared widgets>
|    |    |____themes
|    |____auth
|    |    |____view_model
|    |    |    |____auth_view_model.dart
|    |    |____widgets
|    |    |    |____auth_screen.dart
|    |____posts
|    |    |____view_model
|    |    |    |____posts_view_model.dart
|    |    |____widgets
|    |    |    |____posts_screen.dart
|    |    |    |____post_item.dart
|    |____profile
|    |    |____view_model
|    |    |    |____profile_view_model.dart
|    |    |____widgets
|    |    |    |____profile_screen.dart
|____domain
|    |____models
|    |    |____user.dart
|    |    |____post.dart
|____data
|    |____repositories
|    |    |____auth_repository.dart
|    |    |____posts_repository.dart
|    |    |____profile_repository.dart
|    |____services
|    |    |____auth_service.dart
|    |    |____posts_service.dart
|    |    |____profile_service.dart
|    |____model
|    |    |____auth_response.dart
|    |    |____post_response.dart
|____config
|____utils
|____routing
|    |____app_router.dart
|____main.dart

test
|____data
|____domain
|____ui
|____utils

testing
|____fakes
|____models
```


## Domain Models
Define domain models using `freezed` for immutability and JSON serialization:

### User Model
```dart
// domain/models/user.dart
import 'package:freezed_annotation/freezed_annotation.dart';

part 'user.freezed.dart';
part 'user.g.dart';

@freezed
class User with _$User {
  const factory User({
    required String id,
    required String name,
    required String email,
  }) = _User;

  factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);
}
```

## Services
Implement services to handle network requests:

### Profile Service
```dart
// data/services/profile_service.dart
import 'package:codernest/domain/models/user.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ProfileService {
  Future<User> getUserProfile(String userId) async {
    try {
      // Simulate an API call for fetching user profile
      await Future.delayed(Duration(seconds: 2));
      return User(id: userId, name: 'John Doe', email: 'john.doe@example.com');
    } catch (e) {
      // Handle error
      throw Exception('Failed to fetch user profile');
    }
  }

  Future<void> updateUserProfile(User user) async {
    try {
      // Simulate an API call for updating user profile
      await Future.delayed(Duration(seconds: 1));
      // Simulate a failure
      throw Exception('Failed to update user profile');
    } catch (e) {
      // Handle error
      throw Exception('Failed to update user profile');
    }
  }
}

final profileServiceProvider = Provider<ProfileService>((ref) {
  return ProfileService();
});

final userProfileProvider = FutureProvider.family<User, String>((ref, userId) async {
  final profileService = ref.watch(profileServiceProvider);
  return await profileService.getUserProfile(userId);
});
```

## Repositories
Provide an abstraction over the data sources:

### Profile Repository

```dart
// data/repositories/profile_repository.dart
import 'package:codernest/data/services/profile_service.dart';
import 'package:codernest/domain/models/user.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class ProfileRepository {
  final ProfileService profileService;

  ProfileRepository(this.profileService);

  Future<User> getUserProfile(String userId) async {
    return await profileService.getUserProfile(userId);
  }

  Future<void> updateUserProfile(User user) async {
    await profileService.updateUserProfile(user);
  }
}

final profileRepositoryProvider = Provider<ProfileRepository>((ref) {
  final profileService = ref.watch(profileServiceProvider);
  return ProfileRepository(profileService);
});
```

## ViewModels
Manage the state of the application using `Riverpod`:

### Profile ViewModel

```dart
// ui/profile/view_model/profile_view_model.dart
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:codernest/data/repositories/profile_repository.dart';
import 'package:codernest/domain/models/user.dart';

class ProfileViewModel extends StateNotifier<AsyncValue<User?>> {
  final ProfileRepository profileRepository;

  ProfileViewModel(this.profileRepository) : super(const AsyncValue.loading());

  Future<void> loadUserProfile(String userId) async {
    try {
      final user = await profileRepository.getUserProfile(userId);
      state = AsyncValue.data(user);
    } catch (e) {
      state = AsyncValue.error(e);
    }
  }

  Future<void> updateUserProfile(User user) async {
    try {
      await profileRepository.updateUserProfile(user);
      state = AsyncValue.data(user);
    } catch (e) {
      state = AsyncValue.error(e);
    }
  }
}

final profileViewModelProvider = StateNotifierProvider<ProfileViewModel, AsyncValue<User?>>((ref) {
  final profileRepository = ref.watch(profileRepositoryProvider);
  return ProfileViewModel(profileRepository);
});
```

## UI Components
Implement UI components for profile management:

### Profile Screen
```dart
// ui/profile/widgets/profile_screen.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:codernest/ui/profile/view_model/profile_view_model.dart';
import 'package:codernest/data/services/profile_service.dart';

class ProfileScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userId = '1'; // Replace with the actual user ID
    final userProfile = ref.watch(userProfileProvider(userId));
    final profileViewModel = ref.watch(profileViewModelProvider);

    return Scaffold(
      appBar: AppBar(
        title: Text('Codernest - Profile'),
      ),
      body: userProfile.when(
        data: (user) {
          return Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              children: [
                TextField(
                  decoration: InputDecoration(labelText: 'Name'),
                  controller: TextEditingController(text: user.name),
                  onChanged: (value) {
                    ref.read(profileViewModelProvider.notifier).updateUserProfile(user.copyWith(name: value));
                  },
                ),
                TextField(
                  decoration: InputDecoration(labelText: 'Email'),
                  controller: TextEditingController(text: user.email),
                  onChanged: (value) {
                    ref.read(profileViewModelProvider.notifier).updateUserProfile(user.copyWith(email: value));
                  },
                ),
                SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    ref.read(profileViewModelProvider.notifier).updateUserProfile(user);
                  },
                  child: Text('Save'),
                ),
                if (profileViewModel is AsyncError) ...[
                  SizedBox(height: 20),
                  Text('Error: ${profileViewModel.error}', style: TextStyle(color: Colors.red)),
                ],
              ],
            ),
          );
        },
        loading: () => Center(child: CircularProgressIndicator()),
        error: (error, stack) => Center(child: Text('Error: $error')),
      ),
    );
  }
}
```

## Routing
Use go_router for routing:

### app_router.dart

```dart
// routing/app_router.dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:codernest/ui/profile/widgets/profile_screen.dart';

final GoRouter appRouter = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => ProfileScreen(),
    ),
  ],
);
```

## Main
Set up the main entry point of the application:

### main.dart

```dart
// main.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:codernest/routing/app_router.dart';

void main() {
  runApp(
    ProviderScope(
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: appRouter,
    );
  }
}
```

## Testing
Write unit tests for view models to ensure reliable and maintainable state management logic:

### Profile ViewModel Test

```dart
// test/ui/profile/view_model/profile_view_model_test.dart
import 'package:flutter_test/flutter_test.dart';
import 'package:codernest/ui/profile/view_model/profile_view_model.dart';
import 'package:codernest/data/repositories/profile_repository.dart';
import 'package:codernest/domain/models/user.dart';
import 'package:mockito/mockito.dart';
import 'package:mockito/annotations.dart';

@GenerateMocks([ProfileRepository])
void main() {
  test('ProfileViewModel loads user profile', () async {
    final profileRepository = MockProfileRepository();
    final profileViewModel = ProfileViewModel(profileRepository);

    when(profileRepository.getUserProfile('1'))
        .thenAnswer((_) async => User(id: '1', name: 'John Doe', email: 'john.doe@example.com'));

    await profileViewModel.loadUserProfile('1');

    expect(profileViewModel.state.value?.name, 'John Doe');
  });
}
```

By following this guide, you can build and maintain the profile management feature in your Flutter project efficiently and effectively.
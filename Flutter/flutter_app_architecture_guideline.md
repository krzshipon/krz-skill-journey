# Flutter Project Architecture Guide

## Project Structure
Organize the project into directories to ***separate concerns*** and ensure ***maintainability***:
- **lib**
  - **ui**: Contains UI components and view models.
    - **feature**: Contains feature-related UI components and view models.
  - **domain**: Contains domain models.
  - **data**: Contains repositories, services, and data models.
  - **config**: Configuration files.
  - **utils**: Utility functions and helpers.
  - **routing**: Routing logic.
  - **main.dart**: Main entry point of the application.

Example:
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
- **Feature Model**: Represents feature data.

## Data Models
Define data models for API responses using `freezed`:
- **Feature Response Model**: Represents feature response data.

## Services
Implement services to handle network requests:
- **Feature Service**: Manages feature-related network requests.

## Repositories
Provide an abstraction over the data sources:
- **Feature Repository**: Handles feature logic.

## ViewModels
Manage the state of the application using `Riverpod`:
- **Feature ViewModel**: Manages feature state.

## UI Components
Implement UI components for the feature:
- **Feature Screen**: Displays and manages the feature.

## Routing
Use `go_router` for routing:
- Define routes for different screens in a separate routing file.

## Testing
Write unit tests for view models to ensure reliable and maintainable state management logic:
- **Feature ViewModel Test**: Tests feature logic.

## Key Concepts
1. **Project Structure**: Organize code into directories for maintainability.
2. **Domain Models**: Use `freezed` for immutability and JSON serialization.
3. **Data Models**: Define models for API responses.
4. **Services**: Handle network requests.
5. **Repositories**: Provide an abstraction over data sources.
6. **ViewModels**: Manage state using `Riverpod`.
7. **UI Components**: Implement screens and widgets.
8. **Routing**: Use `go_router` for navigation.
9. **Testing**: Write unit tests for view models.

By following this guide, you can build and maintain any feature in your Flutter project efficiently and effectively.

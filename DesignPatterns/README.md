# Design Patterns

Design patterns are reusable solutions to common problems in software design. They provide best practices for structuring code to make it more maintainable, scalable, and flexible. Below, we break them into **three categories**: **Creational**, **Structural**, and **Behavioral**.

---

## **1. Creational Design Patterns**
Creational patterns deal with object creation mechanisms, ensuring objects are created in a flexible and reusable way.

---

### **a. Singleton**
- **Purpose**: Ensures a class has only one instance and provides a global point of access to it.
- **Use Case**: Managing shared resources like database connections, configuration settings, or logging systems.
- **How It’s Achieved**:
  1. Make the constructor private to prevent external instantiation.
  2. Create a static final instance of the class.
  3. Provide a static method to access the single instance.

---

### **b. Factory Method**
- **Purpose**: Defines an interface for creating objects but lets subclasses decide which class to instantiate.
- **Use Case**: When the exact type of object isn’t known until runtime (e.g., creating different types of vehicles or UI components).
- **How It’s Achieved**:
  1. Define an abstract class or interface for the product.
  2. Create concrete classes that implement the product interface.
  3. Define a factory method in a creator class that returns the appropriate product based on input.

---

### **c. Builder**
- **Purpose**: Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
- **Use Case**: When an object requires multiple steps to be created (e.g., building a pizza with customizable toppings).
- **How It’s Achieved**:
  1. Create a builder class with methods for setting optional parameters.
  2. Use a fluent interface (method chaining) to set parameters.
  3. Provide a `build()` method to return the final object.

---

## **2. Structural Design Patterns**
Structural patterns deal with object composition, ensuring classes and objects work together to form larger structures.

---

### **a. Adapter**
- **Purpose**: Allows incompatible interfaces to work together by converting the interface of one class into another.
- **Use Case**: Integrating a new component into an existing system without modifying the existing code.
- **How It’s Achieved**:
  1. Create an adapter class that implements the target interface.
  2. Use composition to wrap the adaptee (the class with the incompatible interface).
  3. Implement the target interface by delegating calls to the adaptee.

---

### **b. Decorator**
- **Purpose**: Adds behavior to objects dynamically without affecting other objects of the same class.
- **Use Case**: Extending functionality at runtime (e.g., adding toppings to a pizza or adding features to a coffee order).
- **How It’s Achieved**:
  1. Create a base class or interface for the component.
  2. Create concrete classes that implement the component.
  3. Create decorator classes that wrap the component and add additional behavior.

---

### **c. Facade**
- **Purpose**: Provides a simplified interface to a complex subsystem, making it easier to use.
- **Use Case**: Simplifying interactions with a complex system (e.g., starting a computer with multiple subsystems).
- **How It’s Achieved**:
  1. Create a facade class that encapsulates the interactions with the subsystem.
  2. Provide a simple interface for common tasks.
  3. Delegate calls from the facade to the appropriate subsystem components.

---

## **3. Behavioral Design Patterns**
Behavioral patterns deal with object interaction and responsibility, ensuring objects work together effectively.

---

### **a. Observer**
- **Purpose**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified.
- **Use Case**: Implementing event handling systems or notification systems.
- **How It’s Achieved**:
  1. Create a subject class that maintains a list of observers.
  2. Define an observer interface with an `update()` method.
  3. Notify all observers when the subject’s state changes.

---

### **b. Strategy**
- **Purpose**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
- **Use Case**: When you need to switch between different algorithms at runtime (e.g., payment methods or sorting algorithms).
- **How It’s Achieved**:
  1. Define a strategy interface for the algorithm.
  2. Create concrete classes that implement the strategy interface.
  3. Use composition to inject the strategy into the context class.

---

### **c. Command**
- **Purpose**: Encapsulates a request as an object, allowing parameterization of clients with queues, requests, and operations.
- **Use Case**: Implementing undo/redo functionality or queuing requests.
- **How It’s Achieved**:
  1. Create a command interface with an `execute()` method.
  2. Define concrete command classes that implement the interface.
  3. Use an invoker class to execute commands and optionally store them for undo/redo.

---

## **Summary Table of Design Patterns**

| **Category**       | **Pattern**      | **Purpose**                                                                 | **How It’s Achieved**                                                                 |
|---------------------|------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Creational**      | Singleton        | Ensure a single instance of a class.                                        | Private constructor, static final instance, and static access method.                |
|                     | Factory Method   | Let subclasses decide which class to instantiate.                           | Abstract creator class with a factory method.                                         |
|                     | Builder          | Separate construction of complex objects from their representation.         | Builder class with fluent interface and `build()` method.                             |
| **Structural**      | Adapter          | Make incompatible interfaces work together.                                 | Adapter class that implements the target interface and wraps the adaptee.             |
|                     | Decorator        | Add behavior to objects dynamically.                                        | Decorator classes that wrap components and add behavior.                              |
|                     | Facade           | Provide a simplified interface to a complex subsystem.                      | Facade class that encapsulates subsystem interactions.                                |
| **Behavioral**      | Observer         | Notify dependents of state changes.                                         | Subject class with a list of observers and an `update()` method.                      |
|                     | Strategy         | Encapsulate and interchange algorithms.                                     | Strategy interface, concrete strategy classes, and context class.                     |
|                     | Command          | Encapsulate requests as objects.                                            | Command interface, concrete command classes, and invoker class.                       |
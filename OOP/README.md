# Object-Oriented Programming (OOP)

Object-Oriented Programming (OOP) is a programming paradigm that organizes software design around **objects** rather than functions and logic. It is based on four key principles: **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism**. Below, we break these concepts down in a clear and structured way.

---

## **1. Encapsulation**
- **Purpose**: Bundles data (attributes) and methods (functions) that operate on the data into a single unit (class) and restricts direct access to some of the object's components.
- **How It’s Achieved**:
  1. Use **access modifiers** (e.g., `private`, `protected`, `public`) to control access to class members.
  2. Provide **getter and setter methods** to access or modify private data.
- **Use Case**: Protecting sensitive data (e.g., a user’s password) and ensuring controlled access to class properties.

**Example**:
- A `BankAccount` class encapsulates the `balance` attribute and provides methods like `deposit()` and `withdraw()` to interact with it.

---

## **2. Abstraction**
- **Purpose**: Hides complex implementation details and exposes only the necessary features of an object.
- **How It’s Achieved**:
  1. Use **abstract classes** or **interfaces** to define a blueprint for other classes.
  2. Implement only the essential details in derived classes.
- **Use Case**: Simplifying complex systems by exposing only what’s necessary (e.g., a car’s steering wheel hides the complexity of the steering mechanism).

**Example**:
- An abstract class `Vehicle` defines a method `start()`, and derived classes like `Car` and `Bike` provide their own implementations.

---

## **3. Inheritance**
- **Purpose**: Allows a class (child) to inherit properties and methods from another class (parent), promoting code reuse.
- **How It’s Achieved**:
  1. Use the `extends` keyword (in languages like Java, Dart, etc.) to create a subclass.
  2. Override methods in the subclass if needed.
- **Use Case**: Creating a hierarchy of classes with shared behavior (e.g., `Animal` as a parent class and `Dog` and `Cat` as subclasses).

**Example**:
- A `Vehicle` class has properties like `speed` and methods like `move()`. The `Car` class inherits from `Vehicle` and adds its own properties like `numDoors`.

---

## **4. Polymorphism**
- **Purpose**: Allows objects of different classes to be treated as objects of a common superclass and enables methods to behave differently based on the object’s type.
- **How It’s Achieved**:
  1. Use **method overriding** (subclasses provide specific implementations of methods defined in the superclass).
  2. Use **method overloading** (multiple methods with the same name but different parameters).
- **Use Case**: Writing flexible and reusable code (e.g., a `Shape` class with a `draw()` method, and subclasses like `Circle` and `Rectangle` provide their own implementations).

**Example**:
- A `Shape` class has a method `draw()`. Subclasses like `Circle` and `Rectangle` override `draw()` to render their specific shapes.

---

## **5. Key OOP Concepts in Practice**

---

### **a. Classes and Objects**
- **Class**: A blueprint for creating objects. It defines properties (attributes) and behaviors (methods).
- **Object**: An instance of a class. It represents a real-world entity.

**Example**:
- A `Person` class defines properties like `name` and `age` and methods like `walk()` and `talk()`. An object of this class could represent a specific person, e.g., `Person john = new Person("John", 30)`.

---

### **b. Constructors**
- **Purpose**: Initialize objects when they are created.
- **How It’s Achieved**:
  1. Define a constructor method in the class.
  2. Use parameters to set initial values for object properties.

**Example**:
- A `Car` class has a constructor that initializes properties like `make`, `model`, and `year`.

---

### **c. Method Overriding**
- **Purpose**: Allows a subclass to provide a specific implementation of a method defined in its superclass.
- **How It’s Achieved**:
  1. Define a method in the superclass.
  2. Override the method in the subclass using the same method signature.

**Example**:
- A `Bird` class overrides the `move()` method from its parent class `Animal` to implement flying behavior.

---

### **d. Method Overloading**
- **Purpose**: Allows multiple methods with the same name but different parameters in the same class.
- **How It’s Achieved**:
  1. Define multiple methods with the same name but different parameter lists.

**Example**:
- A `Calculator` class has multiple `add()` methods: one for adding two integers and another for adding three integers.

---

## **6. Real-World Applications of OOP**

---

### **a. Encapsulation in Banking Systems**
- A `BankAccount` class encapsulates sensitive data like `accountNumber` and `balance` and provides methods like `deposit()` and `withdraw()` to interact with the data.

---

### **b. Abstraction in Vehicle Systems**
- An abstract class `Vehicle` defines methods like `start()` and `stop()`, and subclasses like `Car` and `Bike` provide their own implementations.

---

### **c. Inheritance in Employee Management**
- A `Person` class defines properties like `name` and `age`, and subclasses like `Employee` and `Manager` inherit these properties and add their own (e.g., `employeeId`, `department`).

---

### **d. Polymorphism in Graphics Rendering**
- A `Shape` class defines a `draw()` method, and subclasses like `Circle`, `Rectangle`, and `Triangle` override `draw()` to render their specific shapes.

---

## **7. Summary Table of OOP Principles**

| **Principle**      | **Purpose**                                                                 | **How It’s Achieved**                                                                 |
|---------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Encapsulation**   | Protect data and control access to it.                                      | Use access modifiers and provide getter/setter methods.                              |
| **Abstraction**     | Hide complexity and expose only essential features.                         | Use abstract classes or interfaces.                                                  |
| **Inheritance**     | Promote code reuse by allowing classes to inherit properties and methods.   | Use the `extends` keyword to create subclasses.                                      |
| **Polymorphism**    | Allow objects of different types to be treated as objects of a common type. | Use method overriding and overloading.                                               |
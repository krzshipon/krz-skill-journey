
# Prompting Frameworks for Developers

## What is a Prompting Framework?
A prompting framework is a structured approach to crafting prompts for AI models, ensuring clarity, specificity, and effectiveness in generating desired outputs. 

These frameworks help developers communicate tasks, actions, and guidelines to AI systems in a way that maximizes accuracy and relevance.

## **Top Prompting Frameworks for Developers**

### 1. **Task-Action-Guideline (TAG)**
#### Use Case: Writing code, debugging, or building applications.
 
#### Example:
```markdown
Task: Develop a Python script to scrape data from a website.

Action:
1. Use the `requests` library to fetch the website content.
2. Parse the HTML using `BeautifulSoup`.
3. Extract specific elements (e.g., titles, links).
4. Save the data to a CSV file.
Guideline:
1. Ensure error handling for failed requests.
2. Use efficient parsing techniques to avoid memory issues.
3. Include comments for clarity.
```

### 2. **Input-Output-Constraint (IOC)**
#### Use Case: Data transformation, code generation, or API development.

#### Example:
```markdown
Input: A JSON object with user data: `{ "name": "John", "age": 30, "email": "john@example.com" }`.

Output: A SQL query to insert this data into a `users` table.

Constraint:
1. Use parameterized queries to prevent SQL injection.
2. Handle null or missing fields gracefully.
```

### 3. **CREATE (Context-Requirement-Example-Action-Test-Expectation)**
#### Use Case: Building or designing systems, APIs, or workflows.

#### Example:
```markdown
Context: You are building a REST API for a task management app.

Requirement:
1. Implement CRUD operations for tasks.
2. Use JWT for authentication.

Example:
- POST /tasks: Create a new task.
- GET /tasks: Retrieve all tasks.

Action:
1. Set up an Express server.
2. Define routes for CRUD operations.
3. Integrate JWT middleware for authentication.

Test:
1. Write unit tests for each endpoint.
2. Test edge cases (e.g., invalid inputs).

Expectation:
1. The API should be well-documented.
2. Code should follow best practices (e.g., error handling, modularity).
```

### 4. **Chain-of-Thought (CoT)**
#### Use Case: Complex problem-solving, algorithm design, or debugging.

#### Example:
```markdown
Task: Explain how to implement a binary search algorithm in Python.

Chain-of-Thought:
1. Start by sorting the input array.
2. Define the low and high pointers.
3. Calculate the mid-point and compare it with the target value.
4. Adjust the pointers based on the comparison.
5. Repeat until the target is found or the search space is exhausted.
```

### 5. **Few-Shot Prompting**
#### Use Case: Learning new libraries, frameworks, or coding patterns.

#### Example:
```markdown
Example 1:
Input: "Convert a list of integers to a comma-separated string."
Output: `",".join(map(str, [1, 2, 3]))`

Example 2:
Input: "Convert a list of strings to uppercase."
Output: `[s.upper() for s in ["hello", "world"]]`

Task: Convert a dictionary to a list of key-value pairs.
```

### 6. **Role-Task-Expectation (RTE)**
#### Use Case: Role-based tasks like system architecture, code reviews, or best practices.

#### Example:
```markdown
Role: You are a senior backend developer.

Task: Review this Python code for a REST API and suggest improvements.

Expectation:
1. Identify potential security vulnerabilities.
2. Suggest performance optimizations.
3. Ensure the code follows PEP 8 guidelines.
```

## **When to Use Which Framework?**
| Framework   | Best For                                                                 |
|-------------|-------------------------------------------------------------------------|
| **TAG**     | Structured tasks with clear steps (e.g., coding, debugging).            |
| **IOC**     | Data transformation, code generation, or API development.              |
| **CREATE**  | Designing systems, APIs, or workflows with detailed requirements.      |
| **CoT**     | Complex problem-solving or explaining algorithms step-by-step.         |
| **Few-Shot**| Learning new libraries, frameworks, or coding patterns with examples.  |
| **RTE**     | Role-based tasks like code reviews, system design, or best practices.  |

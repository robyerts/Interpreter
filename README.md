# Interpreter

Current implementation supports multi-threading and offers a GUI from which the user can pick hard-coded examples to run step by step.

A line of execution is represented by a `PrgState`.
The Repository contains a list of `PrgState` which can be executed simultaneously.

In `PrgState` we keep the initial Statement to be executed, the **execution stack**,  **symbol table**, **console output**, **file table**, **heap table**, **lock table**  and the **PrgState ID**.
All these tables are represented as `Map`.

The main interfaces of the program are `Expression` and `Statement`.

**Expressions:**

Have the method `int evaluate(ISymbolTable<String, Integer> table, IHeap<Integer> h)` which takes as parameters the **symbol table** and the **heap** and are as follows:

1. `ConstExpression(int value)`

2.  `VarExpression(String name)`  
**Evaluate** searches for the String in the ** Symbol Table** and returns it's value;

3. `BooleanExpression(String operator, Expression operand1, Expression operand2)`  
It evaluates the Expressions and uses a comparison operator 

4. `ArithExpression(char op,Expression first, Expression second)`  
Uses a basic arithmetic operation on the values of the given expressions.

**Statements**:

Have the method `PrgState execute(PrgState p)` which takes as parameter the current PrgState and returns a non-null value only if our PrgState **has been forked**. More on that soon.  
Are the main blocks of execution;  **PrgState** takes a Statement initially, but one statement can contain 2 other Statements; these are called **Compound Statements**.

1.`AssignStatement(String var, Expression expr)`  
Assigns to a String variable in the **symbol table** the value of the evaluated expression;

2. `PrintStatement(Expression exp)`  
Prints the evaluated expression;

3. `IfStatement(Expression exp, Statement thenB, Statement elseB)`  
Pushes to the stack of execution statement `thenB` if exp evaluates to a value  != 0, otherwise pushes statements `elseB`;

3. `WhileStatement(Statement stmt, Expression expr)`  
As long as `expr` evaluates to !=0 pushes itself back on the execution stack;

4. `newH(String var_name, Expression expr)`  
Creates a new variable in the heap space such that the Symbol Table will point to the heap record table, where you can find the actual variable value;  
--> `rH(String var_name)` expression
Where `evaluate` looks for the string to be present in the Symbol Table, searches the found ID in heap table and returns the value; 

5. `wH(String var_name, Expression expr) `  
Changes the value of a currently existing variable with the evaluated given expression.

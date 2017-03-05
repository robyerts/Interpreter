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

1. `AssignStatement(String var, Expression expr)`  
Assigns to a String variable in the **symbol table** the value of the evaluated expression;

2. `PrintStatement(Expression exp)`  
Prints the evaluated expression;

1. `CompStatement(Statement first, Statement second)`

1. `IfStatement(Expression exp, Statement thenB, Statement elseB)`  
Pushes to the stack of execution statement `thenB` if exp evaluates to a value  != 0, otherwise pushes statements `elseB`;

4. `WhileStatement(Statement stmt, Expression expr)`  
As long as `expr` evaluates to != 0 pushes itself back on the execution stack;

5. `ForStatement(Statement stmt, Expression expr1, Expression expr2, Expression expr3)`-  limited functionality  
Creates a new variable **v** in the **Symbol Table** . Careful **not to have variable v in use**; application will misbehave.  
**v** will be assigned `expr1.evaluate(...)`. After every step it will take the value of `expr3.evaluate(...)` and the stopping condition is given by ` v < expr2.evaluate(...)`.
5.. `newH(String var_name, Expression expr)`  
Creates a new variable in the heap space such that the Symbol Table will point to the heap record table where you can find the actual variable value;  
--> `rH(String var_name)` expression
Where `evaluate` looks for the string to be present in the Symbol Table, searches the found ID in heap table and returns the value; 

6. `wH(String var_name, Expression expr) `  
Changes the value in the **Heap Table** of a currently existing variable with the evaluated given expression.

7. ` newLock(String var_name)`
Generates an ID for the new lock, creates/updates the entry in the**Symbol Table** and it will point to an entry in the **Lock Table** where the lock will be initialized with `-1`, meaning that no thread holds it;

8. `lock(String var_name)`  
Checks the **Symbol Table** for an entry based on `var_name` and it uses the found value to check the **Lock Table**. If it finds `-1` then it will replace it with the the ID of the currently executing **PrgState**; otherwise, it pushes itself back on the **Exe Stack**

9. `unlock(String var_name)`  
Similar to `lock`, but in **LockTable**, if we find the value to be **equal** to the current **PrgState ID**, it will replace it with `-1`.


The application contains 3 hard-coded examples, the second of which is solely to demonstrate the basic functionality of a lock; the other 2 are more complex.

The interface looks something like this:

![interface](https://github.com/robyerts/Interpreter/blob/master/Screenshot%20from%202017-03-05%2013-28-47.png?raw=true)	

Where the top left table is **heap**, top right is **symbol table**, bottom left is ** file table ** and bottom right is **lock table**.  
In the right side is the execution stack and  beneath are the **output** and a list with ** PrgState ID's** from which you can select to view any available.

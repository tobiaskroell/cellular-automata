# Cellular Automata

Cellular automata are mathematical models used to simulate complex systems with simple rules. A one-dimensional cellular automaton is a row of cells that changes over time based on simple rules involving each cell's state and its neighbors' states. Despite the simplicity, they can create very complex and interesting patterns! 

This program allows you to enter a ruleset via console and draws the respective 1D evolution of cells. 


### How cellular automata work

**Cells and States**: Each square can be in one of two states, like "on" or "off" here represented in black (on) and white (off).

**Initial State**: You start with an initial row of cells in different states. For example, some might be on (black), and some might be off (white). In this version we always start with one black cell in the middle.

![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/214559ee-d68a-4279-beb3-f010da684361)


### Rules

**Neighborhood**: Each cell has a neighborhood, consisting of itself and its immediate neighbors (the cells directly to its left and right). For each cell, the neighborhood includes:
   - The cell itself (center cell)
   - The cell to its immediate left
   - The cell to its immediate right

![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/f046d4bd-efba-422f-b8e3-d0dc49953184)

**Possible Neighborhood Configurations**: Each cell and its two neighbors can be in one of \(2^3 = 8\) possible configurations (since each cell can be 0 or 1). These configurations are:
   - 000
   - 001
   - 010
   - 011
   - 100
   - 101
   - 110
   - 111

**Ruleset**: The rule for updating the state of a cell is usually given as a rule table that specifies the new state for each possible neighborhood configuration. For instance, a specific rule will tell you what the new state of a cell should be if the current state of the cell and its neighbors is 000, what it should be if the configuration is 001, and so on. In this example a cell should come alive when either 001, 010, 011, or 100 cells are active. In all other cases the new state of the cell will be off.

![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/bcc20682-87a0-449b-9879-90a6592efef3)


Or in an alternative visulization:

![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/53149258-bb5f-45c2-9436-e0d40c973ba1)



### Evolution

**Time Steps**: The cellular automaton evolves in steps (generations). At each step, every cell updates its state simultaneously based on the rule and its current neighborhood.

### Example

**Rule 30**: One famous rule is called Rule 30 which is the rule represented above:
  - If a cell and its neighbors are all off (000), the cell stays off in the next step.
  - The same goes for (101), (110), and (111)
  - If the cell is off, its left neighbor is off, and its right neighbor is on (001), the cell turns on.
  - The same goes for (010), (011) and (100)

### Visualizing

- **Pattern Creation**: As you apply the rule over many steps, you get interesting patterns. For Rule 30, starting with a single "on" cell in an "off" row creates a chaotic, fractal-like pattern. In this implementation the edges, meaning the most left and most right cell are ignored.

![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/a4374127-f455-458c-80e8-f51ae066cf58)

Which leads to this pattern:
![image](https://github.com/tobiaskroell/cellular-automata/assets/66690169/d1120c38-cef3-4970-92e7-2938cdf15791)


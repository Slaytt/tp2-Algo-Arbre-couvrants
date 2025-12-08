# Random Spanning Trees

This project implements a library to generate random spanning trees using various algorithms and analyzes their properties.

## Algorithms & Complexity

### 1. Random Weight MST (Kruskal/Prim)
- **Description**: Assigns random weights to edges and finds the Minimum Spanning Tree.
- **Complexity**: O(E log E) or O(E log V) depending on implementation. Using Kruskal's with Union-Find, it is dominated by sorting edges.
- **Result**: Generates trees that are effectively uniform random spanning trees (for random weights).

### 2. Random DFS
- **Description**: Depth-First Search with random neighbor selection.
- **Complexity**: O(V + E). Linear time.
- **Result**: **NOT Uniform**. Tends to generate "long and thin" trees with high diameter and low degree distribution.

### 3. Aldous-Broder
- **Description**: Random walk until all vertices are visited.
- **Complexity**: O(V log V) on average for many graphs, but can be O(V^3) or worse (cover time).
- **Result**: **Uniform**. Generates a uniform random spanning tree.

### 4. Wilson's Algorithm
- **Description**: Loop-Erased Random Walk.
- **Complexity**: More efficient than Aldous-Broder. Average time is proportional to the mean hitting time.
- **Result**: **Uniform**. Generates a uniform random spanning tree.

## Analysis of Results

- **Uniform Algorithms (Aldous-Broder, Wilson, Random Weight MST)**: Produce trees with "balanced" properties. The diameter is typically proportional to sqrt(V) for grid graphs.
- **Random DFS**: Produces trees with much higher diameter (proportional to V) and lower average degree. They look like long paths with short branches.

## How to Run

### Compile
```bash
make compile
```

### Run
```bash
make exec
```
This will run all 4 algorithms on a 20x20 Grid Graph (10 samples each) and print the average statistics.

### Clean
```bash
make clean
```

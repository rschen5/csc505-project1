# csc505-project1

## Build Solution

The `project.sh` file contains all code to build our solution. Please note that it requires the `Project1` folder from [Dr. Jenning's data files repository](https://github.ncsu.edu/jajenni3/csc505-spring-2022) to first be placed in the same directory.

To build our solution, run the following in a bash session:

```bash
chmod +x project.sh
./project.sh
```

## Run Solution

The `src/` folder contains three different scripts for running the sorting algorithms:
* insertion.sh for Insertion sort
* merge.sh for Merge sort
* timsort.sh for Timsort

Each script accepts a parameter (A, B, or C) to run the sorting algorithm for one data set.

For example, to run Merge sort for data set A, run:
```bash
./src/merge.sh A
```

Below are commands to run our sorting algorithm scripts. Replace `<data set>` with the desired data set (A/B/C) before running the command.

Insertion sort:
```bash
./src/insertion.sh <data set>
```

Merge sort:
```bash
./src/merge.sh <data set>
```

Timsort:
```bash
./src/timsort.sh <data set>
```

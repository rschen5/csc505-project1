# csc505-project1

Team members: Rachel Chen (rschen), Manali Shirsekar (mnshirse), Bonnie Chhatrala (bschhatr)

We tested our code on the VCL using the "Eos Linux Lab Computer" environment.

## Build Solution

The `project.sh` file contains all code to build our solution. Please note that it requires the `Project1` folder from [Dr. Jenning's data files repository](https://github.ncsu.edu/jajenni3/csc505-spring-2022) to first be placed in the same directory (with files not yet unzipped).

To build our solution, run the following in a bash session:

```bash
chmod +x project.sh
./project.sh
```

Note: if you need to re-run `project.sh`, you will first need to remove the `outputs` folder that is created by `projects.sh`.

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

## Runtime Output

We have included files showing the raw output for our execution runs for some of the sorting algorithms in the `raw-files` folder. We did our data analysis in Excel, so we copied over our runtime data to the spreadsheets linked in Question 13 of our project report.

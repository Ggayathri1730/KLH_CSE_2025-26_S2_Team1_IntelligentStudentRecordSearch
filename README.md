# Intelligent Student Record Search System

**Course:**   Data Structures and Algorithms – 3  
**Team:**  1
**Supervisor:**   Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Current Phase:**   Pattern/String Matching Implementation – Review 2

---

## Student Details

| S. No. | Student Name         | Roll Number  |
| -----: | ----------------     | ------------ |
|      1 | Guttikonda Gayathri  | 2520030465   |
|      2 | Sai Sowseelya Vedula | 2520030260   |
|      3 | Somana Divya Sai     | 2520039622   |


---

## Abstract

The Intelligent Student Record Search System is a data-structures-and-algorithms-based project designed to provide efficient search and retrieval of information from a structured student record corpus.

The system processes a collection of student profile documents and applies advanced string-matching algorithms to support efficient pattern and keyword searching across student information such as names, skills, projects, certifications, interests, and other relevant attributes.

For pattern matching, the system implements the Knuth-Morris-Pratt (KMP) and Rabin-Karp algorithms. KMP uses the Longest Proper Prefix which is also Suffix (LPS) array to avoid unnecessary comparisons, while Rabin-Karp uses a rolling hash to identify candidate matches efficiently.

The project is designed to demonstrate the practical application of advanced string algorithms in a real-world student information search scenario.

---

## Objectives

1. Implement efficient pattern and string-matching algorithms.
2. Search student information stored in the project's student record corpus.
3. Compare KMP and Rabin-Karp for the same search queries.
4. Verify consistency of matching results produced by different algorithms.
5. Measure and compare algorithm execution performance.
6. Extend the system with advanced search capabilities as the project progresses.
7. Demonstrate practical applications of Data Structures and Algorithms in a student-record search scenario.

---

## Algorithms and Data Structures

The project includes:

- Knuth-Morris-Pratt (KMP) pattern matching
- Rabin-Karp pattern matching
- Pattern/string matching
- Rolling hash
- Student record corpus loading
- Search result matching
- Algorithm performance comparison

Future phases may incorporate additional DSA-3 concepts based on the project requirements and course progression.

---

## Current Phase Status

### Review 2 – Pattern/String Matching

**Status:** In Progress

The current implementation focuses on:

- KMP string matching
- Rabin-Karp string matching
- Search over the project's student record corpus
- Comparison of KMP and Rabin-Karp
- Matching-result consistency verification
- Execution-time benchmarking
- Operation-count comparison

Both algorithms will be integrated into the project's search engine.

---

## Project Flow

```text
Student Record Corpus
        |
        v
   Corpus Loader
        |
        v
 Student Records
        |
        v
    User Query
        |
        +-------------------+
        |                   |
        v                   v
       KMP             Rabin-Karp
        |                   |
        +---------+---------+
                  |
                  v
       Matching Student Records
                  |
                  v
        Algorithm Comparison
                  |
                  v
       Performance Analysis
```

## Build, Run, Tests, and Benchmark

Compile the Java sources and launch the interactive application from the project
root with Java 21 or later:

```powershell
javac -d bin src/algorithms/*.java
java -cp bin algorithms.Main
```

The application reads `.txt` student records from `corpus/student-records`.
Menu option 5 searches those records with Naive Pattern Matching; option 6 runs
the benchmark.

Run the available executable test classes after compiling:

```powershell
$tests = @(
    "NaivePatternMatchingTest", "KMPTest", "RabinKarpTest",
    "EditDistanceTest", "SuffixArrayTest", "DinicTest",
    "EdmondsKarpTest", "FordFulkersonTest", "BipartiteMatchingTest",
    "AhoCorasickTest", "ZAlgorithmTest"
)
foreach ($test in $tests) {
    java -cp bin "algorithms.$test"
}
```

Run the benchmark independently with:

```powershell
java -cp bin algorithms.AlgorithmBenchmark
```

The benchmark searches all 1,500 records for `solidity`, `docker`,
`blockchain`, `image processing`, `human computer interaction`, and
`benchmark-no-match-934872`. It lowercases records and queries with
`Locale.ROOT`, compares the matching record paths from Naive Pattern Matching,
KMP, and Rabin-Karp, and stops before timing if their results differ. It performs
3 warm-up batches and 7 timed batches per algorithm, measures with
`System.nanoTime()`, and reports the median. Each batch includes searching all
six queries across the corpus and collecting matching paths.

The final verification run on Java 21.0.9 found 99, 183, 150, 168, 159, and 0
matching records for those queries, respectively. The run verified consistent
results and measured these median batch times:

| Algorithm | Median |
| --- | ---: |
| Naive Pattern Matching | 102.183 ms |
| KMP | 123.963 ms |
| Rabin-Karp | 518.937 ms |

Timings vary by machine and run; these values document that verification run,
not guaranteed performance.

# Intelligent Student Record Search System

**Course:** Data Structures and Algorithms – III  
**Team:** 1  
**Project Supervisor:** Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Project Phase:** Pattern Matching Implementation – Review 2

---

## 1. Student Details

| S. No. | Student Name | Roll Number |
|---:|---|---|
| 1 | Guttikonda Gayathri | 2520030465 |
| 2 | Sai Sowseelya Vedula | 2520030260 |
| 3 | Somana Divya Sai | 2520039622 |

---

## 2. Abstract

The Intelligent Student Record Search System is a Java-based application developed to demonstrate the application of data structures and algorithms to searching and retrieving information from student profile documents.

The application loads a corpus of student records and supports exact-pattern searches using Knuth–Morris–Pratt (KMP) and Naive Pattern Matching, as well as fuzzy searching using edit distance. It also provides search-history operations and a benchmark that compares Naive Pattern Matching, KMP, and Rabin–Karp against a common set of queries.

KMP uses a Longest Proper Prefix that is also a Suffix (LPS) array to avoid redundant comparisons after a mismatch. Rabin–Karp uses a rolling hash to identify candidate matches. The benchmark compares the matching record paths returned by these three algorithms before measuring their execution times.

The project provides a practical setting for studying string-matching correctness and performance in the context of student-record retrieval.

---

## 3. Project Objectives

1. Implement string-matching algorithms for searching student profile documents.
2. Load and search text records from the project corpus.
3. Compare Naive Pattern Matching, KMP, and Rabin–Karp using identical benchmark queries.
4. Verify that the three algorithms return consistent matching-record results.
5. Measure and compare execution times under a common benchmark procedure.
6. Support fuzzy searching through edit-distance calculations.
7. Provide search-history operations through the interactive application.
8. Demonstrate practical applications of Data Structures and Algorithms in document retrieval.

---

## 4. Algorithms and Supporting Components

### 4.1 Naive Pattern Matching

Naive Pattern Matching checks successive positions in the text and compares the pattern with the corresponding characters. It serves as a baseline for comparison with more advanced string-matching algorithms.

### 4.2 Knuth–Morris–Pratt (KMP)

KMP preprocesses the pattern to construct an LPS array. The array allows the algorithm to skip comparisons that are unnecessary after a mismatch. Its worst-case time complexity is \(O(n+m)\), where \(n\) is the text length and \(m\) is the pattern length.

### 4.3 Rabin–Karp

Rabin–Karp uses a rolling hash to identify candidate pattern matches as the search window moves through the text. Candidate matches are verified as required to account for hash collisions.

### 4.4 Edit Distance and Fuzzy Search

The application provides fuzzy search based on edit distance. The corpus-search implementation uses a configured maximum edit-distance threshold to identify sufficiently close matches.

### 4.5 Additional Algorithm Implementations

The source tree also contains implementations and/or test classes for the Z algorithm, Aho–Corasick, suffix arrays, Dinic’s algorithm, Edmonds–Karp, Ford–Fulkerson, and bipartite matching. These are separate algorithm components and are not presented as options in the main application menu unless explicitly integrated there.

### 4.6 Supporting Components

- Student-record corpus loading
- Query normalization
- Matching-record collection
- Search-history viewing and clearing
- Cross-algorithm result comparison
- Execution-time benchmarking

---

## 5. Current Phase

**Review 2 – Pattern Matching Implementation**

The current phase focuses on corpus-based searching, matching-result consistency, and performance evaluation. The interactive application provides the following menu operations:

1. Search student records using KMP.
2. Perform fuzzy search using edit distance.
3. View search history.
4. Clear search history.
5. Search using Naive Pattern Matching.
6. Run the algorithm benchmark.
7. Exit the application.

Rabin–Karp is used by the benchmark for algorithm comparison; it is not a separate search option in the main menu.

---

## 6. System Workflow

```text
Student Record Corpus
         |
         v
    Corpus Loader
         |
         v
   Loaded Student Records
         |
         v
       User Query
         |
         +-------------------+-------------------+
         |                   |                   |
         v                   v                   v
       Naive                KMP             Rabin–Karp
         |                   |                   |
         +-------------------+-------------------+
                             |
                             v
               Collect Matching Record Paths
                             |
                             v
                 Verify Result Consistency
                             |
                             v
                  Measure Execution Time
                             |
                             v
                    Analyze Results
```

For interactive use, the application also supports fuzzy searching and search-history operations.

---

## 7. Requirements

- Java Development Kit (JDK) 21 or a compatible later version.
- Java source files in `src/algorithms`.
- Student-record text files in `corpus/student-records`.

The application and benchmark expect to be launched from the project root unless a corpus path is explicitly supplied to the benchmark.

---

## 8. Build and Execution

### 8.1 Compile the Java Sources

Run the following command from the project root in PowerShell:

```powershell
javac -d bin src/algorithms/*.java
```

### 8.2 Run the Application

```powershell
java -cp bin algorithms.Main
```

Follow the displayed menu to select a search operation, view or clear search history, run the benchmark, or exit.

---

## 9. Test Execution

The following PowerShell script runs the listed test classes after compilation. Each class must exist in the source tree and support execution through a `main` method.

```powershell
$tests = @(
    "NaivePatternMatchingTest",
    "KMPTest",
    "RabinKarpTest",
    "EditDistanceTest",
    "SuffixArrayTest",
    "DinicTest",
    "EdmondsKarpTest",
    "FordFulkersonTest",
    "BipartiteMatchingTest",
    "AhoCorasickTest",
    "ZAlgorithmTest"
)

foreach ($test in $tests) {
    java -cp bin "algorithms.$test"
}
```

The classes listed above were executed against the uploaded project archive after compilation. Each process exited successfully. Some test classes print demonstrations or expected results rather than using a formal unit-testing framework, so successful process completion should not be interpreted as exhaustive proof of correctness.

---

## 10. Benchmarking

### 10.1 Run the Benchmark

From the project root, execute:

```powershell
java -cp bin algorithms.AlgorithmBenchmark
```

The benchmark also accepts an optional corpus-directory argument:

```powershell
java -cp bin algorithms.AlgorithmBenchmark corpus/student-records
```

### 10.2 Benchmark Queries

The benchmark evaluates the following six queries:

- `solidity`
- `docker`
- `blockchain`
- `image processing`
- `human computer interaction`
- `benchmark-no-match-934872`

### 10.3 Methodology

The benchmark implementation:

1. Loads `.txt` files from the corpus directory and sorts their relative paths.
2. Converts record contents to lowercase using `Locale.ROOT`.
3. Searches all six queries using Naive Pattern Matching, KMP, and Rabin–Karp.
4. Compares the matching record paths returned by the three algorithms and stops if the results differ.
5. Performs three warm-up batches per algorithm.
6. Performs seven timed batches per algorithm.
7. Measures elapsed time with `System.nanoTime()`.
8. Reports the median time for each algorithm.

Each timed batch searches all six queries across the loaded corpus and collects matching-record paths. The reported times are for the complete batch, not for a single query.

### 10.4 Verification Results

The uploaded project archive contained 1,500 `.txt` student records. After compiling the Java sources, the benchmark was executed against that corpus using OpenJDK 21.0.12.1 in the verification environment.

The benchmark reported consistent matching-record paths for all three algorithms and the following counts:

| Search Query | Matching Records |
|---|---:|
| `solidity` | 99 |
| `docker` | 183 |
| `blockchain` | 150 |
| `image processing` | 168 |
| `human computer interaction` | 159 |
| `benchmark-no-match-934872` | 0 |

### 10.5 Median Batch Execution Times

The benchmark run in the verification environment reported the following median times:

| Algorithm | Median Batch Time |
|---|---:|
| Naive Pattern Matching | 110.677 ms |
| Knuth–Morris–Pratt (KMP) | 46.405 ms |
| Rabin–Karp | 627.392 ms |

**Interpretation:** These measurements describe one run in a specific environment. Execution times can vary with hardware, operating-system activity, Java runtime, and corpus contents. They should not be treated as universal performance guarantees. Re-run the benchmark on the target machine before using these timings as final submission results.

---

## 11. Project Scope and Future Development

The current application focuses on exact pattern searching, fuzzy searching, search-history operations, result-consistency checks, and benchmarking of the three string-matching algorithms.

Other algorithms present in the source tree can be tested independently. Their presence does not imply that they are integrated into the interactive search workflow. Further integration and functionality should follow the project requirements and be documented after implementation and verification.

---

## 12. Conclusion

The Intelligent Student Record Search System demonstrates the application of string-matching algorithms to student-record retrieval. Its interactive application supports KMP-based exact searching, Naive Pattern Matching, fuzzy searching through edit distance, and search-history operations. Its benchmark compares Naive Pattern Matching, KMP, and Rabin–Karp using a shared query set and verifies result consistency before measuring execution time.

Compilation, the listed executable test classes, and the benchmark were run against the uploaded project archive. The benchmark confirmed consistent matching results for the six queries in that environment. Performance measurements remain environment-dependent and should be reproduced when preparing final evaluation results.

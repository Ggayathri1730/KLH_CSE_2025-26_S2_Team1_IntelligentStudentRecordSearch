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

The Intelligent Student Record Search System is a Data Structures and Algorithms project designed to facilitate the efficient retrieval of information from a corpus of student profile documents.

The system processes student records and performs pattern-based searches to identify documents containing user-specified keywords or phrases. Depending on the information available in the corpus, searchable attributes may include student names, technical skills, projects, certifications, and interests.

The current implementation incorporates three string-matching algorithms: Naive Pattern Matching, Knuth–Morris–Pratt (KMP), and Rabin–Karp. The KMP algorithm uses a Longest Proper Prefix that is also a Suffix (LPS) array to reduce redundant character comparisons, whereas Rabin–Karp employs a rolling hash to identify candidate matches.

The project evaluates these algorithms by comparing their matching results and measuring their execution times under a common workload. The current phase emphasizes implementation, result consistency, and performance evaluation in a practical student-record retrieval scenario.

---

## 3. Project Objectives

1. Implement string-matching algorithms for searching student profile documents.
2. Develop a corpus-based mechanism for retrieving relevant student records.
3. Compare Naive Pattern Matching, KMP, and Rabin–Karp using identical search queries.
4. Verify the consistency of matching results produced by the implemented algorithms.
5. Measure and compare algorithm execution times using a common benchmarking procedure.
6. Evaluate the practical applicability of string-matching algorithms to document retrieval.
7. Extend the system in subsequent phases according to the project requirements and course objectives.

---

## 4. Algorithms and Data Structures

### 4.1 Naive Pattern Matching

Naive Pattern Matching examines successive positions in the text and compares the search pattern with the corresponding text characters. It provides a baseline for evaluating the performance of more advanced string-matching algorithms.

### 4.2 Knuth–Morris–Pratt (KMP)

The KMP algorithm preprocesses the search pattern to construct an LPS array. This array enables the algorithm to avoid redundant comparisons after a mismatch. Its worst-case time complexity is \(O(n+m)\), where \(n\) denotes the text length and \(m\) denotes the pattern length.

### 4.3 Rabin–Karp

The Rabin–Karp algorithm uses hashing to identify candidate pattern matches within a text. Its rolling-hash mechanism supports efficient hash updates as the search window advances. Candidate matches are verified as required to account for hash collisions.

### 4.4 Supporting Components

The system also includes supporting functionality for:

- Student-record corpus loading
- Query processing and text normalization
- Matching-record collection
- Cross-algorithm result comparison
- Execution-time measurement and benchmarking

Additional algorithms and data structures may be incorporated in future phases in accordance with the project requirements.

---

## 5. Current Phase Status

**Review 2 – Pattern Matching Implementation**

The current phase focuses on implementing and evaluating string-matching algorithms for student-record retrieval.

The principal activities include:

- Searching the student-record corpus using the implemented algorithms.
- Executing identical queries across the algorithms.
- Comparing the resulting matching-record paths.
- Checking result consistency before performance measurement.
- Measuring execution times using a common benchmark procedure.
- Documenting the experimental results and relevant limitations.

The available application operations are determined by the current Java source code.

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
                 Compare Matching Results
                             |
                             v
                 Verify Result Consistency
                             |
                             v
                  Benchmark Execution Time
                             |
                             v
                    Analyze Results
```

---

## 7. Build and Execution

### 7.1 Prerequisites

- A Java Development Kit (JDK) compatible with the project source code.
- Java source files located in `src/algorithms`.
- Student-record text files located in `corpus/student-records`.

### 7.2 Compilation

Execute the following command from the project root in PowerShell:

```powershell
javac -d bin src/algorithms/*.java
```

### 7.3 Application Execution

```powershell
java -cp bin algorithms.Main
```

The application uses the student-record corpus for searching. Available menu options and their corresponding operations are defined by the current implementation of `algorithms.Main`.

---

## 8. Test Execution

The following PowerShell script can be used to execute the listed test classes after compilation, provided that each class exists in the source tree and supports execution through a `main` method.

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

Test results should be considered successful only when the corresponding test execution completes and confirms the expected behavior. The test list should reflect the classes available in the final project version.

---

## 9. Performance Benchmarking

The benchmark component, when available in the project source, evaluates Naive Pattern Matching, KMP, and Rabin–Karp under a common workload.

### 9.1 Benchmark Execution

```powershell
java -cp bin algorithms.AlgorithmBenchmark
```

### 9.2 Benchmark Queries

The reported benchmark uses the following search queries:

- `solidity`
- `docker`
- `blockchain`
- `image processing`
- `human computer interaction`
- `benchmark-no-match-934872`

### 9.3 Benchmark Methodology

According to the supplied benchmark description, the procedure:

1. Searches the corpus using all six queries.
2. Normalizes records and queries using `Locale.ROOT`.
3. Collects matching-record paths for each algorithm.
4. Compares the results produced by the three algorithms.
5. Stops before timing if the matching results differ.
6. Performs three warm-up batches and seven timed batches per algorithm.
7. Measures elapsed time using `System.nanoTime()`.
8. Reports the median of the timed batches.

Each batch includes searches for all six queries across the corpus and the collection of matching-record paths.

### 9.4 Reported Matching Counts

The following values were supplied for a reported verification run using Java 21.0.9.

| Search Query | Reported Matching Records |
|---|---:|
| `solidity` | 99 |
| `docker` | 183 |
| `blockchain` | 150 |
| `image processing` | 168 |
| `human computer interaction` | 159 |
| `benchmark-no-match-934872` | 0 |

### 9.5 Reported Median Execution Times

| Algorithm | Reported Median Time |
|---|---:|
| Naive Pattern Matching | 102.183 ms |
| Knuth–Morris–Pratt (KMP) | 123.963 ms |
| Rabin–Karp | 518.937 ms |

**Note:** These measurements are reported results supplied for the project documentation and have not been independently reproduced here. Execution times may vary according to hardware, runtime conditions, corpus contents, and implementation details. The values should be presented as independently verified results only after the benchmark has been reproduced successfully.

---

## 10. Project Scope and Future Development

The current phase is concerned with string matching, corpus-based retrieval, matching-result consistency, and execution-time benchmarking.

Future development may incorporate additional algorithms and capabilities in accordance with the project requirements and the progression of the DSA-III course. Any additional functionality should be documented as implemented only after its integration and behavior have been verified.

---

## 11. Conclusion

The Intelligent Student Record Search System demonstrates the application of string-matching algorithms to the retrieval of information from student profile documents. The current phase focuses on implementing Naive Pattern Matching, Knuth–Morris–Pratt, and Rabin–Karp, verifying the consistency of their matching results, and evaluating their execution performance.
The project provides a practical foundation for studying algorithmic correctness and performance under a common workload. Source-code validation, test execution, and reproducible benchmarking are essential to ensuring that the final documentation accurately represents the implemented system.

# Intelligent Student Record Search System

**Course:** Data Structures and Algorithms – 3  
**Team:** 1  
**Supervisor:** Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
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

The Intelligent Student Record Search System is a Data Structures and Algorithms project developed to facilitate efficient searching and retrieval of information from a collection of student profile documents.

The system processes a corpus of student records and applies string-matching algorithms to identify documents containing user-specified patterns. The searchable information may include student names, technical skills, projects, certifications, interests, and other attributes present in the records.

The current implementation focuses on three pattern-matching algorithms: Naive Pattern Matching, Knuth–Morris–Pratt (KMP), and Rabin–Karp. KMP employs the Longest Proper Prefix that is also a Suffix (LPS) array to reduce redundant character comparisons. Rabin–Karp uses a rolling hash to identify candidate matches, followed by character-level verification where necessary.

The project demonstrates the implementation and comparative evaluation of string-matching algorithms in a practical student-record retrieval scenario. Its current phase emphasizes matching-result consistency and execution-time benchmarking.

---

## 3. Objectives

1. Implement string-matching algorithms for searching student records.
2. Load and process student profile documents from the project corpus.
3. Compare Naive Pattern Matching, KMP, and Rabin–Karp using identical search queries.
4. Verify the consistency of matching results produced by the implemented algorithms.
5. Measure and compare execution times using a common benchmarking procedure.
6. Evaluate the practical behavior of different string-matching approaches on the same dataset.
7. Extend the system in subsequent phases according to the project requirements and course syllabus.

---

## 4. Algorithms and Data Structures

### 4.1 Pattern-Matching Algorithms

- **Naive Pattern Matching:** Examines possible pattern positions in the text and compares characters to identify matches.
- **Knuth–Morris–Pratt (KMP):** Uses an LPS array to avoid repeating comparisons that can be skipped based on previously matched characters.
- **Rabin–Karp:** Uses a rolling hash to identify candidate pattern matches and verifies candidate matches as required.

### 4.2 Supporting Components

- Student record corpus loading
- Query processing and text normalization
- Matching-record collection
- Cross-algorithm result comparison
- Execution-time measurement and benchmarking

Additional algorithms may be introduced in future phases after implementation and testing.

---

## 5. Current Phase: Review 2

**Focus:** Pattern Matching and Performance Evaluation

The current phase addresses the implementation and evaluation of the three pattern-matching algorithms.

The principal activities are:

- Searching student records using user-specified patterns.
- Executing the implemented algorithms against common queries.
- Comparing the resulting matching-record paths.
- Checking result consistency before performance measurement.
- Measuring execution time under a common benchmark procedure.
- Documenting observed results and experimental limitations.

The available application operations are determined by the current implementation of the Java source files.

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
                   Analyze the Results
```

---

## 7. Build and Execution Instructions

### 7.1 Prerequisites

- Java Development Kit (JDK) compatible with the project source code.
- Project source files located in `src/algorithms`.
- Student record text files located in `corpus/student-records`.

### 7.2 Compile the Source Files

Execute the following commands from the project root in PowerShell:

```powershell
javac -d bin src/algorithms/*.java
```

### 7.3 Run the Application

```powershell
java -cp bin algorithms.Main
```

The application loads student records from the configured corpus directory. Available menu options and their corresponding operations are defined by the current implementation of `algorithms.Main`.

---

## 8. Test Execution

The project includes test classes for different algorithm implementations. The following PowerShell script can be used to run the listed classes after compilation, provided that each class exists and supports execution through a `main` method.

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

Each test should be considered successful only after its execution completes and its output confirms the expected behavior. The list should be updated to reflect the test classes actually present in the repository.

---

## 9. Performance Benchmarking

The benchmark component, when present in the project source, compares Naive Pattern Matching, KMP, and Rabin–Karp under a common workload.

### 9.1 Benchmark Execution

```powershell
java -cp bin algorithms.AlgorithmBenchmark
```

### 9.2 Benchmark Queries

The reported benchmark uses the following six queries:

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
3. Collects matching record paths for each algorithm.
4. Compares the results produced by the three algorithms.
5. Stops before timing if the matching results differ.
6. Performs three warm-up batches and seven timed batches per algorithm.
7. Measures elapsed time using `System.nanoTime()`.
8. Reports the median of the timed batches.

Each batch includes searches for all six queries across the corpus and the collection of matching record paths.

### 9.4 Reported Matching Counts

The following matching counts were supplied for a reported verification run using Java 21.0.9.

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

**Experimental note:** These values represent the supplied benchmark results and have not been independently reproduced as part of this document. Execution times may vary depending on hardware, runtime conditions, corpus contents, and implementation details. The reported values should be retained as verified results only after reproducing the benchmark.

---

## 10. Scope and Future Development

The current phase is limited to pattern matching, student-record searching, matching-result consistency, and execution-time benchmarking.

Further functionality may be developed according to the project requirements and the progression of the DSA-3 course. Any additional algorithm or feature should be documented as implemented only after its source code and behavior have been verified.

---

## 11. Conclusion

The Intelligent Student Record Search System applies string-matching techniques to the retrieval of information from student profile documents. The current phase focuses on implementing Naive Pattern Matching, Knuth–Morris–Pratt, and Rabin–Karp, comparing their matching results, and measuring their execution performance.

The project provides a practical setting for studying algorithm correctness and performance under a common workload. Further validation through source-code inspection, test execution, and reproducible benchmarking will ensure that the final documentation accurately reflects the implemented system.

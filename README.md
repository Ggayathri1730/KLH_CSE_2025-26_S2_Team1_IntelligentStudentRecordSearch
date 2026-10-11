# Intelligent Student Record Search System

**Course:** Data Structures and Algorithms – 3  
**Team:** 1  
**Supervisor:** Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Current Phase:** Pattern/String Matching Implementation – Review 2

---

## Student Details

| S. No. | Student Name | Roll Number |
|---:|---|---|
| 1 | Guttikonda Gayathri | 2520030465 |
| 2 | Sai Sowseelya Vedula | 2520030260 |
| 3 | Somana Divya Sai | 2520039622 |

---

## Abstract

The Intelligent Student Record Search System is a Data Structures and Algorithms project designed to search and retrieve information from a corpus of student profile documents.

The system processes student profile files and applies string-matching algorithms to locate records containing user-specified patterns. The records may contain information such as names, skills, projects, certifications, and interests, depending on the available corpus data.

The current pattern-matching implementation includes Naive Pattern Matching, Knuth–Morris–Pratt (KMP), and Rabin–Karp. KMP uses a Longest Proper Prefix that is also a Suffix (LPS) array to avoid unnecessary comparisons, while Rabin–Karp uses a rolling hash to identify candidate matches.

The project demonstrates the implementation, correctness comparison, and performance measurement of string-matching algorithms in a student-record search scenario.

---

## Objectives

1. Implement pattern-matching algorithms for searching student records.
2. Load and search student information stored in the project corpus.
3. Compare Naive Pattern Matching, KMP, and Rabin–Karp using the same queries.
4. Verify that the algorithms produce consistent matching-record results.
5. Measure and compare algorithm execution times under a common benchmark procedure.
6. Develop additional search capabilities as required in future project phases.
7. Demonstrate practical applications of Data Structures and Algorithms in student-record retrieval.

---

## Algorithms and Data Structures

### Current pattern-matching implementation

- Naive Pattern Matching
- Knuth–Morris–Pratt (KMP)
- Rabin–Karp
- LPS array used by KMP
- Rolling hash used by Rabin–Karp
- Student record corpus loading
- Matching-record collection and comparison
- Execution-time benchmarking

Additional algorithms and data structures may be incorporated in future phases according to the course requirements and project scope.

---

## Current Phase Status

### Review 2 – Pattern/String Matching

**Status:** Pattern-matching implementation and benchmarking

The current phase focuses on:

- Searching the student-record corpus using Naive Pattern Matching, KMP, and Rabin–Karp.
- Comparing matching-record results for the same queries.
- Checking result consistency before comparing performance.
- Measuring execution times using a benchmark procedure.
- Reporting the benchmark results with the test environment and methodology.

The application menu and available operations should be interpreted according to the current implementation in `Main.java`.

---

## Project Flow

```text
Student Record Corpus
        |
        v
   Corpus Loader
        |
        v
   Loaded Records
        |
        v
     User Query
        |
        +-------------------+-------------------+
        |                   |                   |
        v                   v                   v
      Naive                KMP             Rabin-Karp
        |                   |                   |
        +-------------------+-------------------+
                            |
                            v
       Compare Matching Record Results
                            |
                            v
              Verify Result Consistency
                            |
                            v
                 Performance Analysis
```

---

## Build and Run

### Prerequisites

- Java Development Kit (JDK) 21 or later, if required by the source code.
- The project source files and student-record corpus.

### Compile the Java sources

Run the following commands from the project root in PowerShell:

```powershell
javac -d bin src/algorithms/*.java
```

### Launch the application

```powershell
java -cp bin algorithms.Main
```

The application uses text files from `corpus/student-records`. The available menu options and their behavior are determined by the current implementation of `algorithms.Main`.

---

## Running Tests

After compilation, the following commands can be used to run the listed test classes, provided each class exists in the source tree and has a runnable `main` method:

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

A test class should be included in this list only if it exists in the project and is compatible with this execution method. A successful compilation alone does not establish that all tests pass.

---

## Benchmarking

If `AlgorithmBenchmark.java` is present in the project, the benchmark can be launched from the project root using:

```powershell
java -cp bin algorithms.AlgorithmBenchmark
```

The benchmark procedure described for this project searches the corpus using the following six queries:

- `solidity`
- `docker`
- `blockchain`
- `image processing`
- `human computer interaction`
- `benchmark-no-match-934872`

According to the supplied benchmark description, records and queries are normalized using `Locale.ROOT`. The benchmark compares the matching-record paths returned by Naive Pattern Matching, KMP, and Rabin–Karp and stops before timing if their results differ.

The described procedure uses three warm-up batches and seven timed batches per algorithm. It measures elapsed time with `System.nanoTime()` and reports the median. Each batch searches the corpus for all six queries and collects the matching record paths.

### Previously reported benchmark results

The following values were supplied from a reported run using Java 21.0.9. They are retained here as reported results and have not been independently reproduced in this document.

| Query | Reported matching records |
|---|---:|
| `solidity` | 99 |
| `docker` | 183 |
| `blockchain` | 150 |
| `image processing` | 168 |
| `human computer interaction` | 159 |
| `benchmark-no-match-934872` | 0 |

### Reported median batch times

| Algorithm | Reported median |
|---|---:|
| Naive Pattern Matching | 102.183 ms |
| KMP | 123.963 ms |
| Rabin–Karp | 518.937 ms |

**Note:** Execution times depend on the machine, Java runtime, corpus contents, and system load. These measurements describe the reported run and should not be interpreted as guaranteed performance. Re-run the benchmark before presenting these values as independently verified results.

---

## Project Scope

The current phase focuses on pattern matching, corpus searching, result consistency, and performance benchmarking. Other algorithms or capabilities should be described as implemented only after their source code and behavior have been verified.

Future development will follow the project requirements and the progression of the DSA-3 course.

---

## Conclusion

The Intelligent Student Record Search System demonstrates how string-matching algorithms can be applied to searching student profile documents. Its current phase focuses on implementing Naive Pattern Matching, KMP, and Rabin–Karp, comparing their matching results, and measuring their execution performance.

Further validation of the source files, test execution, and benchmark measurements is necessary before claiming that every documented feature and result has been independently verified.

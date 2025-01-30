Automation suite for validating car valuation data against motorway.co.uk

## Features
- 🚗 Car Registration number extraction using regex
- 🌐 Browser automation with Selenium WebDriver
- ✅ TestNG data-driven testing
- 📁 File comparison with expected results
- 🛠 Page Object Model design pattern

## Prerequisites
- Java 17+
- Maven 3.8+
- Any browser (chrome, firefox, edge etc)

## Quick Start
1. Clone repository:
git clone https://github.com/ka555/identity.git

2. Configuration
Input file: src/test/resources/car_input.txt
Output file: src/test/resources/car_output.txt
Registration pattern: [A-Z]{2}\d{2}\s?[A-Z]{3}

3. Test Execution
# Run all tests
mvn test

# Generate test reports
mvn surefire-report:report

# Project Structure

src/
├── main/java/      → Page objects and utilities
├── test/java/      → TestNG test classes
└── test/resources/ → Test data files

# Troubleshooting

File Not Found: Verify files exist in resources directory

Assertion Failures: Check actual vs expected in test-output/ folder

Browser Issues: Update browser versions

Sometimes after hitting continuously the search sites like motorway.co.uk, they block the access to the 
search feature and you will have to wait a while.
 No newline at end of file

# Juke Solution - QA Intern Assessment

Automation testing project untuk assessment QA Intern di Juke Solution menggunakan Selenium WebDriver, Cucumber BDD, dan TestNG.

## 🛠 Technology Stack

- **Java 11** - Programming Language
- **Maven** - Build & Dependency Management
- **Selenium WebDriver** - Browser Automation
- **Cucumber** - BDD Framework
- **TestNG** - Test Execution Framework
- **WebDriverManager** - Automatic driver management
- **Page Object Model (POM)** - Design Pattern

## 📁 Project Structure

```
JukeSolution_Assessment_QAIntern/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java                 # Cucumber hooks (setup & teardown)
│       │   ├── pages/
│       │   │   ├── BasePage.java              # Base page with common methods
│       │   │   ├── LoginPage.java             # Login page object
│       │   │   └── DashboardPage.java         # Dashboard page object
│       │   ├── runners/
│       │   │   └── TestRunner.java            # TestNG Cucumber runner
│       │   ├── stepDefinitions/
│       │   │   └── LoginStepDefinitions.java  # Cucumber step definitions
│       │   └── utils/
│       │       ├── ConfigReader.java          # Configuration file reader
│       │       └── DriverManager.java         # WebDriver management
│       └── resources/
│           ├── features/
│           │   └── Login.feature              # Cucumber feature files
│           └── config.properties              # Test configuration
├── test-output/
│   └── cucumber-reports/                      # Generated test reports
├── pom.xml                                    # Maven dependencies
├── testng.xml                                 # TestNG suite configuration
└── README.md                                  # Project documentation
```

## ✅ Prerequisites

Pastikan sistem Anda memiliki:

- **Java JDK 11** atau lebih tinggi
- **Maven 3.6+**
- **IDE** (IntelliJ IDEA / Eclipse)
- **Git** (untuk clone repository)
- Koneksi internet (untuk download dependencies)

## 🚀 Installation

1. **Clone repository:**
   ```bash
   git clone https://github.com/your-username/JukeSolution_Assessment_QAIntern.git
   cd JukeSolution_Assessment_QAIntern
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify installation:**
   ```bash
   mvn clean test -DskipTests
   ```

## ⚙️ Configuration

Edit file `src/test/resources/config.properties` untuk konfigurasi test:

```properties
# Browser Configuration
browser=chrome        # Options: chrome, firefox, edge

# Application URL
url=https://example.com/login

# Test Credentials
username=your_username
password=your_password

# Timeout Settings
implicit.wait=10
explicit.wait=20
page.load.timeout=30
```

## 🏃 Running Tests

### 1. Run All Tests
```bash
mvn clean test
```

### 2. Run with TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### 3. Run with Parallel Execution
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

### 4. Run Specific Feature
```bash
mvn test -Dcucumber.options="src/test/resources/features/Login.feature"
```

### 5. Run with Tags
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### 6. Run from IDE
- Right-click pada `TestRunner.java` → Run As → TestNG Test
- Atau right-click pada `testng.xml` → Run As → TestNG Suite

## 📊 Test Reports

Setelah eksekusi test selesai, report akan tersedia di:

### 1. Cucumber HTML Report
- **Location:** `test-output/cucumber-reports/cucumber-html-report.html`
- **How to view:** Buka file dengan browser
- **Content:** 
  - Scenario pass/fail status
  - Step details
  - Screenshots (pada failure)
  - Execution time

### 2. Cucumber JSON Report
- **Location:** `test-output/cucumber-reports/cucumber.json`

### 3. TestNG Report
- **Location:** `test-output/index.html`
- **How to view:** Buka file dengan browser
- **Content:**
  - Suite summary
  - Test method details
  - Execution timeline

### 4. Console Output
- Real-time execution logs di console
- Scenario start/end information
- Pass/Fail status untuk setiap step

## 🎯 Features

### Current Features

#### Login Feature (`Login.feature`)
- ✅ **Valid Login:** Test login dengan kredensial yang valid
- ✅ **Invalid Login:** Test login dengan kredensial yang invalid
- ✅ **Empty Credentials:** Test login tanpa input username/password

### Test Scenarios Example

```gherkin
@smoke @login
Scenario: User login dengan kredensial valid
  Given User berada di halaman login
  When User memasukkan username "validUser"
  And User memasukkan password "validPass"
  And User klik tombol login
  Then User berhasil login dan melihat dashboard
```

## 📝 Adding New Tests

### 1. Create Feature File
Buat file `.feature` baru di `src/test/resources/features/`:

```gherkin
Feature: Feature Name
  
  Scenario: Scenario description
    Given precondition
    When action
    Then expected result
```

### 2. Create Step Definitions
Buat class di `src/test/java/stepDefinitions/`:

```java
@Given("precondition")
public void precondition() {
    // Implementation
}
```

### 3. Create Page Object
Buat class di `src/test/java/pages/`:

```java
public class NewPage extends BasePage {
    // Page elements and methods
}
```

## 🚀 Parallel Testing

Project ini mendukung parallel test execution untuk mempercepat waktu eksekusi test.

### Configuration

Parallel testing dikonfigurasi di `testng.xml`:

```xml
<suite name="Test Suite" parallel="methods" thread-count="4" data-provider-thread-count="4">
```

**Parameters:**
- `parallel="methods"` - Menjalankan test methods secara parallel
- `thread-count="4"` - Jumlah thread untuk eksekusi parallel (default: 4)
- `data-provider-thread-count="4"` - Jumlah thread untuk Cucumber scenarios

### Benefits

✅ **Faster Execution:** Mengurangi waktu eksekusi test hingga 50-70%
✅ **Resource Optimization:** Memanfaatkan multi-core CPU secara optimal
✅ **Scalability:** Mudah di-scale untuk test suite yang besar

### Thread Count Recommendation

| CPU Cores | Recommended Thread Count |
|-----------|-------------------------|
| 2 cores   | 2 threads              |
| 4 cores   | 4 threads              |
| 6 cores   | 4-6 threads            |
| 8+ cores  | 6-8 threads            |

### Running Parallel Tests

#### Option 1: Via TestNG XML (Recommended)
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

#### Option 2: Via Maven Command Line
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

#### Option 3: Custom Thread Count
```bash
mvn clean test -DthreadCount=8 -DdataproviderthreadCount=8
```

### Important Notes

⚠️ **Thread Safety:** Pastikan test Anda thread-safe
- Setiap thread memiliki WebDriver instance sendiri (menggunakan ThreadLocal di DriverManager)
- Hindari shared state antar test
- Test data harus independen

⚠️ **Browser Instances:** 
- Setiap thread akan membuka browser instance terpisah
- Pastikan sistem memiliki resource yang cukup (RAM minimum 8GB untuk 4 threads)

⚠️ **Monitoring:**
- Watch CPU dan memory usage selama parallel execution
- Adjust thread count jika sistem overload

### Disable Parallel Execution

Jika ingin disable parallel execution, ubah di `TestRunner.java`:

```java
@DataProvider(parallel = false)  // Set to false
public Object[][] scenarios() {
    return super.scenarios();
}
```

Atau di `testng.xml`:

```xml
<suite name="Test Suite" parallel="none">
```

## 🔧 Best Practices

1. **Follow POM Pattern:** Pisahkan locators dan actions di Page Objects
2. **Use Meaningful Names:** Beri nama yang jelas untuk methods dan variables
3. **Wait Strategies:** Gunakan explicit wait, hindari Thread.sleep()
4. **Independent Tests:** Setiap test harus independen dan tidak bergantung pada urutan eksekusi
5. **Clean Code:** Ikuti Java coding standards
6. **Comments:** Tambahkan comments untuk logic yang kompleks
7. **Thread Safety:** Pastikan shared resources menggunakan ThreadLocal atau synchronization
8. **Resource Management:** Close browser dan resources dengan proper di @After hooks

## 🐛 Troubleshooting

### Browser tidak terbuka
- Pastikan WebDriverManager berfungsi dengan baik
- Check koneksi internet untuk download driver

### Test gagal dengan timeout
- Increase timeout di `config.properties`
- Check selector di Page Objects

### Multiple browsers terbuka
- Pastikan hanya jalankan dari `TestRunner.java`
- Jangan jalankan dari `BaseTest.java`

### Parallel execution issues
- **Too many browser instances:** Reduce thread count di testng.xml
- **Memory issues:** Close unused applications, increase heap size: `mvn test -DargLine="-Xmx2g"`
- **Flaky tests:** Ensure test independence, use proper waits
- **Port conflicts:** Jika menggunakan Selenium Grid, pastikan ports tidak conflict

### System resource exhausted
```bash
# Reduce thread count
mvn clean test -DthreadCount=2

# Or disable parallel execution temporarily
mvn clean test -Dparallel=none
```

- **Email:** your.email@example.com
- **GitHub Issues:** [Create an issue](https://github.com/your-username/repo/issues)

---

**Happy Testing! 🚀**

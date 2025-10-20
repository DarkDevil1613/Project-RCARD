# 🎓 Report Card & CGPA Generator

### Project Name: **Project RCAED**  
**Language:** Java  
**Developed by:** [DarkDevil1613 (Mr. Rohit)](https://www.linkedin.com/in/rohit-goday) & [Akhil (asenaislive)](https://github.com/asenaislive)

---

## 🧩 Overview
This Java console-based project automates the process of generating student academic reports.  
It collects student details, marks for multiple subjects and semesters, converts them to grades and grade points, calculates SGPA & CGPA, and finally exports the entire formatted report as a `.txt` file.  

The project aims to simplify result computation for BTech students while maintaining accuracy and flexibility for different departments.

---

## ⚙️ Features
- 📚 Supports **multiple departments** (CSE, ECE, EEE, MECH, CIVIL)
- 🧾 Handles **two semesters (I-I and I-II)**  
- 🔢 Converts **marks → grades → grade points** automatically
- 🧮 Calculates **SGPA and CGPA**
- 📄 Generates a **professionally formatted report file** (`.txt`)
- 🧠 Built-in **error handling** for invalid marks or input
- 📱 Exports reports compatible with both **PC and Android file systems**

---

## 🏗️ Project Structure
```
├── StudentData.java         # Handles subject data, marks input, grade calculation
├── CgpaCalculator.java      # Main controller for report generation and validation
├── /output_reports          # Folder where generated .txt reports are stored
```

---

## 🚀 How It Works
1. Run the program.  
2. Enter student details — name, registration number, and department.  
3. Input marks for both semesters.  
4. The program:
   - Validates marks
   - Converts marks to grades and points
   - Calculates SGPA for each semester
   - Computes final CGPA
5. Finally, it generates a text report saved to the system or mobile storage.

---

## 🧮 Example Output
```
==============================================================
                STUDENT ACADEMIC REPORT
==============================================================
Name            : Rohit Kumar
Reg No          : 22031A05XX
Department      : Computer Science Engineering
--------------------------------------------------------------
SEMESTER I RESULTS (SGPA: 8.92)
Mathematics I                        | A+  | 27.00
C Programming                        | O   | 30.00
...
--------------------------------------------------------------
SEMESTER II RESULTS (SGPA: 9.01)
Data Structures using C              | O   | 30.00
...
--------------------------------------------------------------
FINAL CGPA                           : 8.97
==============================================================
```

---

## 🤝 Collaboration
This project was collaboratively developed by:

| Developer | GitHub | Role |
|------------|---------|------|
| **Mr. Rohit (DarkDevil1613)** | [DarkDevil1613](https://github.com/DarkDevil1613) | Core Java Development, Data Validation, File Handling |
| **Akhil (asenaislive)** | [asenaislive](https://github.com/asenaislive) | Logic Structure, Testing, Optimization, Collaboration Setup |

---

## 💻 Tech Stack
- **Language:** Java  
- **IDE:** NetBeans / VS Code  
- **Version Control:** Git & GitHub Desktop  
- **Output Format:** Text File (`UTF-8 encoded`)  

---

## 📜 License
This project is open-source and available under the **MIT License**.  
You are free to use, modify, and distribute it with proper credit.

---

## 🔗 Connect with Us

- 👨‍💻 **Mr. Rohit (DarkDevil1613)**  
  [LinkedIn – Rohit Goday](https://www.linkedin.com/in/rohit-goday)  
  [GitHub – DarkDevil1613](https://github.com/DarkDevil1613)

- 👨‍💻 **Akhil (asenaislive)**  
  [LinkedIn – Akhil](https://www.linkedin.com/in/akhil-pasupulati)  
  [GitHub – asenaislive](https://github.com/asenaislive)

---

> _“A grade is just a number, but this project makes sure it’s a precise one.”_

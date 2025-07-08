<h1 align="center">✈️ OptiFly — Smart Air Route Optimization System</h1>

<p align="center">
  🚀 A Java Servlet-based web application to select the most optimal flight routes based on <b>cost</b> and <b>time</b>, with a dedicated admin portal for managing flight data.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Java%20Servlets-007396?style=for-the-badge&logo=apachetomcat&logoColor=white"/>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>
  <img src="https://img.shields.io/badge/Tomcat-FFA500?style=for-the-badge&logo=apachetomcat&logoColor=white"/>
</p>
<br>

---
## 🚀 Features

✅  Find most efficient flight routes based on **time** and **cost**  
✅  Admin dashboard to **add, delete, update and view flights**  
✅  User-friendly, clean **HTML/CSS/JavaScript frontend**  
✅  Dijkstra's algorithm implementation in backend Java code  
✅  Deployed locally via **Apache Tomcat Server**

<br>

---
## 🛠️ Tech Stack

| 🖥️ Technology | ⚙️ Description |
|:-------------|:----------------|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) | Core backend language |
| ![Servlets](https://img.shields.io/badge/Java%20Servlets-007396?style=for-the-badge&logo=apachetomcat&logoColor=white) | Backend application using Servlets |
| ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) | Structure of web pages |
| ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) | Styling web pages |
| ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) | Client-side interactions |
| ![Tomcat](https://img.shields.io/badge/Tomcat-FFA500?style=for-the-badge&logo=apachetomcat&logoColor=white) | Web server to deploy Java apps |
| ![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white) | Data exchange format |

<br>

---
## 📁 Project Directory Structure

```
OptiFly/
├── 📂 WEB_INF/
│   ├── 📂 classes/
│   │   ├── 📂 administrator/
│   │   ├── 📂 clientBackend/
│   │   ├── 📂 main/
│   │   └── 📂 servlets/
│   ├── 📂 lib/
│   │   └── 📄 json-20240303.jar
│   └── 📄 web.xml
├── 📂 admin/
├── 📂 client/
├── 📂 css/
├── 📂 js/
├── 📄 index.html
├── 📄 about.html
```
<br>

---
## 📷 Screenshots

| 📍 Feature | 📸 Screenshot |
|:----------------|:----------------|
| Home Page | *[Insert Screenshot]* |
| Admin Dashboard | *[Insert Screenshot]* |
| Add Flight | *[Insert Screenshot]* |
| View Optimized Route | *[Insert Screenshot]* |

<br>

---

## 📦 How to Run

### 📌 Prerequisites
- ✅ JDK installed
- ✅ Apache Tomcat (v9+ recommended)
- ✅ `json-20240303.jar` placed inside `WEB_INF/lib/`

<br>

---

### 📌 Compile Java Servlets

```bash
cd webapps/Optifly/WEB_INF/classes/javac -cp "C:\path\to\tomcat\lib\servlet-api.jar;WEB_INF/lib/json-20240303.jar" administrator/*.java clientBackend/*.java main/*.java servlets/*.java
```
<br>

### 🚀 How to Deploy on Tomcat

1. Copy the entire `Optifly` folder into the `webapps/` directory of your Tomcat installation.

2. Start the Tomcat server:

   ```bash
   catalina.bat run
   ```

3. Open your browser and visit:

   ```
   http://localhost:8080/Optifly/
   ```
<br>

---

## 📖 Core Components

* **ConstructGraph.java** — Builds the flight graph from flight data
* **OptimisePath.java** — Dijkstra's algorithm implementation
* **AirPortsCodes.java** — Maps airport codes to their names
* **AddFlightServlet.java** — Adds new flight entry
* **DeleteFlightServlet.java** — Deletes existing flight entry
* **UpdateFlightServlet.java** — Updates flight details
* **OptimisePathServlet.java** — Computes optimized flight route

<br>

---

## 📃 web.xml Servlet Mapping Example

```xml
<servlet>
  <servlet-name>AddFlight</servlet-name>
  <servlet-class>servlets.AddFlightServlet</servlet-class>
</servlet>

<servlet-mapping>
  <servlet-name>AddFlight</servlet-name>
  <url-pattern>/AddFlightServlet</url-pattern>
</servlet-mapping>
```
<br>

---

## 🙌 Developed By

**Abhay Kanojia & Anvesha Rawat**<br>
📅 **15th June 2025**

<br>

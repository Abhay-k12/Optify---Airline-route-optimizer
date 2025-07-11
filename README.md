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

## 📖 Problem Statement
In many cases, travelers face difficulty in choosing optimal flight routes based on factors like time, cost, and connectivity. Existing systems often lack transparent, efficient route optimization, especially when considering multiple connecting flights within a network of airports.

<br>

---

## 💡 Our Solution
OptiFly is a Java Servlet-based web application built to:

- 📊 Allow users to view optimized flight paths based on cost and time
- ✈️ Build backend flight network graph dynamically from database
- ⚙️ Use Dijkstra's algorithm for optimal route finding
- 🛠️ Provide admin tools to add, delete, update, and view flights
- 🖥️ Deliver a clean, responsive frontend for travelers and administrators
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


<div align="center">

<table>
<thead>
<tr>
<th>🖥️ Technology</th>
<th>⚙️ Description</th>
</tr>
</thead>
<tbody>
<tr>
<td><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/></td>
<td>Core backend language</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/Java%20Servlets-007396?style=for-the-badge&logo=apachetomcat&logoColor=white"/></td>
<td>Backend application using Servlets</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/></td>
<td>Structure of web pages</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/></td>
<td>Styling web pages</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/></td>
<td>Client-side interactions</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/Tomcat-FFA500?style=for-the-badge&logo=apachetomcat&logoColor=white"/></td>
<td>Web server to deploy Java apps</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white"/></td>
<td>Data exchange format</td>
</tr>
</tbody>
</table>

</div>


<br>

---
## 📁 Project Directory Structure

```
OptiFly/
├── 📂 WEB_INF/                        # 📦 Web application configs and compiled classes
│   ├── 📂 classes/                    # 📚 Compiled Java .class files directory
│   │   ├── 📂 administrator/          # 🛠️ Admin Servlets and backend classes
│   │   ├── 📂 clientBackend/          # 🧳 Client-side backend logic and helpers
│   │   ├── 📂 main/                   # 🖥️ Terminal-based outputs and test utilities
│   │   └── 📂 servlets/               # 🌐 Java Servlet classes handling HTTP requests
│   ├── 📂 lib/                        # 📦 External libraries / JAR dependencies
│   │   └── 📄 json-20240303.jar       # 📝 JSON library for parsing request-response data
│   └── 📄 web.xml                     # 🗺️ Servlet deployment descriptor (URL mappings)
├── 📂 admin/                          # 👨‍✈️ Admin-side HTML frontend pages
├── 📂 client/                         # 👩‍💼 Client-side HTML frontend pages
├── 📂 css/                            # 🎨 CSS files for styling the application
├── 📂 database/                       # 🚀 Database file for dynamic graph generation
├── 📂 js/                             # ✨ JavaScript files for client-side interactivity
├── 📂 images/                         # 🖼️ Image assets for the frontend UI
├── 📄 index.html                      # 🏠 Home page of the application
├── 📄 about.html                      # ℹ️ About Us page explaining the system and team

```
<br>

## 📸 Preview Images

| 📍 Page / Feature            | 📸 Screenshot                                              |
|:----------------------------|:-----------------------------------------------------------|
| About Page                   | ![About](webapps/Optifly/preViewImages/aboutPre.png)                   |
| Flight Menu Page             | ![Flight Menu](webapps/Optifly/preViewImages/menuPre.png)        |
| Add Flight Page              | ![Add Flight](webapps/Optifly/preViewImages/addPre.png)          |
| Delete Flight Page           | ![Delete Flight](webapps/Optifly/preViewImages/deletePre.png)    |
| Update Flight Page           | ![Delete Flight](webapps/Optifly/preViewImages/updatePre.png)    |
| View Flights                 | ![View Flights ](webapps/Optifly/preViewImages/ViewPre2.png)  |
| Path Optimization Result (1) | ![Optimise Path 1](webapps/Optifly/preViewImages/pathPre.png)  |
| Path Optimization Result (2) | ![Optimise Path 2](webapps/Optifly/preViewImages/pathPre2.png)  |


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

## 🌱 Future Scope
- 📱 Develop a mobile app version for cross-platform use

- 🌍 Integrate real-time flight APIs for live schedule updates

- 📊 Include data visualization for route and price analytics

- 📤 Enable flight booking and seat selection features

- 🔐 Add authentication and user session management

  <br>

  ---
## 📞 Help & Contact  

> 💬 *Got a question, suggestion, or need help with Optifly?*  
> We’re here to assist and collaborate!

<div align="center">

<b>👤 Abhay Kanojia</b>  
<a href="https://www.linkedin.com/in/abhay-kanojia-0461a3341">
  <img src="https://img.shields.io/badge/Connect%20on-LinkedIn-blue?style=for-the-badge&logo=linkedin" alt="LinkedIn - Abhay Kanojia"/>
</a>  

<br/>

<b>👤 Anvesha Rawat</b>  
<a href="https://www.linkedin.com/in/anvesha-rawat-b9a1a0308?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app">
  <img src="https://img.shields.io/badge/Connect%20on-LinkedIn-blue?style=for-the-badge&logo=linkedin" alt="LinkedIn - Anvesha Rawat"/>
</a>


<br>

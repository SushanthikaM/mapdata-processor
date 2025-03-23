# **Map Data Processor**  

## **Overview**  
The **Map Data Processor** is a Java-based application built with **Spring Boot** to process map-related data. It merges location and metadata JSON files, performs analytical operations, and generates insights such as:  
- Counting valid points per type  
- Calculating average ratings  
- Identifying the most-reviewed location  
- Detecting incomplete data  

## **Features**  
✅ **Data Loading:** Reads location and metadata from JSON files  
✅ **Data Merging:** Combines location and metadata based on a common `id`  
✅ **Analysis & Insights:**  
   - Counts valid location points per type  
   - Calculates the average rating per location type  
   - Finds the location with the highest number of reviews  
   - Detects and lists locations with missing data  

## **Technologies Used**  
- **Java** (Spring Boot)  
- **Jackson** (JSON Processing)  
- **Maven** (Dependency Management)  

## **Project Structure**  
```
📦 mapdata-processor
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java/com/example/mapdata_processor
 ┃ ┃ ┃ ┣ 📂 controller   # REST API Controllers
 ┃ ┃ ┃ ┣ 📂 model        # Data Models (Location, Metadata)
 ┃ ┃ ┃ ┣ 📂 service      # Business Logic
 ┃ ┃ ┃ ┣ 📂 utils        # Helper Classes (DataLoader, etc.)
 ┃ ┃ ┃ ┗ Application.java # Main Entry Point
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ locations.json  # Sample Location Data
 ┃ ┃ ┃ ┗ metadata.json   # Sample Metadata
 ┣ 📜 pom.xml            # Project Dependencies
 ┣ 📜 README.md          # Project Documentation
```

## **Installation & Setup**  

### **1️⃣ Clone the Repository**  
```bash
git clone https://github.com/your-username/mapdata-processor.git
cd mapdata-processor
```

### **2️⃣ Build the Project**  
Make sure you have **Java 17+** and **Maven** installed. Then, run:  
```bash
mvn clean install
```

### **3️⃣ Run the Application**  
Start the Spring Boot application:  
```bash
mvn spring-boot:run
```
Let me know if you need any modifications! 🚀😊

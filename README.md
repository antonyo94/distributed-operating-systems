# Distributed Operating Systems

## 🛠 Skills
- Docker
- Docker Image
- Docker Container
- Hadoop
- Map-Reduce
- Java

## 📝 Requirements
*Implement the Anagram Finder algorithm in a Hadoop environment using Docker.*

## 📖 Lessons Learned
- The **Distributed Operating Systems** project introduced me to the **Hadoop** world and its role as a distributed data processing framework using computer clusters.

- Since I did not have access to a large cluster network, I used **Docker** to simulate the environment:
  - Learned about **Docker images** and how to reuse existing ones.
  - Deployed a **Hadoop cluster** by modifying the **docker-compose.yml** file and running containers from the image with the **docker-compose up -d** command.

- Once the cluster was set up, I:
  - Checked the general state of the system.
  - Navigated the file system by accessing the **WebUI namenode**.
  - Learned how to upload and download files from/to **HDFS** using the appropriate commands.

- After configuring the Hadoop cluster, I:
  - Developed **Java** classes to solve the anagram finder problem using the Map-Reduce programming model.
  - Understood how to structure classes for the **Map-Reduce pattern**, with a main class, and separate classes for the **Map** and **Reduce** phases.
  - Gained insight into how a Map-type job and Reduce phase work "behind the scenes".

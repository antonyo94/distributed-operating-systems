# Distributed Operating Systems

## 🛠 Skills
Docker, Docker Image, Docker Container, Hadoop, Map-Reduce, Java

## 📝 Requirements
*Implement the Anagram Finder algorithm in a Hadoop environment using Docker.*

## 📖 Lessons Learned
The **Distributed Operating Systems** project brought me closer to the Hadoop world and its characteristics as a distributed data processing framework, through the use of computer clusters. Furthermore, taking into account that I did not have access to a large cluster network to carry out the trace, I had the opportunity to use another component of software development, namely **Docker**. In fact, through the creation of the project, I was able to understand what a **Docker image** is and how to reuse an existing one. Therefore, I was able to deploy the **Hadoop cluster** necessary for carrying out the trace, by modifying the **docker-compose.yml** file and executing the containers, starting from the image mentioned above, using the **docker-compose up -d command**. Once the cluster was set up, I was able to check the general state of the system and navigate within the file system, by accessing the **WebUI namenode**. Similarly, I was able to learn how to upload and/or download files from/to HDFS using the commands dedicated to these activities.
Once the Hadoop cluster was configured, I proceeded to develop the **Java** classes necessary to resolve the anagram finder problem, required by the trace, through the **Map-Reduce** programming model. I therefore had the opportunity to concretely understand how to divide the responsibilities of the various classes when this Pattern is used, the main class and a class respectively for the Map and Reduce phases, and to clarify how a Map-type job Reduce works "behind the scenes".
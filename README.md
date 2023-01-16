# Guide to dockerize an application
The following information are what you need to dockerized my application. With docker images, you can deploy it to any 
virtual machine, i.e aws-ec2.

* There are two ways of dockerized my application, using docker-network or docker-compose.

# Getting Started

### Reference Documentation

For further reference, please find it below

* [Dockerized a Spring Boot + MySQL Application](https://levelup.gitconnected.com/dockerizing-spring-boot-mysql-application-73e09a485c0a)

* [Guide to Dockerized your Spring Boot application with MySQL database](https://dev.to/devanandukalkar/guide-to-dockerize-your-spring-boot-application-with-mysql-b9g)

* [Deploy your application with EC2, Docker, Spring boot using AWS CLI](https://jrakibi.medium.com/deploy-your-application-with-ec2-docker-spring-boot-using-aws-cli-cb9f81260d29)

# Solution 1 - Using Docker Network
### Step 1 - Create Mysql image and run
* Pull the mysql image: `docker pull mysql:5.7`
* Create network: `docker network create NETWORK_NAME`
* Run the mysql image: `docker run -it --name mysqldb --network=NETWORK_NAME -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=polaris -e MYSQL_USER=sys -e MYSQL_PASSWORD=1234 -d mysql:5.7`
* Verify the db image `docker exec -it <container_id> bash`
* Working with DB `mysql -usys -p1234`
* Working with DB `show databases;`
* Working with DB `use DATABASE_NAME;`

### Step 2 - Create service image and run
* Build the image `docker build -t SERVICE_IMAGE_NAME .`
* Run the service in our network `docker run --network=NETWORK_NAME --name SERVICE_NAME_CONTAINER -p 8089:8089 -d SERVICE_IMAGE_NAME`
* Check the logs `docker logs -f <CONTAINER_ID>`

* Stop the server `docker stop <CONTAINER_ID>`

# Solution 2 - Using Docker-Compose
### Run the command `docker-compose up`
### The rest of checking is similar to what we have when we are using docker-network

# VeloFinder - Find cycling group rides.

![alt text](/screenshots/vfmain.PNG)

# Deployment 
The App is available here -> [VeloFinder](http://velofinder.s3-website.eu-west-2.amazonaws.com/) 


# What is VeloFinder?

VeloFinder is a WebAppp which attemtps to solve a problem many cyslist have. Find other cyslist to ride 
with. Velofinder allows users to cerate their own profile and create group rides. GPX files can be added to
group rides to allow others to see the planned route and distance.

# Languages Used 

BackEnd : 
* MySQL - Database
* SpringBoot (Java) - Server
* AWS RDS
* AWS EC2 

#

Frontend : 
* React TS (TypeScript)
* HTML
* CSS
* JavaScript (ES6)
* AWS S3


# Completed task
![alt text](/screenshots/vfmain.PNG)

The user can search see group rides in thier area by clicking on a cluster point which will zoom into the
cluster revelasing more detailed views of the location and data.Once a point is selected the group ride GPX
data is fetched and displayed to the user.


# Key Learning 

![alt text](/screenshots/login.PNG)

![alt text](/screenshots/signup.PNG)

A big learning point of this project was the addition of security. During the development of velofinder I learnt 
about various methods of user authentication and validation . Orignally I built Velofinder using Basic auth but decided
it would be more apprpriate to learn and implement JWT authentication. I also able to also improve my knoweldge and understanding 
on security such as Cross Origin Resource Sharing. 

![alt text](/screenshots/profileride.PNG)
When a user creates a new group ride , they are also able to upload their GPX data file for this group ride. Un authenticated users
are able to see this GPX file and ride on the main page while authenticated users are able to track the rides that they have created
and uploaded.

![alt text](/screenshots/createdrideselect.PNG)


# Future Aims 

* Write Unit Tests to check that code works as intended.
* Improve Map performance
* Improve the organizational structure of the project.
* Further add to the project to allow users to sign up to join a group ride


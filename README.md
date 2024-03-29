# Project Birdnest
This small web-application is a solution to a pre-assignment for a summer internship application. The task for this assignment was to track violations of a supposed 100 meter no-drone-zone (NDZ) around a fictitious birdnest. The necessary data was supplied by the company via two external APIs:

<b>Drone positions:</b> GET assignments.reaktor.com/birdnest/drones
</br>Publishes drone positions in a 500 meter radius around the birdnest every two seconds.

<b>Pilot information:</b> GET assignments.reaktor.com/birdnest/pilots/:serialNumber
</br>Exposes personal details of drone pilots

## Implementation
The app consists of an Angular frontend and a Springboot backend with an H2 in-memory database. The backend continuously fetches the newest drone positions and persists the contact details of NDZ violators. Then, the backend emits a Server-Sent-Event (SSE) containing a list of violators of the last 10 minutes. The frontend listens to the SSE and displays the data in a list. With this implementation, the frontend is a passive receiver of data.


## Demo
![](https://github.com/fravl/project-birdnest/blob/main/project-birdnest-demo.gif)

